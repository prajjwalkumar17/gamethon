package in.gamernation.app.Fragments.referrals;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import in.gamernation.app.APICalls.APICallsOkHttp;
import in.gamernation.app.Activities.HomeActivity;
import in.gamernation.app.R;
import in.gamernation.app.Utils.CommonMethods;
import in.gamernation.app.Utils.Constants;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class invitefriendsFragment extends Fragment {
    private Context thiscontext;
    private TextView toolwithbackbothead, referralreferraallcode;
    private SharedPreferences sharedPreferences;
    private AppCompatButton referralwhatsappshare, referralmoreshare, referralviewfriends;
    private String usrtoken;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_invitefriends, container, false);
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
        toolwithbackbothead = root.findViewById(R.id.toolwithbackbothead);
        referralwhatsappshare = root.findViewById(R.id.referralwhatsappshare);
        referralmoreshare = root.findViewById(R.id.referralmoreshare);
        referralviewfriends = root.findViewById(R.id.referralviewfriends);
        referralreferraallcode = root.findViewById(R.id.referralreferraallcode);
        toolwithbackbothead.setText("All Transactions");
        fetchandshowdata();
        joinedfriends();
    }


    private void fetchandshowdata() {
        String url = APICallsOkHttp.urlbuilderforhttp(Constants.w3devbaseurl + "user/referal");
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
                            try {
                                JSONObject object = new JSONObject(responsez);
                                String code = object.getString("referal_code");
                                String msg = object.getString("message");
                                referralreferraallcode.setText(code);
                                sharewhatsppbotpressed(code, msg);
                                sharewallbotpressed(code, msg);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                });
            }
        });
    }

    private void sharewallbotpressed(String code, String msg) {
        referralmoreshare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT, msg);
                intent.setType("text/plain");
                intent = Intent.createChooser(intent, "Share by");
                startActivity(intent);
            }
        });
    }

    private void sharewhatsppbotpressed(String code, String msg) {
        referralwhatsappshare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(Constants.whatsappAPIwithtextfilter + msg));
                startActivity(intent);
            }
        });
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
                            try {
                                JSONObject object = new JSONObject(responsez);
                                String totalfriendsjoined = object.getString("Total_friends_joined");
                                String Earned_through_friends = object.getString("Earned_through_friends");
                                JSONArray friends = object.getJSONArray("user");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                });
            }
        });
    }

}