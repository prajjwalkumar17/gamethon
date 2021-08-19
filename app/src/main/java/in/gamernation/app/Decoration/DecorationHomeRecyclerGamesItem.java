package in.gamernation.app.Decoration;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.DimenRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

public class DecorationHomeRecyclerGamesItem extends RecyclerView.ItemDecoration {
    private int itemOffset;

    public DecorationHomeRecyclerGamesItem(int itemOffset) {
        this.itemOffset = itemOffset;
    }

    public DecorationHomeRecyclerGamesItem(@NonNull Context context, @DimenRes int itemOffsetId) {
        this(context.getResources().getDimensionPixelSize(itemOffsetId));
    }

    @Override
    public void getItemOffsets(@NonNull @NotNull Rect outRect, @NonNull @NotNull View view, @NonNull @NotNull RecyclerView parent, @NonNull @NotNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.right = itemOffset;
        outRect.left = itemOffset;
        outRect.top = itemOffset;
        outRect.bottom = itemOffset;

    }

}
