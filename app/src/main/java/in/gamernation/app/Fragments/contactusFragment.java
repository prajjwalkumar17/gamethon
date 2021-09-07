package in.gamernation.app.Fragments;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.fragment.app.Fragment;

import in.gamernation.app.Activities.HomeActivity;
import in.gamernation.app.R;
import in.gamernation.app.Utils.Constants;

public class contactusFragment extends Fragment {

    WebView contactuswebview;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_contactus, container, false);
        ((HomeActivity) getActivity()).setToolbarInvisible();
        ((HomeActivity) getActivity()).setDrawerLocked();
        contactuswebview = root.findViewById(R.id.contactuswebview);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(Constants.METAPTEF, MODE_PRIVATE);

        WebSettings settings = contactuswebview.getSettings();
        settings.setJavaScriptEnabled(true);
        contactuswebview.setWebViewClient(new Callback());
        contactuswebview.loadUrl(sharedPreferences.getString(Constants.contactUs, "data not found"));

        return root;
    }


    private class Callback extends WebViewClient {
        @Override
        public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
            return false;
        }
    }
}