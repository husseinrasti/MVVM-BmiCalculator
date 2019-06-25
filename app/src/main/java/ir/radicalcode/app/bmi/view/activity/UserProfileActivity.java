package ir.radicalcode.app.bmi.view.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.io.ByteArrayOutputStream;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModelProviders;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import ir.radicalcode.app.bmi.R;
import ir.radicalcode.app.bmi.data.entity.UserModel;
import ir.radicalcode.app.bmi.root.Injection;
import ir.radicalcode.app.bmi.utils.Font;
import ir.radicalcode.app.bmi.view.viewmodel.FactoryViewModel;
import ir.radicalcode.app.bmi.view.viewmodel.UserViewModel;

public class UserProfileActivity extends AppCompatActivity {

    private static final int REQUEST_IMAGE = 2002;
    private static final int REQUEST_PERMISSIONS = 2001;

    @BindView(R.id.toolbar)
    ConstraintLayout toolbar;
    @BindView(R.id.txtTitleToolbar)
    TextView title;
    @BindView(R.id.edtNameUser)
    AppCompatEditText edtNameUser;
    @BindView(R.id.imgUserProfile)
    CircularImageView imgUserProfile;
    @BindView(R.id.btnExit)
    Button btnExit;
    @BindView(R.id.btnSave)
    Button btnSave;
    @BindView(R.id.txtError)
    TextView txtError;

    private UserViewModel userViewModel;

    private byte[] imageBytes;

    private Unbinder unbinder;

    @Override
    public void onCreate( @Nullable Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_user_profile );
        unbinder = ButterKnife.bind( this );

        title.setText( getString( R.string.str_profile ) );

        Font font = Font.getInstance( this );
        font.yekan( title );
        font.yekan( btnExit );
        font.yekan( btnSave );
        font.iranSans( edtNameUser );
        font.iranSansLight( txtError );

        FactoryViewModel bmiFactoryViewModel = Injection.provideUserViewModelFactory( this );
        userViewModel = ViewModelProviders.of( this , bmiFactoryViewModel ).get( UserViewModel.class );

        userViewModel.getUserModel().observe( this , this::checkUser );

        btnExit.setOnClickListener( v -> finish() );
        btnSave.setOnClickListener( v -> save() );
    }

    private void checkUser( UserModel userModel ) {
        if ( userModel != null ) {
            byte[] image = userModel.getPicProfile();
            if ( image != null ) {
                Bitmap bitmap = BitmapFactory.decodeByteArray( image , 0 , image.length );
                imgUserProfile.setImageBitmap( bitmap );
            }
            String name = userModel.getName();
            if ( !name.equals( "" ) ) {
                edtNameUser.setText( name );
            }
        }
    }

    private void save() {
        String name = Objects.requireNonNull( edtNameUser.getText() ).toString();
        if ( !name.equals( "" ) && imageBytes.length != 0 ) {
            UserModel model = new UserModel();
            model.setName( name );
            model.setPicProfile( imageBytes );
            userViewModel.insertFirst( model );
            finish();
        } else {
            txtError.setVisibility( View.VISIBLE );
        }
    }

    @OnClick({ R.id.imgUserProfile , R.id.imgPlus })
    public void onProfileImageClick() {
        ActivityCompat.requestPermissions( this ,
                new String[] { Manifest.permission.CAMERA } ,
                REQUEST_PERMISSIONS );
    }

    private void showImagePickerOptions() {
        AlertDialog.Builder builder = new AlertDialog.Builder( this );
        builder.setTitle( getString( R.string.lbl_set_profile_photo ) );

        String[] options = { getString( R.string.lbl_take_camera_picture )
                , getString( R.string.lbl_choose_from_gallery ) };

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
        Intent intent = new Intent( this , ImagePickerActivity.class );
        intent.putExtra( ImagePickerActivity.INTENT_IMAGE_PICKER_OPTION , ImagePickerActivity.REQUEST_IMAGE_CAPTURE );

        // setting aspect ratio
        intent.putExtra( ImagePickerActivity.INTENT_LOCK_ASPECT_RATIO , true );
        intent.putExtra( ImagePickerActivity.INTENT_ASPECT_RATIO_X , 1 ); // 16x9, 1x1, 3:4, 3:2
        intent.putExtra( ImagePickerActivity.INTENT_ASPECT_RATIO_Y , 1 );

        // setting maximum bitmap width and height
        intent.putExtra( ImagePickerActivity.INTENT_SET_BITMAP_MAX_WIDTH_HEIGHT , true );
        intent.putExtra( ImagePickerActivity.INTENT_BITMAP_MAX_WIDTH , 1000 );
        intent.putExtra( ImagePickerActivity.INTENT_BITMAP_MAX_HEIGHT , 1000 );
        startActivityForResult( intent , REQUEST_IMAGE );
    }

    private void launchGalleryIntent() {
        Intent intent = new Intent( this , ImagePickerActivity.class );
        intent.putExtra( ImagePickerActivity.INTENT_IMAGE_PICKER_OPTION , ImagePickerActivity.REQUEST_GALLERY_IMAGE );

        // setting aspect ratio
        intent.putExtra( ImagePickerActivity.INTENT_LOCK_ASPECT_RATIO , true );
        intent.putExtra( ImagePickerActivity.INTENT_ASPECT_RATIO_X , 1 ); // 16x9, 1x1, 3:4, 3:2
        intent.putExtra( ImagePickerActivity.INTENT_ASPECT_RATIO_Y , 1 );
        startActivityForResult( intent , REQUEST_IMAGE );
    }

    @Override
    public void onRequestPermissionsResult( int requestCode , @NonNull String[] permissions , @NonNull int[] grantResults ) {
        if ( requestCode == REQUEST_PERMISSIONS ) {
            if ( grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED ) {
                showImagePickerOptions();
            } else {
                Snackbar.make( findViewById( android.R.id.content ) , R.string.runtime_permissions_txt ,
                        Snackbar.LENGTH_LONG ).setAction( R.string.str_btn_snackbar_enable ,
                        v -> ActivityCompat.requestPermissions( this , permissions , requestCode ) ).show();
            }
        }
    }

    @Override
    public void onActivityResult( int requestCode , int resultCode , @Nullable Intent data ) {
        if ( requestCode == REQUEST_IMAGE ) {
            if ( resultCode == Activity.RESULT_OK ) {
                try {
                    assert data != null;
                    Uri uri = data.getParcelableExtra( "path" );
                    // You can update this bitmap to your server
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap( getContentResolver() , uri );
                    imgUserProfile.setImageBitmap( bitmap );
                    ByteArrayOutputStream blob = new ByteArrayOutputStream();
                    bitmap.compress( Bitmap.CompressFormat.PNG , 100 , blob );
                    imageBytes = blob.toByteArray();
                } catch ( Exception e ) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}

