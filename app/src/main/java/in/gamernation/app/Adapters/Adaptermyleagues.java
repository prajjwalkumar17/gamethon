package in.gamernation.app.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import in.gamernation.app.R;
import in.gamernation.app.RecyclerClickInterfaces.ClickMyleagueitem;

public class Adaptermyleagues extends RecyclerView.Adapter<Adaptermyleagues.recyclerviews> {
    JSONObject object;
    Context context;
    int length;
    String typogame;
    ClickMyleagueitem clickMyleagueitem;
    String game_type;
    String returnedgame_type;

    public Adaptermyleagues() {
    }

    public Adaptermyleagues(JSONObject object, Context context, String typogame, ClickMyleagueitem clickMyleagueitem) {
        this.object = object;
        this.context = context;
        this.typogame = typogame;
        this.clickMyleagueitem = clickMyleagueitem;
    }

    @NonNull
    @NotNull
    @Override
    public recyclerviews onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclermyleaguesitem, parent, false);
        return new recyclerviews(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull recyclerviews holder, int position) {


        try {
            JSONArray Leagues = object.getJSONArray("Leagues");
            JSONObject leaguedetail = Leagues.getJSONObject(position);
//            String id = leaguedetail.getString("_id");

            JSONObject leagueobject = leaguedetail.getJSONObject("league");

            game_type = leagueobject.getString("game_type");
            changebuttonaccordingtotype(game_type, holder, typogame);

//            String leagueid = leagueobject.getString("_id");
            String name = leagueobject.getString("name");
            String entry = leagueobject.getString("entry");
            String prizes = leagueobject.getString("prizes");
            String kill_coins = leagueobject.getString("kill_coins");
            String start_date = leagueobject.getString("start_date");
            String total_participant = leagueobject.getString("total_participant");
            String map = leagueobject.getString("map");
//            String bonus = leagueobject.getString("bonus");
            String filled = leagueobject.getString("filled");
//            JSONObject credsforgame=leagueobject.getJSONObject("setting");
//            String roomId=credsforgame.getString("roomId");
//            String password=credsforgame.getString("password");
//            CommonMethods.LOGthesite(Constants.LOG,roomId+"\n" +password);
            JSONObject gamedetail = leagueobject.getJSONObject("game");
//            String gameid = gamedetail.getString("_id");
//            String presentgamename = gamedetail.getString("name");
//            String category = gamedetail.getString("category");
//            String league_pic = gamedetail.getString("league_pic");
            String thumb = gamedetail.getString("thumb");


//        JSONArray prizepool=leagueobject.getJSONArray("prize_pool");
//        JSONObject prizeobj=prizepool.getJSONObject(position);
//        String prizepoolid=prizeobj.getString("_id");
//        String prizepoolstanding=prizeobj.getString("standing");
//        String priizepoolprize=prizeobj.getString("prize");

            holder.myleaguegameheading.setText(name);

            holder.myleagueentrycoins.setText(entry + "\nCoins");
            holder.myleagueprizescoins.setText(prizes + "\nCoins");
            holder.myleaguekillpointcoins.setText(kill_coins + "\nCoins");

            holder.myleagueprogressparticipantsappliedalready.setText(filled);
            holder.myleagueprogresstotalparticipants.setText(total_participant);
            holder.myleaguestartdate.setText(start_date);
            holder.myleaguetotalentries.setText(total_participant);
            holder.myleaguemap.setText(map);

            Picasso.get()
                    .load(thumb)
                    .placeholder(R.drawable.placeholder)
//                .fit()
                    .error(R.drawable.dperror)
//                .centerCrop()
                    .into(holder.myleaguegameimg);

//        holder.myleagueviewbot.

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void changebuttonaccordingtotype(String game_type, recyclerviews holder, String typogamez) {
        if (typogamez.equals("upcoming")) {
            if (game_type.equals("SOLO")) {
                holder.myleagueviewbot.setText("Game details");
            } else {
                holder.myleagueviewbot.setText("Team Details");
            }
        } else if (typogamez.equals("live")) {
            holder.myleagueviewbot.setText("View RoomId and Password");
        } else {
            if (game_type.equals("SOLO")) {
                holder.myleagueviewbot.setText("View LeaderBoard");
            } else {
                holder.myleagueviewbot.setText("View Team LeaderBoard");
            }
        }
    }


    @Override
    public int getItemCount() {
        try {
            length = object.getJSONArray("Leagues").length();
            return length;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return length;
    }


    public class recyclerviews extends RecyclerView.ViewHolder {
        TextView myleaguegameheading, myleagueentryhead, myleagueentrycoins, myleagueprizeshead, myleagueprizescoins, myleaguekillpointhead,
                myleaguekillpointcoins, myleagueprogresstext,
                myleagueprogressparticipantsappliedalready, myleagueprogresstotalparticipants, myleaguestartdatehead, myleaguestartdate,
                myleaguetotalentrieshead, myleaguetotalentries,
                myleaguemaphead, myleaguemap, myleagueviewbot;
        ImageView myleaguegameimg, myleagueentrypic, myleagueprizesimg, myleaguekillpointimg, myleaguestartdateimg, myleaguetotalentriesimg,
                myleaguemapimg;
        ProgressBar myleagueprogressbar;

        public recyclerviews(@NonNull @NotNull View itemView) {
            super(itemView);
            initviews(itemView);


            myleagueviewbot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        clickMyleagueitem.onviewItemClick(getAdapterPosition());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            });

        }

        private void initviews(View itemView) {
            myleaguegameheading = itemView.findViewById(R.id.myleagueheading);
            myleagueentryhead = itemView.findViewById(R.id.myleagueentryhead);
            myleagueentrycoins = itemView.findViewById(R.id.myleagueentrycoins);
            myleagueprizeshead = itemView.findViewById(R.id.myleagueprizeshead);
            myleagueprizescoins = itemView.findViewById(R.id.myleagueprizescoins);
            myleaguekillpointhead = itemView.findViewById(R.id.myleaguekillpointhead);
            myleaguekillpointcoins = itemView.findViewById(R.id.myleaguekillpointcoins);
            myleagueprogresstext = itemView.findViewById(R.id.myleagueprogresstext);
            myleagueprogressparticipantsappliedalready = itemView.findViewById(R.id.myleagueprogressparticipantsappliedalready);
            myleagueprogresstotalparticipants = itemView.findViewById(R.id.myleagueprogresstotalparticipants);
            myleaguestartdatehead = itemView.findViewById(R.id.myleaguestartdatehead);
            myleaguestartdate = itemView.findViewById(R.id.myleaguestartdate);
            myleaguetotalentrieshead = itemView.findViewById(R.id.myleaguetotalentrieshead);
            myleaguetotalentries = itemView.findViewById(R.id.myleaguetotalentries);
            myleaguemaphead = itemView.findViewById(R.id.myleaguemaphead);
            myleaguemap = itemView.findViewById(R.id.myleaguemap);
            myleagueviewbot = itemView.findViewById(R.id.myleagueviewbot);


/*            if(typogame.equals("live")){
//                myleagueviewbot.setText("View RoomId and Password");
            } else if(typogame.equals("past")){
//                myleagueviewbot.setText("View LeaderBoard");
            } else {
//                if(returnedgame_type.equals("SOLO"))
//                myleagueviewbot.setText("Game details");
            }*/

            myleaguegameimg = itemView.findViewById(R.id.myleaguegameimg);
            myleagueentrypic = itemView.findViewById(R.id.myleagueentrypic);
            myleagueprizesimg = itemView.findViewById(R.id.myleagueprizesimg);
            myleaguekillpointimg = itemView.findViewById(R.id.myleaguekillpointimg);
            myleaguestartdateimg = itemView.findViewById(R.id.myleaguestartdateimg);
            myleaguetotalentriesimg = itemView.findViewById(R.id.myleaguetotalentriesimg);
            myleaguemapimg = itemView.findViewById(R.id.myleaguemapimg);

            myleagueprogressbar = itemView.findViewById(R.id.myleagueprogressbar);


        }
    }
}
