package in.gamernation.app.Fragments.wallet;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import in.gamernation.app.APICalls.APICallsOkHttp;
import in.gamernation.app.Activities.HomeActivity;
import in.gamernation.app.Adapters.AdapterWalletlasttransactions;
import in.gamernation.app.Decoration.DecorationHomeRecyclerGamesItem;
import in.gamernation.app.R;
import in.gamernation.app.Utils.Constants;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class walletFragment extends Fragment {
    private SharedPreferences sharedPreferences;
    private String usrtoken;
    private Context thiscontext;
    private TextView walletfragmenttotalcoins, walletfragmentdepositedcoins, walletfragmentwinningcoins, walletfragmentbonuscoins, walletfragmentviewtransactionbot, walletfragmentviewpaymenthistorybot, toolwithbackbothead;
    private RecyclerView walletfragmentrecyclerview;
    private LinearLayout walletfragmentaddcoinsbot, walletfragmentwithdrawcoinsbot;
    private AdapterWalletlasttransactions adapterWalletlasttransactions;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_wallet, container, false);
        initscreen();
        initviews(root);
        return root;
    }

    @Override
    public void onAttach(@NonNull @NotNull Context context) {
        super.onAttach(context);
        thiscontext = context;
    }

    private void initscreen() {
        ((HomeActivity) getActivity()).setToolbarInvisible();
        ((HomeActivity) getActivity()).setDrawerLocked();
        ((HomeActivity) getActivity()).setbotInvisible();
        sharedPreferences = thiscontext.getSharedPreferences(Constants.LOGINPREFS, Context.MODE_PRIVATE);
        usrtoken = sharedPreferences.getString(Constants.TOKENUSINGPREFS, "No data found!!!");
    }

    private void initviews(View root) {
        walletfragmenttotalcoins = root.findViewById(R.id.walletfragmenttotalcoins);
        walletfragmentdepositedcoins = root.findViewById(R.id.walletfragmentdepositedcoins);
        walletfragmentwinningcoins = root.findViewById(R.id.walletfragmentwinningcoins);
        walletfragmentviewtransactionbot = root.findViewById(R.id.walletfragmentviewtransactionbot);
        walletfragmentviewpaymenthistorybot = root.findViewById(R.id.walletfragmentviewpaymenthistorybot);
        walletfragmentbonuscoins = root.findViewById(R.id.walletfragmentbonuscoins);
        walletfragmentaddcoinsbot = root.findViewById(R.id.walletfragmentaddcoinsbot);
        walletfragmentwithdrawcoinsbot = root.findViewById(R.id.walletfragmentwithdrawcoinsbot);
        toolwithbackbothead = root.findViewById(R.id.toolwithbackbothead);

        toolwithbackbothead.setText("My Wallet");

        walletfragmentrecyclerview = root.findViewById(R.id.walletfragmentrecyclerview);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(thiscontext, 1, GridLayoutManager.VERTICAL, false);
        walletfragmentrecyclerview.setLayoutManager(gridLayoutManager);
        walletfragmentrecyclerview.addItemDecoration(new DecorationHomeRecyclerGamesItem(thiscontext, R.dimen.dp_2));

        fetchwallet();
        viewalltransaction();
        showpaymenthistory();
        addcoins();
        withdrawcoins();
    }

    private void addcoins() {
        //TODO ADDCOINS WEBVIEW HERE


    }

    private void withdrawcoins() {

        //TODO WITHDRAWCOINS WEBVIEW HERE

    }

    private void viewalltransaction() {
        walletfragmentviewtransactionbot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainerView, new walletviewalltransactonFragment()).addToBackStack(null).commit();

            }
        });

    }

    private void showpaymenthistory() {
        walletfragmentviewpaymenthistorybot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainerView, new walletpaymenthistoryFragment()).addToBackStack(null).commit();


            }
        });
    }

    private void fetchwallet() {
        String url = Constants.w3devbaseurl + "user/wallet";
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
                            setdatatoviews(myResponse);
//                            CommonMethods.LOGthesite(Constants.LOG,"\n"+deposit+"\n"+winning+"\n"+bonus+"\n"+transaction_amount+"\n"+balance_amount+"\n"+type+"\n"+cause+"\n"+date+"\n");


                        } catch (Exception exception) {
                            exception.printStackTrace();
                        }
                    }

                });

            }
        });

    }

    private void setdatatoviews(String myResponse) {
        try {

            JSONObject object = new JSONObject(myResponse);
            String totalcoins = null;
            totalcoins = object.getString("Total_coins");

            String balancebonus = object.getString("Balance_bonus");
            String balancedeposited = object.getString("Balance_deposited");
            String Balancewinning = object.getString("Balance_winning");
            /*JSONArray transactionarray = object.getJSONArray("Recent_transactions");
            JSONObject lasttransactions = transactionarray.getJSONObject(1);
            String deposit = lasttransactions.getString("deposit");
            String winning = lasttransactions.getString("winning");
            String bonus = lasttransactions.getString("bonus");
            String transaction_amount = lasttransactions.getString("transaction_amount");
            String balance_amount = lasttransactions.getString("balance_amount");
            String type = lasttransactions.getString("type");
            String cause = lasttransactions.getString("for");
            String date = lasttransactions.getString("created_at");*/

            walletfragmenttotalcoins.setText(totalcoins + " Coins");
            walletfragmentbonuscoins.setText(balancebonus + " Coins");
            walletfragmentdepositedcoins.setText(balancedeposited + " Coins");
            walletfragmentwinningcoins.setText(Balancewinning + " Coins");

            adapterWalletlasttransactions = new AdapterWalletlasttransactions(object, thiscontext);
            walletfragmentrecyclerview.setAdapter(adapterWalletlasttransactions);


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}