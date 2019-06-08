package ir.radicalcode.app.bmi.root;

import android.app.Application;

import ir.radicalcode.app.bmi.utils.Config;

public class App extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        Config.PACKAGE_NAME = getApplicationContext().getPackageName();

    }
}
