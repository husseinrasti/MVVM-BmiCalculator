package ir.radicalcode.app.bmi.view.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import butterknife.ButterKnife;
import ir.radicalcode.app.bmi.R;
import ir.radicalcode.app.bmi.utils.Const;

public class ArticleFragment extends Fragment {

    public static ArticleFragment newInstance( int pageNo ) {
        Bundle args = new Bundle();
        args.putInt( Const.ARG_PAGE , pageNo );
        ArticleFragment articleFragment = new ArticleFragment();
        articleFragment.setArguments( args );
        return articleFragment;
    }

    @Override
    public void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        assert getArguments() != null;
        int mPageNo = getArguments().getInt( Const.ARG_PAGE );
    }

    @Override
    public View onCreateView( @NonNull LayoutInflater inflater , ViewGroup container , Bundle savedInstanceState ) {
        View view = inflater.inflate( R.layout.fragment_article , container , false );
        ButterKnife.bind( this , view );
        return view;
    }
}
