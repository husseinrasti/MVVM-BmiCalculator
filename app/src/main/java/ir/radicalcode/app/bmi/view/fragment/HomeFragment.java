package ir.radicalcode.app.bmi.view.fragment;


import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Objects;

import androidx.annotation.NonNull;
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
import ir.radicalcode.app.bmi.view.customviews.GaugeView;
import ir.radicalcode.app.bmi.view.viewmodel.BmiViewModel;
import ir.radicalcode.app.bmi.view.viewmodel.ViewModelFactory;


public class HomeFragment extends Fragment {

    @BindView(R.id.gaugeView)
    GaugeView gaugeView;
    @BindView(R.id.txtUserInfo)
    TextView txtUserInfo;
    @BindView(R.id.txtInfoResultBmi)
    TextView txtInfoResultBmi;
    @BindView(R.id.txtInfoSuggest)
    TextView txtInfoSuggest;
    @BindView(R.id.txtTitleInfoResult)
    TextView txtTitleInfoResult;
    @BindView(R.id.imgExpression)
    ImageView imgExpression;

    private BmiViewModel bmiViewModel;

    public static HomeFragment newInstance( int pageNo ) {
        Bundle args = new Bundle();
        args.putInt( Const.ARG_PAGE , pageNo );
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments( args );
        return fragment;
    }

    @Override
    public void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        int mPageNo = Objects.requireNonNull( getArguments() ).getInt( Const.ARG_PAGE );

        ViewModelFactory viewModelFactory = Injection.provideBMIViewModelFactory( getActivity() );
        bmiViewModel = ViewModelProviders.of( this , viewModelFactory ).get( BmiViewModel.class );
    }


    @Override
    public View onCreateView( @NonNull LayoutInflater inflater , ViewGroup container , Bundle savedInstanceState ) {
        View view = inflater.inflate( R.layout.fragment_home , container , false );
        ButterKnife.bind( this , view );

        bmiViewModel.getLastRecord().observe( this , model -> showUserDetails( Objects.requireNonNull( model ) ) );

        Font font = Font.getInstance( getContext() );
        font.yekan( txtTitleInfoResult );
        font.yekan( txtInfoResultBmi );
        font.iranSans( txtUserInfo );
        font.iranSans( txtInfoSuggest );

        return view;
    }


    @Override
    public void onViewStateRestored( Bundle savedInstanceState ) {
        super.onViewStateRestored( savedInstanceState );
    }

    @SuppressLint("SetTextI18n")
    private void showUserDetails( BmiModel model ) {
        if ( model != null ) {
            int userAge = model.getAge();
            double userWeight = model.getWeight();
            String userWeightUnit = model.getWeightUnit();
            double userHeight = model.getHeight();
            double userHeightInch = model.getHeightInch();
            String userHeightUnit = model.getHeightUnit();
            float bmiResult = model.getResult();

            gaugeView.setTargetValue( bmiResult );

            txtInfoResultBmi.setText( "" + bmiResult );

            if ( bmiResult < 18.5 ) {
                txtInfoResultBmi.setTextColor( Color.rgb( 0 , 153 , 232 ) );
                imgExpression.setImageResource( R.drawable.ic_expressions_blue );
            } else if ( bmiResult < 25 ) {
                txtInfoResultBmi.setTextColor( Color.rgb( 0 , 174 , 74 ) );
                imgExpression.setImageResource( R.drawable.ic_expressions_green );
            } else if ( bmiResult < 30 ) {
                txtInfoResultBmi.setTextColor( Color.rgb( 255 , 198 , 0 ) );
                imgExpression.setImageResource( R.drawable.ic_expressions_yellow );
            } else {
                txtInfoResultBmi.setTextColor( Color.rgb( 224 , 25 , 43 ) );
                imgExpression.setImageResource( R.drawable.ic_expressions_red );
            }

            txtInfoSuggest.setText( getString( R.string.final_bmi_text2 ) + " شما " + BmiHelper.getInstance().getBMIClassification( bmiResult ) + " هست." );

            if ( userHeightUnit.equals( Const.KEY_CM ) ) {
                txtUserInfo.setText( userAge + " سال | " + userWeight + " " + userWeightUnit +
                        " | " + userHeight + " " + userHeightUnit );
            } else {
                txtUserInfo.setText( userAge + " سال | " + userWeight + " " + userWeightUnit +
                        " | " + ( int ) userHeight + "' " + userHeightInch + "\"" );
            }
        }
    }

}