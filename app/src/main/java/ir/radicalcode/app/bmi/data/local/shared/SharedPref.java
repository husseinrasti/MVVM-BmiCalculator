package ir.radicalcode.app.bmi.data.local.shared;

import android.content.Context;
import android.content.SharedPreferences;

import ir.radicalcode.app.bmi.data.local.SharedPrefDataSource;

public class SharedPref implements SharedPrefDataSource {

    private static final String NAME_SESSION_PREF = "bmi_preference";
    private static final String KEY_USER_AGE = "userAge";
    private static final String KEY_USER_SEX = "userSex";
    private static final String KEY_USER_WEIGHT = "userWeight";
    private static final String KEY_USER_WEIGHT_UNIT = "userWeightUnit";
    private static final String KEY_USER_WEIGHT_UNIT_POS = "userWeightUnitPos";
    private static final String KEY_USER_HEIGHT = "userHeight";
    private static final String KEY_USER_HEIGHT_INCH = "userHeightInch";
    private static final String KEY_USER_HEIGHT_UNIT = "userHeightUnit";
    private static final String KEY_USER_HEIGHT_UNIT_POS = "userHeightUnitPos";

    private static final String KEY_FIRST_START = "KEY_FIRST_START";
    private static final String KEY_GRANT = "KEY_GRANT";

    private static Context mContext;

    private static SharedPreferences pref;
    private static SharedPreferences.Editor editor;

    private static SharedPref sharedPref;

    public static SharedPref getInstance( Context context ) {
        mContext = context;
        if ( sharedPref == null ) {
            sharedPref = new SharedPref();
            initPref();
        }

        return sharedPref;
    }

    private static void initPref() {
        pref = mContext.getSharedPreferences( NAME_SESSION_PREF , Context.MODE_PRIVATE );
    }

    public static void setUserAge( String key ) {
        editor = pref.edit();
        editor.putString( KEY_USER_AGE , key );
        editor.apply();
    }

    public static String getUserAge() {
        return pref.getString( KEY_USER_AGE , null );
    }

    public static void setUserSex( String key ) {
        editor = pref.edit();
        editor.putString( KEY_USER_SEX , key );
        editor.apply();
    }

    public static String getUserSex() {
        return pref.getString( KEY_USER_SEX , null );
    }

    public static void setUserWeight( String key ) {
        editor = pref.edit();
        editor.putString( KEY_USER_WEIGHT , key );
        editor.apply();
    }

    public static String getUserWeight() {
        return pref.getString( KEY_USER_WEIGHT , null );
    }

    public static void setUserWeightUnit( String key ) {
        editor = pref.edit();
        editor.putString( KEY_USER_WEIGHT_UNIT , key );
        editor.apply();
    }

    public static String getUserWeightUnit() {
        return pref.getString( KEY_USER_WEIGHT_UNIT , null );
    }

    public static void setUserWeightUnitPos( int key ) {
        editor = pref.edit();
        editor.putInt( KEY_USER_WEIGHT_UNIT_POS , key );
        editor.apply();
    }

    public static int getUserWeightUnitPos() {
        return pref.getInt( KEY_USER_WEIGHT_UNIT_POS , 0 );
    }

    public static void setUserHeight( String key ) {
        editor = pref.edit();
        editor.putString( KEY_USER_HEIGHT , key );
        editor.apply();
    }

    public static String getUserHeight() {
        return pref.getString( KEY_USER_HEIGHT , null );
    }

    public static void setUserHeightInch( String key ) {
        editor = pref.edit();
        editor.putString( KEY_USER_HEIGHT_INCH , key );
        editor.apply();
    }

    public static String getUserHeightInch() {
        return pref.getString( KEY_USER_HEIGHT_INCH , null );
    }

    public static void setUserHeightUnit( String key ) {
        editor = pref.edit();
        editor.putString( KEY_USER_HEIGHT_UNIT , key );
        editor.apply();
    }

    public static String getUserHeightUnit() {
        return pref.getString( KEY_USER_HEIGHT_UNIT , null );
    }

    public static void setUserHeightUnitPos( int key ) {
        editor = pref.edit();
        editor.putInt( KEY_USER_HEIGHT_UNIT_POS , key );
        editor.apply();
    }

    public static int getUserHeightUnitPos() {
        return pref.getInt( KEY_USER_HEIGHT_UNIT_POS , 0 );
    }

    @Override
    public void setStateFirstStart( boolean value ) {
        editor = pref.edit();
        editor.putBoolean( KEY_FIRST_START , value );
        editor.apply();
    }

    @Override
    public boolean getStateFirstStart() {
        return pref.getBoolean( KEY_FIRST_START , false );
    }

    @Override
    public void setGrantPermission( boolean value ) {
        editor = pref.edit();
        editor.putBoolean( KEY_GRANT , value );
        editor.apply();
    }

    @Override
    public boolean getGrantPermission() {
        return pref.getBoolean( KEY_GRANT , false );
    }
}
