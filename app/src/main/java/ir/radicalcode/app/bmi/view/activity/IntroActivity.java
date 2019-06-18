package ir.radicalcode.app.bmi.view.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.github.paolorotolo.appintro.AppIntro2;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import ir.radicalcode.app.bmi.R;
import ir.radicalcode.app.bmi.root.Injection;
import ir.radicalcode.app.bmi.view.fragment.IntroAddInfoBmiFragment;
import ir.radicalcode.app.bmi.view.fragment.IntroUserProfileFragment;
import ir.radicalcode.app.bmi.view.fragment.IntroFinalFragment;
import ir.radicalcode.app.bmi.view.fragment.IntroStartFragment;
import ir.radicalcode.app.bmi.view.viewmodel.SharedPrefViewModel;
import ir.radicalcode.app.bmi.view.viewmodel.FactoryViewModel;


public class IntroActivity extends AppIntro2 {

    private static final int REQUEST_PERMISSIONS = 2001;

    private SharedPrefViewModel sharedPrefViewModel;

    @Override
    protected void onCreate( @Nullable Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );

        FactoryViewModel prefFactoryViewModel = Injection.provideSharedPrefViewModelFactory( this );
        sharedPrefViewModel = ViewModelProviders.of( this , prefFactoryViewModel ).get( SharedPrefViewModel.class );

        getSupportActionBar().hide();

        setGoBackLock( true );
        showSkipButton( false );

        addSlide( new IntroStartFragment() );
        addSlide( IntroUserProfileFragment.getInstance( this ) );
        addSlide( new IntroAddInfoBmiFragment() );
        addSlide( new IntroFinalFragment() );

    }

    public boolean isGrant() {
        if ( sharedPrefViewModel.getGrantPermission() ) {
            return true;
        }
        ActivityCompat.requestPermissions( this ,
                new String[] { Manifest.permission.CAMERA ,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE ,
                        Manifest.permission.READ_EXTERNAL_STORAGE } ,
                REQUEST_PERMISSIONS );
        return false;
    }

    @Override
    public void onRequestPermissionsResult( int requestCode , @NonNull String[] permissions , @NonNull int[] grantResults ) {
        switch ( requestCode ) {
            case REQUEST_PERMISSIONS: {
                if ( grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED ) {
                    sharedPrefViewModel.setGrantPermission( true );
                } else {
                    Snackbar.make( findViewById( android.R.id.content ) , R.string.runtime_permissions_txt ,
                            Snackbar.LENGTH_LONG ).setAction( R.string.str_btn_snackbar_enable ,
                            v -> ActivityCompat.requestPermissions( this , permissions , requestCode ) ).show();

                }
                break;
            }
        }
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

    @Override
    protected void onActivityResult( int requestCode , int resultCode , @Nullable Intent data ) {
        super.onActivityResult( requestCode , resultCode , data );
        if ( requestCode == IntroUserProfileFragment.REQUEST_IMAGE ) {
            for ( Fragment fragment : getSupportFragmentManager().getFragments() ) {
                fragment.onActivityResult( requestCode , resultCode , data );
            }
        }
    }
}
