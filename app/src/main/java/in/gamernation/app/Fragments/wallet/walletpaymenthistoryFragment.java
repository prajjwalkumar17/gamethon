package in.gamernation.app.Fragments.wallet;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import in.gamernation.app.Adapters.AdapterWalletpaymenthistory;
import in.gamernation.app.R;
import in.gamernation.app.Utils.Constants;


public class walletpaymenthistoryFragment extends Fragment {

    private SharedPreferences sharedPreferences;
    private String usrtoken;
    private Context thiscontext;
    private TextView walletpaymenthistorywinningcoin, toolwithbackbothead;
    private RecyclerView walletpaymenthistoryrecycler;
    private AdapterWalletpaymenthistory adapterWalletpaymenthistory;

    WebView walletviewpaymenthistorywebview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_walletpaymenthistory, container, false);
//        initScreen();
//        initviews(root);
//        fetchwallet();

        walletviewpaymenthistorywebview = root.findViewById(R.id.walletviewpaymenthistorywebview);
        WebSettings settings = walletviewpaymenthistorywebview.getSettings();
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(Constants.METAPTEF, MODE_PRIVATE);
        settings.setJavaScriptEnabled(true);
        walletviewpaymenthistorywebview.setWebViewClient(new Callback());
        walletviewpaymenthistorywebview.loadUrl(sharedPreferences.getString(Constants.payment_history, "data not found"));

        return root;
    }

    private class Callback extends WebViewClient {
        @Override
        public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
            return false;
        }
    }
/*
    @Override
    public void onAttach(@NonNull @NotNull Context context) {
        super.onAttach(context);
        thiscontext = context;
    }

    private void initviews(View root) {
        walletpaymenthistorywinningcoin = root.findViewById(R.id.walletpaymenthistorywinningcoin);
        walletpaymenthistoryrecycler = root.findViewById(R.id.walletpaymenthistoryrecycler);
        toolwithbackbothead = root.findViewById(R.id.toolwithbackbothead);

        toolwithbackbothead.setText("Payment History");
        ImageView toolwithbackbotheadbot = root.findViewById(R.id.toolwithbackbotheadbot);

        toolwithbackbotheadbot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getActivity() != null)
                    getActivity().getSupportFragmentManager().popBackStackImmediate();
            }
        });


        GridLayoutManager gridLayoutManager = new GridLayoutManager(thiscontext, 1, GridLayoutManager.VERTICAL, false);
        walletpaymenthistoryrecycler.setLayoutManager(gridLayoutManager);
        walletpaymenthistoryrecycler.addItemDecoration(new DecorationHomeRecyclerGamesItem(thiscontext, R.dimen.dp_2));
    }

    private void initScreen() {
        ((HomeActivity) getActivity()).setToolbarInvisible();
        ((HomeActivity) getActivity()).setDrawerLocked();
        ((HomeActivity) getActivity()).setbotInvisible();
        sharedPreferences = thiscontext.getSharedPreferences(Constants.LOGINPREFS, Context.MODE_PRIVATE);
        usrtoken = sharedPreferences.getString(Constants.TOKENUSINGPREFS, "No data found!!!");
    }

    private void fetchwallet() {
        String url = Constants.w3devbaseurl + "user/wallet/payment_history";
        APICallsOkHttp.okhttpmaster().newCall(
                APICallsOkHttp.requestbuildwithauth(APICallsOkHttp.urlbuilderforhttp(url)
                        , usrtoken)
        ).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                final String myResponse = response.body().string();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {


                            JSONObject object = new JSONObject(myResponse);
                            walletpaymenthistorywinningcoin.setText(String.valueOf(object.get("Balance_winning")));
                            JSONArray listarray = object.getJSONArray("Payment_history");
//                            JSONObject list=listarray.getJSONObject(0);

                      *//*      String type=list.getString("type");
                            String _id=list.getString("_id");
                            String amount=list.getString("amount");
                            String order=list.getString("order");
                            String status=list.getString("status");

                            CommonMethods.LOGthesite(Constants.LOG,"type "+type);
                            CommonMethods.LOGthesite(Constants.LOG,"_id "+_id);
                            CommonMethods.LOGthesite(Constants.LOG,"order "+order);
                            CommonMethods.LOGthesite(Constants.LOG,"status "+status);
                            CommonMethods.LOGthesite(Constants.LOG,"amount "+amount);*//*

                            adapterWalletpaymenthistory = new AdapterWalletpaymenthistory(object);
                            walletpaymenthistoryrecycler.setAdapter(adapterWalletpaymenthistory);
                        } catch (Exception exception) {
                            exception.printStackTrace();
                        }
                    }
                });
            }
        });
    }*/
}