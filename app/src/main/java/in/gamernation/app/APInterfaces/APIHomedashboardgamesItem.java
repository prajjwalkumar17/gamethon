package in.gamernation.app.APInterfaces;

import in.gamernation.app.APIResponses.HomegamesitemResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface APIHomedashboardgamesItem {

    @GET("games")
    Call<HomegamesitemResponse> FetchHomegamesItem(@Header("Authorization") String AuthTokenreq);
}
