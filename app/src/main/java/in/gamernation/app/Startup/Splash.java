package in.gamernation.app.Startup;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import in.gamernation.app.R;

import static in.gamernation.app.Utils.Constants.SPLASH_TIMEOUT;

public class Splash extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //make changes for onboarding
                startActivity(new Intent(getApplicationContext(), Login.class));
            }
        }, SPLASH_TIMEOUT);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


}