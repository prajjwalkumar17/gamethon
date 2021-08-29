package in.gamernation.app.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;
import in.gamernation.app.APICalls.APICallsOkHttp;
import in.gamernation.app.Activities.HomeActivity;
import in.gamernation.app.R;
import in.gamernation.app.Utils.CommonMethods;
import in.gamernation.app.Utils.Constants;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class mystatsFragment extends Fragment {
    private CircleImageView mystat_dp;
    private TextView mystat_name, mystat_username, mystat_email, mystat_ludocontest, mystat_ludowins, mystat_ludolosses, mystat_quizcontest, mystat_quizwins, mystat_quizlosses, mystat_gamingcontests, mystat_gamingwins, mystat_gaminglosses;
    private SharedPreferences sharedPreferences;
    private String usrtoken;
    private Context thiscontext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_mystats, container, false);
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
        mystat_dp = root.findViewById(R.id.mystat_dp);
        mystat_name = root.findViewById(R.id.mystat_name);
        mystat_username = root.findViewById(R.id.mystat_username);
        mystat_email = root.findViewById(R.id.mystat_email);

        mystat_ludocontest = root.findViewById(R.id.mystat_ludocontest);
        mystat_ludowins = root.findViewById(R.id.mystat_ludowins);
        mystat_ludolosses = root.findViewById(R.id.mystat_ludolosses);

        mystat_quizcontest = root.findViewById(R.id.mystat_quizcontest);
        mystat_quizwins = root.findViewById(R.id.mystat_quizwins);
        mystat_quizlosses = root.findViewById(R.id.mystat_quizlosses);

        mystat_gamingcontests = root.findViewById(R.id.mystat_gamingcontests);
        mystat_gamingwins = root.findViewById(R.id.mystat_gamingwins);
        mystat_gaminglosses = root.findViewById(R.id.mystat_gaminglosses);
    }

    private void fetchdata() {
        String url = APICallsOkHttp.urlbuilderforhttp("https://gamernation.w3api.net/user/stat");
        APICallsOkHttp.okhttpmaster().newCall(
                APICallsOkHttp.requestbuildwithauth(APICallsOkHttp.urlbuilderforhttp(url)
                        , usrtoken)).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                final String myresponse = response.body().string();
                if (getActivity() != null) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                CommonMethods.LOGthesite(Constants.LOG, myresponse);
                                JSONObject object = new JSONObject(myresponse);
                                JSONObject ludo = object.getJSONObject("ludo");
                                mystat_ludocontest.setText(ludo.getString("contests"));
                                mystat_ludowins.setText(ludo.getString("wins"));
                                mystat_ludolosses.setText(ludo.getString("losses"));

                                JSONObject quiz = object.getJSONObject("quiz");
                                mystat_quizcontest.setText(quiz.getString("contests"));
                                mystat_quizwins.setText(quiz.getString("wins"));
                                mystat_quizlosses.setText(quiz.getString("losses"));

                                JSONObject game = object.getJSONObject("gaming");
                                mystat_gamingcontests.setText(game.getString("contests"));
                                mystat_gamingwins.setText(game.getString("wins"));
                                mystat_gaminglosses.setText(game.getString("losses"));


                                Picasso.get()
                                        .load(object.getJSONObject("user").getString("picture"))
                                        .placeholder(R.drawable.placeholder)
                                        .fit()
                                        .error(R.drawable.dperror)
                                        .centerCrop()
                                        .into(mystat_dp);

                                mystat_email.setText(object.getJSONObject("user").getString("email"));
                                mystat_name.setText(object.getJSONObject("user").getString("name"));

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }


            }
        });

    }
}