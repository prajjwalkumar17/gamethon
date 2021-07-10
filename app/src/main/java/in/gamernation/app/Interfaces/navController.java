package in.gamernation.app.Interfaces;

public interface navController {
    interface drawerControl {
        public void setDrawerLocked();

        public void setDrawerunLocked();
    }

    interface toolbarController {
        public void setToolbarInvisible();

        public void setToolbarVisible();
    }
}
