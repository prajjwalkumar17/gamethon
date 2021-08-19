package in.gamernation.app.APIResponses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.bson.types.ObjectId;

public class GamesResponse {
    @SerializedName("id")
    @Expose
    private ObjectId id = ObjectId.get();
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("thumb")
    @Expose
    private String thumb;
    @SerializedName("category")
    @Expose
    private String category;

    public GamesResponse(ObjectId id, String name, String thumb, String category) {
        this.id = id;
        this.name = name;
        this.thumb = thumb;
        this.category = category;
    }

    public GamesResponse() {
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
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
