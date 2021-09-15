package in.gamernation.app.Fragments.profile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;
import in.gamernation.app.APICalls.APICallsOkHttp;
import in.gamernation.app.Activities.HomeActivity;
import in.gamernation.app.R;
import in.gamernation.app.Utils.CommonMethods;
import in.gamernation.app.Utils.Constants;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class updatemyprofileActivity extends AppCompatActivity {
    private EditText myprofileedittextname, myprofileedittextusername,
            myprofileedittextemail, myprofileedittstate, myprofileedittextcountry;
    private AppCompatButton myprofileuploadpicbot, myprofileupdatebot;
    private RadioButton myprofilemaleradio, myprofilefemaleradio;
    private CircleImageView myprofiledpbot;
    private ImageView toolwithbackbotheadbot;
    private TextView toolwithbackbothead;
    private SharedPreferences sharedPreferences, preferences;
    private String usrtoken, msg, Gender, encodedString;
    private LinearLayout linlayoutsecond, myprofilelin1;
    private ShimmerFrameLayout profileupdateshimmer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatemyprofile);
        toloadthemyprofile();
        initscreen();
        shimmersetup();
        shimmerstart();
        initviews();
        imgeselector();

    }

    private void uploadpic() {
        myprofileuploadpicbot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String url = Constants.w3devbaseurl + "user/my_profile/update_pic";
                APICallsOkHttp.okhttpmaster().newCall(APICallsOkHttp
                        .requestwithpatch(APICallsOkHttp.urlbuilderforhttp(url)
                                , usrtoken
                                , APICallsOkHttp.buildforuploaddp(encodedString)))
                        .enqueue(new Callback() {
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
                                                CommonMethods.DisplayLongTOAST(updatemyprofileActivity.this, erroralreadyjoined);
                                                CommonMethods.LOGthesite(Constants.LOG, erroralreadyjoined);
                                            } else {
                                                String msg = responsez.getString("message");
                                                CommonMethods.DisplayLongTOAST(updatemyprofileActivity.this, msg);
                                            }
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });
                            }
                        });

            }
        });


    }

    private void toloadthemyprofile() {
        preferences = this.getSharedPreferences(Constants.MYPROFILEPREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(Constants.shouldopenmyprofile, "1").apply();
    }

    private void imgeselector() {
        myprofiledpbot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImagePicker
                        .with(updatemyprofileActivity.this)
                        .galleryOnly()
                        .compress(1024)
                        .maxResultSize(1080, 1080)
                        .start();

                uploadpic();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Uri uri = null;
            if (data != null) {
                uri = data.getData();
                CommonMethods.LOGthesite(Constants.LOG, uri.toString());

            }
            assert uri != null;

            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);

                // initialize byte stream
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                // compress Bitmap
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                // Initialize byte array
                byte[] bytes = stream.toByteArray();
                // get base64 encoded string
                encodedString = Base64.encodeToString(bytes, Base64.DEFAULT);
            } catch (IOException e) {
                e.printStackTrace();
            }


            CommonMethods.LOGthesite(Constants.LOG, uri.toString());
            Picasso.get()
                    .load(uri)
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.dperror)
                    .into(myprofiledpbot);
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            CommonMethods.DisplayShortTOAST(this, ImagePicker.getError(data));
        } else {
            CommonMethods.DisplayShortTOAST(this, "Task Cancelled");
        }
    }

    private void shimmerstart() {
        profileupdateshimmer.setVisibility(View.VISIBLE);
        profileupdateshimmer.startShimmer();
        linlayoutsecond.setVisibility(View.INVISIBLE);
        myprofilelin1.setVisibility(View.INVISIBLE);
    }

    private void shimmersetup() {
        profileupdateshimmer = findViewById(R.id.profileupdateshimmer);
        profileupdateshimmer.setVisibility(View.VISIBLE);
        profileupdateshimmer.startShimmer();


        linlayoutsecond = findViewById(R.id.linlayoutsecond);
        myprofilelin1 = findViewById(R.id.myprofilelin1);

        linlayoutsecond.setVisibility(View.INVISIBLE);
        myprofilelin1.setVisibility(View.INVISIBLE);


    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(updatemyprofileActivity.this, HomeActivity.class);
        startActivity(intent);
    }

    private void initviews() {
        toolwithbackbotheadbot = findViewById(R.id.toolwithbackbotheadbot);
        toolwithbackbothead = findViewById(R.id.toolwithbackbothead);
        toolwithbackbothead.setText("Update Profile");
        toolwithbackbotheadbot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(updatemyprofileActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        myprofileedittextname = findViewById(R.id.myprofileedittextname);
        myprofileedittextusername = findViewById(R.id.myprofileedittextusername);
        myprofileedittextemail = findViewById(R.id.myprofileedittextemail);
        myprofileedittstate = findViewById(R.id.myprofileedittstate);
        myprofileedittextcountry = findViewById(R.id.myprofileedittextcountry);
        myprofileuploadpicbot = findViewById(R.id.myprofileuploadpicbot);
        myprofileupdatebot = findViewById(R.id.myprofileupdatebot);
        myprofilemaleradio = findViewById(R.id.myprofilemaleradio);
        myprofilefemaleradio = findViewById(R.id.myprofilefemaleradio);
        myprofiledpbot = findViewById(R.id.myprofiledpbot);
        myprofilemaleradio = findViewById(R.id.myprofilemaleradio);
        myprofilemaleradio = findViewById(R.id.myprofilemaleradio);


//        fetchdataandsettoviewsintially();


    }

//    private void fetchdataandsettoviewsintially() {
//        SharedPreferences preferences = getActivity().getSharedPreferences(Constants.MYPROFILEPREF, Context.MODE_PRIVATE);
//        String gender = preferences.getString(Constants.myprofileGender, "Data not found");
//        String name = preferences.getString(Constants.myprofilename, "Data not found");
//        String username = preferences.getString(Constants.myprofileUsername, "Data not found");
//        String profilepic = preferences.getString(Constants.myprofileProfile_Picture, "Data not found");
//        String email = preferences.getString(Constants.myprofileEmail, "Data not found");
//        String state = preferences.getString(Constants.myprofilestate, "Data not found");
//        String sountry = preferences.getString(Constants.myprofileCountry, "Data not found");
//
//        CommonMethods.LOGthesite(Constants.LOG, name);
//
//    }

    private void initscreen() {
//        ((HomeActivity) getActivity()).setToolbarInvisible();
//        ((HomeActivity) getActivity()).setDrawerLocked();
//        ((HomeActivity) getActivity()).setbotInvisible();

        sharedPreferences = this.getSharedPreferences(Constants.LOGINPREFS, Context.MODE_PRIVATE);
        usrtoken = sharedPreferences.getString(Constants.TOKENUSINGPREFS, "No data found!!!");

        fetchprofiledata();

    }

    private void fetchprofiledata() {
        String url = APICallsOkHttp.urlbuilderforhttp(Constants.w3devbaseurl + "user/my_profile");
        APICallsOkHttp.okhttpmaster().newCall(APICallsOkHttp.requestbuildwithauth(url, usrtoken)).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(@NotNull okhttp3.Call call, @NotNull IOException e) {
                CommonMethods.DisplayLongTOAST(updatemyprofileActivity.this, "Data fetch failed" + e.getMessage());
            }

            @Override
            public void onResponse(@NotNull okhttp3.Call call, @NotNull okhttp3.Response response) throws IOException {
                final String responsez = response.body().string();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            JSONObject object = new JSONObject(responsez);
                            String Profile_Picture = object.optString("Profile_Picture");
                            String Name = object.optString("Name");
                            String Username = object.optString("Username");
                            String Email = object.optString("Email");
                            String State = object.optString("State");
                            String Country = object.optString("Country");
                            Gender = object.optString("Gender");

                            if (Gender.equals("Male")) {
                                myprofilemaleradio.setChecked(true);
                            } else if (Gender.equals("Female")) {
                                myprofilefemaleradio.setChecked(true);
                            } else {
                                myprofilemaleradio.setChecked(true);
                            }


                            myprofileedittextname.setText(Name, TextView.BufferType.EDITABLE);
                            myprofileedittextusername.setText(Username, TextView.BufferType.EDITABLE);
                            myprofileedittextemail.setText(Email);
                            myprofileedittextemail.setEnabled(false);
                            myprofileedittstate.setText(State, TextView.BufferType.EDITABLE);
                            myprofileedittextcountry.setText(Country, TextView.BufferType.EDITABLE);

                            Picasso.get()
                                    .load(Profile_Picture)
                                    .placeholder(R.drawable.placeholder)
                                    .error(R.drawable.dperror)
                                    .into(myprofiledpbot);

                            stopshimmer();
                            geteditedtexts();


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    private void stopshimmer() {
        linlayoutsecond.setVisibility(View.VISIBLE);
        myprofilelin1.setVisibility(View.VISIBLE);
        profileupdateshimmer.stopShimmer();
        profileupdateshimmer.setVisibility(View.GONE);

    }

    private void geteditedtexts() {
        myprofileupdatebot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String editedname = myprofileedittextname.getText().toString();
                String editedusername = myprofileedittextusername.getText().toString();
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
                        object.put("state", editedstate);
                        object.put("country", editedcountry);
                        object.put("gender", Gender);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                    String urll = APICallsOkHttp.urlbuilderforhttp(Constants.w3devbaseurl + "user/my_profile/update");
                    CommonMethods.LOGthesite(Constants.LOG, encodedString);
                    APICallsOkHttp.okhttpmaster().newCall(APICallsOkHttp.requestwithpatch(urll, usrtoken, APICallsOkHttp.buildforuploaddp(encodedString))).enqueue(
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


                                                if (object1.has("message")) {
                                                    msg = object1.getString("message");

                                                } else if (object1.has("error")) {
                                                    msg = object1.getString("error");

                                                }

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