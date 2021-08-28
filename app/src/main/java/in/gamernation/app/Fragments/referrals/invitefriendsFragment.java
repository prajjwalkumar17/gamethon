package in.gamernation.app.Fragments.referrals;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import in.gamernation.app.Activities.HomeActivity;
import in.gamernation.app.R;

public class invitefriendsFragment extends Fragment {
    private TextView toolwithbackbothead;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_invitefriends, container, false);
        initscreen();
        initviews(root);
        return root;
    }

    private void initscreen() {
        ((HomeActivity) getActivity()).setToolbarInvisible();
        ((HomeActivity) getActivity()).setDrawerLocked();
        ((HomeActivity) getActivity()).setbotInvisible();
    }

    private void initviews(View root) {

        toolwithbackbothead = root.findViewById(R.id.toolwithbackbothead);
        toolwithbackbothead.setText("All Transactions");
    }
}