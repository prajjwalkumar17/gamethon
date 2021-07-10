package in.gamernation.app.Classes;

import in.gamernation.app.Home.HomeActivity;
import in.gamernation.app.Interfaces.navController;

public class ToolController implements navController.toolbarController {
    HomeActivity homeActivity = new HomeActivity();

    @Override
    public void setToolbarInvisible() {
        homeActivity.setToolbarInvisible();

    }

    @Override
    public void setToolbarVisible() {
        homeActivity.setToolbarVisible();
    }
}
