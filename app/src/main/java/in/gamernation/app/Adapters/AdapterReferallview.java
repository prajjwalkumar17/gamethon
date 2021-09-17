package in.gamernation.app.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import de.hdodenhof.circleimageview.CircleImageView;
import in.gamernation.app.R;

public class AdapterReferallview extends RecyclerView.Adapter<AdapterReferallview.view> {
    JSONArray array;

    public AdapterReferallview(JSONArray array) {
        this.array = array;
    }


    @NonNull
    @Override
    public view onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerarcadeparticipantsitem, parent, false);
        return new view(view);
    }

    @Override
    public void onBindViewHolder(@NonNull view holder, int position) {


        JSONObject object1 = array.optJSONObject(position);
        String picture = object1.optString("picture");
        String name = object1.optString("name");

        holder.arcadeparticipantsoloname.setText(name);
        Picasso.get()
                .load(picture)
                .placeholder(R.drawable.placeholder)
                .fit()
                .error(R.drawable.dperror)
                .centerCrop()
                .into(holder.arcadeparticipantssolodp);
    }

    @Override
    public int getItemCount() {
        return array.length();
    }


    public class view extends RecyclerView.ViewHolder {
        CircleImageView arcadeparticipantssolodp;
        TextView arcadeparticipantsoloname, arcadeparticipantsolousernamename;

        public view(@NonNull View itemView) {
            super(itemView);
            inititemviews(itemView);

        }

        private void inititemviews(View itemView) {
            arcadeparticipantssolodp = itemView.findViewById(R.id.arcadeparticipantssolodp);
            arcadeparticipantsoloname = itemView.findViewById(R.id.arcadeparticipantsoloname);
            arcadeparticipantsolousernamename = itemView.findViewById(R.id.arcadeparticipantsolousernamename);
            arcadeparticipantsolousernamename.setVisibility(View.GONE);
        }
    }
}
