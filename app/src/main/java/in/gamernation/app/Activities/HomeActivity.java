package in.gamernation.app.Activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import in.gamernation.app.CommonInterfaces.botnavController;
import in.gamernation.app.CommonInterfaces.navController;
import in.gamernation.app.Fragments.contactusFragment;
import in.gamernation.app.Fragments.home.homeFragment;
import in.gamernation.app.Fragments.leaderboardFragment;
import in.gamernation.app.Fragments.myleaguesFragment;
import in.gamernation.app.Fragments.myprofileFragment;
import in.gamernation.app.Fragments.mystatsFragment;
import in.gamernation.app.Fragments.playedquizzesFragment;
import in.gamernation.app.Fragments.referrals.referralsFragment;
import in.gamernation.app.Fragments.settingsFragment;
import in.gamernation.app.Fragments.tutorialsFragment;
import in.gamernation.app.Fragments.walletFragment;
import in.gamernation.app.R;

public class HomeActivity extends AppCompatActivity implements navController.drawerControl,
        navController.toolbarController, NavigationView.OnNavigationItemSelectedListener, botnavController.botVisibilityController {
    NavigationView nav_view;
    DrawerLayout drawer;
    ImageView navBotimg;
    View tool;
    NavController navController;
    Animation rotate;
    BottomNavigationView botnav;
    BottomAppBar completebotnav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home);

        //FUNCTIONS

        defaultfragmentonstartup(savedInstanceState);
        muticals();


    }


    private void muticals() {
        nav_view = findViewById(R.id.nav_view);
        drawer = findViewById(R.id.drawer);
        navBotimg = findViewById(R.id.commontoolbar_backbot);
        tool = findViewById(R.id.tool);
        navController = Navigation.findNavController(this, R.id.fragmentContainerView);
        rotate = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate);
        botnav = findViewById(R.id.botnav);
        completebotnav = findViewById(R.id.completebotnav);


        NavigationUI.setupWithNavController(nav_view, navController);
        NavigationUI.setupWithNavController(botnav, navController);
        nav_view.setNavigationItemSelectedListener(this);
        manageBottomNavigation(botnav);
        botnav.getMenu().findItem(R.id.botnav_menu_home).setChecked(true);


    }

    private void manageBottomNavigation(BottomNavigationView botnav) {
        botnav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.botnav_menu_my_leagues:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, new myleaguesFragment()).addToBackStack(null).commit();
                        break;
                    case R.id.botnav_menu_home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, new homeFragment()).addToBackStack(null).commit();
                        break;
                    case R.id.botnav_menu_contact_us:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, new contactusFragment()).addToBackStack(null).commit();
                        break;
                }
                return true;
            }
        });
    }

    private void defaultfragmentonstartup(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, new homeFragment()).commit();
        }
    }

    public void clickMenu(View view) {
        openDrawer(drawer);
        navBotimg.setAnimation(rotate);
    }

    private void openDrawer(DrawerLayout drawer) {
        drawer.openDrawer(GravityCompat.START);
    }

    private void closeDrawer(DrawerLayout drawer) {
        drawer.closeDrawer(GravityCompat.START);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            closeDrawer(drawer);
        } else {
            super.onBackPressed();
            botnav.getMenu().findItem(R.id.botnav_menu_home).setChecked(true);

        }
    }

    /**
     * Interface Methods implemented
     **/

    @Override
    public void setDrawerLocked() {
        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

    }

    @Override
    public void setDrawerunLocked() {
        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_OPEN);
    }

    @Override
    public void setToolbarInvisible() {
        tool.setVisibility(View.INVISIBLE);

    }

    @Override
    public void setToolbarVisible() {
        tool.setVisibility(View.VISIBLE);
    }


    /**
     * Called when an item in the navigation menu is selected.
     *
     * @param item The selected item
     * @return true to display the item as the selected item
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_menu_profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, new myprofileFragment()).addToBackStack(null).commit();
                break;
            case R.id.nav_menu_mystats:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, new mystatsFragment()).addToBackStack(null).commit();
                break;
            case R.id.nav_menu_mywallet:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, new walletFragment()).addToBackStack(null).commit();
                break;
            case R.id.nav_menu_leaderboard:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, new leaderboardFragment()).addToBackStack(null).commit();
                break;
            case R.id.nav_menu_myplayedquiz:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, new playedquizzesFragment()).addToBackStack(null).commit();
                break;
            case R.id.nav_menu_myreferrals:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, new referralsFragment()).addToBackStack(null).commit();
                break;
            case R.id.nav_menu_settings:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, new settingsFragment()).addToBackStack(null).commit();
                break;
            case R.id.nav_menu_tutorial:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, new tutorialsFragment()).addToBackStack(null).commit();
                break;
            case R.id.nav_menu_telegram:
                break;
            case R.id.nav_menu_Instagram:
                break;
            case R.id.nav_menu_Youtube:
                break;
        }
        closeDrawer(drawer);
        return true;
    }

    @Override
    public void setbotInvisible() {
        botnav.setVisibility(View.INVISIBLE);
        completebotnav.setVisibility(View.INVISIBLE);
    }

    @Override
    public void setbotVisible() {
        botnav.setVisibility(View.VISIBLE);
        completebotnav.setVisibility(View.VISIBLE);

    }
}