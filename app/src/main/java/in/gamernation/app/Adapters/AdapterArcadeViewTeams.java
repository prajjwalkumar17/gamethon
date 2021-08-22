package in.gamernation.app.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import in.gamernation.app.APIResponses.ArcadeViewTeamsResponse;
import in.gamernation.app.R;

public class AdapterArcadeViewTeams extends RecyclerView.Adapter<AdapterArcadeViewTeams.recylerlist> {
    private ArcadeViewTeamsResponse response;

    public AdapterArcadeViewTeams() {
    }

    public AdapterArcadeViewTeams(ArcadeViewTeamsResponse response) {
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
        holder.arcadeviewteams.setText("Slot: " + response.getSlot().toString() + " " + response.getTeamName());

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class recylerlist extends RecyclerView.ViewHolder {
        TextView arcadeviewteams;

        public recylerlist(@NonNull @NotNull View itemView) {
            super(itemView);
            arcadeviewteams = itemView.findViewById(R.id.arcadeviewteams);
        }
    }
}
