package in.gamernation.app.Adapters;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

public class HomeFragGamesItemAdapter extends RecyclerView.Adapter<HomeFragGamesItemAdapter.Myadapter> {


    @NonNull
    @Override
    public Myadapter onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return null;
    }


    @Override
    public void onBindViewHolder(@NonNull @NotNull Myadapter holder, int position) {

    }


    @Override
    public int getItemCount() {
        return 0;
    }

    public class Myadapter extends RecyclerView.ViewHolder {
        public Myadapter(@NonNull @NotNull View itemView) {
            super(itemView);


        }
    }
}
