package in.gamernation.app.Startup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import in.gamernation.app.Activities.WebvieweClient;
import in.gamernation.app.R;
import in.gamernation.app.Utils.Constants;

public class LoginwebviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginwebview);

        WebView loginwebview;
        loginwebview = findViewById(R.id.loginwebview);
        SharedPreferences sharedPreferences = getSharedPreferences(Constants.METAPTEF, MODE_PRIVATE);
        WebSettings settings = loginwebview.getSettings();
        settings.setJavaScriptEnabled(true);
        loginwebview.setWebViewClient(new Callback());
        loginwebview.addJavascriptInterface(new WebvieweClient.MyJavaScriptInterface(),
                "android");

        loginwebview.loadUrl(sharedPreferences.getString(Constants.metasignup, "data not found"));


    }

    private class Callback extends WebViewClient {
        @Override
        public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
            return false;
        }


    }
}