package ir.radicalcode.app.bmi.view.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import butterknife.ButterKnife;
import ir.radicalcode.app.bmi.R;
import ir.radicalcode.app.bmi.root.Injection;
import ir.radicalcode.app.bmi.utils.Const;
import ir.radicalcode.app.bmi.view.viewmodel.BmiViewModel;
import ir.radicalcode.app.bmi.view.viewmodel.FactoryViewModel;

public class SettingsFragment extends Fragment {

    private FactoryViewModel factoryViewModel;
    private BmiViewModel bmiViewModel;

    public static SettingsFragment newInstance( int pageNo ) {
        Bundle args = new Bundle();
        args.putInt( Const.ARG_PAGE , pageNo );
        SettingsFragment settingsFragment = new SettingsFragment();
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
    }

    @Override
    public View onCreateView( @NonNull LayoutInflater inflater , ViewGroup container ,
                              Bundle savedInstanceState ) {
        View view = inflater.inflate( R.layout.fragment_settings , container , false );
        ButterKnife.bind( this , view );
        return view;
    }

}
