package in.gamernation.app.Interfaces;

import in.gamernation.app.APIRequests.UserLoginRequest;
import in.gamernation.app.ModalClasses.UserLoginResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface APILoginInterfaces {

    @POST("v1/login/")
    Call<UserLoginResponse> userLogin(@Body UserLoginRequest userLoginRequest);

}
