package in.gamernation.app.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import in.gamernation.app.APIResponses.GamesResponse;
import in.gamernation.app.R;
import in.gamernation.app.RecyclerClickInterfaces.ClicksHomeFraggames;

public class AdapterHomeFragGamesItem extends RecyclerView.Adapter<AdapterHomeFragGamesItem.Myadapter> {

    private List<GamesResponse> list;
    String category;
    private ClicksHomeFraggames clicksHomeFraggames;

    public AdapterHomeFragGamesItem() {
    }

    public AdapterHomeFragGamesItem(List<GamesResponse> list, ClicksHomeFraggames clicksHomeFraggames) {
        this.list = list;
        this.clicksHomeFraggames = clicksHomeFraggames;
    }

    @NonNull
    @Override
    public Myadapter onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.homegamesitem, parent, false);
        return new Myadapter(view);
    }


    @Override
    public void onBindViewHolder(@NonNull @NotNull Myadapter holder, int position) {
        String gameid = list.get(position).getId();
        String name = list.get(position).getName();
        category = list.get(position).getCategory();
        String profilepic = list.get(position).getThumb();

        holder.homegamesname.setText(name);

        Picasso.get()
                .load(profilepic)
                .fit()
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.dperror)
                .centerInside()
                .into(holder.homegamesimg);


    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Myadapter extends RecyclerView.ViewHolder {
        ImageView homegamesimg;
        TextView homegamesname;

        public Myadapter(@NonNull @NotNull View itemView) {
            super(itemView);
            homegamesimg = itemView.findViewById(R.id.homegamesimg);
            homegamesname = itemView.findViewById(R.id.homegamesname);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clicksHomeFraggames.onItemClick(getAdapterPosition());
                }
            });
        }


    }
}
