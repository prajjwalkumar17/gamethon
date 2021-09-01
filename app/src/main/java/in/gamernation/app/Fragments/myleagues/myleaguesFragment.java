package in.gamernation.app.Fragments.myleagues;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import in.gamernation.app.APICalls.APICallsOkHttp;
import in.gamernation.app.Activities.HomeActivity;
import in.gamernation.app.Adapters.Adaptermyleagues;
import in.gamernation.app.Decoration.DecorationHomeRecyclerGamesItem;
import in.gamernation.app.Fragments.home.arcadeopenedFragment;
import in.gamernation.app.R;
import in.gamernation.app.RecyclerClickInterfaces.ClickMyleagueitem;
import in.gamernation.app.Utils.CommonMethods;
import in.gamernation.app.Utils.Constants;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class myleaguesFragment extends Fragment implements ClickMyleagueitem {
    private AppCompatButton myleagufreefirebot, myleagufreefireclashsquadbot,
            myleagubgmibot, myleagutdmbot, myleagulivechangebot, myleagulpastchangebot,
            myleagulupcomingchangebot;
    private RecyclerView myleagurecycler;
    private Context thiscontext;
    private String usrtoken, query, type, game;
    private String gametype, gamenamee;
    private String reaturnedgametype, returnedgamenamee;
    private SharedPreferences sharedPreferences;
    private Adaptermyleagues adaptermyleagues;
    private JSONObject object;
    private String id, leagueid, name, entry, prizes, kill_coins, start_date,
            total_participant, map, bonus, filled,
            game_type, league_pic, thumb, gameid, categ,
            prizepoolid, prizepoolstanding, priizepoolprize, roomId, password;

    //livedialog
    private TextView dilogmyleagueroomid, dilogmyleagueroompass;
    private AppCompatButton dilogmyleaguecancelbot, dilogmyleaguecopybot;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_myleagues, container, false);
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
        myleagufreefirebot = root.findViewById(R.id.myleagufreefirebot);
        myleagufreefireclashsquadbot = root.findViewById(R.id.myleagufreefireclashsquadbot);
        myleagubgmibot = root.findViewById(R.id.myleagubgmibot);
        myleagutdmbot = root.findViewById(R.id.myleagutdmbot);

        myleagulivechangebot = root.findViewById(R.id.myleagulivechangebot);
        myleagulupcomingchangebot = root.findViewById(R.id.myleagulupcomingchangebot);
        myleagulpastchangebot = root.findViewById(R.id.myleagulpastchangebot);


        myleagurecycler = root.findViewById(R.id.myleagurecycler);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(thiscontext, 1, GridLayoutManager.VERTICAL, false);
        myleagurecycler.setLayoutManager(gridLayoutManager);
        myleagurecycler.addItemDecoration(new DecorationHomeRecyclerGamesItem(thiscontext, R.dimen.dp_2));

        getbotnameofgame();
        getbotnameoftype();

    }

    private void getbotnameofgame() {
        getgamename("Free_Fire");
        gettypename("upcoming");

        fetchdata(reaturnedgametype, returnedgamenamee);
        myleagufreefirebot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gamenamee = "Free_Fire";
                getgamename(gamenamee);
                gettypename("upcoming");
                fetchdata(reaturnedgametype, returnedgamenamee);
            }
        });
        myleagufreefireclashsquadbot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gamenamee = "Free_Fire_Clash_Squad";
                getgamename(gamenamee);
                gettypename("upcoming");
                fetchdata(reaturnedgametype, returnedgamenamee);
            }
        });
        myleagubgmibot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gamenamee = "BGMI";
                getgamename(gamenamee);
                gettypename("upcoming");
                fetchdata(reaturnedgametype, returnedgamenamee);
            }
        });
        myleagutdmbot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gamenamee = "BGMI_Team_Death_Match";
                getgamename(gamenamee);
                gettypename("upcoming");
                fetchdata(reaturnedgametype, returnedgamenamee);
            }
        });
    }

    private void getbotnameoftype() {
        myleagulivechangebot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gametype = "live";
                gettypename(gametype);
                fetchdata(reaturnedgametype, returnedgamenamee);
            }
        });

        myleagulpastchangebot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gametype = "past";
                gettypename(gametype);
                fetchdata(reaturnedgametype, returnedgamenamee);
            }
        });

        myleagulupcomingchangebot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gametype = "upcoming";
                gettypename(gametype);
                fetchdata(reaturnedgametype, returnedgamenamee);
            }
        });
    }


    private String getgamename(String gamenamee) {
//        CommonMethods.LOGthesite(Constants.LOG,"The bot pressed is of "+gamenamee);
        returnedgamenamee = gamenamee;
        return gamenamee;
    }

    private String gettypename(String gametype) {
//        CommonMethods.LOGthesite(Constants.LOG,"The bot pressed is of "+gametype);
        reaturnedgametype = gametype;
        return gametype;
    }


    private void fetchdata(String gametype, String gamename) {
        type = "type=" + gametype;
        game = "&game=" + gamename;
        query = type + game;
        String url = APICallsOkHttp.urlbuilderforhttpwithquery("https://gamernation.w3api.net/user/my_leagues", "filter", query);
//        CommonMethods.LOGthesite(Constants.LOG,"The url is "+url);

        APICallsOkHttp.okhttpmaster().newCall(
                APICallsOkHttp.requestbuildwithauth(APICallsOkHttp.urlbuilderforhttp(url), usrtoken)).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                CommonMethods.DisplayLongTOAST(thiscontext, e.getMessage());
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                final String myresponse = response.body().string();
//                CommonMethods.LOGthesite(Constants.LOG, myresponse);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            object = new JSONObject(myresponse);
//                            CommonMethods.LOGthesite(Constants.LOG,object.toString());
//                            JSONArray Leagues = object.getJSONArray("Leagues");
//                            JSONObject leaguedetail = Leagues.getJSONObject(position);
//            String id = leaguedetail.getString("_id");
//
//                            JSONObject leagueobject = leaguedetail.getJSONObject("league");

//            String game_type = leagueobject.getString("game_type");

//            String leagueid = leagueobject.getString("_id");
//                            String name = leagueobject.getString("name");
//                            String entry = leagueobject.getString("entry");
//                            String prizes = leagueobject.getString("prizes");
//                            String kill_coins = leagueobject.getString("kill_coins");
//                            String start_date = leagueobject.getString("start_date");
//                            String total_participant = leagueobject.getString("total_participant");
//                            String map = leagueobject.getString("map");
//            String bonus = leagueobject.getString("bonus");
//                            String filled = leagueobject.getString("filled");


//                            JSONObject credsforgame = leagueobject.getJSONObject("setting");
//            String roomId=credsforgame.getString("roomId");
//            String password=credsforgame.getString("password");
//            CommonMethods.LOGthesite(Constants.LOG,roomId+"\n" +password);

//                            JSONObject gamedetail = leagueobject.getJSONObject("game");
//            String gameid = gamedetail.getString("_id");
//            String presentgamename = gamedetail.getString("name");
//            String category = gamedetail.getString("category");
//            String league_pic = gamedetail.getString("league_pic");
//                            String thumb = gamedetail.getString("thumb");


//        JSONArray prizepool=leagueobject.getJSONArray("prize_pool");
//        JSONObject prizeobj=prizepool.getJSONObject(position);
//        String prizepoolid=prizeobj.getString("_id");
//        String prizepoolstanding=prizeobj.getString("standing");
//        String priizepoolprize=prizeobj.getString("prize");

//                            CommonMethods.LOGthesite(Constants.LOG,game_type);
                            adaptermyleagues = new Adaptermyleagues(object, thiscontext, gametype, myleaguesFragment.this);
                            myleagurecycler.setAdapter(adaptermyleagues);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
                /*      CommonMethods.LOGthesite(Constants.LOG,
                            "\n"+id+"\n"+game_type
                                    +"\n"+entry
                                    +"\n"+kill_coins
                                    +"\n"+total_participant
                                    +"\n"+bonus
                                    +"\n"+presentgamename
                                    +"\n"+category
                                    +"\n"+prizepoolstanding
                                    +"\n"+priizepoolprize
                    );
*/
            }
        });
    }


    @Override
    public void onviewItemClick(int position) throws JSONException {
        JSONArray Leagues = object.optJSONArray("Leagues");
        JSONObject leaguedetail = Leagues.optJSONObject(position);
        id = leaguedetail.optString("_id");

        JSONObject leagueobject = leaguedetail.optJSONObject("league");

        game_type = leagueobject.optString("game_type");

        leagueid = leagueobject.optString("_id");
        name = leagueobject.optString("name");
        entry = leagueobject.optString("entry");
        prizes = leagueobject.optString("prizes");
        kill_coins = leagueobject.optString("kill_coins");
        start_date = leagueobject.optString("start_date");
        total_participant = leagueobject.optString("total_participant");
        map = leagueobject.optString("map");
        bonus = leagueobject.optString("bonus");
        filled = leagueobject.optString("filled");

        if (reaturnedgametype.equals("live")) {
            JSONObject credsforgame = leagueobject.optJSONObject("setting");
            roomId = credsforgame.optString("roomId");
            password = credsforgame.optString("password");
//            CommonMethods.LOGthesite(Constants.LOG, roomId + "\n" + password);
        }

        JSONObject gamedetail = leagueobject.optJSONObject("game");
        gameid = gamedetail.optString("_id");
        String presentgamename = gamedetail.optString("name");
        String category = gamedetail.optString("category");
        league_pic = gamedetail.optString("league_pic");
        thumb = gamedetail.optString("thumb");

        JSONArray prizepool = leagueobject.optJSONArray("prize_pool");
        JSONObject prizeobj = prizepool.optJSONObject(0);
        prizepoolid = prizeobj.optString("_id");
        prizepoolstanding = prizeobj.optString("standing");
        priizepoolprize = prizeobj.optString("prize");

//        CommonMethods.LOGthesite(Constants.LOG, "the game type is " + game_type);
//        CommonMethods.LOGthesite(Constants.LOG, "the game category is " + reaturnedgametype);

        if (reaturnedgametype.equals("upcoming")) {
            if (game_type.equals("SOLO")) {
                //TODO open the same arcade games fragment
                categ = "SOLO";
                changedatatoopenarcadeopenrooms();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainerView, new arcadeopenedFragment()).addToBackStack(null).commit();
            } else {
                //TODO open a alert dialog to show team details upcoming duo and squad
                categ = "Others";
                changedatatoopenarcadeopenrooms();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainerView, new arcadeopenedFragment()).addToBackStack(null).commit();
            }
        } else if (reaturnedgametype.equals("live")) {

            //TODO show a dialog to show game rooomid and password
            AlertDialog.Builder joinalertdialog = new AlertDialog.Builder(thiscontext);
            View joindialog = LayoutInflater.from(thiscontext).inflate(R.layout.dialogmyleagueliveromeidandpass, null);
            dilogmyleagueroomid = joindialog.findViewById(R.id.dilogmyleagueroomid);
            dilogmyleagueroompass = joindialog.findViewById(R.id.dilogmyleagueroompass);
            dilogmyleaguecancelbot = joindialog.findViewById(R.id.dilogmyleaguecancelbot);
            dilogmyleaguecopybot = joindialog.findViewById(R.id.dilogmyleaguecopybot);
            joinalertdialog.setView(joindialog);
            dilogmyleagueroomid.setText(roomId);
            dilogmyleagueroompass.setText(password);
            final AlertDialog d = joinalertdialog.show();
            dilogmyleaguecancelbot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    d.dismiss();
                }
            });
            dilogmyleaguecopybot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });

        } else {
            if (game_type.equals("SOLO")) {
                changedatatoopenarcadeopenrooms();
                //TODO show single player leaderboard
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, new myleaguesshowsololeaderboardFragment()).addToBackStack(null)
                        .commit();


            } else {
                //TODO show team leaderboard
                changedatatoopenarcadeopenrooms();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, new myleagueteamleaderboardFragment()).addToBackStack(null)
                        .commit();
            }
        }

    }

    private void changedatatoopenarcadeopenrooms() {
        SharedPreferences preferences = thiscontext.getSharedPreferences(Constants.ARCADEGAMEPREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(Constants.arcadeopenedid, leagueid).apply();
        editor.putString(Constants.arcadeopenedthumb, thumb).apply();
        editor.putString(Constants.arcadeopenedname, name).apply();
        editor.putString(Constants.arcadeopenedentrycoins, entry).apply();
        editor.putString(Constants.arcadeopenedprizescoins, prizes).apply();
        editor.putString(Constants.arcadeopenedkillcoins, kill_coins).apply();
        editor.putString(Constants.arcadeopenedfilled, filled).apply();
        editor.putString(Constants.arcadeopenedtotalparticipants, total_participant).apply();
        editor.putString(Constants.arcadeopenedmap, map).apply();
        editor.putString(Constants.arcadeopenedstartdate, start_date).apply();
        editor.putString(Constants.arcadeopenedleaguepic, league_pic).apply();
        editor.putString(Constants.arcadeopenedbonus, bonus).apply();
        editor.putString(Constants.arcadeopenedgametype, game_type).apply();
        editor.putString(Constants.myleaguedatafetched, "true").apply();
        editor.putString(Constants.myleaguecateg, categ).apply();
    }
}