package in.gamernation.app.Fragments;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.jetbrains.annotations.NotNull;

import in.gamernation.app.APICalls.APICalls;
import in.gamernation.app.Activities.HomeActivity;
import in.gamernation.app.R;
import in.gamernation.app.Utils.CommonMethods;


public class myprofileFragment extends Fragment {

//    private static String myprofile_name_route, myprofile_username_route, myprofile_email_route, myprofile_place_route,
//            myprofile_progress_route, myprofile_phoneno_route, myprofile_password_route, myprofile_birthdate_route, myprofile_panno_route, myprofile_refferalcode_route;


    //TODO CHANGEABLE
    private static String fetchedProfileData;
    private static String madeurl;
    private static String ID;
    private static String pathsegment = "users";
    //TODO


    private static Context thiscontext;
    private static ImageView commontoolbar_backbot;
    private static ImageView myprofile_sharerefferalcodebot;
    private static TextView commontoolbar_fragname;
    private static FloatingActionButton myprofile_updatebiocredsbot;
    private static TextView myprofile_name, myprofile_username, myprofile_email, myprofile_place, myprofile_progress, myprofile_phoneno, myprofile_password, myprofile_birthdate, myprofile_panno, myprofile_refferalcode;
    private static TextView myprofile_phoneverifieidbot, myprofile_passwordchangebot, myprofile_updatebirthdatebot, myprofile_updatepanno;
    private static ProgressBar progressBar2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_myprofile, container, false);
        initscreen();
        initializers();
        findviews(root);
        initfunctions();
        return root;
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
//        myprofile_name_route = MyprofileRoutes.myprofile_name_route;
//        myprofile_username_route = MyprofileRoutes.myprofile_username_route;
//        myprofile_email_route = MyprofileRoutes.myprofile_email_route;
//        myprofile_place_route = MyprofileRoutes.myprofile_place_route;
//        myprofile_progress_route = MyprofileRoutes.myprofile_progress_route;
//        myprofile_phoneno_route = MyprofileRoutes.myprofile_phoneno_route;
//        myprofile_password_route = MyprofileRoutes.myprofile_password_route;
//        myprofile_birthdate_route = MyprofileRoutes.myprofile_birthdate_route;
//        myprofile_panno_route = MyprofileRoutes.myprofile_panno_route;
//        myprofile_refferalcode_route = MyprofileRoutes.myprofile_refferalcode_route;


        new datafetcherasyncqueires().execute();


    }

    private void findviews(View root) {
        myprofile_name = root.findViewById(R.id.myprofile_name);
        myprofile_username = root.findViewById(R.id.myprofile_username);
        myprofile_email = root.findViewById(R.id.myprofile_email);
        myprofile_place = root.findViewById(R.id.myprofile_place);
        myprofile_progress = root.findViewById(R.id.myprofile_progress);
        progressBar2 = root.findViewById(R.id.progressBar2);
        myprofile_phoneno = root.findViewById(R.id.myprofile_phoneno);
        myprofile_phoneverifieidbot = root.findViewById(R.id.myprofile_phoneverifieidbot);
        myprofile_password = root.findViewById(R.id.myprofile_password);
        myprofile_passwordchangebot = root.findViewById(R.id.myprofile_passwordchangebot);
        myprofile_birthdate = root.findViewById(R.id.myprofile_birthdate);
        myprofile_updatebirthdatebot = root.findViewById(R.id.myprofile_updatebirthdatebot);
        myprofile_panno = root.findViewById(R.id.myprofile_panno);
        myprofile_updatepanno = root.findViewById(R.id.myprofile_updatepanno);
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

    static class datafetcherasyncqueires extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {


                madeurl = APICalls.buildhttpurlforgetreq(pathsegment, ID);
                fetchedProfileData = APICalls.gethttpRequest(madeurl);


            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            if (CommonMethods.isnetworkConnected(thiscontext)) {
                // UI changes done here
            } else {
                CommonMethods.DisplayLongTOAST(thiscontext, "Check the network connectivity");
            }
        }
    }

}