package in.gamernation.app.Adapters;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import in.gamernation.app.R;

public class AdapterHomeSlider extends RecyclerView.Adapter<AdapterHomeSlider.MyViewHolder> {
    Drawable[] list;

    public AdapterHomeSlider(Drawable[] list) {
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.img_slider, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterHomeSlider.MyViewHolder holder, int position) {
        holder.view.setBackground(list[position]);

    }

    @Override
    public int getItemCount() {
        return list.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        View view;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView.findViewById(R.id.sliderpager);
        }
    }
}
