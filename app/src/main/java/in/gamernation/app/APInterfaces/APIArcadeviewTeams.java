package in.gamernation.app.APInterfaces;

import in.gamernation.app.APIResponses.ArcadeViewTeamsResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface APIArcadeviewTeams {
    @GET("games/view_teams/{leaguueid}")
    Call<ArcadeViewTeamsResponse> FetchOtherParticipants(
            @Path("leaguueid") String gaemeid,
            @Header("Authorization") String AuthTokenreq);

}
