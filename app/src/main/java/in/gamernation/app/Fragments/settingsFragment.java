package in.gamernation.app.Fragments;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

import in.gamernation.app.Activities.HomeActivity;
import in.gamernation.app.R;

public class settingsFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);
        ((HomeActivity) getActivity()).setToolbarInvisible();
        ((HomeActivity) getActivity()).setDrawerLocked();
    }
}