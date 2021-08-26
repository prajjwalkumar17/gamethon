package in.gamernation.app.APICalls;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.util.Objects;

import in.gamernation.app.Utils.Constants;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
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


}
