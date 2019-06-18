package ir.radicalcode.app.bmi.view.viewmodel;

import androidx.lifecycle.ViewModel;
import ir.radicalcode.app.bmi.data.datasource.SharedPrefDataSource;

public class SharedPrefViewModel extends ViewModel {

    private SharedPrefDataSource sharedPrefDataSource;

    public SharedPrefViewModel( SharedPrefDataSource sharedPrefDataSource ) {
        this.sharedPrefDataSource = sharedPrefDataSource;
    }

    public void setStateFirstStart( boolean value ) {
        sharedPrefDataSource.setStateFirstStart( value );
    }

    public boolean getStateFirstStart() {
        return sharedPrefDataSource.getStateFirstStart();
    }

    public void setGrantPermission( boolean value ) {
        sharedPrefDataSource.setGrantPermission( value );
    }

    public boolean getGrantPermission() {
        return sharedPrefDataSource.getGrantPermission();
    }
}
