package in.gamernation.app.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import de.hdodenhof.circleimageview.CircleImageView;
import in.gamernation.app.R;

public class Adaptermyleagueshowteam extends RecyclerView.Adapter<Adaptermyleagueshowteam.viewrecycler> {
    JSONObject object;
    int length;
    String kill, won, picture, name, username;
    JSONArray array;
    JSONObject arrayobj;

    public Adaptermyleagueshowteam(JSONObject object) {
        this.object = object;
    }

    public Adaptermyleagueshowteam() {
    }

    @NonNull
    @NotNull
    @Override
    public viewrecycler onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclermyleagueshowteamitem, parent, false);
        return new viewrecycler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull viewrecycler holder, int position) {
        try {
            if (object.has("Player")) {
                array = object.optJSONArray("Player");
                arrayobj = array.getJSONObject(position);
                kill = arrayobj.optString("kill");
                won = arrayobj.optString("won_coins");
                picture = arrayobj.optString("picture");
                name = arrayobj.optString("name");
                username = arrayobj.optString("username");

            } else {
                array = object.optJSONArray("team");
                arrayobj = array.getJSONObject(position);
                kill = arrayobj.optString("kill");
                won = arrayobj.optString("won");
                picture = arrayobj.optString("picture");
                name = arrayobj.optString("name");
                username = arrayobj.optString("username");
            }
            holder.arcadeparticipantskill.setText("Kill: " + kill);
            holder.arcadeparticipantswon.setText("Won: " + won);
            holder.arcadeparticipantswon.setText(won);
            holder.arcadeparticipantskill.setText(kill);
            holder.arcadeparticipantsolousernamename.setText(username);
            holder.arcadeparticipantsoloname.setText(name);
            Picasso.get()
                    .load(picture)
                    .placeholder(R.drawable.placeholder)
                    .fit()
                    .error(R.drawable.dperror)
                    .into(holder.arcadeparticipantssolodp);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        if (object.has("Player")) {
            length = object.optJSONArray("Player").length();
        } else {
            length = object.optJSONArray("team").length();
        }
        return length;
    }

    public class viewrecycler extends RecyclerView.ViewHolder {
        TextView arcadeparticipantsoloname, arcadeparticipantsolousernamename, arcadeparticipantswon,
                arcadeparticipantskill;
        CircleImageView arcadeparticipantssolodp;

        public viewrecycler(@NonNull @NotNull View itemView) {
            super(itemView);
            initviews(itemView);
        }

        private void initviews(View itemView) {
            arcadeparticipantsoloname = itemView.findViewById(R.id.arcadeparticipantsoloname);
            arcadeparticipantsolousernamename = itemView.findViewById(R.id.arcadeparticipantsolousernamename);
            arcadeparticipantswon = itemView.findViewById(R.id.arcadeparticipantswon);
            arcadeparticipantskill = itemView.findViewById(R.id.arcadeparticipantskill);
            arcadeparticipantssolodp = itemView.findViewById(R.id.arcadeparticipantssolodp);
        }
    }
}
