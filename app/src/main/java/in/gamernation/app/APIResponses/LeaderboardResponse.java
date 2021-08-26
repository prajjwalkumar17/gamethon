package in.gamernation.app.APIResponses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LeaderboardResponse {

    @SerializedName("Leaderboard")
    @Expose
    private List<Leaderboard> leaderboard = null;


    public LeaderboardResponse() {
    }


    public LeaderboardResponse(List<Leaderboard> leaderboard) {
        super();
        this.leaderboard = leaderboard;
    }

    public List<Leaderboard> getLeaderboard() {
        return leaderboard;
    }

    public void setLeaderboard(List<Leaderboard> leaderboard) {
        this.leaderboard = leaderboard;
    }

    public class User {

        @SerializedName("picture")
        @Expose
        private String picture;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("id")
        @Expose
        private String id;

        /**
         * No args constructor for use in serialization
         */
        public User() {
        }

        /**
         * @param name
         * @param id
         * @param picture
         */
        public User(String picture, String name, String id) {
            super();
            this.picture = picture;
            this.name = name;
            this.id = id;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

    }


    public class Leaderboard {

        @SerializedName("_id")
        @Expose
        private String id;
        @SerializedName("user")
        @Expose
        private User user;
        @SerializedName("won")
        @Expose
        private Integer won;

        /**
         * No args constructor for use in serialization
         */
        public Leaderboard() {
        }

        /**
         * @param won
         * @param id
         * @param user
         */
        public Leaderboard(String id, User user, Integer won) {
            super();
            this.id = id;
            this.user = user;
            this.won = won;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }

        public Integer getWon() {
            return won;
        }

        public void setWon(Integer won) {
            this.won = won;
        }

    }
}

