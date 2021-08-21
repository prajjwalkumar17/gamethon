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
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import in.gamernation.app.APICalls.APICalls;
import in.gamernation.app.APIResponses.ArcadeResponse;
import in.gamernation.app.Activities.HomeActivity;
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
        arcadegamerecyclerview = root.findViewById(R.id.arcadegamerecyclerview);
        arcadesolobot = root.findViewById(R.id.arcadesolobot);
        arcadeduobot = root.findViewById(R.id.arcadeduobot);
        arcadesquadbot = root.findViewById(R.id.arcadesquadbot);
    }

    private void allmethods() {
        SharedPreferences sharedPreferences = thiscontext.getSharedPreferences(Constants.LOGINPREFS, Context.MODE_PRIVATE);
        usrtoken = sharedPreferences.getString(Constants.TOKENUSINGPREFS, "No data found!!!");
        SharedPreferences sharedPreferences1 = thiscontext.getSharedPreferences(Constants.ARCADEGAMEPREF, Context.MODE_PRIVATE);
        arcadegameID = sharedPreferences1.getString(Constants.ARCADEGAMEIDPREF, "No data found!!!");
        CommonMethods.LOGthesite(Constants.LOG, arcadegameID);
        fetchallarcadegames();
    }

    private void fetchallarcadegames() {
        gamemode = "SOLO";
        changedatasetonclicks();
        Call<ArcadeResponse> responseCall = APICalls.getarcadegamerooms().FetchArcadeRooms(arcadegameID, Constants.arcadefiltergame + gamemode, Constants.AuthBearer + usrtoken);
        responseCall.enqueue(new Callback<ArcadeResponse>() {
            @Override
            public void onResponse(@NotNull Call<ArcadeResponse> call, @NotNull Response<ArcadeResponse> response) {
                if (response.isSuccessful()) {
                    CommonMethods.DisplayLongTOAST(thiscontext, "Arcade rooms received sucesssfully");
                    assert response.body() != null;
                    List<ArcadeResponse.League> leagueList = response.body().getLeagues();

//                  CommonMethods.LOGthesite(Constants.LOG,String.valueOf(response.body().getLeagues().get(0).getMap()));
                }
            }

            @Override
            public void onFailure(@NotNull Call<ArcadeResponse> call, Throwable t) {

            }
        });
    }

    private String changedatasetonclicks() {
        arcadesolobot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gamemode = "SOLO";
            }
        });
        arcadeduobot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gamemode = "DUO";
            }
        });
        arcadesquadbot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gamemode = "SQUAD";
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
    public void onItemClick(int position) {

    }
}