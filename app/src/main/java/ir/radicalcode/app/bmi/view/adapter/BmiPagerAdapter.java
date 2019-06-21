package ir.radicalcode.app.bmi.view.adapter;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import ir.radicalcode.app.bmi.view.fragment.AddInfoBmiFragment;
import ir.radicalcode.app.bmi.view.fragment.HistoryFragment;
import ir.radicalcode.app.bmi.view.fragment.HomeFragment;
import ir.radicalcode.app.bmi.view.fragment.ArticleFragment;

public class BmiPagerAdapter extends FragmentPagerAdapter {

    private Context context;

    public BmiPagerAdapter( Context context , FragmentManager fm ) {
        super( fm );
        this.context = context;
    }

    @Override
    public Fragment getItem( int pos ) {
        switch ( pos ) {
            case 0:
                return ArticleFragment.newInstance( 1 );
            case 1:
                return AddInfoBmiFragment.newInstance( 2 );
            case 2:
                return HistoryFragment.newInstance( 3 );
            case 3:
                return HomeFragment.newInstance( 4 );
        }
        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }

}
