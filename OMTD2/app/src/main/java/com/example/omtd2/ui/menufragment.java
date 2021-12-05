package com.example.omtd2.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.omtd2.ListVO;
import com.example.omtd2.MenuListViewAdapter;
import com.example.omtd2.R;

import java.util.ArrayList;

public class menufragment extends Fragment {


    private MenuListViewAdapter adapter1;
    //private int[] img = {R.drawable.suten, R.drawable.sutwo, R.drawable.suthree, R.drawable.fishbob, R.drawable.mealbob};
    //private String[] Cost = {"모듬 초밥", "모듬 초밥", "연어 초밥", "회덮밥", "육회비빔밥"};
    //private String[] Context = {"8,000원", "10,000원", "9,000원", "6,000원", "6,500원"};
    ListView listView1; // menu
    public static ArrayList<ListVO> list1;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        //adapter1 = new MenuListViewAdapter();

        View root = inflater.inflate(R.layout.menu_main, container, false);

        Button reviewbtn = (Button) root.findViewById(R.id.reviewbtn);
        final ListView menu_list = (ListView) root.findViewById(R.id.menulist);





        list1 = new ArrayList<ListVO>();
        list1.add(new ListVO(R.drawable.suten, "모듬초밥 10p", "8,000원"));
        list1.add(new ListVO(R.drawable.sutwo, "모듬초밥 12p", "10,000원"));
        list1.add(new ListVO(R.drawable.suthree, "연어초밥 10p", "9,000원"));
        list1.add(new ListVO(R.drawable.fishbob, "회덮밥", "6,000원"));
        list1.add(new ListVO(R.drawable.mealbob, "육회비빔밥", "6,500원"));

        adapter1 = new MenuListViewAdapter(getActivity(), R.layout.menu_listview, list1);

        menu_list.setAdapter(adapter1);



        reviewbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // review fragment로 가기
                Navigation.findNavController(v).navigate(R.id.action_menuFragment_to_reviewFragment);

            }
        });

        return root;
    }
}