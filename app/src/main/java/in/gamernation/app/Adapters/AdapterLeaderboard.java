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

public class AdapterLeaderboard extends RecyclerView.Adapter<AdapterLeaderboard.recycleritems> {


    JSONObject responselist;
    int length;


    public AdapterLeaderboard() {
    }

    public AdapterLeaderboard(JSONObject responselist) {
        this.responselist = responselist;
    }

    @NonNull
    @NotNull
    @Override
    public recycleritems onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerleaderboarditems, parent, false);
        return new recycleritems(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull recycleritems holder, int position) {
        try {
            JSONArray responses = responselist.getJSONArray("Leaderboard");
            JSONObject object = responses.getJSONObject(position + 3);
            JSONObject user = object.getJSONObject("user");
            String name = user.getString("name");
            String dp = user.getString("picture");
            String woncoins = object.getString("won");
            String positionz = String.valueOf(position + 4);

            holder.leaderboardrecyclername.setText(name);

            holder.leaderboardrecyclercoinswon.setText(String.valueOf(woncoins) + " Coins");
            Picasso.get()
                    .load(dp)
                    .placeholder(R.drawable.placeholder)
                    .fit()
                    .error(R.drawable.dperror)
                    .centerCrop()
                    .into(holder.leaderboardrecyclerdp);
            String rank = "#" + positionz;
            holder.leaderboardrecyclerrank.setText(rank);

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    @Override
    public int getItemCount() {
        try {
            length = responselist.getJSONArray("Leaderboard").length() - 3;
            return length;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return length;
    }


    public static class recycleritems extends RecyclerView.ViewHolder {
        private TextView leaderboardrecyclername, leaderboardrecyclercoinswon, leaderboardrecyclerrank;
        private CircleImageView leaderboardrecyclerdp;

        public recycleritems(@NonNull @NotNull View itemView) {
            super(itemView);
            leaderboardrecyclername = itemView.findViewById(R.id.leaderboardrecyclername);
            leaderboardrecyclercoinswon = itemView.findViewById(R.id.leaderboardrecyclercoinswon);
            leaderboardrecyclerrank = itemView.findViewById(R.id.leaderboardrecyclerrank);
            leaderboardrecyclerdp = itemView.findViewById(R.id.leaderboardrecyclerdp);

        }
    }
}


