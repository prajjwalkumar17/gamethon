package in.gamernation.app.Startup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import in.gamernation.app.Home.HomeActivity;
import in.gamernation.app.R;

public class Splash extends AppCompatActivity {

    private static final int SPLASH_TIMEOUT = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //make changes for onboarding
                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
            }
        }, SPLASH_TIMEOUT);
    }
}