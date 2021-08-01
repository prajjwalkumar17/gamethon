package in.gamernation.app.APICalls;

import java.io.IOException;

import in.gamernation.app.Utils.Constants;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class APICalls {
    private static OkHttpClient httpClient = new OkHttpClient();
    private static Request httpRequest;
    private static Response httpResponse;
    private static String baseurl;


    public static String gethttpRequest(String getURL) throws IOException {
        httpRequest = new Request.Builder().url(getURL).build();
        httpResponse = httpClient.newCall(httpRequest).execute();
        return httpResponse.body().string();
    }

    public static String buildhttpurlforgetreq(String pathsegment, String querycode) throws IOException {
        HttpUrl httpUrl = new HttpUrl.Builder()
                .scheme(Constants.w3devScheme)
                .host(Constants.w3devbaseurl)
                .addPathSegment(pathsegment)
//                .addPathSegment("v1")
//                .addPathSegment("students")
                .addPathSegment(querycode)

                // <- id here for query
                // .addQueryParameter("auth_token", "71x23768234hgjwqguygqew")
                // Each addPathSegment separated add a / symbol to the final url
                // finally my Full URL is:
                // https://gamernation.w3api.net/api/v1/students/8873?auth_token=71x23768234hgjwqguygqew
                // https://gamernation.w3api.net/users/id_of_particular_user

                .build();
        return httpUrl.toString();

    }

    public static String posthttpRequest(RequestBody requestBody, String postURL) throws IOException {
        httpRequest = new Request.Builder().url(postURL).post(requestBody).build();
        httpResponse = httpClient.newCall(httpRequest).execute();
        return httpResponse.body().string();

    }

}
