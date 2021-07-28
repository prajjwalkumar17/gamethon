package in.gamernation.app.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import in.gamernation.app.Activities.HomeActivity;
import in.gamernation.app.R;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class myprofileFragment extends Fragment {

    Request httprequest;
    String myprofileurl;
    private TextView myprofile_name;
    private OkHttpClient httpClient;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_myprofile, container, false);
        ((HomeActivity) getActivity()).setToolbarInvisible();
        ((HomeActivity) getActivity()).setDrawerLocked();
        ((HomeActivity) getActivity()).setbotInvisible();
        findviews(root);
        initializers();
        return root;
    }

    private void initializers() {
        httpClient = new OkHttpClient();
        request(httpClient, myprofileurl);

    }

    private void request(OkHttpClient httpClient, String myprofileurl) {
        httprequest = new Request.Builder().url(myprofileurl).build();
        httpClient.newCall(httprequest).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

            }
        });
    }

    private void findviews(View root) {
        myprofile_name = root.findViewById(R.id.myprofile_name);


        myprofileurl = "https://";

    }

}