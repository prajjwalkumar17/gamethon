package in.gamernation.app.APICalls;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import in.gamernation.app.Utils.CommonMethods;
import in.gamernation.app.Utils.Constants;
import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class APIInterceptor implements Interceptor {
    private String creds;

    public APIInterceptor(String user, String password) {
        this.creds = Credentials.basic(user, password);
    }


    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        Request request = chain.request();
        Request authreq = request.newBuilder()
                .header("Authorization", creds).build();
        String req = chain.proceed(authreq).toString();
        CommonMethods.LOGthesite(Constants.LOG, req);
        return chain.proceed(authreq);
    }
}
