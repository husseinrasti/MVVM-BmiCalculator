package ir.radicalcode.app.bmi.view.fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.paolorotolo.appintro.ISlidePolicy;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.io.ByteArrayOutputStream;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ir.radicalcode.app.bmi.R;
import ir.radicalcode.app.bmi.data.entity.UserModel;
import ir.radicalcode.app.bmi.root.Injection;
import ir.radicalcode.app.bmi.utils.Font;
import ir.radicalcode.app.bmi.view.activity.ImagePickerActivity;
import ir.radicalcode.app.bmi.view.activity.IntroActivity;
import ir.radicalcode.app.bmi.view.viewmodel.UserViewModel;
import ir.radicalcode.app.bmi.view.viewmodel.FactoryViewModel;

public class IntroUserProfileFragment extends Fragment implements ISlidePolicy {

    public static final int REQUEST_IMAGE = 2002;

    @BindView(R.id.edtNameUser)
    AppCompatEditText edtNameUser;
    @BindView(R.id.imgUserProfile)
    CircularImageView imgUserProfile;

    private UserViewModel userViewModel;

    private boolean isImage;
    private byte[] imageBytes;

    private static IntroActivity mActivity;

    public static IntroUserProfileFragment getInstance( IntroActivity activity ) {
        mActivity = activity;

        return new IntroUserProfileFragment();
    }

    @Override
    public void onCreate( @Nullable Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );

        FactoryViewModel bmiFactoryViewModel = Injection.provideUserViewModelFactory( mActivity );
        userViewModel = ViewModelProviders.of( this , bmiFactoryViewModel ).get( UserViewModel.class );
    }

    @Nullable
    @Override
    public View onCreateView( @NonNull LayoutInflater inflater , @Nullable ViewGroup container , @Nullable Bundle savedInstanceState ) {
        View view = inflater.inflate( R.layout.fragment_intor_user_profile , container , false );
        ButterKnife.bind( this , view );

        Font.getInstance( getContext() ).iranSans( edtNameUser );
        return view;
    }

    @Override
    public boolean isPolicyRespected() {

        UserModel model = new UserModel();
        model.setName( Objects.requireNonNull( edtNameUser.getText() ).toString() );
        model.setPicProfile( imageBytes );

        userViewModel.insertFirst( model );

        return isImage &&
                !edtNameUser.getText().toString().equals( "" );
    }

    @Override
    public void onUserIllegallyRequestedNextPage() {
        if ( Objects.requireNonNull( edtNameUser.getText() ).toString().equals( "" ) ) {
            Toast.makeText( getContext() , R.string.name_policy_error , Toast.LENGTH_SHORT ).show();
        } else if ( !isImage ) {
            Toast.makeText( getContext() , R.string.pic_profile_policy_error , Toast.LENGTH_SHORT ).show();
        }
    }

    @OnClick({ R.id.imgUserProfile , R.id.imgPlus })
    public void onProfileImageClick() {
        if ( mActivity.isGrant() ) {
            showImagePickerOptions();
        }
    }

    private void showImagePickerOptions() {
        AlertDialog.Builder builder = new AlertDialog.Builder( mActivity );
        builder.setTitle( mActivity.getString( R.string.lbl_set_profile_photo ) );

        String[] options = { mActivity.getString( R.string.lbl_take_camera_picture )
                , mActivity.getString( R.string.lbl_choose_from_gallery ) };

        builder.setItems( options , ( dialog , which ) -> {
            switch ( which ) {
                case 0:
                    launchCameraIntent();
                    break;
                case 1:
                    launchGalleryIntent();
                    break;
            }
        } );

        AlertDialog dialog = builder.create();
        dialog.show();

    }

    private void launchCameraIntent() {
        Intent intent = new Intent( mActivity , ImagePickerActivity.class );
        intent.putExtra( ImagePickerActivity.INTENT_IMAGE_PICKER_OPTION , ImagePickerActivity.REQUEST_IMAGE_CAPTURE );

        // setting aspect ratio
        intent.putExtra( ImagePickerActivity.INTENT_LOCK_ASPECT_RATIO , true );
        intent.putExtra( ImagePickerActivity.INTENT_ASPECT_RATIO_X , 1 ); // 16x9, 1x1, 3:4, 3:2
        intent.putExtra( ImagePickerActivity.INTENT_ASPECT_RATIO_Y , 1 );

        // setting maximum bitmap width and height
        intent.putExtra( ImagePickerActivity.INTENT_SET_BITMAP_MAX_WIDTH_HEIGHT , true );
        intent.putExtra( ImagePickerActivity.INTENT_BITMAP_MAX_WIDTH , 1000 );
        intent.putExtra( ImagePickerActivity.INTENT_BITMAP_MAX_HEIGHT , 1000 );
        mActivity.startActivityForResult( intent , REQUEST_IMAGE );

    }

    private void launchGalleryIntent() {
        Intent intent = new Intent( mActivity , ImagePickerActivity.class );
        intent.putExtra( ImagePickerActivity.INTENT_IMAGE_PICKER_OPTION , ImagePickerActivity.REQUEST_GALLERY_IMAGE );

        // setting aspect ratio
        intent.putExtra( ImagePickerActivity.INTENT_LOCK_ASPECT_RATIO , true );
        intent.putExtra( ImagePickerActivity.INTENT_ASPECT_RATIO_X , 1 ); // 16x9, 1x1, 3:4, 3:2
        intent.putExtra( ImagePickerActivity.INTENT_ASPECT_RATIO_Y , 1 );
        mActivity.startActivityForResult( intent , REQUEST_IMAGE );

    }


    @Override
    public void onActivityResult( int requestCode , int resultCode , @Nullable Intent data ) {
        if ( requestCode == REQUEST_IMAGE ) {
            if ( resultCode == Activity.RESULT_OK ) {
                try {
                    assert data != null;
                    Uri uri = data.getParcelableExtra( "path" );
                    // You can update this bitmap to your server
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap( mActivity.getContentResolver() , uri );
                    imgUserProfile.setImageBitmap( bitmap );
                    ByteArrayOutputStream blob = new ByteArrayOutputStream();
                    bitmap.compress( Bitmap.CompressFormat.PNG , 100 , blob );
                    imageBytes = blob.toByteArray();
                    isImage = true;
                } catch ( Exception e ) {
                    e.printStackTrace();
                }
            }
        }
    }

}

