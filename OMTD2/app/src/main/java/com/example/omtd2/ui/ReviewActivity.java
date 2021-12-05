package com.example.omtd2.ui;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.media.Rating;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.omtd2.R;

import org.w3c.dom.Text;

public class ReviewActivity extends Fragment {
    myDBHelper myHelper;
    EditText edtmenu, edtreview, edtmenuResult, edtreviewResult, edtresult, edtstar;
    Button writebtn, seebtn;
    SQLiteDatabase sqlDB;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.review_main, container, false);

        edtmenu = (EditText) root.findViewById(R.id.edtMenu);
        edtreview = (EditText) root.findViewById(R.id.edtReview);
        edtmenuResult = (EditText) root.findViewById(R.id.edtMenuResult);
        edtreviewResult = (EditText) root.findViewById(R.id.edtReviewResult);
        edtstar = (EditText) root.findViewById(R.id.edtStar);
        edtresult = (EditText) root.findViewById(R.id.edtResult);
        writebtn = (Button) root.findViewById(R.id.writebtn);
        seebtn = (Button) root.findViewById(R.id.seebtn);
        Button menubtn = (Button) root.findViewById(R.id.menubtn);


        menubtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // review fragment로 가기
                Navigation.findNavController(v).navigate(R.id.action_reviewFragment_to_menuFragment);
            }
        });

        myHelper = new myDBHelper(this.getActivity());
        writebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqlDB = myHelper.getWritableDatabase();

                sqlDB.execSQL("INSERT INTO groupTBL VALUES( "
                        + edtstar.getText().toString() + ", '"
                        + edtmenu.getText().toString() + "', '"
                        + edtreview.getText().toString() + "');");
                sqlDB.close();
                Toast.makeText(getActivity(), "작성완료", Toast.LENGTH_SHORT).show();
            }
        });
        seebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update();
            }
        });
        return root;
    }
    public class myDBHelper extends SQLiteOpenHelper{
        public myDBHelper(Context context){
            super(context, "groupDB2", null, 1);
        }
        @Override
        public void onCreate(SQLiteDatabase db){
            db.execSQL("CREATE TABLE groupTBL (gStar Integer PRIMARY KEY, gMenu CHAR(20) , gReview CHAR(20));");
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersions, int newVersion){
            db.execSQL("DROP TABLE IF EXISTS groupTBL");
            onCreate(db);
        }
    }
    void update(){
        sqlDB = myHelper.getReadableDatabase();
        Cursor cursor;
        cursor = sqlDB.rawQuery("SELECT * FROM groupTBL;", null);

        String star = "평점" + "\n";
        String menu = "메뉴" + "\n";
        String review = "리뷰" + "\n";

        while(cursor.moveToNext()){
            star += cursor.getString(0) + "\n";
            menu += cursor.getString(1) + "\n";
            review += cursor.getString(2) + "\n";
        }

        edtresult.setText(star);
        edtmenuResult.setText(menu);
        edtreviewResult.setText(review);


        cursor.close();
        sqlDB.close();
    }
}
