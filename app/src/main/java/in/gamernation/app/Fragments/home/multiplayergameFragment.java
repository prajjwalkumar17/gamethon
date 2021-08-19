package in.gamernation.app.Fragments.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import in.gamernation.app.R;


public class multiplayergameFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate t he layout for this fragment
        View view = inflater.inflate(R.layout.fragment_multiplayergame, container, false);
        return view;
    }
}