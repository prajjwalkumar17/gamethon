
package in.gamernation.app.APIResponses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ArcadeViewTeamsResponse {

    @SerializedName("slot")
    @Expose
    private Integer slot;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("team_name")
    @Expose
    private String teamName;


    public ArcadeViewTeamsResponse() {
    }


    public ArcadeViewTeamsResponse(Integer slot, String id, String teamName) {
        super();
        this.slot = slot;
        this.id = id;
        this.teamName = teamName;
    }

    public Integer getSlot() {
        return slot;
    }

    public void setSlot(Integer slot) {
        this.slot = slot;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

}