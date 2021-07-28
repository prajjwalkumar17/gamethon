package in.gamernation.app.Fragments.referrals;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import in.gamernation.app.Activities.HomeActivity;
import in.gamernation.app.R;


public class referralsFragment extends Fragment {

    AppCompatButton referralbot;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_referrals, container, false);
        ((HomeActivity) getActivity()).setToolbarInvisible();
        ((HomeActivity) getActivity()).setDrawerLocked();
        ((HomeActivity) getActivity()).setbotInvisible();

        muticals(root);
        referralbot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainerView, new invitefriendsFragment()).commit();
            }
        });


        return root;
    }

    private void muticals(View root) {
        referralbot = root.findViewById(R.id.referralbot);
    }

}