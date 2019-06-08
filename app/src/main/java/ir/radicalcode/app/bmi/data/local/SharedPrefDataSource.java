package ir.radicalcode.app.bmi.data.local;

public interface SharedPrefDataSource {

    void setStateFirstStart( boolean value );


    boolean getStateFirstStart();


    void setGrantPermission( boolean value );


    boolean getGrantPermission();
}
