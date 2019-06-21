package ir.radicalcode.app.bmi.view.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import ir.radicalcode.app.bmi.data.datasource.BmiDataSource;
import ir.radicalcode.app.bmi.data.datasource.SharedPrefDataSource;
import ir.radicalcode.app.bmi.data.datasource.UserDataSource;

public class FactoryViewModel implements ViewModelProvider.Factory {

    private BmiDataSource bmiDataSource;
    private SharedPrefDataSource sharedPrefDataSource;
    private UserDataSource userDataSource;

    public FactoryViewModel( UserDataSource userDataSource ) {
        this.userDataSource = userDataSource;
    }


    public FactoryViewModel( BmiDataSource bmiDataSource ) {
        this.bmiDataSource = bmiDataSource;
    }

    public FactoryViewModel( SharedPrefDataSource bmiDataSource ) {
        this.sharedPrefDataSource = bmiDataSource;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create( @NonNull Class<T> modelClass ) {
        if ( modelClass.isAssignableFrom( BmiViewModel.class ) ) {
            return ( T ) new BmiViewModel( bmiDataSource );
        } else if ( modelClass.isAssignableFrom( SharedPrefViewModel.class ) ) {
            return ( T ) new SharedPrefViewModel( sharedPrefDataSource );
        } else if ( modelClass.isAssignableFrom( UserViewModel.class ) ) {
            return ( T ) new UserViewModel( userDataSource );
        }
        throw new IllegalArgumentException( "Unknown ViewModel class" );
    }
}
