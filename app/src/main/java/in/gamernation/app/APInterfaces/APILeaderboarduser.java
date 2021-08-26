package in.gamernation.app.APInterfaces;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface APILeaderboarduser {
    @GET("user/leaderboard")
    Call<JSONObject>
    fetchleaderboardusers(
            @Query("filter") String game_name,
            @Header("Authorization") String AuthTokenreq
    );
}
