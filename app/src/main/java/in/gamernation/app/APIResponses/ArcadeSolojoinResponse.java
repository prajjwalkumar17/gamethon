
package in.gamernation.app.APIResponses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ArcadeSolojoinResponse {

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("error")
    @Expose
    private String error;

    public ArcadeSolojoinResponse() {
    }


    public ArcadeSolojoinResponse(String message, String error) {
        this.message = message;
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}