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
import in.gamernation.app.RecyclerClickInterfaces.ClickArcadeGameItem;

public class AdapterHomearcade extends RecyclerView.Adapter<AdapterHomearcade.recyclerlayout> {
    private List<ArcadeResponse.League> leagueList;
    private String id, thumb, name, map, startdate, entrycoins, prizescoins, filled, killcoins, totalparticipants;
    private Integer bonuscoins;
    private ClickArcadeGameItem arcadeGameItemclick;

    public AdapterHomearcade() {
    }

    public AdapterHomearcade(List<ArcadeResponse.League> leagueList, ClickArcadeGameItem arcadeGameItemclick) {
        this.leagueList = leagueList;
        this.arcadeGameItemclick = arcadeGameItemclick;
    }

    @NotNull
    @Override
    public recyclerlayout onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerarcadeleaguescarditem, parent, false);
        return new recyclerlayout(view);
    }

    @NonNull
    @Override
    public void onBindViewHolder(@NonNull @NotNull recyclerlayout holder, int position) {
        extractresourcesfromlist(position);
        holder.arcadeitemgameheading.setText(name);
        holder.arcadeitementrycoins.setText(entrycoins + "\nCoins");
        holder.arcadeitemprizescoins.setText(prizescoins + "\nCoins");
        holder.arcadeitemkillpointcoins.setText(killcoins + "\nCoins");
        holder.arcadeitemprogressparticipantsappliedalready.setText(filled);
        holder.arcadeitemprogresstotalparticipants.setText(totalparticipants);
        holder.arcadeitemtotalentries.setText(totalparticipants);
        holder.arcadeitemmap.setText(map);
        holder.arcadeitemstartdate.setText(startdate);
        holder.arcadeitemprogressbar.setProgress(leagueList.get(position).getFilled() / leagueList.get(position).getTotalParticipant());



    }

    private void extractresourcesfromlist(int position) {
        id = leagueList.get(position).getId();
        thumb = leagueList.get(position).getThumb();
        name = leagueList.get(position).getName();
        entrycoins = leagueList.get(position).getEntry().toString();
        prizescoins = leagueList.get(position).getPrizes().toString();
        killcoins = leagueList.get(position).getKillCoins().toString();
        filled = leagueList.get(position).getFilled().toString();
        totalparticipants = leagueList.get(position).getTotalParticipant().toString();
        map = leagueList.get(position).getMap();
        startdate = leagueList.get(position).getStartDate();
        bonuscoins = leagueList.get(position).getBonus();
    }

    @NonNull
    @Override
    public int getItemCount() {
        return leagueList.size();
    }

    public class recyclerlayout extends RecyclerView.ViewHolder {
        TextView arcadeitemgameheading, arcadeitementryhead, arcadeitementrycoins, arcadeitemprizeshead, arcadeitemprizescoins, arcadeitemkillpointhead,
                arcadeitemkillpointcoins, arcadeitemprogresstext,
                arcadeitemprogressparticipantsappliedalready, arcadeitemprogresstotalparticipants, arcadeitemstartdatehead, arcadeitemstartdate,
                arcadeitemtotalentrieshead, arcadeitemtotalentries,
                arcadeitemmaphead, arcadeitemmap, arcadeitemviewbot;
        ImageView arcadeitemgameimg, arcadeitementrypic, arcadeitemprizesimg, arcadeitemkillpointimg, arcadeitemstartdateimg, arcadeitemtotalentriesimg,
                arcadeitemmapimg;
        ProgressBar arcadeitemprogressbar;

        public recyclerlayout(@NonNull @NotNull View itemView) {
            super(itemView);
            initviews(itemView);
            arcadeitemviewbot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    arcadeGameItemclick.onviewItemClick(getAdapterPosition());
                }
            });


        }

        private void initviews(View itemView) {
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

