package in.gamernation.app.Fragments.home;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import in.gamernation.app.APICalls.APICalls;
import in.gamernation.app.APIResponses.ArcadeSoloParticipantsResponse;
import in.gamernation.app.APIResponses.ArcadeViewTeamsResponse;
import in.gamernation.app.Activities.HomeActivity;
import in.gamernation.app.Adapters.AdapterArcadeViewTeams;
import in.gamernation.app.Adapters.AdapterHomeArcadesoloParticipants;
import in.gamernation.app.Decoration.DecorationHomeRecyclerGamesItem;
import in.gamernation.app.R;
import in.gamernation.app.Utils.CommonMethods;
import in.gamernation.app.Utils.Constants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class arcadegameshowparticipantsFragment extends Fragment {

    private RecyclerView arcadegamesshowparticipantsrecyclerview;
    private SharedPreferences sharedPreferences;
    private Context thiscontext;
    private String usrtoken, gameid;
    private List<ArcadeSoloParticipantsResponse.Participant> list;
    private List<ArcadeViewTeamsResponse.Team> responseList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_arcadegameshowparticipants, container, false);
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
        SharedPreferences sharedPreferences = thiscontext.getSharedPreferences(Constants.LOGINPREFS, Context.MODE_PRIVATE);
        usrtoken = sharedPreferences.getString(Constants.TOKENUSINGPREFS, "No data found!!!");
        arcadegamesshowparticipantsrecyclerview = root.findViewById(R.id.arcadegamesshowparticipantsrecyclerview);
        sharedPreferences = thiscontext.getSharedPreferences(Constants.ARCADEGAMEPREF, Context.MODE_PRIVATE);
        String gametype = sharedPreferences.getString(Constants.arcadeopenedgametype, "SOLO");
        gameid = sharedPreferences.getString(Constants.arcadeopenedid, "nodata found !!");
        GridLayoutManager gridLayoutManager = new GridLayoutManager(thiscontext, 1, GridLayoutManager.VERTICAL, false);
        arcadegamesshowparticipantsrecyclerview.setLayoutManager(gridLayoutManager);
        arcadegamesshowparticipantsrecyclerview.addItemDecoration(new DecorationHomeRecyclerGamesItem(thiscontext, R.dimen.dp_2));
        if (gametype.equals("SOLO")) {
            showparticipantforsolo();
        }
        showparticipantforother();


    }

    private void showparticipantforsolo() {
        Call<ArcadeSoloParticipantsResponse> call = APICalls.getarcadesoloparticipants().FetchSoloParticipants(gameid, Constants.AuthBearer + usrtoken);
        call.enqueue(new Callback<ArcadeSoloParticipantsResponse>() {
            @Override
            public void onResponse(Call<ArcadeSoloParticipantsResponse> call, Response<ArcadeSoloParticipantsResponse> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    list = response.body().getParticipants();
                    AdapterHomeArcadesoloParticipants participantsadapter = new AdapterHomeArcadesoloParticipants(list);
                    arcadegamesshowparticipantsrecyclerview.setAdapter(participantsadapter);

                }
            }

            @Override
            public void onFailure(Call<ArcadeSoloParticipantsResponse> call, Throwable t) {
                CommonMethods.DisplayShortTOAST(thiscontext, "Network Error not able to fetch data");

            }
        });


    }

    private void showparticipantforother() {
        Call<ArcadeViewTeamsResponse> call = APICalls.getviewteams().FetchOtherParticipants(gameid, Constants.AuthBearer + usrtoken);

        call.enqueue(new Callback<ArcadeViewTeamsResponse>() {
            @Override
            public void onResponse(Call<ArcadeViewTeamsResponse> call, Response<ArcadeViewTeamsResponse> response) {
                assert response.body() != null;
                responseList = response.body().getTeams();
                AdapterArcadeViewTeams adapterArcadeViewTeams = new AdapterArcadeViewTeams(responseList);
                arcadegamesshowparticipantsrecyclerview.setAdapter(adapterArcadeViewTeams);

            }

            @Override
            public void onFailure(Call<ArcadeViewTeamsResponse> call, Throwable t) {
                CommonMethods.DisplayShortTOAST(thiscontext, "Network Error not able to fetch data" + t.getMessage());
            }
        });


    }


    private void initscreen() {
        ((HomeActivity) getActivity()).setToolbarInvisible();
        ((HomeActivity) getActivity()).setDrawerLocked();
        ((HomeActivity) getActivity()).setbotInvisible();
    }
}