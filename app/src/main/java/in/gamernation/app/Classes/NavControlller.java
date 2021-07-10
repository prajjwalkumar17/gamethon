package in.gamernation.app.Classes;

import in.gamernation.app.Home.HomeActivity;
import in.gamernation.app.Interfaces.navController;
import in.gamernation.app.R;

public class NavControlller implements navController.drawerControl {
    HomeActivity homeActivity = new HomeActivity();

    @Override
    public void setDrawerLocked() {
        homeActivity.setDrawerLocked();
    }

    @Override
    public void setDrawerunLocked() {
        homeActivity.setDrawerunLocked();

    }
}
