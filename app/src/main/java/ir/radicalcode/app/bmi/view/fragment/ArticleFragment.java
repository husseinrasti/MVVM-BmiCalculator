package ir.radicalcode.app.bmi.view.fragment;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import ir.radicalcode.app.bmi.R;
import ir.radicalcode.app.bmi.data.entity.ArticleModel;
import ir.radicalcode.app.bmi.utils.Const;
import ir.radicalcode.app.bmi.view.adapter.ArticleItemAdapter;
import ir.radicalcode.app.bmi.view.interfaces.OnArticleItemClickListener;

public class ArticleFragment extends Fragment implements OnArticleItemClickListener {

    private static final String DIR_ASSETS_WEB = "file:///android_asset/web/";
    private static final String DEFUALT_PAGE = "file:///android_asset/web/index.html";

    @BindView(R.id.webView)
    WebView webView;
    @BindView(R.id.recyclerArticle)
    RecyclerView recyclerArticle;

    private float lastViewY;

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

        webView.loadUrl( DEFUALT_PAGE );
        if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.M ) {
            webView.setOnScrollChangeListener( ( v , scrollX , scrollY , oldScrollX , oldScrollY ) -> {
                if ( scrollY > oldScrollY ) {
                    slideDown( recyclerArticle );
                } else if ( ( scrollY + 20 ) < oldScrollY ) {
                    slideUp( recyclerArticle );
                }
            } );
        }

        List<ArticleModel> list = makeArticleList();
        ArticleItemAdapter adapter = new ArticleItemAdapter( getContext() , this , list );

        recyclerArticle.setLayoutManager( new LinearLayoutManager( getContext() , RecyclerView.HORIZONTAL , false ) );
        recyclerArticle.setAdapter( adapter );

        lastViewY = recyclerArticle.getY();

        return view;
    }

    private List<ArticleModel> makeArticleList() {
        List<ArticleModel> list = new ArrayList<>();

        for ( int i = 0 ; i < Const.NAMES_ARTICLE.length ; i++ ) {
            ArticleModel model = new ArticleModel();
            model.setId( i + 1 );
            model.setTitle( Const.NAMES_ARTICLE[i] );
            model.setPic( Const.RES_IMAGE_ARTICLE[i] );
            model.setDesc( Const.NAMES_WEB_ASSETS_ARTICLE[i] );

            list.add( model );
        }

        return list;
    }

    @Override
    public void onItemClick( ArticleModel articleModel ) {
        webView.loadUrl( DIR_ASSETS_WEB + articleModel.getDesc() );
    }

    private void slideUp( View view ) {
        view.setVisibility( View.VISIBLE );
        view.animate()
                .setDuration( 1000 )
                .translationY( lastViewY )
                .setListener( null );
    }

    private void slideDown( View view ) {
        view.animate()
                .setDuration( 1000 )
                .translationY( 0 )
                .setListener( new AnimatorListenerAdapter() {

                    @Override
                    public void onAnimationEnd( Animator animation ) {
                        super.onAnimationEnd( animation );
                        view.setVisibility( View.GONE );
                    }
                } );
    }
}
