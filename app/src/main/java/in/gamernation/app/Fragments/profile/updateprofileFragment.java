package in.gamernation.app.Fragments.profile;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import com.facebook.shimmer.ShimmerFrameLayout;
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

public class updateprofileFragment extends Fragment {
    private EditText myprofileedittextname, myprofileedittextusername,
            myprofileedittextemail, myprofileedittstate, myprofileedittextcountry;
    private AppCompatButton myprofileuploadpicbot, myprofileupdatebot;
    private RadioButton myprofilemaleradio, myprofilefemaleradio;
    private CircleImageView myprofiledpbot;
    private ImageView toolwithbackbotheadbot;
    private TextView toolwithbackbothead;
    private SharedPreferences sharedPreferences;
    private Context thiscontext;
    private String usrtoken, msg, Gender;
    private LinearLayout linlayoutsecond, myprofilelin1;
    private ShimmerFrameLayout profileupdateshimmer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_updateprofile, container, false);
        initscreen();
        shimmersetup(root);
        imguploadbot();
        shimmerstart();
        initviews(root);

        return root;
    }

    private void imguploadbot() {
        myprofileuploadpicbot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void shimmerstart() {
        profileupdateshimmer.setVisibility(View.VISIBLE);
        profileupdateshimmer.startShimmer();
        linlayoutsecond.setVisibility(View.INVISIBLE);
        myprofilelin1.setVisibility(View.INVISIBLE);
    }

    private void shimmersetup(View root) {
        profileupdateshimmer = root.findViewById(R.id.profileupdateshimmer);
        profileupdateshimmer.setVisibility(View.VISIBLE);
        profileupdateshimmer.startShimmer();


        linlayoutsecond = root.findViewById(R.id.linlayoutsecond);
        myprofilelin1 = root.findViewById(R.id.myprofilelin1);

        linlayoutsecond.setVisibility(View.INVISIBLE);
        myprofilelin1.setVisibility(View.INVISIBLE);


    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        thiscontext = context;
    }

    private void initviews(View root) {
        toolwithbackbotheadbot = root.findViewById(R.id.toolwithbackbotheadbot);
        toolwithbackbothead = root.findViewById(R.id.toolwithbackbothead);
        toolwithbackbothead.setText("Update Profile");
        toolwithbackbotheadbot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStackImmediate();
            }
        });

        myprofileedittextname = root.findViewById(R.id.myprofileedittextname);
        myprofileedittextusername = root.findViewById(R.id.myprofileedittextusername);
        myprofileedittextemail = root.findViewById(R.id.myprofileedittextemail);
        myprofileedittstate = root.findViewById(R.id.myprofileedittstate);
        myprofileedittextcountry = root.findViewById(R.id.myprofileedittextcountry);
        myprofileuploadpicbot = root.findViewById(R.id.myprofileuploadpicbot);
        myprofileupdatebot = root.findViewById(R.id.myprofileupdatebot);
        myprofilemaleradio = root.findViewById(R.id.myprofilemaleradio);
        myprofilefemaleradio = root.findViewById(R.id.myprofilefemaleradio);
        myprofiledpbot = root.findViewById(R.id.myprofiledpbot);
        myprofilemaleradio = root.findViewById(R.id.myprofilemaleradio);
        myprofilemaleradio = root.findViewById(R.id.myprofilemaleradio);


//        fetchdataandsettoviewsintially();


    }

//    private void fetchdataandsettoviewsintially() {
//        SharedPreferences preferences=getActivity().getSharedPreferences(Constants.MYPROFILEPREF,Context.MODE_PRIVATE);
//        String gender =preferences.getString(Constants.myprofileGender,"Data not found");
//        String name =preferences.getString(Constants.myprofilename,"Data not found");
//        String username =preferences.getString(Constants.myprofileUsername,"Data not found");
//        String profilepic =preferences.getString(Constants.myprofileProfile_Picture,"Data not found");
//        String email =preferences.getString(Constants.myprofileEmail,"Data not found");
//        String state =preferences.getString(Constants.myprofilestate,"Data not found");
//        String sountry =preferences.getString(Constants.myprofileCountry,"Data not found");
//
//        CommonMethods.LOGthesite(Constants.LOG,name);
//
//    }

    private void initscreen() {
        ((HomeActivity) getActivity()).setToolbarInvisible();
        ((HomeActivity) getActivity()).setDrawerLocked();
        ((HomeActivity) getActivity()).setbotInvisible();

        sharedPreferences = thiscontext.getSharedPreferences(Constants.LOGINPREFS, Context.MODE_PRIVATE);
        usrtoken = sharedPreferences.getString(Constants.TOKENUSINGPREFS, "No data found!!!");

        fetchprofiledata();

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
                    CommonMethods.DisplayLongTOAST(thiscontext, "Name & Username field can't be empty");
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
                                    CommonMethods.DisplayLongTOAST(thiscontext, e.getMessage().toString());
                                }

                                @Override
                                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                    final String responsez = response.body().string();
                                    getActivity().runOnUiThread(new Runnable() {

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
                                            CommonMethods.DisplayLongTOAST(thiscontext, msg);
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