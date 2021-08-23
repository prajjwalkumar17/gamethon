package in.gamernation.app.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import in.gamernation.app.APIResponses.ArcadeViewTeamsResponse;
import in.gamernation.app.R;

public class AdapterArcadeViewTeams extends RecyclerView.Adapter<AdapterArcadeViewTeams.recylerlist> {
    private List<ArcadeViewTeamsResponse.Team> response;

    public AdapterArcadeViewTeams() {
    }

    public AdapterArcadeViewTeams(List<ArcadeViewTeamsResponse.Team> response) {
        this.response = response;
    }

    @NonNull
    @NotNull
    @Override
    public recylerlist onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerarcadeviewteams, parent, false);
        return new recylerlist(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull recylerlist holder, int position) {
        holder.arcadeviewteams.setText("Slot: " + response.get(position).getSlot().toString() + " " + response.get(position).getTeamName());

    }

    @Override
    public int getItemCount() {
        return response.size();
    }

    public class recylerlist extends RecyclerView.ViewHolder {
        TextView arcadeviewteams;

        public recylerlist(@NonNull @NotNull View itemView) {
            super(itemView);
            arcadeviewteams = itemView.findViewById(R.id.arcadeviewteams);
        }
    }
}
