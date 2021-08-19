package in.gamernation.app.Fragments.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;

import in.gamernation.app.Activities.HomeActivity;
import in.gamernation.app.R;

public class arcadegameFragment extends Fragment {
    LinearLayout tempclick;
    View dummy;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_arcadegame, container, false);
        ((HomeActivity) getActivity()).setToolbarInvisible();
        ((HomeActivity) getActivity()).setDrawerLocked();
        ((HomeActivity) getActivity()).setbotInvisible();
        tempclick = root.findViewById(R.id.tempclick);
        dummy = root.findViewById(R.id.dummy);
        tempclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainerView, new arcadeopenedFragment()).addToBackStack(null).commit();
            }
        });
        dummy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainerView, new arcadeopenedFragment()).addToBackStack(null).commit();
            }
        });

        return root;
    }
}