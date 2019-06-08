package ir.radicalcode.app.bmi.view.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import butterknife.BindView;
import butterknife.ButterKnife;
import ir.radicalcode.app.bmi.R;
import ir.radicalcode.app.bmi.root.Injection;
import ir.radicalcode.app.bmi.utils.Font;
import ir.radicalcode.app.bmi.view.viewmodel.BmiViewModel;
import ir.radicalcode.app.bmi.view.viewmodel.ViewModelFactory;


public class IntroFinalFragment extends Fragment {

    @BindView(R.id.txtResultBmi)
    TextView txtResultBmi;
    @BindView(R.id.txtTitleResultBmi)
    TextView txtTitleResultBmi;

    private double bmiResult;

    private ViewModelFactory viewModelFactory;
    private BmiViewModel bmiViewModel;

    @Override
    public void onCreate( @Nullable Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        viewModelFactory = Injection.provideBMIViewModelFactory( getActivity() );
        bmiViewModel = ViewModelProviders.of( this , viewModelFactory ).get( BmiViewModel.class );
    }

    @Nullable
    @Override
    public View onCreateView( LayoutInflater inflater , @Nullable ViewGroup container , @Nullable Bundle savedInstanceState ) {
        View view = inflater.inflate( R.layout.fragment_intor_final , container , false );
        ButterKnife.bind( this , view );

        Font.getInstance( getContext() ).yekan( txtResultBmi );
        Font.getInstance( getContext() ).yekan( txtTitleResultBmi );

        return view;
    }

    @Override
    public void setUserVisibleHint( boolean isVisibleToUser ) {
        super.setUserVisibleHint( isVisibleToUser );
        if ( isVisibleToUser ) {
            bmiResult = bmiViewModel.getLastResult();
            if ( bmiResult != 0 ) {
                animateTextView( 0 , ( int ) Math.round( bmiResult ) , txtResultBmi );
            } else {
                Toast.makeText( getContext() , "نتیجه یافت نشد یا ثبت نشده است" , Toast.LENGTH_LONG ).show();
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private void animateTextView( int initialValue , int finalValue , final TextView textview ) {
        DecelerateInterpolator decelerateInterpolator = new DecelerateInterpolator( 0.8f );
        int start = Math.min( initialValue , finalValue );
        int end = Math.max( initialValue , finalValue );
        int difference = ( finalValue - initialValue ); //Math.abs
        Handler handler = new Handler();
        for ( int count = start ; count <= end ; count++ ) {
            int time = Math.round( decelerateInterpolator.getInterpolation( ( ( ( float ) count ) / difference ) ) * 100 ) * count;
            final int finalCount = ( ( initialValue > finalValue ) ? initialValue - count : count );
            handler.postDelayed( () -> textview.setText( finalCount + "" ) , time );
        }
    }

}
