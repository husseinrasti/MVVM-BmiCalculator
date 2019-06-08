package ir.radicalcode.app.bmi.view.fragment;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import ir.radicalcode.app.bmi.R;
import ir.radicalcode.app.bmi.utils.Config;
import ir.radicalcode.app.bmi.utils.Utils;

public class BottomSheetNavigationFragment extends BottomSheetDialogFragment {

    @SuppressLint("StaticFieldLeak")
    private static BottomSheetNavigationFragment INSTANCE;

    public static BottomSheetNavigationFragment newInstance() {
        if ( INSTANCE == null ) {
            INSTANCE = new BottomSheetNavigationFragment();
        }

        return INSTANCE;
    }

    //Bottom Sheet Callback
    private BottomSheetBehavior.BottomSheetCallback mBottomSheetBehaviorCallback = new BottomSheetBehavior.BottomSheetCallback() {

        @Override
        public void onStateChanged( @NonNull View bottomSheet , int newState ) {
            if ( newState == BottomSheetBehavior.STATE_HIDDEN ) {
                dismiss();
            }
        }

        @Override
        public void onSlide( @NonNull View bottomSheet , float slideOffset ) {
            //check the slide offset and change the visibility of close button
            if ( slideOffset > 0.5 ) {
//                closeButton.setVisibility( View.VISIBLE );
            } else {
//                closeButton.setVisibility( View.GONE );
            }
        }
    };

    @SuppressLint("RestrictedApi")
    @Override
    public void setupDialog( Dialog dialog , int style ) {
        super.setupDialog( dialog , style );

        //Get the content View
        View contentView = View.inflate( getContext() , R.layout.bottom_navigation_drawer , null );
        dialog.setContentView( contentView );

        NavigationView navigationView = contentView.findViewById( R.id.navigation_view );

        //implement navigation menu item click event
        navigationView.setNavigationItemSelectedListener( item -> {
            switch ( item.getItemId() ) {
                case R.id.navigation_item_help: {
//                    Intent intent = new Intent( getActivity() , HelpActivity.class );
//                    startActivity( intent );
                    break;
                }
                case R.id.navigation_item_about: {
//                    Intent intent = new Intent( getActivity() , AboutActivity.class );
//                    startActivity( intent );
                    break;
                }
                case R.id.navigation_item_share: {
                    String data = getResources().getString( R.string.share_text ) + "\n"
                            + "http://cafebazaar.ir/app/?id=" + Config.PACKAGE_NAME + "&ref=share";
                    Intent intent = new Intent( Intent.ACTION_SEND );
                    intent.setType( "text/plain" );
                    intent.putExtra( Intent.EXTRA_SUBJECT , data );
                    intent.putExtra( Intent.EXTRA_TITLE , getResources().getString( R.string.app_name ) );
                    intent.putExtra( Intent.EXTRA_TEXT , data );
                    startActivity( Intent.createChooser( intent , getResources().getString( R.string.share_using ) ) );
                    break;
                }
                case R.id.navigation_item_rate: {
                    if ( Utils.isPackageExisted( getContext() , "com.farsitel.bazaar" ) ) {
                        Intent intent = new Intent( Intent.ACTION_EDIT );
                        intent.setData( Uri.parse( "bazaar://details?id=" + Config.PACKAGE_NAME ) );
                        intent.setPackage( "com.farsitel.bazaar" );
                        startActivity( intent );
                    } else {
                        Toast.makeText( getContext() , getString( R.string.str_toast_install_market ) , Toast.LENGTH_LONG ).show();
                    }
                    break;
                }
                case R.id.navigation_item_app: {
                    if ( Utils.isPackageExisted( getContext() , "com.farsitel.bazaar" ) ) {
                        Intent intent = new Intent( Intent.ACTION_VIEW );
                        intent.setData( Uri.parse( "bazaar://collection?slug=by_author&aid=" + Config.DEVELOPER_ID ) );
                        intent.setPackage( "com.farsitel.bazaar" );
                        startActivity( intent );
                    } else {
                        Toast.makeText( getContext() , getString( R.string.str_toast_install_market ) , Toast.LENGTH_LONG ).show();
                    }
                    break;
                }
            }
            return false;
        } );

        //Set the coordinator layout behavior
        CoordinatorLayout.LayoutParams params = ( CoordinatorLayout.LayoutParams ) ( ( View ) contentView.getParent() ).getLayoutParams();
        CoordinatorLayout.Behavior behavior = params.getBehavior();

        //Set callback
        if ( behavior instanceof BottomSheetBehavior ) {
            ( ( BottomSheetBehavior ) behavior ).setBottomSheetCallback( mBottomSheetBehaviorCallback );
        }
    }

}