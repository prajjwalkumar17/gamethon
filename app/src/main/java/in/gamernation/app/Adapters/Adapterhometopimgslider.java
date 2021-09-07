package in.gamernation.app.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.denzcoskun.imageslider.ImageSlider;

import org.json.JSONArray;
import org.json.JSONObject;

import in.gamernation.app.R;

public class Adapterhometopimgslider extends RecyclerView.Adapter<Adapterhometopimgslider.MyViewHolder> {
    JSONArray object;
    int length;

    public Adapterhometopimgslider(JSONArray object) {
        this.object = object;
    }

    public Adapterhometopimgslider() {
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.img_slider, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        try {
            JSONObject images = object.getJSONObject(position);
            String _id = images.getString("_id");
            String picture = images.getString("picture");
            String link = images.getString("link");

//            Picasso.get()
//                    .load(picture)
//                    .placeholder(R.drawable.placeholder)
//                    .fit()
//                    .error(R.drawable.dperror)
//                    .centerCrop()
//                    .into((Target) holder.sliderhomeimage);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        length = object.length();
        return length;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageSlider slider;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            slider = itemView.findViewById(R.id.sliderpageree);
        }
    }
}

