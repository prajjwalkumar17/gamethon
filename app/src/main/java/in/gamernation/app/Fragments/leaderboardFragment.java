package in.gamernation.app.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;
import in.gamernation.app.APICalls.APICallsOkHttp;
import in.gamernation.app.Activities.HomeActivity;
import in.gamernation.app.Adapters.AdapterLeaderboard;
import in.gamernation.app.Decoration.DecorationHomeRecyclerGamesItem;
import in.gamernation.app.R;
import in.gamernation.app.Utils.Constants;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static in.gamernation.app.R.layout;

public class leaderboardFragment extends Fragment {
    private CircleImageView leaderboard1strankdp, leaderboard3rdrankdp, leaderboard2ndrankdp;
    private TextView leaderboard1strankname, leaderboard1strankcoinswon, leaderboard3rdrankname, leaderboard3rdrankcoinswon, leaderboard2ndrankname, leaderboard2ndrankcoinswon;
    private AppCompatButton Leaderboardfreefirebot, Leaderboardludobot, Leaderboardclashsquadbot, Leaderboardquizbot, Leaderboardfanbattlebot, Leaderboardbgmibot, Leaderboardbackbot;
    private RecyclerView leaderboardrecyclerview;
    private SwitchCompat leaderboardswitch;
    private SharedPreferences sharedPreferences;
    private String usrtoken;
    private Context thiscontext;
    private AdapterLeaderboard adapterLeaderboard;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(layout.fragment_leaderboard, container, false);
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
        leaderboard1strankdp = root.findViewById(R.id.leaderboard1strankdp);
        leaderboard3rdrankdp = root.findViewById(R.id.leaderboard3rdrankdp);
        leaderboard2ndrankdp = root.findViewById(R.id.leaderboard2ndrankdp);


        leaderboard1strankname = root.findViewById(R.id.leaderboard1strankname);
        leaderboard1strankcoinswon = root.findViewById(R.id.leaderboard1strankcoinswon);
        leaderboard3rdrankname = root.findViewById(R.id.leaderboard3rdrankname);
        leaderboard3rdrankcoinswon = root.findViewById(R.id.leaderboard3rdrankcoinswon);
        leaderboard2ndrankname = root.findViewById(R.id.leaderboard2ndrankname);
        leaderboard2ndrankcoinswon = root.findViewById(R.id.leaderboard2ndrankcoinswon);

        Leaderboardfreefirebot = root.findViewById(R.id.Leaderboardfreefirebot);
        Leaderboardludobot = root.findViewById(R.id.Leaderboardludobot);
        Leaderboardclashsquadbot = root.findViewById(R.id.Leaderboardclashsquadbot);
        Leaderboardquizbot = root.findViewById(R.id.Leaderboardquizbot);
        Leaderboardfanbattlebot = root.findViewById(R.id.Leaderboardfanbattlebot);
        Leaderboardbgmibot = root.findViewById(R.id.Leaderboardbgmibot);

        leaderboardswitch = root.findViewById(R.id.leaderboardswitch);
        leaderboardrecyclerview = root.findViewById(R.id.leaderboardrecyclerview);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(thiscontext, 1, GridLayoutManager.VERTICAL, false);
        leaderboardrecyclerview.setLayoutManager(gridLayoutManager);
        leaderboardrecyclerview.addItemDecoration(new DecorationHomeRecyclerGamesItem(thiscontext, R.dimen.dp_2));
        Leaderboardfreefirebot.setTextColor(getResources().getColor(R.color.white));

        Leaderboardbackbot = root.findViewById(R.id.Leaderboardbackbot);
        Leaderboardbackbot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getActivity() != null)
                    getActivity().getSupportFragmentManager().popBackStackImmediate();
            }

        });


        fetchdata(Constants.leaderboardfiltergame + "FREE_FIRE");
        showdataaccordingtobotpresssed();

    }

    private void showdataaccordingtobotpresssed() {

        Leaderboardfreefirebot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Leaderboardfreefirebot.setTextColor(getResources().getColor(R.color.white));
                fetchdata(Constants.leaderboardfiltergame + "FREE_FIRE");

                Leaderboardludobot.setTextColor(getResources().getColor(R.color.final_secondary));
                Leaderboardclashsquadbot.setTextColor(getResources().getColor(R.color.final_secondary));
                Leaderboardquizbot.setTextColor(getResources().getColor(R.color.final_secondary));
                Leaderboardfanbattlebot.setTextColor(getResources().getColor(R.color.final_secondary));
                Leaderboardbgmibot.setTextColor(getResources().getColor(R.color.final_secondary));
            }
        });
        Leaderboardludobot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Leaderboardludobot.setTextColor(getResources().getColor(R.color.white));
                fetchdata(Constants.leaderboardfiltergame + "LUDO");

                Leaderboardfreefirebot.setTextColor(getResources().getColor(R.color.final_secondary));
                Leaderboardclashsquadbot.setTextColor(getResources().getColor(R.color.final_secondary));
                Leaderboardquizbot.setTextColor(getResources().getColor(R.color.final_secondary));
                Leaderboardfanbattlebot.setTextColor(getResources().getColor(R.color.final_secondary));
                Leaderboardbgmibot.setTextColor(getResources().getColor(R.color.final_secondary));
            }
        });
        Leaderboardclashsquadbot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Leaderboardclashsquadbot.setTextColor(getResources().getColor(R.color.white));
                fetchdata(Constants.leaderboardfiltergame + "CLASHSQUAD");

                Leaderboardfreefirebot.setTextColor(getResources().getColor(R.color.final_secondary));
                Leaderboardludobot.setTextColor(getResources().getColor(R.color.final_secondary));
                Leaderboardquizbot.setTextColor(getResources().getColor(R.color.final_secondary));
                Leaderboardfanbattlebot.setTextColor(getResources().getColor(R.color.final_secondary));
                Leaderboardbgmibot.setTextColor(getResources().getColor(R.color.final_secondary));
            }
        });
        Leaderboardquizbot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Leaderboardquizbot.setTextColor(getResources().getColor(R.color.white));
                fetchdata(Constants.leaderboardfiltergame + "QUIZ");

                Leaderboardludobot.setTextColor(getResources().getColor(R.color.final_secondary));
                Leaderboardclashsquadbot.setTextColor(getResources().getColor(R.color.final_secondary));
                Leaderboardfreefirebot.setTextColor(getResources().getColor(R.color.final_secondary));
                Leaderboardfanbattlebot.setTextColor(getResources().getColor(R.color.final_secondary));
                Leaderboardbgmibot.setTextColor(getResources().getColor(R.color.final_secondary));
            }
        });
        Leaderboardfanbattlebot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Leaderboardfanbattlebot.setTextColor(getResources().getColor(R.color.white));
                fetchdata(Constants.leaderboardfiltergame + "CRICKET_LEAGUE");

                Leaderboardludobot.setTextColor(getResources().getColor(R.color.final_secondary));
                Leaderboardclashsquadbot.setTextColor(getResources().getColor(R.color.final_secondary));
                Leaderboardquizbot.setTextColor(getResources().getColor(R.color.final_secondary));
                Leaderboardfreefirebot.setTextColor(getResources().getColor(R.color.final_secondary));
                Leaderboardbgmibot.setTextColor(getResources().getColor(R.color.final_secondary));
            }
        });
        Leaderboardbgmibot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Leaderboardbgmibot.setTextColor(getResources().getColor(R.color.white));
                fetchdata(Constants.leaderboardfiltergame + "BGMI");

                Leaderboardludobot.setTextColor(getResources().getColor(R.color.final_secondary));
                Leaderboardclashsquadbot.setTextColor(getResources().getColor(R.color.final_secondary));
                Leaderboardquizbot.setTextColor(getResources().getColor(R.color.final_secondary));
                Leaderboardfanbattlebot.setTextColor(getResources().getColor(R.color.final_secondary));
                Leaderboardfreefirebot.setTextColor(getResources().getColor(R.color.final_secondary));
            }
        });


    }

    private void fetchdata(String gamename) {
        String url = APICallsOkHttp.urlbuilderforhttpwithquery("https://gamernation.w3api.net/user/leaderboard", "filter", gamename);
        APICallsOkHttp.okhttpmaster().newCall(APICallsOkHttp.requestbuildwithauth(url, usrtoken)).enqueue(new Callback() {
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
                            JSONObject responsez = new JSONObject(myResponse);
                            setTopPositiondata(responsez);
                            adapterLeaderboard = new AdapterLeaderboard(responsez);
                            leaderboardrecyclerview.setAdapter(adapterLeaderboard);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    private void setTopPositiondata(JSONObject responsez) throws JSONException {

        JSONArray responses = responsez.getJSONArray("Leaderboard");
        JSONObject object1 = responses.getJSONObject(0);
        JSONObject user1 = object1.getJSONObject("user");
        String name1 = user1.getString("name");
        String dp1 = user1.getString("picture");
        String woncoins1 = object1.getString("won");
        leaderboard1strankname.setText(name1);
        leaderboard1strankcoinswon.setText(woncoins1 + "Coins");
        Picasso.get()
                .load(dp1)
                .placeholder(R.drawable.placeholder)
                .fit()
                .error(R.drawable.dperror)
                .centerCrop()
                .into(leaderboard1strankdp);

        JSONObject object2 = responses.getJSONObject(1);
        JSONObject user2 = object2.getJSONObject("user");
        String name2 = user2.getString("name");
        String dp2 = user2.getString("picture");
        String woncoins2 = object2.getString("won");
        leaderboard2ndrankname.setText(name2);
        leaderboard2ndrankcoinswon.setText(woncoins2 + "Coins");
        Picasso.get()
                .load(dp2)
                .placeholder(R.drawable.placeholder)
                .fit()
                .error(R.drawable.dperror)
                .centerCrop()
                .into(leaderboard2ndrankdp);

        JSONObject object3 = responses.getJSONObject(2);
        JSONObject user3 = object3.getJSONObject("user");
        String name3 = user3.getString("name");
        String dp3 = user3.getString("picture");
        String woncoins3 = object3.getString("won");
        leaderboard3rdrankname.setText(name3);
        leaderboard3rdrankcoinswon.setText(woncoins3 + "Coins");
        Picasso.get()
                .load(dp3)
                .placeholder(R.drawable.placeholder)
                .fit()
                .error(R.drawable.dperror)
                .centerCrop()
                .into(leaderboard3rdrankdp);


    }


}



/*
        retrofit2.Call<JSONObject> call = APICallsRetrofit.getuserdataleaderboard().fetchleaderboardusers(Constants.leaderboardfiltergame+"FREE_FIRE", Constants.AuthBearer + usrtoken);
        call.enqueue(new retrofit2.Callback<JSONObject>() {
            @Override
            public void onResponse(retrofit2.Call<JSONObject> call, retrofit2.Response<JSONObject> response) {
                String myResponse =response.body().toString();
                JSONObject responselist = null;
                try {
                    responselist = new JSONObject(myResponse);
                    JSONArray responses = responselist.getJSONArray("Leaderboard");


                for (int r = 0; r < responses.length(); r++) {
                    JSONObject object = responses.getJSONObject(r);


                    String woncoins=object.getString("won");;
                    CommonMethods.LOGthesite(Constants.LOG, woncoins);

                    JSONObject user=object.getJSONObject("user");
                    String name=user.getString("name");
                    String dp=user.getString("picture");
                    CommonMethods.LOGthesite(Constants.LOG, name);
                    CommonMethods.LOGthesite(Constants.LOG, dp);
                }
                } catch (JSONException e) {
                        e.printStackTrace();
                    }

            }

            @Override
            public void onFailure(retrofit2.Call<JSONObject> call, Throwable t) {

            }
        });*/