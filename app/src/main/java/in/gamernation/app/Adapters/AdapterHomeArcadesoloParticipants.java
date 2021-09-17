package in.gamernation.app.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import in.gamernation.app.APIResponses.ArcadeSoloParticipantsResponse;
import in.gamernation.app.R;

public class AdapterHomeArcadesoloParticipants extends RecyclerView.Adapter<AdapterHomeArcadesoloParticipants.recyclerlayout> {
    private List<ArcadeSoloParticipantsResponse.Participant> list;

    public AdapterHomeArcadesoloParticipants() {
    }

    public AdapterHomeArcadesoloParticipants(List<ArcadeSoloParticipantsResponse.Participant> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public recyclerlayout onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerarcadeparticipantsitem, parent, false);
        return new recyclerlayout(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull recyclerlayout holder, int position) {
//        holder.arcadeparticipantsolousernamename.setText(list.get(position).getPicture());
        holder.arcadeparticipantsoloname.setText(list.get(position).getName());
        holder.arcadeparticipantsolousernamename.setText(list.get(position).getUsername());
        Picasso.get()
                .load(list.get(position).getPicture())
                .placeholder(R.drawable.placeholder)
                .fit()
                .error(R.drawable.dperror)
                .centerCrop()
                .into(holder.arcadeparticipantssolodp);

    }

    @NotNull
    @Override
    public int getItemCount() {
        return list.size();
    }

    public class recyclerlayout extends RecyclerView.ViewHolder {
        CircleImageView arcadeparticipantssolodp;
        TextView arcadeparticipantsoloname, arcadeparticipantsolousernamename;

        public recyclerlayout(@NonNull @NotNull View itemView) {
            super(itemView);
            inititemviews(itemView);


        }

        private void inititemviews(View itemView) {
            arcadeparticipantssolodp = itemView.findViewById(R.id.arcadeparticipantssolodp);
            arcadeparticipantsoloname = itemView.findViewById(R.id.arcadeparticipantsoloname);
            arcadeparticipantsolousernamename = itemView.findViewById(R.id.arcadeparticipantsolousernamename);
        }
    }
}
