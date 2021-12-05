package com.example.omtd2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends Fragment {

    public static RegisterActivity newInstance() {
        return new RegisterActivity();
    }

    private EditText et_id, et_pass, et_name, et_age;
    private Button btn_register;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_register, container, false);

        //아이디값 찾아주기
        et_id = root.findViewById( R.id.et_id );
        et_pass = root.findViewById( R.id.et_pass );
        et_name = root.findViewById( R.id.et_name );
        et_age = root.findViewById( R.id.et_age );


        //회원가입 버튼 클릭 시 수행
        btn_register = root.findViewById( R.id.btn_register );
        btn_register.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userID = et_id.getText().toString();
                String userPass = et_id.getText().toString();
                String userName = et_name.getText().toString();
                int userAge =  Integer.parseInt( et_age.getText().toString() );
                Log.v("회원가입버튼 누름", "response는 안지남");


                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            Log.v("가입버튼 누름", "response는 지남");
                            JSONObject jsonObject = new JSONObject( response );
                            boolean success = jsonObject.getBoolean( "success" );

                            //회원가입 성공시
                            if(success) {

                                Toast.makeText( getActivity(), "성공", Toast.LENGTH_SHORT ).show();
                                Intent intent = new Intent( RegisterActivity.this.getActivity(), LoginActivity.class );
                                startActivity( intent );

                                //회원가입 실패시
                            } else {
                                Toast.makeText( getActivity(), "실패", Toast.LENGTH_SHORT ).show();
                                return;
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };

                //서버로 Volley를 이용해서 요청
                RegisterRequest registerRequest = new RegisterRequest( userID, userPass, userName, userAge, responseListener);
                RequestQueue queue = Volley.newRequestQueue( RegisterActivity.this.getActivity() );
                queue.add( registerRequest );
            }
        });
        return root;
    }
}
