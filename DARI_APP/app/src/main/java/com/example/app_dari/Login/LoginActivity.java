package com.example.app_dari.Login;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.app_dari.Chat.SocketHandler;
import com.example.app_dari.MainActivity;
import com.example.app_dari.R;
import com.example.app_dari.RetrofitClient;
import com.example.app_dari.Signup.SignupActivity;
import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.engineio.client.transports.Polling;
import io.socket.engineio.client.transports.WebSocket;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private Socket mSocket;
    static public String token;
    private RetrofitClient retrofitClient;
    private com.example.app_dari.initMyApi initMyApi;
    EditText idtext;
    EditText pwtext;
    CheckBox checkBox;
    static public String name;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        idtext = (EditText)findViewById(R.id.edit_id);
        pwtext = (EditText)findViewById(R.id.edit_pw);
        retrofitClient = RetrofitClient.getInstance();
        initMyApi = RetrofitClient.getRetrofitInterface();
        checkBox = (CheckBox)findViewById(R.id.auto_Login);


        Button login = (Button)findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = idtext.getText().toString();
                String pw = pwtext.getText().toString();
                hideKeyboard();

                if(id.isEmpty() ==true || pw.isEmpty()){
                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                    builder.setTitle("알림")
                            .setMessage("로그인 정보를 입력해주세요.")
                            .setPositiveButton("확인",null)
                            .create().show();
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();

                }else {
                        LoginResponse();
                }
            }
        });

        Button signup = (Button)findViewById(R.id.sign_up);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent to_signup = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(to_signup);

            }
        });


    }
    public void LoginResponse() {
        String userID = idtext.getText().toString().trim();
        String userPassword = pwtext.getText().toString().trim();

        //loginRequest에 사용자가 입력한 id와 pw를 저장
        LoginRequest loginRequest = new LoginRequest(userID,userPassword);

        //loginRequest에 저장된 데이터와 함께 init에서 정의한 getLoginResponse 함수를 실행한 후 응답을 받음
        initMyApi.getLoginResponse(loginRequest).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                Log.d("retrofit", "Data fetch success");
                //통신 성공
                if (response.isSuccessful()) {
                    //response.body()를 result에 저장
                    LoginResponse result = response.body();
                    //받은 코드 저장
                    int resultCode = result.getResultCode();

                    token = result.getToken();

                    //받은 토큰 저장
                    name = result.getName();


                    int success = 201; //로그인 성공
                    int errorId = 300; //아이디 일치x
                    int errorPw = 400; //비밀번호 일치x


                    if (resultCode == success) {
                        String userID = idtext.getText().toString();
                        String userPassword = pwtext.getText().toString();

                        //다른 통신을 하기 위해 token 저장
                        setPreference("token",token);
                        setPreference("hastoken","true");

                        if(checkBox.isChecked() ==true) {
                            setPreference("check", "true");
                        }
                        else { setPreference("check","false");}
                        init();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        LoginActivity.this.finish();


                    } else if(resultCode==errorId){

                        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                        builder.setTitle("알림")
                                .setMessage("등록되지 않은 아이디입니다.")
                                .setPositiveButton("확인", null)
                                .create()
                                .show();

                    }
                    else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                        builder.setTitle("알림")
                                .setMessage("비밀번호가 일치하지 않습니다.")
                                .setPositiveButton("확인", null)
                                .create()
                                .show();
                    }
                }
            }

            //통신 실패
            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                builder.setTitle("알림")
                        .setMessage("예기치 못한 오류가 발생하였습니다.")
                        .setPositiveButton("확인", null)
                        .create()
                        .show();
            }
        });
    }
    public void setPreference(String key, String value){
        SharedPreferences pref = getSharedPreferences( "Tfile", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(key, value);
        editor.apply();
    }

    //내부 저장소에 저장된 데이터 가져오기
    public String getPreferenceString(String key) {
        SharedPreferences pref = getSharedPreferences("Tfile", MODE_PRIVATE);
        return pref.getString(key, "");
    }

    private void hideKeyboard()
    {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(idtext.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(pwtext.getWindowToken(), 0);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View focusView = getCurrentFocus();
        if (focusView != null) {
            Rect rect = new Rect();
            focusView.getGlobalVisibleRect(rect);
            int x = (int) ev.getX(), y = (int) ev.getY();
            if (!rect.contains(x, y)) {
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                if (imm != null)
                    imm.hideSoftInputFromWindow(focusView.getWindowToken(), 0);
                focusView.clearFocus();
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    public void Login(){

        initMyApi.getLogin(token).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                LoginResponse result = response.body();

                int status = result.getResultCode();

                int success = 201;
                int fail = 401;

                if(status == success){
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    LoginActivity.this.finish();
                }
                else {
                }
            }
            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
            }
        });
    }
    private void init() {
        try {
            IO.Options options = new IO.Options();
            options.transports = new String[]{WebSocket.NAME, Polling.NAME};
            options.path = "/socket.io";
            options.query = "token=" + getPreferenceString("token");
            mSocket = IO.socket("http://dari-app.kro.kr", options);
            Log.d("SOCKET", "Connection success : " + mSocket.id());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        SocketHandler.setSocket(mSocket);
        SocketHandler.getSocket().connect();


    }
}
