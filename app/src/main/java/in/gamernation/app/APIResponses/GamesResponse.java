package in.gamernation.app.APIResponses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GamesResponse {
    //TODO changing the object id to normal is giving null pointer error and crashing the app and this initialisation is generating a new object  id instead of stored one in backend
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("thumb")
    @Expose
    private String thumb;
    @SerializedName("category")
    @Expose
    private String category;

    public GamesResponse(String id, String name, String thumb, String category) {
        this.id = id;
        this.name = name;
        this.thumb = thumb;
        this.category = category;
    }

    public GamesResponse() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
