package com.example.omtd2;

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
import androidx.navigation.Navigation;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends Fragment {

    public static LoginActivity newInstance() {
        return new LoginActivity();
    }

    private EditText et_id, et_pass;
    private Button btn_login, btn_register;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_login, container, false);

        et_id = root.findViewById(R.id.et_id);
        et_pass = root.findViewById(R.id.et_pass);

        btn_register = root.findViewById(R.id.btn_register);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(LoginActivity.this.getActivity(), RegisterActivity.class);
                //startActivity(intent);
                Navigation.findNavController(view).navigate(R.id.action_menuLogin_to_RegisterFragment);
            }
        });


        btn_login = root.findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userID = et_id.getText().toString();
                String userPass = et_pass.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");

                            if (success) {//로그인 성공시

                                String userID = jsonObject.optString("userID");
                                String userPass = jsonObject.optString("userPassword");
                                String userName = jsonObject.optString("username");
                                String userAge = jsonObject.optString( "userAge" );

                                Toast.makeText(getActivity(), "로그인 성공", Toast.LENGTH_SHORT).show();
                              /*  Intent intent = new Intent( LoginActivity.this, MainActivity.class );

                                intent.putExtra( "userID", userID );
                                intent.putExtra( "userPass", userPass );
                                intent.putExtra( "userName", userName );
                                intent.putExtra( "userAge", userAge );

                                startActivity( intent );*/

                            } else {//로그인 실패시
                                Toast.makeText(getActivity(), "로그인 실패", Toast.LENGTH_SHORT).show();
                                return;
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                LoginRequest loginRequest = new LoginRequest(userID, userPass, responseListener);
                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this.getActivity());
                queue.add(loginRequest);

            }
        });
        return root;
    }
}

