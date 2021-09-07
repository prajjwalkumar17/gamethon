package in.gamernation.app.Fragments.wallet;

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

import in.gamernation.app.R;
import in.gamernation.app.Utils.Constants;

public class walletwithdrawcoinsFragment extends Fragment {

    WebView withdrawwalletwebview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_walletwithdrawcoins, container, false);
        withdrawwalletwebview = root.findViewById(R.id.withdrawwalletwebview);
        WebSettings settings = withdrawwalletwebview.getSettings();
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(Constants.METAPTEF, MODE_PRIVATE);

        settings.setJavaScriptEnabled(true);
        withdrawwalletwebview.setWebViewClient(new Callback());
        withdrawwalletwebview.loadUrl(sharedPreferences.getString(Constants.withdraw, "data not found")
        );


        return root;
    }


    private class Callback extends WebViewClient {
        @Override
        public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
            return false;
        }
    }
}