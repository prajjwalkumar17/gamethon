package in.gamernation.app.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import de.hdodenhof.circleimageview.CircleImageView;
import in.gamernation.app.APICalls.APICalls;
import in.gamernation.app.Activities.HomeActivity;
import in.gamernation.app.ModalClasses.MyProfileResponse;
import in.gamernation.app.R;
import in.gamernation.app.Utils.CommonMethods;
import in.gamernation.app.Utils.Constants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class myprofileFragment extends Fragment {

    //TODO CHANGEABLE


    //TODO


    private static Context thiscontext;
    private static String usrtoken;
    private static ImageView commontoolbar_backbot;
    private static ImageView myprofile_sharerefferalcodebot;
    private static TextView commontoolbar_fragname;
    private static FloatingActionButton myprofile_updatebiocredsbot;
    private static TextView myprofile_name, myprofile_username, myprofile_email, myprofile_place, myprofile_phoneno, myprofile_password, myprofile_birthdate, myprofile_panno, myprofile_refferalcode;
    private static TextView myprofile_phoneverifieidbot, myprofile_passwordchangebot, myprofile_updatebirthdatebot, myprofile_updatepanno;
    private static ProgressBar progressBar2;
    private static CircleImageView myprofile_dp;
    private static SharedPreferences sharedPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_myprofile, container, false);
        allfunctions(root);
        return root;
    }

    private void allfunctions(View root) {
        initscreen();
        initializers();
        findviews(root);
        initfunctions();
    }

    private void initscreen() {
        ((HomeActivity) getActivity()).setToolbarInvisible();
        ((HomeActivity) getActivity()).setDrawerLocked();
        ((HomeActivity) getActivity()).setbotInvisible();
    }

    private void initfunctions() {
        ontoolbarbackpressed();
    }

    private void initializers() {
        sharedPreferences = thiscontext.getSharedPreferences(Constants.LOGINPREFS, Context.MODE_PRIVATE);
        usrtoken = sharedPreferences.getString(Constants.TOKENUSINGPREFS, "No data found!!!");

        showprofiledata();


    }

    private void findviews(View root) {
        myprofile_name = root.findViewById(R.id.myprofile_name);
        myprofile_username = root.findViewById(R.id.myprofile_username);
        myprofile_email = root.findViewById(R.id.myprofile_email);
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
        myprofile_dp = root.findViewById(R.id.myprofile_dp);
        myprofile_refferalcode = root.findViewById(R.id.myprofile_refferalcode);
        myprofile_updatebiocredsbot = root.findViewById(R.id.myprofile_updatebiocredsbot);
        commontoolbar_backbot = root.findViewById(R.id.commontoolbar_backbot);
        commontoolbar_fragname = root.findViewById(R.id.commontoolbar_fragname);
        myprofile_sharerefferalcodebot = root.findViewById(R.id.myprofile_sharerefferalcodebot);
    }

    private void ontoolbarbackpressed() {
        commontoolbar_backbot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getFragmentManager().getBackStackEntryCount() != 0) {
                    getFragmentManager().popBackStackImmediate();
                } else {
                    CommonMethods.DisplayLongTOAST(getContext(), "there is no backward stack available");
                }
            }
        });
    }

    @Override
    public void onAttach(@NonNull @NotNull Context context) {
        super.onAttach(context);
        thiscontext = context;
    }


    public void showprofiledata() {
        Call<MyProfileResponse> myProfileResponseCall = APICalls.getmyprofiledata().FetchProfileData("bearer " + usrtoken);
        myProfileResponseCall.enqueue(new Callback<MyProfileResponse>() {
            @Override
            public void onResponse(@NotNull Call<MyProfileResponse> call, @NotNull Response<MyProfileResponse> response) {


                if (response.isSuccessful()) {
                    CommonMethods.DisplayLongTOAST(thiscontext, "Message received sucesssfully");
                    MyProfileResponse myProfileResponse = response.body();


                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (myProfileResponse != null) {
                                setMyProfileViews(myProfileResponse);
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


        myprofile_name.setText(myProfileResponse.getName());
        myprofile_birthdate.setText(myProfileResponse.getDate_of_Birth());
        myprofile_email.setText(myProfileResponse.getEmail());
        myprofile_refferalcode.setText(myProfileResponse.getInvitation_code());
        progressBar2.setProgress(myProfileResponse.getProgress());
        myprofile_phoneno.setText(myProfileResponse.getPhone_no());
        Picasso.get()
                .load(myProfileResponse.getProfile_Picture())
                .resize(86, 86)
                .centerCrop()
                .into(myprofile_dp);
        if (myProfileResponse.getEmail_Verified()) {
            myprofile_phoneverifieidbot.setText("verified");
        } else {
            myprofile_phoneverifieidbot.setTextColor(Color.RED);
            myprofile_phoneverifieidbot.setText("Not-Verified");
        }


    }


}