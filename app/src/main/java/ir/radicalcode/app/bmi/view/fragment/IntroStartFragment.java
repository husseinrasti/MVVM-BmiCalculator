package ir.radicalcode.app.bmi.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import ir.radicalcode.app.bmi.R;
import ir.radicalcode.app.bmi.utils.Font;

public class IntroStartFragment extends Fragment {

    @BindView(R.id.txtAppName)
    TextView txtAppName;
    @BindView(R.id.txtStartApp)
    TextView txtStartApp;
    @BindView(R.id.txtStartAppBMI)
    TextView txtStartAppBMI;

    @Override
    public void onCreate( @Nullable Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
    }

    @Nullable
    @Override
    public View onCreateView( LayoutInflater inflater , @Nullable ViewGroup container , @Nullable Bundle savedInstanceState ) {
        View view = inflater.inflate( R.layout.fragment_intor_start , container , false );
        ButterKnife.bind( this , view );

        Font font = Font.getInstance( getContext() );
        font.titr( txtAppName );
        font.iranSansBold( txtStartApp );
        font.iranSans( txtStartAppBMI );

        return view;
    }
}
