package in.gamernation.app.Home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import in.gamernation.app.Interfaces.navController;
import in.gamernation.app.R;

public class HomeActivity extends AppCompatActivity implements navController.drawerControl, navController.toolbarController {
    NavigationView nav_view;
    DrawerLayout drawer;
    ImageView navBotimg;
    View tool;
    NavController navController;
    Animation rotate;
    BottomNavigationView botnav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home);

        //FUNCTIONS

        muticals();


    }


    private void muticals() {
        nav_view = findViewById(R.id.nav_view);
        drawer = findViewById(R.id.drawer);
        navBotimg = findViewById(R.id.imageView2);
        tool = findViewById(R.id.tool);
        navController = Navigation.findNavController(this, R.id.fragmentContainerView);
        rotate = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate);
        botnav = findViewById(R.id.botnav);


        NavigationUI.setupWithNavController(nav_view, navController);
        NavigationUI.setupWithNavController(botnav, navController);

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

}