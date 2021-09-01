package in.gamernation.app.Fragments.wallet;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

import in.gamernation.app.APICalls.APICallsOkHttp;
import in.gamernation.app.Activities.HomeActivity;
import in.gamernation.app.Adapters.AdapterWalletpaymenthistory;
import in.gamernation.app.Decoration.DecorationHomeRecyclerGamesItem;
import in.gamernation.app.R;
import in.gamernation.app.Utils.Constants;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class walletpaymenthistoryFragment extends Fragment {

    private SharedPreferences sharedPreferences;
    private String usrtoken;
    private Context thiscontext;
    private TextView walletpaymenthistorywinningcoin, toolwithbackbothead;
    private RecyclerView walletpaymenthistoryrecycler;
    private AdapterWalletpaymenthistory adapterWalletpaymenthistory;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_walletpaymenthistory, container, false);
        initScreen();
        initviews(root);
        fetchwallet();
        return root;
    }


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

                      /*      String type=list.getString("type");
                            String _id=list.getString("_id");
                            String amount=list.getString("amount");
                            String order=list.getString("order");
                            String status=list.getString("status");

                            CommonMethods.LOGthesite(Constants.LOG,"type "+type);
                            CommonMethods.LOGthesite(Constants.LOG,"_id "+_id);
                            CommonMethods.LOGthesite(Constants.LOG,"order "+order);
                            CommonMethods.LOGthesite(Constants.LOG,"status "+status);
                            CommonMethods.LOGthesite(Constants.LOG,"amount "+amount);*/

                            adapterWalletpaymenthistory = new AdapterWalletpaymenthistory(object);
                            walletpaymenthistoryrecycler.setAdapter(adapterWalletpaymenthistory);
                        } catch (Exception exception) {
                            exception.printStackTrace();
                        }
                    }
                });
            }
        });
    }
}