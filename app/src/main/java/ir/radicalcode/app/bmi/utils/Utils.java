package ir.radicalcode.app.bmi.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Build;

import java.util.Locale;

public class Utils {

    public static Boolean isPackageExisted( Context context , String targetPackage ) {
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo info = pm.getPackageInfo( targetPackage , PackageManager.GET_META_DATA );
        } catch ( PackageManager.NameNotFoundException e ) {
            return false;
        }
        return true;
    }

    public static Locale getCurrentLocale( Context context ) {
        if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.N ) {
            return context.getResources().getConfiguration().getLocales().get( 0 );
        } else {
            return context.getResources().getConfiguration().locale;
        }
    }

    public static void setRTL( Context context ) {
        Configuration configuration = context.getResources().getConfiguration();
        configuration.setLayoutDirection( new Locale( "fa" ) );
        context.getResources().updateConfiguration( configuration , context.getResources().getDisplayMetrics() );
    }
}
