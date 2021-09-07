package in.gamernation.app.Fragments.home;

import android.content.Context;
import android.os.Bundle;
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

import com.denzcoskun.imageslider.ImageSlider;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;

import in.gamernation.app.APICalls.APICallsOkHttp;
import in.gamernation.app.Activities.HomeActivity;
import in.gamernation.app.Adapters.AdapterHomeActivitySlider;
import in.gamernation.app.Adapters.AdapterHomeSlider;
import in.gamernation.app.Adapters.Adapterhometopimgslider;
import in.gamernation.app.R;
import in.gamernation.app.Utils.CommonMethods;
import in.gamernation.app.Utils.Constants;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class homeFragment extends Fragment {
    final int noofimganddots = 5;
    TabLayout tabLayout;
    ViewPager2 homepager;
    AdapterHomeActivitySlider adapterHomeActivitySlider;
    LinearLayout dotlayout;
    AdapterHomeSlider adapterHomeSlider;
    ImageSlider sliderpager;
    /*    Drawable[] sliderimg;
        Timer timer;
        Handler handler;*/
    TextView[] dot;
    private FragmentActivity myContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        ((HomeActivity) getActivity()).setbotVisible();

        muticals(root);
        fetchhomeslider();
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
        adapterHomeActivitySlider = new AdapterHomeActivitySlider(fragmentManager, getLifecycle());
        homepager.setAdapter(adapterHomeActivitySlider);
        tabLayout.addTab(tabLayout.newTab().setText("GAMES"));
        tabLayout.addTab(tabLayout.newTab().setText("QUIZES"));
        managetabs(tabLayout);
        swipemanagerforpager(homepager, tabLayout);
        dotlayout = root.findViewById(R.id.sliderdotcontainer);
        sliderpager = root.findViewById(R.id.sliderpageree);

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

//        dot = new TextView[noofimganddots];
//        imageofslider(sliderimg, dot);


    }

    private void fetchhomeslider() {
        String url = APICallsOkHttp.urlbuilderforhttp(Constants.w3devbaseurl + "games/slider");
        APICallsOkHttp.okhttpmaster().newCall(APICallsOkHttp.requesthttpwithoutauth(url)).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                CommonMethods.DisplayLongTOAST(getContext(), e.getMessage());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {

                final String responsez = response.body().string();
//                CommonMethods.LOGthesite(Constants.LOG,responsez);
                try {
                    JSONArray object = new JSONArray(responsez);
                    Adapterhometopimgslider adapterhometopimgslider = new Adapterhometopimgslider(object);

//                    sliderpager.setAdapter(adapterhometopimgslider);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

    }


/*    private void imageofslider(Drawable[] sliderimgs, TextView[] dot) {
        sliderimgs = new Drawable[noofimganddots];
        sliderimgs[0] = getResources().getDrawable(R.drawable.pager1);
        sliderimgs[1] = getResources().getDrawable(R.drawable.pager2);
        sliderimgs[2] = getResources().getDrawable(R.drawable.pager3);
        sliderimgs[3] = getResources().getDrawable(R.drawable.pager4);
        sliderimgs[4] = getResources().getDrawable(R.drawable.pager5);

        adapterHomeSlider = new AdapterHomeSlider(sliderimgs);
        sliderpager.setAdapter(adapterHomeSlider);
        dotindicator(dot);
        sliderpager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                selectedIndicator(position);
                super.onPageSelected(position);
            }
        });
    }*/

    private void selectedIndicator(int position) {
        for (int i = 0; i < noofimganddots; i++) {
            if (i == position) {
                dot[i].setTextColor(getResources().getColor(R.color.final_secondary));
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


