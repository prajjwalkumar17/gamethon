package in.gamernation.app.Fragments;

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

import java.io.IOException;

import in.gamernation.app.APICalls.APICallsOkHttp;
import in.gamernation.app.Activities.HomeActivity;
import in.gamernation.app.Decoration.DecorationHomeRecyclerGamesItem;
import in.gamernation.app.R;
import in.gamernation.app.Utils.Constants;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class myleaguesFragment extends Fragment {
    private AppCompatButton myleagufreefirebot, myleagufreefireclashsquadbot, myleagubgmibot, myleagutdmbot, myleagulivechangebot, myleagulpastchangebot, myleagulupcomingchangebot;
    private RecyclerView myleagurecycler;
    private Context thiscontext;
    private String usrtoken, typetime, gamename;
    private SharedPreferences sharedPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_myleagues, container, false);
        initscreen();
        initviews(root);
        fetchdata();
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
    }

    private void fetchdata() {
        String url = APICallsOkHttp.urlbuilderforhttpwithtwoquery("", "", typetime, "", gamename);
        APICallsOkHttp.okhttpmaster().newCall(
                APICallsOkHttp.requestbuildwithauth(APICallsOkHttp.urlbuilderforhttp(url)
                        , usrtoken)).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

            }
        });


    }


}