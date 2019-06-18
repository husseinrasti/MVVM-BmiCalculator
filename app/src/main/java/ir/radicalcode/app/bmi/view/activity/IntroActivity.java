package ir.radicalcode.app.bmi.view.activity;

import android.content.Intent;
import android.os.Bundle;

import com.github.paolorotolo.appintro.AppIntro2;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import ir.radicalcode.app.bmi.view.fragment.IntroAddInfoBmiFragment;
import ir.radicalcode.app.bmi.view.fragment.IntroFinalFragment;
import ir.radicalcode.app.bmi.view.fragment.IntroStartFragment;


public class IntroActivity extends AppIntro2 {


    @Override
    protected void onCreate( @Nullable Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );

        getSupportActionBar().hide();

        setGoBackLock( true );
        showSkipButton( false );

        addSlide( new IntroStartFragment() );
        addSlide( new IntroAddInfoBmiFragment() );
        addSlide( new IntroFinalFragment() );

    }

    @Override
    public void onSkipPressed( Fragment currentFragment ) {
        super.onSkipPressed( currentFragment );
        startActivity( new Intent( this , StartupActivity.class ) );
        finish();
    }

    @Override
    public void onDonePressed( Fragment currentFragment ) {
        super.onDonePressed( currentFragment );
        startActivity( new Intent( this , StartupActivity.class ) );
        finish();
    }

    @Override
    public void onSlideChanged( @Nullable Fragment oldFragment , @Nullable Fragment newFragment ) {
        super.onSlideChanged( oldFragment , newFragment );
    }

}
