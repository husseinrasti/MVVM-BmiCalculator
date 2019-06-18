package ir.radicalcode.app.bmi.view.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import ir.radicalcode.app.bmi.R;
import ir.radicalcode.app.bmi.root.Injection;
import ir.radicalcode.app.bmi.utils.Const;
import ir.radicalcode.app.bmi.view.adapter.BmiDataAdapter;
import ir.radicalcode.app.bmi.view.viewmodel.BmiViewModel;
import ir.radicalcode.app.bmi.view.viewmodel.FactoryViewModel;


public class HistoryFragment extends Fragment {

    private BmiDataAdapter adapter;

    @BindView(R.id.list)
    RecyclerView recyclerView;

    private FactoryViewModel factoryViewModel;
    private BmiViewModel bmiViewModel;

    public static HistoryFragment newInstance( int pageNo ) {
        Bundle args = new Bundle();
        args.putInt( Const.ARG_PAGE , pageNo );
        HistoryFragment historyFragment = new HistoryFragment();
        historyFragment.setArguments( args );
        return historyFragment;
    }

    @Override
    public void onCreate( @Nullable Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        factoryViewModel = Injection.provideBMIViewModelFactory( getActivity() );
        bmiViewModel = ViewModelProviders.of( this , factoryViewModel ).get( BmiViewModel.class );
    }

    @Override
    public View onCreateView( @NonNull LayoutInflater inflater , ViewGroup container , Bundle savedInstanceState ) {
        View view = inflater.inflate( R.layout.fragment_history , container , false );
        ButterKnife.bind( this , view );

        adapter = new BmiDataAdapter( getActivity() );
        recyclerView.setLayoutManager( new LinearLayoutManager( getActivity() ) );
        recyclerView.setAdapter( adapter );

        bmiViewModel.getAll().observe( this , bmiModels -> adapter.setList( bmiModels ) );

        return view;
    }

    @Override
    public void onViewStateRestored( @Nullable Bundle savedInstanceState ) {
        super.onViewStateRestored( savedInstanceState );
    }

    @Override
    public void setUserVisibleHint( boolean isVisibleToUser ) {
        super.setUserVisibleHint( isVisibleToUser );
    }

}
