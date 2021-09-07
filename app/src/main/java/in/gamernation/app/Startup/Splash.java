package in.gamernation.app.Startup;

import static in.gamernation.app.Utils.Constants.SPLASH_TIMEOUT;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import in.gamernation.app.APICalls.APICallsOkHttp;
import in.gamernation.app.Activities.HomeActivity;
import in.gamernation.app.R;
import in.gamernation.app.Utils.CommonMethods;
import in.gamernation.app.Utils.Constants;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class Splash extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);


        fetchmetalinks();


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //make changes for onboarding
                SharedPreferences sharedPreferences = Splash.this.getSharedPreferences(Constants.LOGINPREFS, MODE_PRIVATE);

                if (sharedPreferences.getString(Constants.TOKENUSINGPREFS, "-1") == "-1") {
                    startActivity(new Intent(getApplicationContext(), Login.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                } else {
                    startActivity(new Intent(getApplicationContext(), HomeActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                }
            }
        }, SPLASH_TIMEOUT);
    }


    private void fetchmetalinks() {
        String url = APICallsOkHttp.urlbuilderforhttp(Constants.w3devbaseurl + "games/meta");
        APICallsOkHttp.okhttpmaster().newCall(APICallsOkHttp.requesthttpwithoutauth(url)).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                CommonMethods.DisplayLongTOAST(getApplicationContext(), e.getMessage());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                final String responsez = response.body().string();
//                CommonMethods.LOGthesite(Constants.LOG,responsez);
                try {

                    JSONObject object = new JSONObject(responsez);
                    JSONObject data = object.getJSONObject("data");
                    String signup_url = data.getString("signup_url");
                    String login_url = data.getString("login_url");
                    String contactUs = data.getString("contactUs");

                    JSONObject wallet = data.getJSONObject("wallet");
                    String add_money = wallet.getString("add_money");
                    String payment_gateway = wallet.getString("payment_gateway");
                    String transaction_history = wallet.getString("transaction_history");
                    String payment_history = wallet.getString("payment_history");
                    String withdraw = wallet.getString("withdraw");
                    String withdraw_history = wallet.getString("withdraw_history");
                    String add_bank_account = wallet.getString("add_bank_account");
                    String delete_linked_account = wallet.getString("delete_linked_account");
                    String withdraw_coins = wallet.getString("withdraw_coins");


                    SharedPreferences sharedPreferences = getSharedPreferences(Constants.METAPTEF, MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(Constants.metasignup, signup_url).apply();
                    editor.putString(Constants.login_url, login_url).apply();
                    editor.putString(Constants.contactUs, contactUs).apply();
                    editor.putString(Constants.add_money, add_money).apply();
                    editor.putString(Constants.payment_gateway, payment_gateway).apply();
                    editor.putString(Constants.transaction_history, transaction_history).apply();
                    editor.putString(Constants.payment_history, payment_history).apply();
                    editor.putString(Constants.withdraw, withdraw).apply();
                    editor.putString(Constants.withdraw_history, withdraw_history).apply();
                    editor.putString(Constants.add_bank_account, add_bank_account).apply();
                    editor.putString(Constants.delete_linked_account, delete_linked_account).apply();
                    editor.putString(Constants.withdraw_coins, withdraw_coins).apply();

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


}