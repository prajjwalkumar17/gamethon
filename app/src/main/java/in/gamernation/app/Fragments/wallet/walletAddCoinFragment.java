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


public class walletAddCoinFragment extends Fragment {

    private WebView addcoinswalletwebview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_wallet_add_coin, container, false);
        addcoinswalletwebview = root.findViewById(R.id.addcoinswalletwebview);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(Constants.METAPTEF, MODE_PRIVATE);
        WebSettings settings = addcoinswalletwebview.getSettings();
        settings.setJavaScriptEnabled(true);
        addcoinswalletwebview.setWebViewClient(new Callback());
        addcoinswalletwebview.loadUrl(sharedPreferences.getString(Constants.add_money, "data not found"));


        return root;
    }

    private class Callback extends WebViewClient {
        @Override
        public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
            return false;
        }
    }
}