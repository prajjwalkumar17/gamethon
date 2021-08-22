
package in.gamernation.app.APIResponses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ArcadeSoloParticipantsResponse {

    @SerializedName("Participants")
    @Expose
    private List<Participant> participants = null;


    public ArcadeSoloParticipantsResponse() {
    }


    public ArcadeSoloParticipantsResponse(List<Participant> participants) {
        super();
        this.participants = participants;
    }

    public List<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Participant> participants) {
        this.participants = participants;
    }


    public class Participant {

        @SerializedName("picture")
        @Expose
        private String picture;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("username")
        @Expose
        private String username;
        @SerializedName("user_id")
        @Expose
        private String userId;


        public Participant() {
        }


        public Participant(String picture, String name, String username, String userId) {
            super();
            this.picture = picture;
            this.name = name;
            this.username = username;
            this.userId = userId;
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

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

    }
}


