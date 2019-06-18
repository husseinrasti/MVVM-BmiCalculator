package ir.radicalcode.app.bmi.root;

import android.content.Context;

import ir.radicalcode.app.bmi.data.datasource.BmiDataSource;
import ir.radicalcode.app.bmi.data.datasource.UserDataSource;
import ir.radicalcode.app.bmi.data.local.db.DatabaseHelper;
import ir.radicalcode.app.bmi.data.local.db.LocalBmiDataSource;
import ir.radicalcode.app.bmi.data.local.db.LocalUserDataSource;
import ir.radicalcode.app.bmi.data.local.shared.SharedPref;
import ir.radicalcode.app.bmi.view.viewmodel.FactoryViewModel;

public class Injection {

    private static BmiDataSource provideBMIDataSource( Context context ) {
        DatabaseHelper database = DatabaseHelper.getInstance( context );
        return new LocalBmiDataSource( database.bmiDao() );
    }

    private static UserDataSource provideUserDataSource( Context context ) {
        DatabaseHelper database = DatabaseHelper.getInstance( context );
        return new LocalUserDataSource( database.userDao() );
    }

    public static FactoryViewModel provideBMIViewModelFactory( Context context ) {
        BmiDataSource dataSource = provideBMIDataSource( context );
        return new FactoryViewModel( dataSource );
    }

    public static FactoryViewModel provideUserViewModelFactory( Context context ) {
        UserDataSource dataSource = provideUserDataSource( context );
        return new FactoryViewModel( dataSource );
    }

    public static FactoryViewModel provideSharedPrefViewModelFactory( Context context ) {
        return new FactoryViewModel( SharedPref.getInstance( context ) );
    }
}
