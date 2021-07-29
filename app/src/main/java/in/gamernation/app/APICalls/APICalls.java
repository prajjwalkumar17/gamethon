package in.gamernation.app.APICalls;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class APICalls {
    private static OkHttpClient httpClient = new OkHttpClient();
    private static Request httpRequest;
    private static Response httpResponse;


    public static String gethttpRequest(String getURL) throws IOException {
        httpRequest = new Request.Builder().url(getURL).build();
        httpResponse = httpClient.newCall(httpRequest).execute();
        return httpResponse.body().string();

    }

    public static String posthttpRequest(RequestBody requestBody, String postURL) throws IOException {
        httpRequest = new Request.Builder().url(postURL).post(requestBody).build();
        httpResponse = httpClient.newCall(httpRequest).execute();
        return httpResponse.body().string();

    }

}
