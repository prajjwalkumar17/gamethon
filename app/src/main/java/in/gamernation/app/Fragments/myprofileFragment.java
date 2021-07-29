package in.gamernation.app.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import in.gamernation.app.Activities.HomeActivity;
import in.gamernation.app.R;


public class myprofileFragment extends Fragment {

    String myprofileurl;
    private TextView myprofile_name;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_myprofile, container, false);
        ((HomeActivity) getActivity()).setToolbarInvisible();
        ((HomeActivity) getActivity()).setDrawerLocked();
        ((HomeActivity) getActivity()).setbotInvisible();
        findviews(root);
        initializers();
        return root;
    }

    private void initializers() {

    }



    private void findviews(View root) {
        myprofile_name = root.findViewById(R.id.myprofile_name);
        myprofileurl = "https://";

    }

}