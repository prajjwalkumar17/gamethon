package in.gamernation.app.APInterfaces;

import in.gamernation.app.APIRequests.ArcadeSolojoingameRequest;
import in.gamernation.app.APIResponses.ArcadeSolojoinResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface APIArcadeSOLOjoinGamepost {

    @POST("games/join/{leaguueid}")
    Call<ArcadeSolojoinResponse> userLogin(
            @Header("Authorization") String AuthTokenreq,
            @Path("leaguueid") String gaemeid,
            @Body ArcadeSolojoingameRequest arcadeSolojoingameRequest


    );
}
