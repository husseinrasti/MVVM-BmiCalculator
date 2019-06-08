package ir.radicalcode.app.bmi.root;

import android.content.Context;

import ir.radicalcode.app.bmi.data.local.BmiDataSource;
import ir.radicalcode.app.bmi.data.local.UserDataSource;
import ir.radicalcode.app.bmi.data.local.db.DatabaseHelper;
import ir.radicalcode.app.bmi.data.local.db.LocalBmiDataSource;
import ir.radicalcode.app.bmi.data.local.db.LocalUserDataSource;
import ir.radicalcode.app.bmi.data.local.shared.SharedPref;
import ir.radicalcode.app.bmi.view.viewmodel.ViewModelFactory;

public class Injection {

    private static BmiDataSource provideBMIDataSource( Context context ) {
        DatabaseHelper database = DatabaseHelper.getInstance( context );
        return new LocalBmiDataSource( database.bmiDao() );
    }

    private static UserDataSource provideUserDataSource( Context context ) {
        DatabaseHelper database = DatabaseHelper.getInstance( context );
        return new LocalUserDataSource( database.userDao() );
    }

    public static ViewModelFactory provideBMIViewModelFactory( Context context ) {
        BmiDataSource dataSource = provideBMIDataSource( context );
        return new ViewModelFactory( dataSource );
    }

    public static ViewModelFactory provideUserViewModelFactory( Context context ) {
        UserDataSource dataSource = provideUserDataSource( context );
        return new ViewModelFactory( dataSource );
    }

    public static ViewModelFactory provideSharedPrefViewModelFactory( Context context ) {
        return new ViewModelFactory( SharedPref.getInstance( context ) );
    }
}
