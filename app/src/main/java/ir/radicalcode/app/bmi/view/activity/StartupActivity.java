package ir.radicalcode.app.bmi.view.activity;


import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Objects;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import butterknife.BindView;
import butterknife.ButterKnife;
import ir.radicalcode.app.bmi.R;
import ir.radicalcode.app.bmi.root.Injection;
import ir.radicalcode.app.bmi.view.adapter.BmiPagerAdapter;
import ir.radicalcode.app.bmi.view.customviews.CustomViewPager;
import ir.radicalcode.app.bmi.view.fragment.BottomSheetNavigationFragment;
import ir.radicalcode.app.bmi.view.viewmodel.SharedPrefViewModel;
import ir.radicalcode.app.bmi.view.viewmodel.ViewModelFactory;


public class StartupActivity extends AppCompatActivity {

    //    @BindView(R.id.tab_layout)
//    TabLayout mTabLayout;
    @BindView(R.id.bottomAppbar)
    BottomAppBar bottomAppBar;
    @BindView(R.id.fabAdd)
    FloatingActionButton fabAdd;
    @BindView(R.id.viewPager)
    CustomViewPager viewPager;
    @BindView(R.id.containerHistory)
    LinearLayout containerHistory;
    @BindView(R.id.containerHome)
    LinearLayout containerHome;
    @BindView(R.id.containerSettings)
    LinearLayout containerSettings;
    @BindView(R.id.containerMore)
    LinearLayout containerMore;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_startup );
        ButterKnife.bind( this );

        checkIntro();

        Objects.requireNonNull( getSupportActionBar() ).setDisplayOptions( ActionBar.DISPLAY_SHOW_CUSTOM );
        getSupportActionBar().setCustomView( R.layout.toolbar );

        BmiPagerAdapter pagerAdapter = new BmiPagerAdapter( this , getSupportFragmentManager() );

        if ( viewPager != null ) {
            viewPager.setPagingEnabled( false );
            viewPager.setAdapter( pagerAdapter );
        }

        viewPager.setCurrentItem( 3 );
        assert viewPager != null;
        viewPager.setPageTransformer( true , ( page , position ) -> {
            final float normalizedposition = Math.abs( Math.abs( position ) - 1 );
            page.setAlpha( normalizedposition );
        } );

        containerSettings.setOnClickListener( v -> viewPager.setCurrentItem( 0 ) );
        fabAdd.setOnClickListener( v -> viewPager.setCurrentItem( 1 ) );
        containerHistory.setOnClickListener( v -> viewPager.setCurrentItem( 2 ) );
        containerHome.setOnClickListener( v -> viewPager.setCurrentItem( 3 ) );
        containerMore.setOnClickListener( v ->
                BottomSheetNavigationFragment.newInstance().show( getSupportFragmentManager() , "Bottom Sheet More" ) );
    }

    @Override
    public void onBackPressed() {
        if ( viewPager.getCurrentItem() != 3 ) {
            viewPager.setCurrentItem( 3 );
            return;
        }
        startActivity( new Intent( this , ThanksActivity.class ) );
        finish();
    }

    private void checkIntro() {
        ViewModelFactory viewModelFactory = Injection.provideSharedPrefViewModelFactory( this );
        SharedPrefViewModel sharedPrefViewModel = ViewModelProviders.of( this , viewModelFactory ).get( SharedPrefViewModel.class );
        new Thread( () -> {
            boolean isFirstStart = sharedPrefViewModel.getStateFirstStart();
            if ( !isFirstStart ) {
                Intent intro = new Intent( StartupActivity.this , IntroActivity.class );
                startActivity( intro );
                sharedPrefViewModel.setStateFirstStart( true );
                finish();
            }
        } ).start();
    }
}