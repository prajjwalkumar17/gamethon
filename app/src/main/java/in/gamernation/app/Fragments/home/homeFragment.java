package in.gamernation.app.Fragments.home;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;

import java.util.Timer;

import in.gamernation.app.Activities.HomeActivity;
import in.gamernation.app.Adapters.HomeActivitySliderAdapter;
import in.gamernation.app.Adapters.SliderAdapter;
import in.gamernation.app.R;

public class homeFragment extends Fragment {
    final int noofimganddots = 5;
    TabLayout tabLayout;
    ViewPager2 homepager;
    HomeActivitySliderAdapter homeActivitySliderAdapter;
    LinearLayout dotlayout;
    SliderAdapter sliderAdapter;
    ViewPager2 sliderpager;
    Drawable[] sliderimg;
    TextView[] dot;
    Timer timer;
    Handler handler;
    private FragmentActivity myContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        ((HomeActivity) getActivity()).setbotVisible();

        muticals(root);

        return root;
    }
    @Override
    public void onAttach(@NonNull Context context) {
        myContext = (HomeActivity) context;
        super.onAttach(context);
    }

    private void muticals(View root) {
        tabLayout = root.findViewById(R.id.tab_layout);
        homepager = root.findViewById(R.id.home_pager);
        FragmentManager fragmentManager = getChildFragmentManager();
        homeActivitySliderAdapter = new HomeActivitySliderAdapter(fragmentManager, getLifecycle());
        homepager.setAdapter(homeActivitySliderAdapter);
        tabLayout.addTab(tabLayout.newTab().setText("GAMES"));
        tabLayout.addTab(tabLayout.newTab().setText("QUIZES"));
        managetabs(tabLayout);
        swipemanagerforpager(homepager, tabLayout);
        dotlayout = root.findViewById(R.id.sliderdotcontainer);
        sliderpager = root.findViewById(R.id.sliderpager);
//        timer = new Timer();
//        handler = new Handler();
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                handler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        int i = sliderpager.getCurrentItem();
//                        if (i == noofimganddots - 1) {
//                            i = 0;
//                            sliderpager.setCurrentItem(i, true);
//                        } else {
//                            i++;
//                            sliderpager.setCurrentItem(i, true);
//                        }
//
//                    }
//                });
//            }
//        }, 4000, 5000);
//

        dot = new TextView[noofimganddots];
        imageofslider(sliderimg, dot);


    }

    private void imageofslider(Drawable[] sliderimgs, TextView[] dot) {
        sliderimgs = new Drawable[noofimganddots];
        sliderimgs[0] = getResources().getDrawable(R.drawable.pager1);
        sliderimgs[1] = getResources().getDrawable(R.drawable.pager2);
        sliderimgs[2] = getResources().getDrawable(R.drawable.pager3);
        sliderimgs[3] = getResources().getDrawable(R.drawable.pager4);
        sliderimgs[4] = getResources().getDrawable(R.drawable.pager5);

        sliderAdapter = new SliderAdapter(sliderimgs);
        sliderpager.setAdapter(sliderAdapter);
        dotindicator(dot);
        sliderpager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                selectedIndicator(position);
                super.onPageSelected(position);
            }
        });
    }

    private void selectedIndicator(int position) {
        for (int i = 0; i < noofimganddots; i++) {
            if (i == position) {
                dot[i].setTextColor(getResources().getColor(R.color.purple_700));
            } else {
                dot[i].setTextColor(getResources().getColor(R.color.teal_700));
            }

        }
    }

    private void dotindicator(TextView[] doty) {
        for (int i = 0; i < noofimganddots; i++) {
            doty[i] = new TextView(getContext());
            doty[i].setText(Html.fromHtml("&#9679;"));
            doty[i].setTextSize(18);
            dotlayout.addView(doty[i]);

        }

    }

    private void managetabs(TabLayout tabLayout) {
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                homepager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void swipemanagerforpager(ViewPager2 homepager, TabLayout tabLayout) {
        homepager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            /**
             * This method will be invoked when a new page becomes selected. Animation is not
             * necessarily complete.
             *
             * @param position Position index of the new selected page.
             */
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });
    }

}


