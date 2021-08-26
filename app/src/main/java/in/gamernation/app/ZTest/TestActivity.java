package in.gamernation.app.ZTest;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import in.gamernation.app.R;
import in.gamernation.app.Utils.CommonMethods;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class TestActivity extends AppCompatActivity {

    private static OkHttpClient httpClient = new OkHttpClient();
    private static Request httpRequest;
    private static Response httpResponse;
    String url = "https://reqres.in/api/users?page=2";
    String ty;
    private TextView httpcheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        httpcheck = findViewById(R.id.httpcheck);
//        httpRequest =new Request.Builder().url(url).build();
//        httpClient.newCall(httpRequest).enqueue(new Callback() {
//            @Override
//            public void onFailure(@NotNull Call call, @NotNull IOException e) {
//
//            }
//
//            @Override
//            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
//                if(response.isSuccessful()){
//                    TestActivity.this.runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            httpcheck.setText(ty);
//
//                        }
//                    });
//                }
//            }
//        });

        new async().execute();

    }


    class async extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                if (CommonMethods.isnetworkConnected(getApplicationContext())) {
//                    httpRequest =new Request.Builder().url(url).build();
//                    httpResponse=httpClient.newCall(httpRequest).execute();
//                    ty = APICalls.gethttpRequest(url);
                } else {
                    CommonMethods.DisplayLongTOAST(getApplicationContext(), "Check the network connectivity");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            try {
                httpcheck.setText(ty);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

