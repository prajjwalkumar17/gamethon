package in.gamernation.app.Startup;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

import in.gamernation.app.APICalls.APICalls;
import in.gamernation.app.APIRequests.UserLoginRequest;
import in.gamernation.app.Activities.HomeActivity;
import in.gamernation.app.ModalClasses.UserLoginResponse;
import in.gamernation.app.R;
import in.gamernation.app.Utils.CommonMethods;
import in.gamernation.app.Utils.Constants;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

    private static AppCompatEditText loginemail, loginpass;
    private static AppCompatButton loginbot;
    private static String username, password, authtoken;
    SharedPreferences sharedPreferences;
    //    private static final OkHttpClient.Builder client = new OkHttpClient.Builder();
    private static OkHttpClient httpClient = new OkHttpClient();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViews();
        initializers();
        onloginbotpressed();
    }

    private void onloginbotpressed() {
        loginbot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = loginemail.getText().toString();
                password = loginpass.getText().toString();
                if (username.length() == 0 || password.length() == 0) {
                    CommonMethods.DisplayLongTOAST(getApplicationContext(), "No text found !!");
                } else {
                    Loginwithcreds();
                }
            }
        });
    }

    private void findViews() {
        loginbot = findViewById(R.id.loginbot);
        loginemail = findViewById(R.id.loginemail);
        loginpass = findViewById(R.id.loginpass);
    }

    private void initializers() {
        sharedPreferences = getSharedPreferences(Constants.LOGINPREFS, Context.MODE_PRIVATE);
    }

    private void Loginwithcreds() {
        UserLoginRequest request = new UserLoginRequest();
        request.setEmail(username);
        request.setPassword(password);
        Call<UserLoginResponse> loginResponseCall = APICalls.getUserLogginService().userLogin(request);

        loginResponseCall.enqueue(new Callback<UserLoginResponse>() {
            @Override
            public void onResponse(Call<UserLoginResponse> call, Response<UserLoginResponse> response) {

                if (response.isSuccessful()) {
                    CommonMethods.DisplayLongTOAST(getApplicationContext(), "Login sucessfull");
                    UserLoginResponse userLoginResponse = response.body();//pass to get token
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (userLoginResponse != null) {
                                Sharethedateusingprefs(userLoginResponse.getToken());
                                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                            }
                        }
                    }, Constants.delaybeforelogin);
                } else {
                    CommonMethods.DisplayLongTOAST(getApplicationContext(), "Login Failed");
                }

            }

            @Override
            public void onFailure(Call<UserLoginResponse> call, Throwable t) {

                CommonMethods.DisplayLongTOAST(getApplicationContext(), "Login Failed  " + t);
            }
        });
    }

    private void Sharethedateusingprefs(String token) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Constants.TOKENUSINGPREFS, token);
        editor.apply();
    }


}
