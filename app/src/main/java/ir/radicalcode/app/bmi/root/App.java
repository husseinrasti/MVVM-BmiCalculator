package ir.radicalcode.app.bmi.root;

import android.app.Application;

import java.util.Locale;

import ir.radicalcode.app.bmi.utils.Config;
import ir.radicalcode.app.bmi.utils.Utils;

public class App extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        Config.PACKAGE_NAME = getApplicationContext().getPackageName();

        Locale currentLocale = Utils.getCurrentLocale( getApplicationContext() );

        if ( currentLocale.getLanguage().toString().equals( "fa" ) ) {
            Utils.setSupportRTL( getApplicationContext() );
        }
    }
}
