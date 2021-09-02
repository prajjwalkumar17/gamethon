package in.gamernation.app.Decoration;

import android.annotation.SuppressLint;

import com.facebook.shimmer.Shimmer;
import com.facebook.shimmer.ShimmerDrawable;

import in.gamernation.app.R;

public class Shimmerapplier {

    public static void initshimmer() {
        @SuppressLint("ResourceAsColor")
        Shimmer shimmer = new Shimmer.ColorHighlightBuilder()
                .setBaseColor(R.color.shimmer_secondary)
                .setBaseAlpha(1)
                .setHighlightColor(R.color.shimmer_primary)
                .setHighlightAlpha(1)
                .setDropoff(50)
                .build();

        ShimmerDrawable shimmerDrawable = new ShimmerDrawable();
        shimmerDrawable.setShimmer(shimmer);
    }

}
