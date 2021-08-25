package in.gamernation.app.APIRequests;

public class ArcadeSolojoingameRequest {
    private String username;
    private String userId;

    public ArcadeSolojoingameRequest() {
    }

    public ArcadeSolojoingameRequest(String username, String userId) {
        this.username = username;
        this.userId = userId;
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
