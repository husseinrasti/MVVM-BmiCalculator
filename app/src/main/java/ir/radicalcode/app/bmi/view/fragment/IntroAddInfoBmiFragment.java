package ir.radicalcode.app.bmi.view.fragment;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.github.paolorotolo.appintro.ISlidePolicy;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import butterknife.BindView;
import butterknife.ButterKnife;
import ir.radicalcode.app.bmi.R;
import ir.radicalcode.app.bmi.data.entity.BmiModel;
import ir.radicalcode.app.bmi.root.Injection;
import ir.radicalcode.app.bmi.utils.BmiHelper;
import ir.radicalcode.app.bmi.utils.Const;
import ir.radicalcode.app.bmi.utils.Font;
import ir.radicalcode.app.bmi.view.viewmodel.BmiViewModel;
import ir.radicalcode.app.bmi.view.viewmodel.ViewModelFactory;

public class IntroAddInfoBmiFragment extends Fragment implements ISlidePolicy {

    @BindView(R.id.edtAge)
    AppCompatEditText edtAge;
    @BindView(R.id.radioMale)
    AppCompatRadioButton radioMale;
    @BindView(R.id.radioFemale)
    AppCompatRadioButton radioFemale;
    @BindView(R.id.edtWeight)
    AppCompatEditText edtWeight;
    @BindView(R.id.edtHeight)
    AppCompatEditText edtHeight;
    @BindView(R.id.edtHeightInch)
    AppCompatEditText edtHeightInch;
    @BindView(R.id.spinnerWeightUnit)
    Spinner spinnerWeightUnit;
    @BindView(R.id.spinnerHeightUnit)
    Spinner spinnerHeightUnit;
    @BindView(R.id.txtGender)
    TextView txtGender;

    private double userHeight;
    private double userHeightInch;
    private String userHeightUnit;
    private int userHeightUnitPos;
    private double userWeight;
    private String userWeightUnit;
    private int userWeightUnitPos;
    private int userAge;
    private String userSex;

    private ViewModelFactory viewModelFactory;
    private BmiViewModel bmiViewModel;

    private boolean doSave;

    @Override
    public void onCreate( @Nullable Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        viewModelFactory = Injection.provideBMIViewModelFactory( getActivity() );
        bmiViewModel = ViewModelProviders.of( this , viewModelFactory ).get( BmiViewModel.class );
        setRetainInstance( true );
    }


    @Nullable
    @Override
    public View onCreateView( @NonNull LayoutInflater inflater , @Nullable ViewGroup container , @Nullable Bundle savedInstanceState ) {
        View view = inflater.inflate( R.layout.fragment_intro_add_info_bmi , container , false );
        ButterKnife.bind( this , view );

        setupSpinnerHeightUnit();

        Font font = Font.getInstance( getContext() );
        font.iranSansMedium( edtAge );
        font.iranSansMedium( edtHeight );
        font.iranSansMedium( edtHeightInch );
        font.iranSansMedium( edtWeight );
        font.yekan( txtGender );

        return view;
    }

    private void save() {
        BmiModel model = new BmiModel();
        model.setId( 1 );
        model.setStatus( 1 );
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd" );
        model.setDate( sdf.format( new Date() ) );
        model.setAge( userAge );
        model.setSex( userSex );
        model.setWeight( userWeight );
        model.setWeightUnitPosition( userWeightUnitPos );
        model.setWeightUnit( userWeightUnit );
        model.setHeight( userHeight );
        model.setHeightUnitPosition( userHeightUnitPos );
        model.setHeightInch( userHeightInch );
        model.setHeightUnit( userHeightUnit );
        model.setResult( BmiHelper.getInstance().getBMIResult( userWeightUnitPos , userHeightUnitPos , userHeight , userWeight , userHeightInch ) );

        bmiViewModel.insertFirst( model );
    }

    @Override
    public boolean isPolicyRespected() {
        try {
            userAge = Integer.parseInt( Objects.requireNonNull( edtAge.getText() ).toString() );
            userWeight = Double.parseDouble( Objects.requireNonNull( edtWeight.getText() ).toString() );
            userHeightInch = Double.parseDouble( ( Objects.requireNonNull( edtHeightInch.getText() ).toString().length() > 0 )
                    ? edtHeightInch.getText().toString() : "0" );
            userHeight = Double.parseDouble( Objects.requireNonNull( edtHeight.getText() ).toString() );
        } catch ( Exception e ) {
            e.printStackTrace();
        }

        userSex = ( radioMale.isChecked() ) ? Const.KEY_MALE : ( radioFemale.isChecked() ) ? Const.KEY_FEMALE : "";
        userWeightUnit = spinnerWeightUnit.getSelectedItem().toString();
        userWeightUnitPos = spinnerWeightUnit.getSelectedItemPosition();
        userHeightUnit = spinnerHeightUnit.getSelectedItem().toString();
        userHeightUnitPos = spinnerHeightUnit.getSelectedItemPosition();

        doSave = userAge > 0
                && ( radioMale.isChecked() || radioFemale.isChecked() )
                && userWeight > 0
                && userHeight > 0;

        if ( doSave ) {
            save();
        }

        return doSave;
    }


    @Override
    public void onUserIllegallyRequestedNextPage() {
        if ( userAge == 0 ) {
            Toast.makeText( getContext() , R.string.age_policy_error , Toast.LENGTH_SHORT ).show();
        } else if ( userSex.equals( "" ) ) {
            Toast.makeText( getContext() , R.string.gender_policy_error , Toast.LENGTH_SHORT ).show();
        } else if ( userWeight == 0 ) {
            Toast.makeText( getContext() , R.string.weight_policy_error , Toast.LENGTH_SHORT ).show();
        } else if ( userHeight == 0 ) {
            Toast.makeText( getContext() , R.string.height_policy_error , Toast.LENGTH_SHORT ).show();
        }
    }

    private void setupSpinnerHeightUnit() {
        spinnerHeightUnit.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected( AdapterView parent , View view , int position , long id ) {
                if ( position == 0 ) {
                    edtHeightInch.setVisibility( View.GONE );
                } else {
                    edtHeightInch.setVisibility( View.VISIBLE );
                }
            }

            @Override
            public void onNothingSelected( AdapterView<?> parent ) {
            }
        } );
    }
}
