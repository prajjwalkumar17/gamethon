package in.gamernation.app.Startup;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.fragment.app.Fragment;

import in.gamernation.app.Activities.WebvieweClient;
import in.gamernation.app.R;
import in.gamernation.app.Utils.Constants;

public class LoginWebviewFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_login_webview, container, false);

        WebView loginwebview;
        loginwebview = root.findViewById(R.id.addcoinswalletwebview);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(Constants.METAPTEF, MODE_PRIVATE);
        WebSettings settings = loginwebview.getSettings();
        settings.setJavaScriptEnabled(true);
        loginwebview.setWebViewClient(new WebvieweClient());
        loginwebview.addJavascriptInterface(new WebvieweClient.MyJavaScriptInterface(),
                "android");

        loginwebview.loadUrl(sharedPreferences.getString(Constants.metasignup, "data not found"));


        return root;
    }


}


