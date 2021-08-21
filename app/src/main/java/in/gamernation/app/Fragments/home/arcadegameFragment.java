package in.gamernation.app.Fragments.home;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import in.gamernation.app.APICalls.APICalls;
import in.gamernation.app.APIResponses.ArcadeResponse;
import in.gamernation.app.Activities.HomeActivity;
import in.gamernation.app.Adapters.AdapterHomearcade;
import in.gamernation.app.Decoration.DecorationHomeRecyclerGamesItem;
import in.gamernation.app.R;
import in.gamernation.app.RecyclerClickInterfaces.ClickArcadeGameItem;
import in.gamernation.app.Utils.CommonMethods;
import in.gamernation.app.Utils.Constants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class arcadegameFragment extends Fragment implements ClickArcadeGameItem {
    private RecyclerView arcadegamerecyclerview;
    private String usrtoken, arcadegameID;
    private AppCompatButton arcadesolobot, arcadeduobot, arcadesquadbot;
    private Context thiscontext;
    private String gamemode;
    private List<ArcadeResponse.League> leagueList;
    private String id, thumb, name, entrycoins, prizescoins, killcoins, filled, totalparticipants, map, startdate, league_pic;
    private Integer bonus;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_arcadegame, container, false);
        initlayout();
        initviews(root);
        allmethods();
        return root;
    }


    private void initlayout() {
        ((HomeActivity) getActivity()).setToolbarInvisible();
        ((HomeActivity) getActivity()).setDrawerLocked();
        ((HomeActivity) getActivity()).setbotInvisible();
    }

    private void initviews(View root) {
        arcadesolobot = root.findViewById(R.id.arcadesolobot);
        arcadeduobot = root.findViewById(R.id.arcadeduobot);
        arcadesquadbot = root.findViewById(R.id.arcadesquadbot);
        arcadegamerecyclerview = root.findViewById(R.id.arcadegamerecyclerview);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(thiscontext, 1, GridLayoutManager.VERTICAL, false);
        arcadegamerecyclerview.setLayoutManager(gridLayoutManager);
        arcadegamerecyclerview.addItemDecoration(new DecorationHomeRecyclerGamesItem(thiscontext, R.dimen.dp_2));
    }

    private void allmethods() {
        SharedPreferences sharedPreferences = thiscontext.getSharedPreferences(Constants.LOGINPREFS, Context.MODE_PRIVATE);
        usrtoken = sharedPreferences.getString(Constants.TOKENUSINGPREFS, "No data found!!!");
        SharedPreferences sharedPreferences1 = thiscontext.getSharedPreferences(Constants.ARCADEGAMEPREF, Context.MODE_PRIVATE);
        arcadegameID = sharedPreferences1.getString(Constants.ARCADEGAMEIDPREF, "No data found!!!");
        fetchallarcadegames("SOLO");
        changedatasetonclicks();
    }

    private void fetchallarcadegames(String gamemode) {
        Call<ArcadeResponse> responseCall = APICalls.getarcadegamerooms().FetchArcadeRooms(arcadegameID, Constants.arcadefiltergame + gamemode, Constants.AuthBearer + usrtoken);
        responseCall.enqueue(new Callback<ArcadeResponse>() {
            @Override
            public void onResponse(@NotNull Call<ArcadeResponse> call, @NotNull Response<ArcadeResponse> response) {
                if (response.isSuccessful()) {
                    CommonMethods.DisplayLongTOAST(thiscontext, "Arcade rooms received sucesssfully");
                    assert response.body() != null;
                    leagueList = response.body().getLeagues();
                    AdapterHomearcade adapterHomearcade = new AdapterHomearcade(leagueList, arcadegameFragment.this);
                    arcadegamerecyclerview.setAdapter(adapterHomearcade);
                }
            }

            @Override
            public void onFailure(@NotNull Call<ArcadeResponse> call, Throwable t) {
                CommonMethods.DisplayLongTOAST(thiscontext, "Error occured in fetch" + t.getMessage());

            }
        });
    }

    private String changedatasetonclicks() {
        arcadesolobot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gamemode = "SOLO";
                fetchallarcadegames(gamemode);
            }
        });
        arcadeduobot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gamemode = "DUO";
                fetchallarcadegames(gamemode);
            }
        });
        arcadesquadbot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gamemode = "SQUAD";
                fetchallarcadegames(gamemode);
            }
        });
        return gamemode;
    }

    @Override
    public void onAttach(@NonNull @NotNull Context context) {
        super.onAttach(context);
        thiscontext = context;
    }

    @Override
    public void onviewItemClick(int position) {
        id = leagueList.get(position).getId();
        thumb = leagueList.get(position).getThumb();
        name = leagueList.get(position).getName();
        entrycoins = leagueList.get(position).getEntry().toString();
        prizescoins = leagueList.get(position).getPrizes().toString();
        killcoins = leagueList.get(position).getKillCoins().toString();
        filled = leagueList.get(position).getFilled().toString();
        totalparticipants = leagueList.get(position).getTotalParticipant().toString();
        map = leagueList.get(position).getMap();
        startdate = leagueList.get(position).getStartDate();
        league_pic = leagueList.get(position).getLeague_pic();
        bonus = leagueList.get(position).getBonus();
        savedatatosharedpref();

        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainerView, new arcadeopenedFragment()).addToBackStack(null).commit();
    }

    private void savedatatosharedpref() {

        SharedPreferences preferences = thiscontext.getSharedPreferences(Constants.ARCADEGAMEPREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(Constants.arcadeopenedid, id).apply();
        editor.putString(Constants.arcadeopenedthumb, thumb).apply();
        editor.putString(Constants.arcadeopenedname, name).apply();
        editor.putString(Constants.arcadeopenedentrycoins, entrycoins).apply();
        editor.putString(Constants.arcadeopenedprizescoins, prizescoins).apply();
        editor.putString(Constants.arcadeopenedkillcoins, killcoins).apply();
        editor.putString(Constants.arcadeopenedfilled, filled).apply();
        editor.putString(Constants.arcadeopenedtotalparticipants, totalparticipants).apply();
        editor.putString(Constants.arcadeopenedmap, map).apply();
        editor.putString(Constants.arcadeopenedstartdate, startdate).apply();
        editor.putString(Constants.arcadeopenedleaguepic, league_pic).apply();
        editor.putString(Constants.arcadeopenedbonus, bonus.toString()).apply();

    }
}