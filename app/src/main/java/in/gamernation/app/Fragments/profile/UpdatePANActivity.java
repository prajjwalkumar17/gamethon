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
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.Response;

public class UpdatePANActivity extends AppCompatActivity {
    private static TextView toolwithbackbothead, updatepantext;
    private static AppCompatButton updatepanbot;
    private static EditText updatepanname, updatepannumber;
    private static ImageView updatepanpic, toolwithbackbotheadbot;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_update_panactivity);
        toloadthemyprofile();
        initviews();
        uploadpic();
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
            Uri uri = null;
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

    private void geteditedtexts() {

        //pic will not work in update

        //name username not empty
        myprofileupdatebot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String editedname = myprofileedittextname.getText().toString();
                String editedusername = myprofileedittextusername.getText().toString();
//       String editedemail=myprofileedittextemail.getText().toString();
                myprofileedittextemail.setEnabled(false);
                String editedstate = myprofileedittstate.getText().toString();
                String editedcountry = myprofileedittextcountry.getText().toString();

                if (myprofilemaleradio.isChecked()) {
                    Gender = "Male";
                } else {
                    Gender = "Female";
                }


                //normalurl


                if (editedname.isEmpty() || editedusername.isEmpty()) {
                    CommonMethods.DisplayLongTOAST(updatemyprofileActivity.this, "Name & Username field can't be empty");
                } else {
                    shimmerstart();

                    JSONObject object = new JSONObject();
                    try {
                        object.put("name", editedname);
                        object.put("username", editedusername);
//                        object.put("email",editedemail);
                        object.put("state", editedstate);
                        object.put("country", editedcountry);
                        object.put("gender", Gender);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                    String url = APICallsOkHttp.urlbuilderforhttp(Constants.w3devbaseurl + "user/my_profile/update");
                    MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                    // put your json here
                    RequestBody body = RequestBody.create(JSON, object.toString());
                    APICallsOkHttp.okhttpmaster().newCall(APICallsOkHttp.requestwithpatch(url, usrtoken, body)).enqueue(
                            new Callback() {
                                @Override
                                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                                    CommonMethods.DisplayLongTOAST(updatemyprofileActivity.this, e.getMessage().toString());
                                }

                                @Override
                                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                    final String responsez = response.body().string();
                                    runOnUiThread(new Runnable() {

                                        JSONObject object1;

                                        {
                                            try {
                                                object1 = new JSONObject(responsez);


                                                msg = object1.getString("message");
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                        }

                                        @Override
                                        public void run() {
                                            stopshimmer();
                                            CommonMethods.DisplayLongTOAST(updatemyprofileActivity.this, msg);
                                        }

                                    });

                                }
                            }
                    );
                }
            }
        });


        //TODO variable names


    }


}