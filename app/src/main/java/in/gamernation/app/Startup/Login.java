package in.gamernation.app.Startup;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

import in.gamernation.app.APICalls.APICalls;
import in.gamernation.app.APICalls.APIInterceptor;
import in.gamernation.app.R;
import in.gamernation.app.Utils.Constants;
import okhttp3.OkHttpClient;

public class Login extends AppCompatActivity {

    AppCompatEditText loginemail, loginpass;
    AppCompatButton loginbot;
    String username, password, authtoken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViews();
        working();
    }

    private void findViews() {
        loginbot = findViewById(R.id.loginbot);
        loginemail = findViewById(R.id.loginemail);
        loginpass = findViewById(R.id.loginpass);
    }

    private void working() {
        loginbot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = loginemail.getText().toString();
                password = loginpass.getText().toString();
                try {
                    OkHttpClient okHttpClient = new OkHttpClient.Builder()
                            .addInterceptor(new APIInterceptor(username, password))
                            .build();
                    APICalls.gethttpRequestwithoutclient(Constants.loginroute, okHttpClient);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


}