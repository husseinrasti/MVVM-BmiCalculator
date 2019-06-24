package ir.radicalcode.app.bmi.view.fragment;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.mikhaellopez.circularimageview.CircularImageView;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.lifecycle.ViewModelProviders;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ir.radicalcode.app.bmi.R;
import ir.radicalcode.app.bmi.data.entity.UserModel;
import ir.radicalcode.app.bmi.root.Injection;
import ir.radicalcode.app.bmi.utils.Config;
import ir.radicalcode.app.bmi.utils.Font;
import ir.radicalcode.app.bmi.utils.Utils;
import ir.radicalcode.app.bmi.view.activity.AboutActivity;
import ir.radicalcode.app.bmi.view.activity.UserProfileActivity;
import ir.radicalcode.app.bmi.view.viewmodel.UserViewModel;
import ir.radicalcode.app.bmi.view.viewmodel.FactoryViewModel;

public class BottomSheetNavigationFragment extends BottomSheetDialogFragment {

    @SuppressLint("StaticFieldLeak")
    private static BottomSheetNavigationFragment INSTANCE;

    private UserViewModel userViewModel;
    private FactoryViewModel factoryViewModel;

    @BindView(R.id.imgItemUserProfile)
    CircularImageView imgItemUserProfile;
    @BindView(R.id.imgClose)
    ImageView imgClose;
    @BindView(R.id.txtItemUserProfile)
    TextView txtItemUserProfile;

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
            if ( slideOffset > 0.35 ) {
                imgClose.setVisibility( View.VISIBLE );
            } else {
                imgClose.setVisibility( View.GONE );
            }
        }
    };

    @SuppressLint("RestrictedApi")
    @Override
    public void setupDialog( Dialog dialog , int style ) {
        super.setupDialog( dialog , style );

        factoryViewModel = Injection.provideUserViewModelFactory( getContext() );
        userViewModel = ViewModelProviders.of( this , factoryViewModel ).get( UserViewModel.class );

        //Get the content View
        View contentView = View.inflate( getContext() , R.layout.bottom_navigation_drawer , null );
        dialog.setContentView( contentView );

        ButterKnife.bind( this , contentView );

        //Set the coordinator layout behavior
        CoordinatorLayout.LayoutParams params = ( CoordinatorLayout.LayoutParams ) ( ( View ) contentView.getParent() ).getLayoutParams();
        CoordinatorLayout.Behavior behavior = params.getBehavior();

        //Set callback
        if ( behavior instanceof BottomSheetBehavior ) {
            ( ( BottomSheetBehavior ) behavior ).setBottomSheetCallback( mBottomSheetBehaviorCallback );
        }

        UserModel userModel = userViewModel.getUserModel();
        if ( userModel != null ) {
            byte[] image = userModel.getPicProfile();
            if ( image != null ) {
                Bitmap bitmap = BitmapFactory.decodeByteArray( image , 0 , image.length );
                imgItemUserProfile.setImageBitmap( bitmap );
            }
            String name = userModel.getName();
            if ( !name.equals( "" ) ) {
                txtItemUserProfile.setText( name );
            }
        }

        imgClose.setOnClickListener( v -> dismiss() );

        Font font = Font.getInstance( getContext() );
        font.iranSans( txtItemUserProfile );
        font.iranSans( contentView.findViewById( R.id.txtItemRate ) );
        font.iranSans( contentView.findViewById( R.id.txtItemAbout ) );
        font.iranSans( contentView.findViewById( R.id.txtItemShare ) );
        font.iranSans( contentView.findViewById( R.id.txtItemApps ) );
        font.yekan( contentView.findViewById( R.id.txtTitleApp ) );
        font.yekan( contentView.findViewById( R.id.txtTitleDeveloper ) );
    }

    @OnClick({ R.id.imgItemAbout , R.id.txtItemAbout ,
            R.id.imgItemUserProfile , R.id.txtItemUserProfile ,
            R.id.imgItemShare , R.id.txtItemShare ,
            R.id.imgItemRate , R.id.txtItemRate ,
            R.id.imgItemApps , R.id.txtItemApps })
    public void onItemNavClick( View view ) {
        switch ( view.getId() ) {
            case R.id.imgItemUserProfile:
            case R.id.txtItemUserProfile: {
                startActivity( new Intent( getActivity() , UserProfileActivity.class ) );
                break;
            }
            case R.id.imgItemAbout:
            case R.id.txtItemAbout: {
                startActivity( new Intent( getActivity() , AboutActivity.class ) );
                break;
            }
            case R.id.imgItemShare:
            case R.id.txtItemShare: {
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
            case R.id.imgItemRate:
            case R.id.txtItemRate: {
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
            case R.id.imgItemApps:
            case R.id.txtItemApps: {
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
    }
}