package in.gamernation.app.Fragments.home;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import in.gamernation.app.Activities.HomeActivity;
import in.gamernation.app.R;
import in.gamernation.app.Utils.Constants;


public class arcadeopenedFragment extends Fragment {
    String league_pic;
    private ImageView arcadeopenedtopimg;
    private TextView arcadeopenedgamename, arcadeopenedentryfee, arcadeopenedtotalparticipants, arcadeopenedprizecoins, arcadeopenedkillcoins, arcadeopenedgamemode, arcadeopenedbonuscoins,
            arcadeopenedmap, arcadeopeneddate;
    private AppCompatButton arcadeopenedprizepoolbreakup, arcadeopenedfullprizepool;
    private Context thiscontext;
    private String thumb, id, name, entrycoins, prizescoins, killcoins, filled, totalparticipants, map, startdate, bonus;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_arcadeopened, container, false);

        initscreen();
        initviews(root);
        return root;
    }

    @Override
    public void onAttach(@NonNull @NotNull Context context) {
        super.onAttach(context);
        thiscontext = context;
    }

    private void initscreen() {
        ((HomeActivity) getActivity()).setToolbarInvisible();
        ((HomeActivity) getActivity()).setDrawerLocked();
        ((HomeActivity) getActivity()).setbotInvisible();
    }

    private void initviews(View root) {
        arcadeopenedgamename = root.findViewById(R.id.arcadeopenedgamename);
        arcadeopenedentryfee = root.findViewById(R.id.arcadeopenedentryfee);
        arcadeopenedtotalparticipants = root.findViewById(R.id.arcadeopenedtotalparticipants);
        arcadeopenedprizecoins = root.findViewById(R.id.arcadeopenedprizecoins);
        arcadeopenedkillcoins = root.findViewById(R.id.arcadeopenedkillcoins);
        arcadeopenedgamemode = root.findViewById(R.id.arcadeopenedgamemode);
        arcadeopenedbonuscoins = root.findViewById(R.id.arcadeopenedbonuscoins);
        arcadeopenedmap = root.findViewById(R.id.arcadeopenedmap);
        arcadeopenedprizepoolbreakup = root.findViewById(R.id.arcadeopenedprizepoolbreakup);
        arcadeopenedfullprizepool = root.findViewById(R.id.arcadeopenedfullprizepool);
        arcadeopeneddate = root.findViewById(R.id.arcadeopeneddate);
        arcadeopenedtopimg = root.findViewById(R.id.arcadeopenedtopimg);


        fetchdatafromsharedpref();

    }

    private void fetchdatafromsharedpref() {
        SharedPreferences preferences = thiscontext.getSharedPreferences(Constants.ARCADEGAMEPREF, Context.MODE_PRIVATE);
        id = preferences.getString(Constants.arcadeopenedid, "no data found!!!");
        name = preferences.getString(Constants.arcadeopenedname, "no data found!!!");
        thumb = preferences.getString(Constants.arcadeopenedthumb, "no data found!!!");
        entrycoins = preferences.getString(Constants.arcadeopenedentrycoins, "no data found!!!");
        prizescoins = preferences.getString(Constants.arcadeopenedprizescoins, "no data found!!!");
        killcoins = preferences.getString(Constants.arcadeopenedkillcoins, "no data found!!!");
        map = preferences.getString(Constants.arcadeopenedmap, "no data found!!!");
        league_pic = preferences.getString(Constants.arcadeopenedleaguepic, "no data found!!!");
        startdate = preferences.getString(Constants.arcadeopenedstartdate, "no data found!!!");
        filled = preferences.getString(Constants.arcadeopenedfilled, "no data found!!!");
        totalparticipants = preferences.getString(Constants.arcadeopenedtotalparticipants, "no data found!!!");
        bonus = preferences.getString(Constants.arcadeopenedbonus, "no data found!!!");


        setdatatoviews();


    }

    private void setdatatoviews() {
        arcadeopenedgamename.setText(name);
        arcadeopenedentryfee.setText(entrycoins + " Coins");
        arcadeopenedtotalparticipants.setText(totalparticipants);
        arcadeopenedprizecoins.setText(prizescoins + " Coins");
        arcadeopenedkillcoins.setText(killcoins + " Coins per kill");
        arcadeopenedgamemode.setText("TPP/FPP");
        arcadeopenedbonuscoins.setText("");
        arcadeopenedmap.setText(map);
        arcadeopeneddate.setText(startdate);
        arcadeopenedbonuscoins.setText(bonus);
        Picasso.get()
                .load(league_pic)
                .placeholder(R.drawable.placeholder)
                .fit()
                .error(R.drawable.dperror)
                .into(arcadeopenedtopimg);


    }


}