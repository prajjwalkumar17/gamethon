package in.gamernation.app.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import in.gamernation.app.APIResponses.ArcadeResponse;
import in.gamernation.app.R;

public class AdapterHomearcade extends RecyclerView.Adapter<AdapterHomearcade.recyclerlayout> {
    private List<ArcadeResponse.League> leagueList;


    @NotNull
    @Override
    public recyclerlayout onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.arcadeleaguescarditem, parent, false);
        return new recyclerlayout(view);
    }

    @NonNull
    @Override
    public void onBindViewHolder(@NonNull @NotNull recyclerlayout holder, int position) {

    }

    @NonNull
    @Override
    public int getItemCount() {
        return 0;
    }

    public class recyclerlayout extends RecyclerView.ViewHolder {
        TextView arcadeitemgameheading, arcadeitementryhead, arcadeitementrycoins, arcadeitemprizeshead, arcadeitemprizescoins, arcadeitemkillpointhead, arcadeitemkillpointcoins, arcadeitemprogresstext,
                arcadeitemprogressparticipantsappliedalready, arcadeitemprogresstotalparticipants, arcadeitemstartdatehead, arcadeitemstartdate, arcadeitemtotalentrieshead, arcadeitemtotalentries,
                arcadeitemmaphead, arcadeitemmap, arcadeitemviewbot;
        ImageView arcadeitemgameimg, arcadeitementrypic, arcadeitemprizesimg, arcadeitemkillpointimg, arcadeitemstartdateimg, arcadeitemtotalentriesimg, arcadeitemmapimg;
        ProgressBar arcadeitemprogressbar;

        public recyclerlayout(@NonNull @NotNull View itemView) {
            super(itemView);
            arcadeitemgameheading = itemView.findViewById(R.id.arcadeitemgameheading);
            arcadeitementryhead = itemView.findViewById(R.id.arcadeitementryhead);
            arcadeitementrycoins = itemView.findViewById(R.id.arcadeitementrycoins);
            arcadeitemprizeshead = itemView.findViewById(R.id.arcadeitemprizeshead);
            arcadeitemprizescoins = itemView.findViewById(R.id.arcadeitemprizescoins);
            arcadeitemkillpointhead = itemView.findViewById(R.id.arcadeitemkillpointhead);
            arcadeitemkillpointcoins = itemView.findViewById(R.id.arcadeitemkillpointcoins);
            arcadeitemprogresstext = itemView.findViewById(R.id.arcadeitemprogresstext);
            arcadeitemprogressparticipantsappliedalready = itemView.findViewById(R.id.arcadeitemprogressparticipantsappliedalready);
            arcadeitemprogresstotalparticipants = itemView.findViewById(R.id.arcadeitemprogresstotalparticipants);
            arcadeitemstartdatehead = itemView.findViewById(R.id.arcadeitemstartdatehead);
            arcadeitemstartdate = itemView.findViewById(R.id.arcadeitemstartdate);
            arcadeitemtotalentrieshead = itemView.findViewById(R.id.arcadeitemtotalentrieshead);
            arcadeitemtotalentries = itemView.findViewById(R.id.arcadeitemtotalentries);
            arcadeitemmaphead = itemView.findViewById(R.id.arcadeitemmaphead);
            arcadeitemmap = itemView.findViewById(R.id.arcadeitemmap);
            arcadeitemviewbot = itemView.findViewById(R.id.arcadeitemviewbot);

            arcadeitemgameimg = itemView.findViewById(R.id.arcadeitemgameimg);
            arcadeitementrypic = itemView.findViewById(R.id.arcadeitementrypic);
            arcadeitemprizesimg = itemView.findViewById(R.id.arcadeitemprizesimg);
            arcadeitemkillpointimg = itemView.findViewById(R.id.arcadeitemkillpointimg);
            arcadeitemstartdateimg = itemView.findViewById(R.id.arcadeitemstartdateimg);
            arcadeitemtotalentriesimg = itemView.findViewById(R.id.arcadeitemtotalentriesimg);
            arcadeitemmapimg = itemView.findViewById(R.id.arcadeitemmapimg);

            arcadeitemprogressbar = itemView.findViewById(R.id.arcadeitemprogressbar);


        }
    }

}
