
package in.gamernation.app.APIResponses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ArcadeViewTeamsResponse {

    @SerializedName("Teams")
    @Expose
    private List<Team> teams = null;


    public ArcadeViewTeamsResponse() {
    }


    public ArcadeViewTeamsResponse(List<Team> teams) {
        super();
        this.teams = teams;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public class Team {

        @SerializedName("slot")
        @Expose
        private Integer slot;
        @SerializedName("_id")
        @Expose
        private String id;
        @SerializedName("team_name")
        @Expose
        private String teamName;

        /**
         * No args constructor for use in serialization
         */
        public Team() {
        }

        /**
         * @param teamName
         * @param slot
         * @param id
         */
        public Team(Integer slot, String id, String teamName) {
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
}