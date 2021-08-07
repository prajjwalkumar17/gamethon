package in.gamernation.app.Interfaces;

import in.gamernation.app.ModalClasses.MyProfileResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface APIMyprofileDataFetchInterface {

    @GET("user/my_profile/")
    Call<MyProfileResponse> FetchProfileData(@Header("Authorization") String AuthTokenreq);


}
