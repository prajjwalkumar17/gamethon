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
import in.gamernation.app.APIResponses.GamesResponse;
import in.gamernation.app.APIResponses.HomegamesitemResponse;
import in.gamernation.app.Adapters.AdapterHomeFragGamesItem;
import in.gamernation.app.Decoration.DecorationHomeRecyclerGamesItem;
import in.gamernation.app.R;
import in.gamernation.app.RecyclerClickInterfaces.ClicksHomeFraggames;
import in.gamernation.app.Utils.CommonMethods;
import in.gamernation.app.Utils.Constants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class homegamesFragment extends Fragment implements ClicksHomeFraggames {
    private static SharedPreferences sharedPreferences;
    RecyclerView homerecycler;
    private Context thiscontext;
    private String usrtoken;
    private AdapterHomeFragGamesItem adapter;
    private List<GamesResponse> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_homegames, container, false);
        initviews(root);
        initmethods();
        return root;
    }

    private void initviews(View root) {
        homerecycler = root.findViewById(R.id.HomeRecycler);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(thiscontext, 2, GridLayoutManager.VERTICAL, false);
        homerecycler.setLayoutManager(gridLayoutManager);
        homerecycler.addItemDecoration(new DecorationHomeRecyclerGamesItem(thiscontext, R.dimen.dp_2));
    }

    private void initmethods() {
        sharedPreferences = thiscontext.getSharedPreferences(Constants.LOGINPREFS, Context.MODE_PRIVATE);
        usrtoken = sharedPreferences.getString(Constants.TOKENUSINGPREFS, "No data found!!!");
        fetchgameitems();
    }

    @Override
    public void onAttach(@NonNull @NotNull Context context) {
        super.onAttach(context);
        thiscontext = context;
    }

    private void fetchgameitems() {

        Call<HomegamesitemResponse> responseCall = APICalls.gethomedashboardsitem().FetchHomegamesItem(Constants.AuthBearer + usrtoken);
        responseCall.enqueue(new Callback<HomegamesitemResponse>() {
            @Override
            public void onResponse(@NotNull Call<HomegamesitemResponse> call, @NotNull Response<HomegamesitemResponse> response) {
                if (response.isSuccessful()) {
                    CommonMethods.DisplayLongTOAST(thiscontext, "games received sucesssfully");
                    assert response.body() != null;
                    list = response.body().getGamesResponse();
                    assert list != null;
                    adapter = new AdapterHomeFragGamesItem(list, homegamesFragment.this);
                    homerecycler.setAdapter(adapter);

                }
            }

            @Override
            public void onFailure(Call<HomegamesitemResponse> call, Throwable t) {
                CommonMethods.LOGthesite(Constants.LOG, "game fetch Failed  " + t);
            }
        });
    }


    @Override
    public void onItemClick(int position) {
        savedatatosharedprefforarcade(list, position);
        if (list.get(position).getCategory().equals(Constants.Gamecategoryarcade)) {
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainerView, new arcadegameFragment()).addToBackStack(null).commit();
        } else if (list.get(position).getCategory().equals(Constants.Gamecategorymultiplayer)) {
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainerView, new multiplayergameFragment()).addToBackStack(null).commit();
        } else if (list.get(position).getCategory().equals(Constants.Gamecategorycommingsoon)) {
            CommonMethods.DisplayLongTOAST(thiscontext, Constants.Comingsoonmsg);
        }

    }

    private void savedatatosharedprefforarcade(List<GamesResponse> list, int position) {
        SharedPreferences sharedPreferences = thiscontext.getSharedPreferences(Constants.ARCADEGAMEPREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Constants.ARCADEGAMEIDPREF, list.get(position).getId().toString());
        editor.apply();
    }

/*    private void gamesrecievedoperation(Response<HomegamesitemResponse> response) {
        CommonMethods.DisplayLongTOAST(thiscontext, "games received sucesssfully");
       HomegamesitemResponse homegamesitemResponse = response.body();
        List<GamesResponse> list=response.body().getGamesResponse();
        assert list != null;

        adapter=new HomeFragGamesItemAdapter(list);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (list != null) {
                    Showgames(list);
                    CommonMethods.LOGthesite(Constants.LOG,list.get(0).getName());

                } else {
                    CommonMethods.DisplayLongTOAST(thiscontext, "games failed");
                }
            }
        }, Constants.delaybeforelogin);
    }
    private void configrecyclerview(RecyclerView recyclerView,HomeFragGamesItemAdapter adapter){
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
    }
    private void Showgames(GamesResponse homegamesitemResponse) {
        CommonMethods.LOGthesite(Constants.LOG, String.valueOf(homegamesitemResponse.getCount()));
        CommonMethods.LOGthesite(Constants.LOG, String.valueOf(homegamesitemResponse.getGamesResponse().get(0).getId()));
        CommonMethods.LOGthesite(Constants.LOG, homegamesitemResponse.getGamesResponse().get(0).getName());
        CommonMethods.LOGthesite(Constants.LOG, homegamesitemResponse.getGamesResponse().get(0).getThumb());
        CommonMethods.LOGthesite(Constants.LOG, homegamesitemResponse.getGamesResponse().get(0).getCategory());

    }
        crd1 = root.findViewById(R.id.crd1);
        crd2 = root.findViewById(R.id.crd2);
        crd3 = root.findViewById(R.id.crd3);
        crd4 = root.findViewById(R.id.crd4);
        crd5 = root.findViewById(R.id.crd5);
        crd6 = root.findViewById(R.id.crd6);
        clicklistnersoncards();
    private void clicklistnersoncards() {
        crd1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainerView, new freefirenormFragment()).addToBackStack(null).commit();

            }
        });
        crd2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        crd3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainerView, new ludokingFragment()).addToBackStack(null).commit();
            }
        });
        crd4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainerView, new freefireclashsquadFragment()).addToBackStack(null).commit();
            }
        });
        crd5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainerView, new pubgnormFragment()).addToBackStack(null).commit();
            }
        });
        crd6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }*/

}