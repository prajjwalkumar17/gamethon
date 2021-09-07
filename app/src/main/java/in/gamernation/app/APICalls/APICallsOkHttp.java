package in.gamernation.app.APICalls;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.util.Objects;

import in.gamernation.app.Utils.Constants;
import okhttp3.HttpUrl;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;

public class APICallsOkHttp {

    public static OkHttpClient okhttpmaster() {
        return new OkHttpClient.Builder().addNetworkInterceptor(new StethoInterceptor()).addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)).build();
    }

    public static String urlbuilderforhttp(String url) {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(url).newBuilder();
        return urlBuilder.build().toString();
    }

    public static String urlbuilderforhttpwithquery(String url, String filtername, String value) {
        return Objects.requireNonNull(HttpUrl.parse(url)).newBuilder().addQueryParameter(filtername, value).build().toString();
    }

    public static Request requestbuildwithauth(String url, String usrauthtoken) {
        return new Request.Builder()
                .header("Authorization", Constants.AuthBearer + usrauthtoken)
                .url(url)
                .build();
    }

    public static Request requesthttpwithoutauth(String url) {
        return new Request.Builder()
                .url(url)
                .build();
    }

    public static Request requesthttpwithoutauthandwithbody(String url, RequestBody requestBody) {
        return new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
    }

    public static Request requestwithpost(String url, String usrauthtoken, RequestBody requestBody) {
        return new Request.Builder()
                .header("Authorization", Constants.AuthBearer + usrauthtoken)
                .url(url)
                .post(requestBody)
                .build();
    }

    public static Request requestwithpatch(String url, String usrauthtoken, RequestBody requestBody) {
        return new Request.Builder()
                .header("Authorization", Constants.AuthBearer + usrauthtoken)
                .url(url)
                .patch(requestBody)
                .build();
    }

    public static RequestBody buildrequestbodyforusernameandpassword(String username, String userid) {
        return new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("username", username)
                .addFormDataPart("userId", userid)
                .build();
    }

    public static RequestBody buildrequestbodyforusernameandpasswordteamid(String username, String userid, String teamid) {
        return new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("username", username)
                .addFormDataPart("userid", userid)
                .addFormDataPart("teamid", teamid)
                .build();
    }

    public static RequestBody buildrequestbodyforusernameandpasswordteamname(String username, String userid, String teamname) {
        return new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("username", username)
                .addFormDataPart("userId", userid)
                .addFormDataPart("teamName", teamname)
                .build();
    }

}

/*Implementation for this Class
 *        Request request = new Request.Builder()*/
//                .header("Authorization", Constants.AuthBearer + usrtoken)
//                .url(url)
//                .build();
//
//        okHttpClient.newCall(request).enqueue(new Callback() {
//
//            @Override
//            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
//
//
//               getActivity().runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        try {
//                            JSONObject responselist = new JSONObject(myResponse);
//                            JSONArray responses = responselist.getJSONArray("Leaderboard");
//
//                            for (int r = 0; r < responses.length(); r++) {
//                                JSONObject object = responses.getJSONObject(r);
//                                CommonMethods.LOGthesite(Constants.LOG, object.getString("_id"));
//                                CommonMethods.LOGthesite(Constants.LOG, object.getString("won"));
//
//                                JSONObject user=object.getJSONObject("user");
//                                CommonMethods.LOGthesite(Constants.LOG, user.getString("picture"));
//                                CommonMethods.LOGthesite(Constants.LOG, user.getString("name"));
//                                CommonMethods.LOGthesite(Constants.LOG, user.getString("id"));
//
//                            }
