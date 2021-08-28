package in.gamernation.app.Fragments.wallet;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;
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

public class walletviewalltransactonFragment extends Fragment {
    private SharedPreferences sharedPreferences;
    private String usrtoken;
    private Context thiscontext;
    private TextView walletalltransactionwinningcoin;
    private RecyclerView walletalltransactionrecycler;
    private AdapterWalletlasttransactions adapterWalletlasttransactions;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_walletviewalltransacton, container, false);
        initScreen();
        initviews(root);
        fetchwallet();


        return root;
    }

    private void initviews(View root) {
        walletalltransactionwinningcoin = root.findViewById(R.id.walletalltransactionwinningcoin);
        walletalltransactionrecycler = root.findViewById(R.id.walletalltransactionrecycler);


        GridLayoutManager gridLayoutManager = new GridLayoutManager(thiscontext, 1, GridLayoutManager.VERTICAL, false);
        walletalltransactionrecycler.setLayoutManager(gridLayoutManager);
        walletalltransactionrecycler.addItemDecoration(new DecorationHomeRecyclerGamesItem(thiscontext, R.dimen.dp_2));
    }


    @Override
    public void onAttach(@NonNull @NotNull Context context) {
        super.onAttach(context);
        thiscontext = context;
    }

    private void initScreen() {
        ((HomeActivity) getActivity()).setToolbarInvisible();
        ((HomeActivity) getActivity()).setDrawerLocked();
        ((HomeActivity) getActivity()).setbotInvisible();
        sharedPreferences = thiscontext.getSharedPreferences(Constants.LOGINPREFS, Context.MODE_PRIVATE);
        usrtoken = sharedPreferences.getString(Constants.TOKENUSINGPREFS, "No data found!!!");
    }

    private void fetchwallet() {
        String url = Constants.w3devbaseurl + "user/wallet/view_all_transactions";
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
                            walletalltransactionwinningcoin.setText(String.valueOf(object.get("Balance_winning")));
                            adapterWalletlasttransactions = new AdapterWalletlasttransactions(object, thiscontext);
                            walletalltransactionrecycler.setAdapter(adapterWalletlasttransactions);


                        } catch (Exception exception) {
                            exception.printStackTrace();
                        }
                    }

                });

            }
        });

    }


}