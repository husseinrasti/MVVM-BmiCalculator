package ir.radicalcode.app.bmi.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

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

}
