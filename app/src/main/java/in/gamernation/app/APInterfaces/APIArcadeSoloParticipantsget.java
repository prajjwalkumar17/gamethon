package in.gamernation.app.APInterfaces;

import in.gamernation.app.APIResponses.ArcadeSoloParticipantsResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface APIArcadeSoloParticipantsget {
    @GET("games/view_participants/{leaguueid}")
    Call<ArcadeSoloParticipantsResponse> FetchSoloParticipants(
            @Path("leaguueid") String gaemeid,
            @Header("Authorization") String AuthTokenreq


    );
}
