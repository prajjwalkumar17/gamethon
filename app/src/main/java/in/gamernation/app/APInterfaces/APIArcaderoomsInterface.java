package in.gamernation.app.APInterfaces;

import in.gamernation.app.APIResponses.ArcadeResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIArcaderoomsInterface {


    @GET("games/leagues/{gameid}")
    Call<ArcadeResponse> FetchArcadeRooms(
            @Path("gameid") String gaemeid,
            @Query("filter") String game_type,
            @Header("Authorization") String AuthTokenreq


    );


}
