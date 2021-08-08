package in.gamernation.app.APIResponses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HomegamesitemResponse {
    @SerializedName("count")
    @Expose
    private int count;

    @SerializedName("Games")
    @Expose
    private List<GamesResponse> gamesResponse = null;

    public HomegamesitemResponse() {
    }

    public HomegamesitemResponse(int count, List<GamesResponse> gamesResponse) {
        this.count = count;
        this.gamesResponse = gamesResponse;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<GamesResponse> getGamesResponse() {
        return gamesResponse;
    }

    public void setGamesResponse(List<GamesResponse> gamesResponse) {
        this.gamesResponse = gamesResponse;
    }
}
