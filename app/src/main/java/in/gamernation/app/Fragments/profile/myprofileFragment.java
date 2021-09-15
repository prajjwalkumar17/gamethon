package in.gamernation.app.Fragments.profile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;
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


public class myprofileFragment extends Fragment {

    //TODO CHANGEABLE

    //TODO

    private static Context thiscontext;
    private static String usrtoken, Invitation_code, msgee, msgdob;
    private static ImageView commontoolbar_backbot;
    private static ImageView myprofile_sharerefferalcodebot;

    private static FloatingActionButton myprofile_updatebiocredsbot;
    private static TextView myprofile_name, myprofile_username, myprofile_email, myprofile_place,
            myprofile_phoneno, myprofile_password, myprofile_birthdate, myprofile_panno, myprofile_refferalcode;
    private static TextView myprofile_phoneverifieidbot, myprofile_passwordchangebot, myprofile_updatebirthdatebot, myprofile_updatepanno;
    private static ProgressBar progressBar2;
    private static CircleImageView myprofile_dp;
    private static SharedPreferences sharedPreferences;


    //    private static MyProfileResponse myProfileResponse;
    private ShimmerFrameLayout shimmerFrameLayout;
    private ConstraintLayout profilemain;
    private LinearLayout layoutbottompprofile;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_myprofile, container, false);
        allfunctions(root);
        return root;
    }

    private void allfunctions(View root) {
        findviews(root);
        updatebot();
        updatepan();
        initscreen();
        initializers();
        initfunctions();
        clickchangepasswordbot();

        clickchangebirthdaybot();
    }

    private void clickchangebirthdaybot() {
        myprofile_updatebirthdatebot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(thiscontext);
                View layout = LayoutInflater.from(thiscontext).inflate(R.layout.dialogchangedob, null);

                DatePicker dialogupdatedobdatepicker = layout.findViewById(R.id.dialogupdatedobdatepicker);
                AppCompatButton dialogupdatedobokbot = layout.findViewById(R.id.dialogupdatedobokbot);
                builder.setView(layout);
                final AlertDialog d = builder.show();

                dialogupdatedobokbot.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String birth_date = dialogupdatedobdatepicker.getDayOfMonth() + "-" + dialogupdatedobdatepicker.getMonth() + "-" + dialogupdatedobdatepicker.getYear();

                        CommonMethods.LOGthesite(Constants.LOG, birth_date);
                        updatedob(birth_date, d);
                        d.dismiss();
                    }
                });
            }
        });
    }

    private void updatedob(String birth_date, AlertDialog d) {
        JSONObject object = new JSONObject();
        try {
            object.put("birth_date", birth_date);
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
                        CommonMethods.DisplayLongTOAST(thiscontext, e.getMessage());
                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        final String responsez = response.body().string();
                        requireActivity().runOnUiThread(new Runnable() {

                            JSONObject object1;

                            {
                                try {
                                    object1 = new JSONObject(responsez);
                                    if (object1.has("message")) {
                                        msgdob = object1.getString("message");

                                    } else if (object1.has("error")) {
                                        msgdob = object1.getString("error");

                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void run() {
                                CommonMethods.DisplayLongTOAST(thiscontext, msgdob);
                            }

                        });

                    }
                }
        );


    }

    private void clickchangepasswordbot() {
        myprofile_passwordchangebot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(thiscontext);
                View layout = LayoutInflater.from(thiscontext).inflate(R.layout.dialogchangepassword, null);

                EditText changepassworddialoggamename = layout.findViewById(R.id.changepassworddialoggamename);
                EditText changepassworddialoggameId = layout.findViewById(R.id.changepassworddialoggameId);
                EditText changepassworddialogteamname = layout.findViewById(R.id.changepassworddialogteamname);


                AppCompatButton changepassworddialogcancelbot = layout.findViewById(R.id.changepassworddialogcancelbot);
                AppCompatButton changepassworddialogjoinbot = layout.findViewById(R.id.changepassworddialogjoinbot);

                builder.setView(layout);
                final AlertDialog d = builder.show();
                changepassworddialogjoinbot.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String OLDPassword = changepassworddialoggamename.getText().toString();
                        String NEWPassword = changepassworddialoggameId.getText().toString();
                        String RETYPENewPassword = changepassworddialogteamname.getText().toString();

                        if (OLDPassword.isEmpty() || NEWPassword.isEmpty() || RETYPENewPassword.isEmpty()) {
                            CommonMethods.DisplayLongTOAST(thiscontext, "Fill OLD-Password , NEW-Password and RETYPE it to continue");
                        } else if (NEWPassword.equals(RETYPENewPassword)) {
                            changepassword(OLDPassword, NEWPassword, RETYPENewPassword, d);
                            d.dismiss();
                        } else {
                            CommonMethods.DisplayLongTOAST(thiscontext, "Passwords Don't Match");

                        }
                    }
                });
                changepassworddialogcancelbot.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        d.dismiss();
                    }
                });


            }
        });

    }

    private void changepassword(String oldPassword, String newPassword, String retypeNewPassword, AlertDialog d) {
        JSONObject object = new JSONObject();
        try {
            object.put("old_password", oldPassword);
            object.put("password", newPassword);
            object.put("confirm_password", retypeNewPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }


        String url = APICallsOkHttp.urlbuilderforhttp(Constants.w3devbaseurl + "user/my_profile/change_password");
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        // put your json here
        RequestBody body = RequestBody.create(JSON, object.toString());
        APICallsOkHttp.okhttpmaster().newCall(APICallsOkHttp.requestwithput(url, usrtoken, body)).enqueue(
                new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {
                        CommonMethods.DisplayLongTOAST(thiscontext, e.getMessage());
                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        final String responsez = response.body().string();
                        requireActivity().runOnUiThread(new Runnable() {

                            JSONObject object1;

                            {
                                try {
                                    object1 = new JSONObject(responsez);


                                    if (object1.has("message")) {
                                        msgee = object1.getString("message");
                                    } else {
                                        msgee = object1.getString("error");
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void run() {

                                CommonMethods.DisplayLongTOAST(thiscontext, msgee);
                            }

                        });

                    }
                });


    }

    private void updatepan() {
        myprofile_updatepanno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), UpdatePANActivity.class);
                startActivity(intent);
            }
        });
    }

    private void updatebot() {
        myprofile_updatebiocredsbot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), updatemyprofileActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initscreen() {
        ((HomeActivity) getActivity()).setToolbarInvisible();
        ((HomeActivity) getActivity()).setDrawerLocked();
        ((HomeActivity) getActivity()).setbotInvisible();
    }

    private void initfunctions() {
        onsharepressed();
    }

    private void onsharepressed() {
        myprofile_sharerefferalcodebot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg = "Hi,Download this app and register through my referral code : " + Invitation_code + "\n"
                        + "https://www.gamernation.in";
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT, msg);
                intent.setType("text/plain");
                intent = Intent.createChooser(intent, "Share by");
                startActivity(intent);
            }
        });
    }

    private void initializers() {
        sharedPreferences = thiscontext.getSharedPreferences(Constants.LOGINPREFS, Context.MODE_PRIVATE);
        usrtoken = sharedPreferences.getString(Constants.TOKENUSINGPREFS, "No data found!!!");
        fetchprofiledata();
    }

    private void findviews(View root) {
        myprofile_name = root.findViewById(R.id.mystat_name);
        myprofile_username = root.findViewById(R.id.mystat_username);
        myprofile_email = root.findViewById(R.id.mystat_email);
        myprofile_place = root.findViewById(R.id.myprofile_place);
        progressBar2 = root.findViewById(R.id.progressBar2);
        myprofile_phoneno = root.findViewById(R.id.myprofile_phoneno);
        myprofile_phoneverifieidbot = root.findViewById(R.id.myprofile_phoneverifieidbot);
        myprofile_password = root.findViewById(R.id.myprofile_password);
        myprofile_passwordchangebot = root.findViewById(R.id.myprofile_passwordchangebot);
        myprofile_birthdate = root.findViewById(R.id.myprofile_birthdate);
        myprofile_updatebirthdatebot = root.findViewById(R.id.myprofile_updatebirthdatebot);
        myprofile_panno = root.findViewById(R.id.myprofile_panno);
        myprofile_updatepanno = root.findViewById(R.id.myprofile_updatepanno);
        myprofile_dp = root.findViewById(R.id.mystat_dp);
        myprofile_refferalcode = root.findViewById(R.id.myprofile_refferalcode);
        myprofile_updatebiocredsbot = root.findViewById(R.id.myprofile_updatebiocredsbot);
        myprofile_sharerefferalcodebot = root.findViewById(R.id.myprofile_sharerefferalcodebot);


        shimmerFrameLayout = root.findViewById(R.id.myprofileshimmer);
        shimmerFrameLayout.setVisibility(View.VISIBLE);
        profilemain = root.findViewById(R.id.profilemain);
        layoutbottompprofile = root.findViewById(R.id.layoutbottompprofile);
        profilemain.setVisibility(View.GONE);
        layoutbottompprofile.setVisibility(View.GONE);
        shimmerFrameLayout.startShimmer();


        TextView toolwithbackbothead = root.findViewById(R.id.toolwithbackbothead);

        ImageView toolwithbackbotheadbot = root.findViewById(R.id.toolwithbackbotheadbot);

        toolwithbackbotheadbot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getActivity() != null)
                    getActivity().getSupportFragmentManager().popBackStackImmediate();
            }
        });

        toolwithbackbothead.setText("My Profile");


    }

    @Override
    public void onAttach(@NonNull @NotNull Context context) {
        super.onAttach(context);
        thiscontext = context;
    }

    private void fetchprofiledata() {
        String url = APICallsOkHttp.urlbuilderforhttp(Constants.w3devbaseurl + "user/my_profile");
        APICallsOkHttp.okhttpmaster().newCall(APICallsOkHttp.requestbuildwithauth(url, usrtoken)).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(@NotNull okhttp3.Call call, @NotNull IOException e) {
                CommonMethods.DisplayLongTOAST(thiscontext, "Data fetch failed" + e.getMessage());
            }

            @Override
            public void onResponse(@NotNull okhttp3.Call call, @NotNull okhttp3.Response response) throws IOException {
                final String responsez = response.body().string();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            JSONObject object = new JSONObject(responsez);
                            String Profile_Picture = object.optString("Profile_Picture");
                            String Name = object.optString("Name");
                            String Username = object.optString("Username");
                            String Progress = object.optString("Progress");
                            String Email = object.optString("Email");
                            String DOB = object.optString("Date_of_Birth");
                            String phno = object.optString("Phone_no");
                            String Invitation_code = object.optString("Invitation_code");
                            String Email_Verified = object.optString("Email_Verified");
                            String State = object.optString("State");
                            String Country = object.optString("Country");
                            String Gender = object.optString("Gender");

                            myprofile_name.setText(Name);
                            myprofile_username.setText(Username);
                            myprofile_birthdate.setText(DOB);
                            myprofile_email.setText(Email);
                            myprofile_refferalcode.setText(Invitation_code);
                            myprofile_phoneno.setText(phno);
                            myprofile_place.setText(State + "," + Country);

                            progressBar2.setProgress(Integer.parseInt(Progress));
                            Picasso.get()
                                    .load(Profile_Picture)
                                    .placeholder(R.drawable.placeholder)
                                    .fit()
                                    .error(R.drawable.dperror)
                                    .centerCrop()
                                    .into(myprofile_dp);
                            if (Email_Verified.equals("true")) {
                                myprofile_phoneverifieidbot.setText("verified");
                            } else {
                                myprofile_phoneverifieidbot.setTextColor(Color.RED);
                                myprofile_phoneverifieidbot.setText("Not-Verified");
                            }


                            hideshimmer();

                            SharedPreferences preferences = getActivity().getSharedPreferences(Constants.MYPROFILEPREF, Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putString(Constants.myprofilename, Name);
                            editor.putString(Constants.myprofileUsername, Username);
                            editor.putString(Constants.myprofileProfile_Picture, Profile_Picture);
                            editor.putString(Constants.myprofileEmail, Email);
                            editor.putString(Constants.myprofilestate, State);
                            editor.putString(Constants.myprofileCountry, Country);
                            editor.putString(Constants.myprofileGender, Gender);
//                            nav_name = headerView.findViewById(R.id.nav_name);
//                            nav_email = headerView.findViewById(R.id.nav_email);
//                            nav_filledandtotalentries = headerView.findViewById(R.id.nav_filledandtotalentries);
//                            nav_progress = headerView.findViewById(R.id.nav_progress);
//                            nav_dp = headerView.findViewById(R.id.nav_dp);
//
//                            nav_name.setText(Name);
//                            nav_email.setText(Email);
//                            nav_filledandtotalentries.setText(Progress + "/100");
//                            nav_progress.setProgress(Integer.parseInt(Progress));
//                            Picasso.get()
//                                    .load(Profile_Picture)
//                                    .placeholder(R.drawable.placeholder)
//                                    .fit()
//                                    .error(R.drawable.dperror)
//                                    .centerCrop()
//                                    .into(nav_dp);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    private void hideshimmer() {
        shimmerFrameLayout.hideShimmer();
        shimmerFrameLayout.setVisibility(View.GONE);
        profilemain.setVisibility(View.VISIBLE);
        layoutbottompprofile.setVisibility(View.VISIBLE);
    }
/*
    public void showprofiledata() {
        Call<MyProfileResponse> myProfileResponseCall = APICallsRetrofit.getmyprofiledata().FetchProfileData("bearer " + usrtoken);
        myProfileResponseCall.enqueue(new Callback<MyProfileResponse>() {
            @Override
            public void onResponse(@NotNull Call<MyProfileResponse> call, @NotNull Response<MyProfileResponse> response) {


                if (response.isSuccessful()) {
                    myProfileResponse = response.body();

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (myProfileResponse != null) {
//                                setMyProfileViews(myProfileResponse);
                            }
                        }
                    }, Constants.delaybeforelogin);


                } else {
                    CommonMethods.DisplayLongTOAST(thiscontext, "Data fetch failed");

                }
            }

            @Override
            public void onFailure(Call<MyProfileResponse> call, Throwable t) {
                CommonMethods.LOGthesite(Constants.LOG, "Data fetch Failed  " + t);

            }
        });
    }

    private void setMyProfileViews(MyProfileResponse myProfileResponse) {
//        myprofile_birthdate.setText(myProfileResponse.getDate_of_Birth());
//        myprofile_email.setText(myProfileResponse.getEmail());
//        myprofile_refferalcode.setText(myProfileResponse.getInvitation_code());
//        progressBar2.setProgress(myProfileResponse.getProgress());
//        myprofile_phoneno.setText(myProfileResponse.getPhone_no());
//        myprofile_username.setText(myProfileResponse.getUsername());
//        Picasso.get()
//                .load(myProfileResponse.getProfile_Picture())
//                .placeholder(R.drawable.placeholder)
//                .fit()
//                .error(R.drawable.dperror)
//                .centerCrop()
//                .into(myprofile_dp);

//
//        SharedPreferences sharedPreferences1 = getActivity().getSharedPreferences(Constants.navpref, Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences1.edit();
//        editor.putString(Constants.navheadername, myProfileResponse.getName()).apply();
//        editor.putString(Constants.navheaddp, myProfileResponse.getProfile_Picture()).apply();
//        editor.putString(Constants.navprogressbar, String.valueOf(myProfileResponse.getProgress())).apply();
//        editor.putString(Constants.navemail, myProfileResponse.getEmail()).apply();
//

        if (myProfileResponse.getEmail_Verified()) {
            myprofile_phoneverifieidbot.setText("verified");
        } else {
            myprofile_phoneverifieidbot.setTextColor(Color.RED);
            myprofile_phoneverifieidbot.setText("Not-Verified");
        }
    }*/
}