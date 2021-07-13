package in.gamernation.app.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import in.gamernation.app.Fragments.homegamesFragment;
import in.gamernation.app.Fragments.homequizesFragment;

public class HomefragAdapter extends FragmentStateAdapter {


    public HomefragAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0) {
            return new homegamesFragment();
        }
        return new homequizesFragment();
    }


    @Override
    public int getItemCount() {
        //no of fragments
        return 2;
    }
}
