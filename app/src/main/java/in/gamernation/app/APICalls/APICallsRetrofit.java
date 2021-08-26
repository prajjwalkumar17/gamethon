package in.gamernation.app.APICalls;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import in.gamernation.app.APInterfaces.APIArcadeSOLOjoinGamepost;
import in.gamernation.app.APInterfaces.APIArcadeSoloParticipantsget;
import in.gamernation.app.APInterfaces.APIArcaderoomsInterface;
import in.gamernation.app.APInterfaces.APIArcadeviewTeams;
import in.gamernation.app.APInterfaces.APIHomedashboardgamesItem;
import in.gamernation.app.APInterfaces.APILeaderboarduser;
import in.gamernation.app.APInterfaces.APILoginInterfaces;
import in.gamernation.app.APInterfaces.APIMyprofileDataFetchInterface;
import in.gamernation.app.Utils.Constants;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APICallsRetrofit {
    private static OkHttpClient httpClient = new OkHttpClient();
    private static Request httpRequest;
    private static Response httpResponse;
    private static String baseurl;

    private static Retrofit getRetrofit() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addNetworkInterceptor(new StethoInterceptor()).addInterceptor(interceptor).build();
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.w3devbaseurl)
                .client(okHttpClient)
                .build();

        return retrofit;
    }

    public static APILoginInterfaces getUserLogginService() {
        APILoginInterfaces APILoginInterfaces = getRetrofit().create(APILoginInterfaces.class);
        return APILoginInterfaces;
    }

    public static APIMyprofileDataFetchInterface getmyprofiledata() {
        APIMyprofileDataFetchInterface apiMyprofileDataFetchInterface = getRetrofit().create(APIMyprofileDataFetchInterface.class);
        return apiMyprofileDataFetchInterface;
    }

    public static APIHomedashboardgamesItem gethomedashboardsitem() {
        APIHomedashboardgamesItem apiHomedashboardgamesItem = getRetrofit().create(APIHomedashboardgamesItem.class);
        return apiHomedashboardgamesItem;
    }

    public static APIArcaderoomsInterface getarcadegamerooms() {
        APIArcaderoomsInterface apiArcaderoomsInterface = getRetrofit().create(APIArcaderoomsInterface.class);
        return apiArcaderoomsInterface;
    }

    public static APIArcadeSoloParticipantsget getarcadesoloparticipants() {
        APIArcadeSoloParticipantsget apiArcadeSoloParticipantsget = getRetrofit().create(APIArcadeSoloParticipantsget.class);
        return apiArcadeSoloParticipantsget;
    }

    public static APIArcadeviewTeams getviewteams() {
        APIArcadeviewTeams apiArcadeviewTeams = getRetrofit().create(APIArcadeviewTeams.class);
        return apiArcadeviewTeams;
    }

    public static APIArcadeSOLOjoinGamepost getsolojoingame() {
        APIArcadeSOLOjoinGamepost apiArcadeSOLOjoinGamepost = getRetrofit().create(APIArcadeSOLOjoinGamepost.class);
        return apiArcadeSOLOjoinGamepost;
    }

    public static APILeaderboarduser getuserdataleaderboard() {
        APILeaderboarduser apiLeaderboarduser = getRetrofit().create(APILeaderboarduser.class);
        return apiLeaderboarduser;
    }

  /*  public static String gethttpRequest(String getURL) throws IOException {
        httpRequest = new Request.Builder().url(getURL).build();
        httpResponse = httpClient.newCall(httpRequest).execute();
        return httpResponse.body().string();
    }


    public static String gethttpRequestwithoutclient(String getURL, OkHttpClient httpClient) throws IOException {
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

    }*/

}
