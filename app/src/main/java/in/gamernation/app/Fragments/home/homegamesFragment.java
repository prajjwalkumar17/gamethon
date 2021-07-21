package in.gamernation.app.Fragments.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.google.android.material.card.MaterialCardView;

import in.gamernation.app.R;

public class homegamesFragment extends Fragment {
    MaterialCardView crd1, crd2, crd3, crd4, crd5, crd6;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_homegames, container, false);

        muticals(root);


        return root;
    }

    private void muticals(View root) {
        crd1 = root.findViewById(R.id.crd1);
        crd2 = root.findViewById(R.id.crd2);
        crd3 = root.findViewById(R.id.crd3);
        crd4 = root.findViewById(R.id.crd4);
        crd5 = root.findViewById(R.id.crd5);
        crd6 = root.findViewById(R.id.crd6);
        clicklistnersoncards();
    }

    private void clicklistnersoncards() {
        crd1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainerView, new freefirepubgFragment()).addToBackStack(null).commit();

            }
        });
        crd2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainerView, new fanbattleFragment()).addToBackStack(null).commit();

            }
        });
        crd3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainerView, new ludokingFragment()).addToBackStack(null).commit();
            }
        });
        crd4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainerView, new freefirepubgFragment()).addToBackStack(null).commit();
            }
        });
        crd5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainerView, new freefirepubgFragment()).addToBackStack(null).commit();
            }
        });
        crd6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
}