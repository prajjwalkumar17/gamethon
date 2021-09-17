package in.gamernation.app.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.browser.customtabs.CustomTabsIntent;
import androidx.fragment.app.FragmentActivity;

import com.smarteist.autoimageslider.SliderViewAdapter;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import in.gamernation.app.R;
import in.gamernation.app.Utils.Constants;

public class AdapterSlidernew extends SliderViewAdapter<AdapterSlidernew.Holder> {

    JSONArray jsonArray;
    Context context;
    FragmentActivity activity;

    public AdapterSlidernew(JSONArray jsonArray, Context context, FragmentActivity activity) {
        this.jsonArray = jsonArray;
        this.context = context;
        this.activity = activity;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.slideritem, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder viewHolder, int position) {
        JSONObject object = jsonArray.optJSONObject(position);
        try {
            String pic = object.getString("picture");
            String url = object.getString("link");

            viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    CustomTabsIntent.Builder customtabintent = new CustomTabsIntent.Builder();
                    opencustomtabyyy(activity, customtabintent.build(), Uri.parse(url));


                }
            });

            Picasso.get()
                    .load(pic)
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.dperror)
                    .into(viewHolder.imageView);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getCount() {
        return jsonArray.length();
    }

    public class Holder extends SliderViewAdapter.ViewHolder {
        ImageView imageView;

        public Holder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.sliderimage);
        }



    }

    private void opencustomtabyyy(FragmentActivity activity, CustomTabsIntent build, Uri uri) {
        String PackageName = "com.android.chrome";
        if (PackageName != null) {
            build.intent.setPackage(PackageName);
            build.launchUrl(activity, uri);
        } else {
            context.startActivity(new Intent(Intent.ACTION_VIEW, uri));
        }
    }


}
