package in.gamernation.app.Fragments.profile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.squareup.picasso.Picasso;

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

public class UpdatePANActivity extends AppCompatActivity {
    private static TextView toolwithbackbothead, updatepantext;
    private static AppCompatButton updatepanbot;
    private static EditText updatepanname, updatepannumber;
    private static ImageView updatepanpic, toolwithbackbotheadbot;
    SharedPreferences preferences;
    Uri uri;
    private SharedPreferences sharedPreferences;
    private String usrtoken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_panactivity);

        toloadthemyprofile();
        initviews();
        uploadpic();
        clickpanbutton();
    }

    private void clickpanbutton() {
        updatepanbot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String panname = updatepanname.getText().toString();
                String panno = updatepannumber.getText().toString();

                if (panname.isEmpty() || panno.isEmpty() || uri == null) {
                    CommonMethods.DisplayLongTOAST(UpdatePANActivity.this, "PAN Name and PAN number field can't be empty");
                } else {
                    getpanandupload(panname, panno, uri.toString());
                }
            }
        });
    }


    private void getpanandupload(String panname, String panno, String picuri) {
        String url = Constants.w3devbaseurl + "user/my_profile/update_pan";
        APICallsOkHttp.okhttpmaster().newCall(APICallsOkHttp
                .requestwithpatch(APICallsOkHttp.urlbuilderforhttp(url)
                        , usrtoken
                        , APICallsOkHttp.buildforpanupdate(picuri, panno, panname)
                )).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                CommonMethods.DisplayLongTOAST(getApplicationContext(), e.getMessage());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                final String myResponse = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            JSONObject responsez = new JSONObject(myResponse);
                            if (responsez.has("error")) {
                                String erroralreadyjoined = responsez.optString("error");
                                CommonMethods.DisplayLongTOAST(UpdatePANActivity.this, erroralreadyjoined);
                                CommonMethods.LOGthesite(Constants.LOG, erroralreadyjoined);
                            } else {
                                String msg = responsez.getString("message");
                                CommonMethods.DisplayLongTOAST(UpdatePANActivity.this, msg);

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    private void uploadpic() {
        updatepanpic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImagePicker.with(UpdatePANActivity.this)
                        .galleryOnly()
                        .compress(1024)
                        .start();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            updatepantext.setVisibility(View.GONE);
            uri = null;
            if (data != null) {
                uri = data.getData();
                CommonMethods.LOGthesite(Constants.LOG, uri.toString());

            }
            assert uri != null;
            Picasso.get()
                    .load(uri)
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.dperror)
                    .into(updatepanpic);
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            CommonMethods.DisplayShortTOAST(this, ImagePicker.getError(data));
        } else {
            CommonMethods.DisplayShortTOAST(this, "Task Cancelled");
        }
    }

    private void initviews() {
        toolwithbackbothead = findViewById(R.id.toolwithbackbothead);
        updatepanpic = findViewById(R.id.updatepanpic);
        toolwithbackbothead.setText("Update PAN");
        toolwithbackbotheadbot = findViewById(R.id.toolwithbackbotheadbot);
        updatepanbot = findViewById(R.id.updatepanbot);
        updatepanname = findViewById(R.id.updatepanname);
        updatepantext = findViewById(R.id.updatepantext);
        updatepannumber = findViewById(R.id.updatepannumber);

        sharedPreferences = this.getSharedPreferences(Constants.LOGINPREFS, Context.MODE_PRIVATE);
        usrtoken = sharedPreferences.getString(Constants.TOKENUSINGPREFS, "No data found!!!");

        toolwithbackbotheadbot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UpdatePANActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(UpdatePANActivity.this, HomeActivity.class);
        startActivity(intent);
    }

    private void toloadthemyprofile() {
        preferences = this.getSharedPreferences(Constants.MYPROFILEPREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(Constants.shouldopenmyprofile, "1").apply();
    }


}