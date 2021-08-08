package in.gamernation.app.APInterfaces;

import in.gamernation.app.APIResponses.MyProfileResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface APIMyprofileDataFetchInterface {

    @GET("user/my_profile/")
    Call<MyProfileResponse> FetchProfileData(@Header("Authorization") String AuthTokenreq);


}
