package in.gamernation.app.Startup;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

import in.gamernation.app.APICalls.APICalls;
import in.gamernation.app.Activities.HomeActivity;
import in.gamernation.app.Classes.UserLoginRequest;
import in.gamernation.app.Classes.UserLoginResponse;
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
    //    private static final OkHttpClient.Builder client = new OkHttpClient.Builder();
    private static OkHttpClient httpClient = new OkHttpClient();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViews();
//        new asyncquery().execute();
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

                            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
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

    private void findViews() {
        loginbot = findViewById(R.id.loginbot);
        loginemail = findViewById(R.id.loginemail);
        loginpass = findViewById(R.id.loginpass);
    }


   /* static class asyncquery extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }


        @Override
        protected Void doInBackground(Void... voids) {
            loginbot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    username = loginemail.getText().toString();
                    password = loginpass.getText().toString();
                    MediaType JSON=MediaType.parse("application/json;charset=utf-8");
                    JSONObject creds=new JSONObject();
                    try{
                        creds.put("email","first@gmail.com");
                        creds.put("password","qwerty@123");
                    } catch (Exception e) {
                        CommonMethods.LOGthesite(Constants.LOG,"The exception is "+e);
                        e.printStackTrace();
                    }
                    RequestBody requestBody= RequestBody.create(JSON,creds.toString());
                    Request request=new Request.Builder()
                            .url(Constants.loginroute)
                            .post(requestBody)
                            .build();
                    try {
                        Response response = httpClient.newCall(request).execute();
                        CommonMethods.LOGthesite(Constants.LOG,"The toke is" +response.body().toString());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            });
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
        }
    }


*/
}


//        client = new OkHttpClient.Builder();
//        client.authenticator(new Authenticator() {
//@Override
//public Request authenticate(Route route, Response response) throws IOException {
//        if (responseCount(response) >= 3) {
//        return null; // If we've failed 3 times, give up. - in real life, never give up!!
//        }
//        String credential = Credentials.basic("name", "password");
//        return response.request().newBuilder().header("Authorization", credential).build();
//        }
//        });
//        client.connectTimeout(10, TimeUnit.SECONDS);
//        client.writeTimeout(10, TimeUnit.SECONDS);
//        client.readTimeout(30, TimeUnit.SECONDS);
//        }


//                    APICalls.posthttpRequest();

//                    try {
//                        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                                .addInterceptor(new APIInterceptor(username, password))
//                                .build();
//                        APICalls.gethttpRequestwithoutclient(Constants.loginroute, okHttpClient);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                    client.authenticator(new Authenticator() {
//                        @Nullable
//                        @Override
//                        public Request authenticate(@Nullable Route route, @NotNull Response response) throws IOException {
//                            String credential = Credentials.basic(username, password);
//                            return response.request().newBuilder().header("Authorization", credential).build();
//                        }
//
//                    });