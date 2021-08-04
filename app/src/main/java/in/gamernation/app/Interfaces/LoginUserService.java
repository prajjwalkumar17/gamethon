package in.gamernation.app.Interfaces;

import in.gamernation.app.Classes.UserLoginRequest;
import in.gamernation.app.Classes.UserLoginResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginUserService {

    @POST("v1/login/")
    Call<UserLoginResponse> userLogin(@Body UserLoginRequest userLoginRequest);


}
