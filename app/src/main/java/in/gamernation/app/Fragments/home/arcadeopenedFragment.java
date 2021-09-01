package in.gamernation.app.Fragments.home;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import in.gamernation.app.APICalls.APICallsOkHttp;
import in.gamernation.app.Activities.HomeActivity;
import in.gamernation.app.Fragments.myleagues.myleaguesFragment;
import in.gamernation.app.Fragments.myleagues.myleaguesviewteamFragment;
import in.gamernation.app.R;
import in.gamernation.app.Utils.CommonMethods;
import in.gamernation.app.Utils.Constants;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class arcadeopenedFragment extends Fragment {
    String league_pic;
    private ImageView arcadeopenedtopimg;
    private TextView arcadeopenedgamename, arcadeopenedentryfee, arcadeopenedtotalparticipants, arcadeopenedprizecoins, arcadeopenedkillcoins, arcadeopenedgamemode, arcadeopenedbonuscoins,
            arcadeopenedmap, arcadeopeneddate, arcadeopenedbottomparticiapantstext, arcadeopenedbottomjointext;
    private AppCompatButton arcadeopenedprizepoolbreakup, arcadeopenedfullprizepool, joindialoggamecancel, joindialoggamejoin;
    private Context thiscontext;
    private EditText joindialoggamename, joindialoggameId;
    private String thumb, id, name, entrycoins, prizescoins, gametype, killcoins, filled, totalparticipants, map, startdate, bonus, joingamename, joingameId, myleaguedata, myleaguecateg;
    private LinearLayout arcadeopenedbottomlinearlayout, arcadeopenedmyleaguesopened, arcadeopenedcreatetam, arcadeopenedjoinbot, arcadeopenedshowparticipants, myleagueshowmyteam;

    //jointeamdialog
    private EditText jointeamdialoggamename, jointeamdialoggameId, jointeamdialogteamid;
    private AppCompatButton jointeamdialogcancelbot, jointeamdialogsubmitbot;

    //createteamresponsedetails
    private TextView dialogitemcreateteammsgresponse, dialogitemcreateteamname, dialogitemcreateteammteamcode;

    //createteamdialog
    private EditText createteamdialoggamename, createteamdialoggameId, createteamdialogteamname;
    private AppCompatButton createteamdialogcancelbot, createteamdialogjoinbot;
    private TextView createteamdialogpayablecoin;

    //Walletdialog
    private String walletbalforjoingame;
    private TextView arcadejoinwalletentrycoins, arcadejoinwalletemainbal, arcadejoinwalletentdepositpluswin,
            arcadejoinwalletbonus, arcadejoinwallettotalcoins, dialogtotalwalletbalbrac, dialogbonusbrac,
            dialogdepositwinbrac, dialogwalletresultmessage;
    private AppCompatButton arcadejoinwalletcancelbot, arcadejoinwalletrechargebot;
    private String depositpluswin, usrtoken;
    private String totalmoneytoplay;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment

        View root = inflater.inflate(R.layout.fragment_arcadeopened, container, false);

        initscreen();
        initviews(root);
        viewparticipants();
        showleagues();
        if (myleaguedata.equals("null")) {
            if (gametype.equals("SOLO")) {
                solojoingamedialogclicked();
            } else {
                otherjointeamdialogclicked();
                createteamdialogclicked();
            }
        } else {
            if (myleaguecateg.equals("SOLO")) {
                changeviewsformyleaguedata();
            } else {
                changedataformyleagueteam();
            }
        }
        return root;
    }


    private void changeviewsformyleaguedata() {
        //TODO change views for my leagues fragment solo
        arcadeopenedbottomlinearlayout.setWeightSum(1);
        arcadeopenedmyleaguesopened.setVisibility(View.GONE);
        arcadeopenedbottomparticiapantstext.setText("View Participants");
    }

    private void changedataformyleagueteam() {
        //TODO change views for my leagues fragment others
        changeviewsformyleaguedata();
        arcadeopenedbottomlinearlayout.setWeightSum(2);
        arcadeopenedbottomparticiapantstext.setText("View Teams");
        myleagueshowmyteam.setVisibility(View.VISIBLE);
        viewmyteamformyleague();
    }

    private void viewmyteamformyleague() {
        myleagueshowmyteam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().
                        replace(R.id.fragmentContainerView, new myleaguesviewteamFragment()).addToBackStack(null).commit();

            }
        });
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
        SharedPreferences sharedPreferences = thiscontext.getSharedPreferences(Constants.LOGINPREFS, Context.MODE_PRIVATE);
        usrtoken = sharedPreferences.getString(Constants.TOKENUSINGPREFS, "No data found!!!");
    }

    private void initviews(View root) {
        arcadeopenedgamename = root.findViewById(R.id.arcadeopenedgamename);
        arcadeopenedentryfee = root.findViewById(R.id.arcadeopenedentryfee);
        arcadeopenedtotalparticipants = root.findViewById(R.id.arcadeopenedtotalparticipants);
        arcadeopenedprizecoins = root.findViewById(R.id.arcadeopenedprizecoins);
        arcadeopenedkillcoins = root.findViewById(R.id.arcadeopenedkillcoins);
        arcadeopenedgamemode = root.findViewById(R.id.arcadeopenedgamemode);
        arcadeopenedbonuscoins = root.findViewById(R.id.arcadeopenedbonuscoins);
        arcadeopenedmap = root.findViewById(R.id.arcadeopenedmap);
        arcadeopenedprizepoolbreakup = root.findViewById(R.id.arcadeopenedprizepoolbreakup);
        arcadeopenedfullprizepool = root.findViewById(R.id.arcadeopenedfullprizepool);
        arcadeopeneddate = root.findViewById(R.id.arcadeopeneddate);
        arcadeopenedtopimg = root.findViewById(R.id.arcadeopenedtopimg);
        arcadeopenedjoinbot = root.findViewById(R.id.arcadeopenedjoinbot);
        arcadeopenedshowparticipants = root.findViewById(R.id.arcadeopenedshowparticipants);
        arcadeopenedbottomlinearlayout = root.findViewById(R.id.arcadeopenedbottomlinearlayout);
        arcadeopenedcreatetam = root.findViewById(R.id.arcadeopenedcreatetam);
        arcadeopenedbottomparticiapantstext = root.findViewById(R.id.arcadeopenedbottomparticiapantstext);
        arcadeopenedbottomjointext = root.findViewById(R.id.arcadeopenedbottomjointext);
        arcadeopenedmyleaguesopened = root.findViewById(R.id.arcadeopenedmyleaguesopened);
        myleagueshowmyteam = root.findViewById(R.id.myleagueshowmyteam);


        fetchdatafromsharedpref();

    }

    private void fetchdatafromsharedpref() {
        SharedPreferences preferences = thiscontext.getSharedPreferences(Constants.ARCADEGAMEPREF, Context.MODE_PRIVATE);
        id = preferences.getString(Constants.arcadeopenedid, "no data found!!!");
        name = preferences.getString(Constants.arcadeopenedname, "no data found!!!");
        thumb = preferences.getString(Constants.arcadeopenedthumb, "no data found!!!");
        entrycoins = preferences.getString(Constants.arcadeopenedentrycoins, "no data found!!!");
        prizescoins = preferences.getString(Constants.arcadeopenedprizescoins, "no data found!!!");
        killcoins = preferences.getString(Constants.arcadeopenedkillcoins, "no data found!!!");
        map = preferences.getString(Constants.arcadeopenedmap, "no data found!!!");
        league_pic = preferences.getString(Constants.arcadeopenedleaguepic, "no data found!!!");
        startdate = preferences.getString(Constants.arcadeopenedstartdate, "no data found!!!");
        filled = preferences.getString(Constants.arcadeopenedfilled, "no data found!!!");
        totalparticipants = preferences.getString(Constants.arcadeopenedtotalparticipants, "no data found!!!");
        bonus = preferences.getString(Constants.arcadeopenedbonus, "no data found!!!");
        myleaguedata = preferences.getString(Constants.myleaguedatafetched, "null");
        myleaguecateg = preferences.getString(Constants.myleaguecateg, "null");

        changedatatforduoandsquad();
        setdatatoviews();


    }

    private void changedatatforduoandsquad() {
        SharedPreferences sharedPreferences = thiscontext.getSharedPreferences(Constants.ARCADEGAMEPREF, Context.MODE_PRIVATE);
        gametype = sharedPreferences.getString(Constants.arcadeopenedgametype, "SOLO");
        if (!gametype.equals("SOLO")) {
            //TODO change screen for botoom layout and join team and create team
            arcadeopenedbottomlinearlayout.setWeightSum(4);
            arcadeopenedcreatetam.setVisibility(View.VISIBLE);
            arcadeopenedbottomparticiapantstext.setText("Show Teams");
            arcadeopenedbottomjointext.setText("Join Team");

        }
    }

    private void setdatatoviews() {
        arcadeopenedgamename.setText(name);
        arcadeopenedentryfee.setText(entrycoins + " Coins");
        arcadeopenedtotalparticipants.setText(totalparticipants);
        arcadeopenedprizecoins.setText(prizescoins + " Coins");
        arcadeopenedkillcoins.setText(killcoins + " Coins per kill");
        arcadeopenedgamemode.setText("TPP/FPP");
        arcadeopenedbonuscoins.setText("");
        arcadeopenedmap.setText(map);
        arcadeopeneddate.setText(startdate);
        arcadeopenedbonuscoins.setText(bonus);
        Picasso.get()
                .load(league_pic)
                .placeholder(R.drawable.placeholder)
                .fit()
                .error(R.drawable.dperror)
                .into(arcadeopenedtopimg);
    }

    private void viewparticipants() {
        arcadeopenedshowparticipants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainerView, new arcadegameshowparticipantsFragment()).addToBackStack(null).commit();
            }
        });


    }

    private void showleagues() {
        arcadeopenedmyleaguesopened.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainerView, new myleaguesFragment()).addToBackStack(null).commit();
            }
        });
    }


    private void solojoingamedialogclicked() {
        arcadeopenedjoinbot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder joinalertdialog = new AlertDialog.Builder(thiscontext);
                View joindialog = LayoutInflater.from(thiscontext).inflate(R.layout.dialogarcadejoingames, null);
                joindialoggamename = joindialog.findViewById(R.id.joindialoggamename);
                joindialoggameId = joindialog.findViewById(R.id.joindialoggameId);
                joindialoggamecancel = joindialog.findViewById(R.id.joindialoggamecancel);
                joindialoggamejoin = joindialog.findViewById(R.id.joindialoggamejoin);
                joinalertdialog.setView(joindialog);
                final AlertDialog d = joinalertdialog.show();

                joindialoggamejoin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        joingamename = joindialoggamename.getText().toString();
                        joingameId = joindialoggameId.getText().toString();
                        if (joingamename.isEmpty() || joingameId.isEmpty()) {
                            Toast.makeText(thiscontext, "Fill Username and Id to continue", Toast.LENGTH_SHORT).show();
                        } else {
                            //TODO join post request here
                            joingameforsolo(id);
                            d.dismiss();
                        }
                    }
                });
                joindialoggamecancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        d.dismiss();
                    }
                });


            }
        });

    }

    private void joingameforsolo(String id) {
        String url = Constants.w3devbaseurl + "games/join/" + id;
        APICallsOkHttp.okhttpmaster().newCall(
                APICallsOkHttp.requestwithpost(APICallsOkHttp.urlbuilderforhttp(url),
                        usrtoken,
                        APICallsOkHttp.buildrequestbodyforusernameandpassword(name, id))
        ).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                CommonMethods.DisplayShortTOAST(thiscontext, e.getMessage());
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                final String myResponse = response.body().string();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            JSONObject responsez = new JSONObject(myResponse);
                            String code = String.valueOf(response.code());
                            if (responsez.has("error")) {
                                if (code.equals("409")) {
                                    String erroralreadyjoined = responsez.optString("error");
                                    CommonMethods.DisplayLongTOAST(thiscontext, erroralreadyjoined);
                                    CommonMethods.LOGthesite(Constants.LOG, erroralreadyjoined);
                                } else if (code.equals("402")) {
                                    JSONObject object = responsez.optJSONObject("error");
                                    String entry_fee = object.optString("entry_fee");
                                    String total_balancebrac = object.optString("total_balance");
                                    String deposit_winningbrac = object.optString("deposit_winning");
                                    String bonusbrac = object.optString("bonus");
                                    String bonus_use = object.optString("bonus_use");
                                    String deposit_winning_use = object.optString("deposit_winning_use");
                                    String resultmsg = object.optString("result");
                                    walletdialog(entry_fee, entry_fee, total_balancebrac,
                                            deposit_winning_use, deposit_winningbrac, bonus_use, bonusbrac, entry_fee, resultmsg);
                                } else {
                                    String erroralreadyjoined = responsez.optString("error");
                                    CommonMethods.DisplayLongTOAST(thiscontext, erroralreadyjoined);
                                }
                            } else {
                                String trueobj = responsez.optString("message");
                                CommonMethods.DisplayLongTOAST(thiscontext, trueobj);

                                //TODO createa lil dialog
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    private void walletdialog(String entrycoins,
                              String walletbalforjoingame,
                              String totalbalbrac,
                              String depositpluswin,
                              String depositwinbrac,
                              String bonus,
                              String bonusbrac,
                              String totalmoneytoplay,
                              String resultmessage) {
        AlertDialog.Builder walletalert = new AlertDialog.Builder(thiscontext);
        View walletdialogview = LayoutInflater.from(thiscontext).inflate(R.layout.dialogarcadejoinwalletoptions, null);
        arcadejoinwalletentrycoins = walletdialogview.findViewById(R.id.arcadejoinwalletentrycoins);
        arcadejoinwalletemainbal = walletdialogview.findViewById(R.id.arcadejoinwalletemainbal);
        arcadejoinwalletentdepositpluswin = walletdialogview.findViewById(R.id.arcadejoinwalletentdepositpluswin);
        arcadejoinwalletbonus = walletdialogview.findViewById(R.id.arcadejoinwalletbonus);
        arcadejoinwallettotalcoins = walletdialogview.findViewById(R.id.arcadejoinwallettotalcoins);
        arcadejoinwalletcancelbot = walletdialogview.findViewById(R.id.arcadejoinwalletcancelbot);
        arcadejoinwalletrechargebot = walletdialogview.findViewById(R.id.arcadejoinwalletrechargebot);

        dialogtotalwalletbalbrac = walletdialogview.findViewById(R.id.dialogtotalwalletbalbrac);
        dialogdepositwinbrac = walletdialogview.findViewById(R.id.dialogdepositwinbrac);
        dialogbonusbrac = walletdialogview.findViewById(R.id.dialogbonusbrac);
        dialogwalletresultmessage = walletdialogview.findViewById(R.id.dialogwalletresultmessage);

        walletalert.setView(walletdialogview);

        dialogwalletresultmessage.setText(resultmessage);
        dialogtotalwalletbalbrac.setText("Tootal Wallet Balance (" + totalbalbrac + ")");
        dialogdepositwinbrac.setText("Deposit+Winning (" + depositwinbrac + ")");
        dialogbonusbrac.setText("Bonus (" + bonusbrac + ")");
        arcadejoinwalletentrycoins.setText(entrycoins);
        arcadejoinwalletemainbal.setText(walletbalforjoingame);
        arcadejoinwalletentdepositpluswin.setText(depositpluswin);
        arcadejoinwalletbonus.setText(bonus);
        arcadejoinwallettotalcoins.setText(totalmoneytoplay);
        final AlertDialog walletdialog = walletalert.show();

        arcadejoinwalletcancelbot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                walletdialog.dismiss();
            }
        });

        arcadejoinwalletrechargebot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO doo call wallet recharge here webview


            }
        });

    }

    private void otherjointeamdialogclicked() {
        arcadeopenedjoinbot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(thiscontext);
                View layout = LayoutInflater.from(thiscontext).inflate(R.layout.dialogarcadejointeam, null);
                jointeamdialoggamename = layout.findViewById(R.id.jointeamdialoggamename);
                jointeamdialoggameId = layout.findViewById(R.id.jointeamdialoggameId);
                jointeamdialogteamid = layout.findViewById(R.id.jointeamdialogteamid);
                jointeamdialogcancelbot = layout.findViewById(R.id.jointeamdialogcancelbot);
                jointeamdialogsubmitbot = layout.findViewById(R.id.jointeamdialogsubmitbot);
                builder.setView(layout);
                final AlertDialog i = builder.show();
                jointeamdialogsubmitbot.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String username = jointeamdialoggamename.getText().toString();
                        String userId = jointeamdialoggameId.getText().toString();
                        String teamid = jointeamdialogteamid.getText().toString();
                        if (username.isEmpty() || userId.isEmpty() || teamid.isEmpty()) {
                            Toast.makeText(thiscontext, "Fill Username, Id and Team id to continue", Toast.LENGTH_SHORT).show();
                        } else {
                            jointeamforothers(id, username, userId, teamid);
                            i.dismiss();
                        }
                    }
                });
                jointeamdialogcancelbot.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        i.dismiss();
                    }
                });

            }
        });


    }

    private void jointeamforothers(String id, String username, String userId, String teamid) {
        String url = Constants.w3devbaseurl + "games/join/team/" + id;
        CommonMethods.LOGthesite(Constants.LOG, url);
        APICallsOkHttp.okhttpmaster().newCall(
                APICallsOkHttp.requestwithpost(APICallsOkHttp.urlbuilderforhttp(url),
                        usrtoken,
                        APICallsOkHttp.buildrequestbodyforusernameandpasswordteamid(username, userId, teamid))
        ).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                CommonMethods.DisplayShortTOAST(thiscontext, e.getMessage());
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                final String myResponse = response.body().string();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            JSONObject responsez = new JSONObject(myResponse);
                            if (responsez.has("error")) {
                                String erroralreadyjoined = responsez.optString("error");
                                CommonMethods.DisplayLongTOAST(thiscontext, erroralreadyjoined);
                                CommonMethods.LOGthesite(Constants.LOG, erroralreadyjoined);
                            } else {
                                String trueobj = responsez.optString("message");
                                CommonMethods.DisplayLongTOAST(thiscontext, trueobj);
                            }
                        /*
                            if (response.code() == 409) {
                                CommonMethods.LOGthesite(Constants.LOG, responsez.getString("error"));
                                CommonMethods.LOGthesite(Constants.LOG, String.valueOf(response.code()));
                            } else {
                                CommonMethods.LOGthesite(Constants.LOG, responsez.getString("message"));
                                CommonMethods.LOGthesite(Constants.LOG, String.valueOf(response.code()));
                            }*/
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    //TODO logistics


    private void createteamdialogclicked() {
        arcadeopenedcreatetam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(thiscontext);
                View layout = LayoutInflater.from(thiscontext).inflate(R.layout.dialogarcadecreateteam, null);
                createteamdialoggamename = layout.findViewById(R.id.createteamdialoggamename);
                createteamdialoggameId = layout.findViewById(R.id.createteamdialoggameId);
                createteamdialogteamname = layout.findViewById(R.id.createteamdialogteamname);
                createteamdialogcancelbot = layout.findViewById(R.id.createteamdialogcancelbot);
                createteamdialogjoinbot = layout.findViewById(R.id.createteamdialogjoinbot);
                createteamdialogpayablecoin = layout.findViewById(R.id.createteamdialogpayablecoin);
                createteamdialogpayablecoin.setText(entrycoins);
                builder.setView(layout);
                final AlertDialog d = builder.show();
                createteamdialogjoinbot.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String name = createteamdialoggamename.getText().toString();
                        String id = createteamdialoggameId.getText().toString();
                        String teamname = createteamdialogteamname.getText().toString();
                        if (name.isEmpty() || id.isEmpty() || teamname.isEmpty()) {
                            CommonMethods.DisplayLongTOAST(thiscontext, "Fill name ,id and your team name to continue");
                        } else {
                            createteamreqfetch(name, id, teamname, d);
                            d.dismiss();
                        }
                    }
                });
                createteamdialogcancelbot.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        d.dismiss();
                    }
                });
            }
        });
    }

    private void createteamreqfetch(String name, String userid, String teamname, AlertDialog d) {
        String url = Constants.w3devbaseurl + "games/team/" + id;
        CommonMethods.LOGthesite(Constants.LOG, url);
        APICallsOkHttp.okhttpmaster().newCall(
                APICallsOkHttp.requestwithpost(APICallsOkHttp.urlbuilderforhttp(url),
                        usrtoken,
                        APICallsOkHttp.buildrequestbodyforusernameandpasswordteamname(name, userid, teamname))
        ).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                CommonMethods.DisplayShortTOAST(thiscontext, e.getMessage());
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                final String myResponse = response.body().string();
                CommonMethods.LOGthesite(Constants.LOG, myResponse);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            JSONObject responsez = new JSONObject(myResponse);
                            String code = String.valueOf(response.code());
                            CommonMethods.LOGthesite(Constants.LOG, code);
                            if (responsez.has("error")) {
                                if (code.equals("409")) {
                                    String erroralreadyjoined = responsez.optString("error");
                                    CommonMethods.DisplayLongTOAST(thiscontext, erroralreadyjoined);
                                    CommonMethods.LOGthesite(Constants.LOG, erroralreadyjoined);
                                } else if (code.equals("402")) {
                                    JSONObject object = responsez.optJSONObject("error");
                                    String entry_fee = object.optString("entry_fee");
                                    String total_balancebrac = object.optString("total_balance");
                                    String deposit_winningbrac = object.optString("deposit_winning");
                                    String bonusbrac = object.optString("bonus");
                                    String bonus_use = object.optString("bonus_use");
                                    String deposit_winning_use = object.optString("deposit_winning_use");
                                    String resultmsg = object.optString("result");
                                    walletdialog(entry_fee, entry_fee, total_balancebrac,
                                            deposit_winning_use, deposit_winningbrac, bonus_use, bonusbrac, entry_fee, resultmsg);
                                } else {
                                    String erroralreadyjoined = responsez.optString("error");
                                    CommonMethods.DisplayLongTOAST(thiscontext, erroralreadyjoined);
                                }
                            } else {
/*
                                CommonMethods.LOGthesite(Constants.LOG, responsez.getString("message"));
*/
                                String msg = responsez.optString("message");
                                String teamnamegot = responsez.optJSONObject("your_details").optString("team_name");
                                String teamidtoshare = responsez.optJSONObject("your_details").optString("team_id");
/*                                CommonMethods.LOGthesite(Constants.LOG, String.valueOf(response.code()));
                                CommonMethods.LOGthesite(Constants.LOG, teamnamegot);
                                CommonMethods.LOGthesite(Constants.LOG, teamidtoshare);*/

                                AlertDialog.Builder builder = new AlertDialog.Builder(thiscontext);
                                View view = LayoutInflater.from(thiscontext).inflate(R.layout.dialogarcadecreateteamresponse, null);
                                dialogitemcreateteammsgresponse = view.findViewById(R.id.dialogitemcreateteammsgresponse);
                                dialogitemcreateteamname = view.findViewById(R.id.dialogitemcreateteamname);
                                dialogitemcreateteammteamcode = view.findViewById(R.id.dialogitemcreateteammteamcode);

                                dialogitemcreateteammsgresponse.setText(msg);
                                dialogitemcreateteamname.setText(teamnamegot);
                                dialogitemcreateteammteamcode.setText(teamidtoshare);

                                builder.setView(view);
                                final AlertDialog dialog = builder.show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }
}