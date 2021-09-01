package in.gamernation.app.Fragments.myleagues;

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
import org.json.JSONObject;

import java.io.IOException;

import in.gamernation.app.APICalls.APICallsOkHttp;
import in.gamernation.app.Activities.HomeActivity;
import in.gamernation.app.Adapters.Adaptermyleagueshowteam;
import in.gamernation.app.Decoration.DecorationHomeRecyclerGamesItem;
import in.gamernation.app.R;
import in.gamernation.app.Utils.Constants;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class myleaguesviewteamFragment extends Fragment {

    private SharedPreferences sharedPreferences;
    private String usrtoken, id;
    private Context thiscontext;
    private TextView myleagueshowteamhead,
            myleagueshowteamslot, myleagueshowteamteamid, myleagueshowteamteamname;
    private ImageView myleagueshowteambackbot;
    private RecyclerView myleagueshowteamrecycler;
    private Adaptermyleagueshowteam adaptermyleagueshowteam;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_myleaguesviewteam, container, false);
        initscreen();
        initviews(root);

        return root;
    }

    @Override
    public void onAttach(@NonNull @NotNull Context context) {
        super.onAttach(context);
        thiscontext = context;
    }

    private void initviews(View root) {
        myleagueshowteamhead = root.findViewById(R.id.myleagueshowteamhead);
        myleagueshowteambackbot = root.findViewById(R.id.myleagueshowteambackbot);
        myleagueshowteamslot = root.findViewById(R.id.myleagueshowteamslot);
        myleagueshowteamteamid = root.findViewById(R.id.myleagueshowteamteamid);
        myleagueshowteamteamname = root.findViewById(R.id.myleagueshowteamteamname);
        myleagueshowteamrecycler = root.findViewById(R.id.myleagueshowteamrecycler);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(thiscontext, 1, GridLayoutManager.VERTICAL, false);
        myleagueshowteamrecycler.setLayoutManager(gridLayoutManager);
        myleagueshowteamrecycler.addItemDecoration(new DecorationHomeRecyclerGamesItem(thiscontext, R.dimen.dp_2));
        myleagueshowteambackbot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStackImmediate();
            }
        });
    }

    private void initscreen() {
        ((HomeActivity) getActivity()).setToolbarInvisible();
        ((HomeActivity) getActivity()).setDrawerLocked();
        ((HomeActivity) getActivity()).setbotInvisible();
        sharedPreferences = thiscontext.getSharedPreferences(Constants.LOGINPREFS, Context.MODE_PRIVATE);
        usrtoken = sharedPreferences.getString(Constants.TOKENUSINGPREFS, "No data found!!!");
        SharedPreferences preferences = thiscontext.getSharedPreferences(Constants.ARCADEGAMEPREF, Context.MODE_PRIVATE);
        id = preferences.getString(Constants.arcadeopenedid, "no data found!!!");

        fetchdataandshow();
    }


    private void fetchdataandshow() {
        String url = APICallsOkHttp.urlbuilderforhttp(Constants.w3devbaseurl + "user/view_your_team/" + id);
        APICallsOkHttp.okhttpmaster().newCall(APICallsOkHttp.requestbuildwithauth(url, usrtoken)).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            final String responsez = response.body().string();
                            JSONObject object = new JSONObject(responsez);
                            String teamname = object.getString("team_name");
                            if (!object.optString("slot").isEmpty()) {
                                myleagueshowteamslot.setText("SLOT: " + object.optString("slot"));
                            }
                            if (!object.optString("teamid").isEmpty()) {
                                myleagueshowteamteamid.setText("Team-Id: " + object.optString("teamid"));
                            }
                            myleagueshowteamteamname.setText("Team-Name: " + teamname);
                            adaptermyleagueshowteam = new Adaptermyleagueshowteam(object);
                            myleagueshowteamrecycler.setAdapter(adaptermyleagueshowteam);


                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                });
            }
        });
    }
}