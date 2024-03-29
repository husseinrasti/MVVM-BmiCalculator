package ir.radicalcode.app.bmi.view.fragment;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;
import ir.radicalcode.app.bmi.R;
import ir.radicalcode.app.bmi.data.entity.BmiModel;
import ir.radicalcode.app.bmi.root.Injection;
import ir.radicalcode.app.bmi.utils.BmiHelper;
import ir.radicalcode.app.bmi.utils.Const;
import ir.radicalcode.app.bmi.utils.Font;
import ir.radicalcode.app.bmi.view.viewmodel.BmiViewModel;
import ir.radicalcode.app.bmi.view.viewmodel.FactoryViewModel;

public class AddInfoBmiFragment extends Fragment implements TextWatcher {

    @BindView(R.id.txtInfo)
    TextView txtInfo;
    @BindView(R.id.editAge)
    AppCompatEditText editAge;
    @BindView(R.id.editWeight)
    AppCompatEditText editWeight;
    @BindView(R.id.editHeight)
    AppCompatEditText editHeight;
    @BindView(R.id.editHeightInch)
    AppCompatEditText editHeightInch;
    @BindView(R.id.radioBtnMale)
    RadioButton radioBtnMale;
    @BindView(R.id.radioBtnFemale)
    RadioButton radioBtnFemale;
    @BindView(R.id.btnCalculate)
    Button btnCalculate;
    @BindView(R.id.spinnerWeightUnit)
    Spinner spinnerWeightUnit;
    @BindView(R.id.spinnerHeightUnit)
    Spinner spinnerHeightUnit;

    private ViewPager viewPager;

    private FactoryViewModel factoryViewModel;
    private BmiViewModel bmiViewModel;
    private boolean isInch;

    public static AddInfoBmiFragment newInstance( int pageNo ) {
        Bundle args = new Bundle();
        args.putInt( Const.ARG_PAGE , pageNo );
        AddInfoBmiFragment settingsFragment = new AddInfoBmiFragment();
        settingsFragment.setArguments( args );
        return settingsFragment;
    }

    @Override
    public void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        assert getArguments() != null;
        int mPageNo = getArguments().getInt( Const.ARG_PAGE );
        factoryViewModel = Injection.provideBMIViewModelFactory( getActivity() );
        bmiViewModel = ViewModelProviders.of( this , factoryViewModel ).get( BmiViewModel.class );
        bmiViewModel.getLastRecord().observe( this , this::showUserValue );
    }

    @Override
    public View onCreateView( @NonNull LayoutInflater inflater , ViewGroup container ,
                              Bundle savedInstanceState ) {
        View view = inflater.inflate( R.layout.fragment_add_info_bmi , container , false );
        ButterKnife.bind( this , view );
        viewPager = Objects.requireNonNull( getActivity() ).findViewById( R.id.viewPager );

        settingsHeightSpinners();

        editAge.addTextChangedListener( this );
        editWeight.addTextChangedListener( this );
        editHeight.addTextChangedListener( this );

        btnCalculate.setOnClickListener( v -> calculate() );

        Font font = Font.getInstance( getContext() );
        font.iranSansMedium( editAge );
        font.iranSansMedium( editHeight );
        font.iranSansMedium( editHeightInch );
        font.iranSansMedium( editWeight );
        font.yekan( txtInfo );
        font.yekan( btnCalculate );

        return view;
    }

    private void calculate() {
        double userWeight = 0;
        double userHeight = 0;
        double userHeightInch = 0;

        BmiModel model = new BmiModel();
        model.setId( bmiViewModel.getLastId() + 1 );
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd" );
        model.setDate( sdf.format( new Date() ) );
        model.setAge( Integer.parseInt( editAge.getText().toString() ) );
        model.setSex( ( radioBtnMale.isChecked() ) ? Const.KEY_MALE : ( radioBtnFemale.isChecked() ) ? Const.KEY_FEMALE : "" );

        userWeight = Double.valueOf( editWeight.getText().toString() );
        userHeight = Double.valueOf( editHeight.getText().toString() );
        if ( isInch ) {
            userHeightInch = Double.valueOf( editHeightInch.getText().toString() );
        }

        model.setHeight( userHeight );
        model.setWeight( userWeight );
        model.setWeightUnit( spinnerWeightUnit.getSelectedItem().toString() );
        model.setHeightInch( userHeightInch );
        model.setHeightUnit( spinnerHeightUnit.getSelectedItem().toString() );

        int userWeightUnitPos = spinnerWeightUnit.getSelectedItemPosition();
        model.setWeightUnitPosition( userWeightUnitPos );
        int userHeightUnitPos = spinnerHeightUnit.getSelectedItemPosition();
        model.setHeightUnitPosition( userHeightUnitPos );

        model.setResult( BmiHelper.getInstance().getBMIResult( userWeightUnitPos , userHeightUnitPos , userHeight , userWeight , userHeightInch ) );
        model.setStatus( 1 );

        bmiViewModel.insert( model );

        viewPager.setCurrentItem( 3 );
    }

    @Override
    public void onViewStateRestored( Bundle savedInstanceState ) {
        super.onViewStateRestored( savedInstanceState );
    }

    @SuppressLint("SetTextI18n")
    private void showUserValue( BmiModel model ) {
        if ( model != null ) {
            int userWeight;
            int userHeight;
            int userHeightInch;

            int userAge = model.getAge();
            String userSex = model.getSex();
            userWeight = ( int ) model.getWeight();
            userHeight = ( int ) model.getHeight();
            String userWeightUnit = model.getWeightUnit();
            String userHeightUnit = model.getHeightUnit();
            userHeightInch = ( int ) model.getHeightInch();

            editAge.setText( userAge + "" );
            editWeight.setText( userWeight + "" );
            editHeight.setText( userHeight + "" );

            ArrayAdapter<CharSequence> weightArray = ArrayAdapter.createFromResource( Objects.requireNonNull( this.getContext() )
                    , R.array.weightArray , android.R.layout.simple_spinner_item );
            weightArray.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
            spinnerWeightUnit.setAdapter( weightArray );
            int weightSpinnerPos = weightArray.getPosition( userWeightUnit );
            spinnerWeightUnit.setSelection( weightSpinnerPos );

            ArrayAdapter<CharSequence> heightArray = ArrayAdapter.createFromResource( Objects.requireNonNull( this.getContext() )
                    , R.array.heightArray , android.R.layout.simple_spinner_item );
            heightArray.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
            spinnerHeightUnit.setAdapter( heightArray );
            int heightSpinnerPos = heightArray.getPosition( userHeightUnit );
            spinnerHeightUnit.setSelection( heightSpinnerPos );

            if ( userHeightUnit.equals( Const.KEY_CM ) ) {
                editHeightInch.setVisibility( View.GONE );
            } else {
                editHeightInch.setText( userHeightInch + "" );
                editHeightInch.setVisibility( View.VISIBLE );
            }

            if ( userSex.equals( Const.KEY_MALE ) ) {
                radioBtnMale.setChecked( true );
            } else if ( userSex.equals( Const.KEY_FEMALE ) ) {
                radioBtnFemale.setChecked( true );
            }
        }
    }

    private void settingsHeightSpinners() {
        spinnerHeightUnit.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected( AdapterView parent , View view , int position , long id ) {
                if ( position == 0 ) {
                    editHeightInch.setVisibility( View.GONE );
                    isInch = false;
                } else {
                    editHeightInch.setVisibility( View.VISIBLE );
                    isInch = true;
                }
            }

            @Override
            public void onNothingSelected( AdapterView<?> parent ) {
            }
        } );
    }

    @Override
    public void beforeTextChanged( CharSequence s , int start , int count , int after ) {
        if ( s.toString().trim().length() == 0 ) {
            btnCalculate.setEnabled( false );
        } else {
            btnCalculate.setEnabled( true );
        }
    }

    @Override
    public void onTextChanged( CharSequence s , int start , int before , int count ) {

    }

    @Override
    public void afterTextChanged( Editable s ) {

    }
}