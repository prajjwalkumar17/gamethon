package in.gamernation.app.Fragments.referrals;

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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import in.gamernation.app.APICalls.APICallsOkHttp;
import in.gamernation.app.Activities.HomeActivity;
import in.gamernation.app.Adapters.AdapterReferallview;
import in.gamernation.app.Decoration.DecorationHomeRecyclerGamesItem;
import in.gamernation.app.R;
import in.gamernation.app.Utils.CommonMethods;
import in.gamernation.app.Utils.Constants;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class referraljoinedfriendsFragment extends Fragment {
    private SharedPreferences sharedPreferences;
    private String usrtoken;
    private Context thiscontext;
    private RecyclerView referralshowusersrecyclerview;
    private TextView referraljoinedjoined, referraljoinedearned;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_referraljoinedfriends, container, false);
        initscreen();
        initviews(root);


        return root;
    }

    private void initviews(View root) {
        TextView toolwithbackbothead = root.findViewById(R.id.toolwithbackbothead);
        ImageView toolwithbackbotheadbot = root.findViewById(R.id.toolwithbackbotheadbot);
        referraljoinedjoined = root.findViewById(R.id.referraljoinedjoined);
        referraljoinedearned = root.findViewById(R.id.referraljoinedearned);
        referralshowusersrecyclerview = root.findViewById(R.id.referralshowusersrecyclerview);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(thiscontext, 1, GridLayoutManager.VERTICAL, false);
        referralshowusersrecyclerview.setLayoutManager(gridLayoutManager);
        referralshowusersrecyclerview.addItemDecoration(new DecorationHomeRecyclerGamesItem(thiscontext, R.dimen.dp_2));

        toolwithbackbotheadbot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getActivity() != null)
                    getActivity().getSupportFragmentManager().popBackStackImmediate();
            }
        });

        toolwithbackbothead.setText("My Referrals");
        joinedfriends();
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

    private void joinedfriends() {
        String url = APICallsOkHttp.urlbuilderforhttp(Constants.w3devbaseurl + "user/referal/my_referals");
        APICallsOkHttp.okhttpmaster().newCall(APICallsOkHttp.requestbuildwithauth(url, usrtoken)).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                CommonMethods.DisplayLongTOAST(thiscontext, e.getMessage());
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {


                        try {
                            final String responsez = response.body().string();
                            JSONObject object = new JSONObject(responsez);
                            String totalfriendsjoined = object.optString("Total_friends_joined");
                            String Earned_through_friends = object.optString("Earned_through_friends");
                            JSONArray friends = object.optJSONArray("User");

                            AdapterReferallview adapterReferallview = new AdapterReferallview(friends);
                            referralshowusersrecyclerview.setAdapter(adapterReferallview);

                            referraljoinedjoined.setText("Total Friends\nJoined\n" + totalfriendsjoined);
                            referraljoinedearned.setText("Earned through\nFriends\n" + Earned_through_friends);
                        } catch (IOException | JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

}