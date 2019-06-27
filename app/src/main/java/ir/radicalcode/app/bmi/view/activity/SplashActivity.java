package ir.radicalcode.app.bmi.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import ir.radicalcode.app.bmi.R;
import ir.radicalcode.app.bmi.root.Injection;
import ir.radicalcode.app.bmi.utils.Font;
import ir.radicalcode.app.bmi.view.viewmodel.FactoryViewModel;
import ir.radicalcode.app.bmi.view.viewmodel.SharedPrefViewModel;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate( @Nullable Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.splash );

        Font.getInstance( this ).yekan( findViewById( R.id.txtBmi ) );

        new Handler().postDelayed( this::checkIntro , 2000 );
    }

    private void checkIntro() {
        FactoryViewModel factoryViewModel = Injection.provideSharedPrefViewModelFactory( this );
        SharedPrefViewModel sharedPrefViewModel = ViewModelProviders.of( this , factoryViewModel ).get( SharedPrefViewModel.class );
        boolean isFirstStart = sharedPrefViewModel.getStateFirstStart();
        if ( !isFirstStart ) {
            startActivity( new Intent( SplashActivity.this , IntroActivity.class ) );
        } else {
            startActivity( new Intent( SplashActivity.this , StartupActivity.class ) );
        }
        finish();
    }
}
