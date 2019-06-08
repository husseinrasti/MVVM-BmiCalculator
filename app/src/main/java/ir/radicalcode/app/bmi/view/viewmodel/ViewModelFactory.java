package ir.radicalcode.app.bmi.view.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import ir.radicalcode.app.bmi.data.local.BmiDataSource;
import ir.radicalcode.app.bmi.data.local.SharedPrefDataSource;
import ir.radicalcode.app.bmi.data.local.UserDataSource;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private BmiDataSource bmiDataSource;
    private SharedPrefDataSource sharedPrefDataSource;
    private UserDataSource userDataSource;

    public ViewModelFactory( UserDataSource userDataSource ) {
        this.userDataSource = userDataSource;
    }


    public ViewModelFactory( BmiDataSource bmiDataSource ) {
        this.bmiDataSource = bmiDataSource;
    }

    public ViewModelFactory( SharedPrefDataSource bmiDataSource ) {
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
