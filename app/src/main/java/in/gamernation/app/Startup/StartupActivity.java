package in.gamernation.app.Startup;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import in.gamernation.app.R;

public class StartupActivity extends AppCompatActivity {

    AppCompatButton startupsigninbot, startupsignupbot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup);

        startupsigninbot = findViewById(R.id.startupsigninbot);
        startupsignupbot = findViewById(R.id.startupsignupbot);

        startupsigninbot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Login.class));

//                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(Constants.METAPTEF, Context.MODE_PRIVATE);
//                CustomTabsIntent.Builder customtabintent=new CustomTabsIntent.Builder();
//                opencustomtabyy(StartupActivity.this,customtabintent.build(), Uri.parse(sharedPreferences.getString(Constants.login_url, "data not found")));


            }
        });
        startupsignupbot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), LoginwebviewActivity.class));
//                getSupportFragmentManager().beginTransaction()
//                        .replace(R.id.fragmentContainerView, new LoginWebviewFragment()).addToBackStack(null).commit();
//                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(Constants.METAPTEF, Context.MODE_PRIVATE);
//                CustomTabsIntent.Builder customtabintent = new CustomTabsIntent.Builder();
//                opencustomtabyy(StartupActivity.this, customtabintent.build(), Uri.parse(sharedPreferences.getString(Constants.metasignup, "data not found")));


            }
        });


    }



}