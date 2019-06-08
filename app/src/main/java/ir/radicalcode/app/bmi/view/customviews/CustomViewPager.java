package ir.radicalcode.app.bmi.view.customviews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.viewpager.widget.ViewPager;

public class CustomViewPager extends ViewPager {

    private boolean enabled;

    public CustomViewPager( Context context , AttributeSet attrs ) {
        super( context , attrs );
        this.enabled = true;
    }

    @Override
    public boolean onTouchEvent( MotionEvent event ) {
        if ( enabled ) {
            return super.onTouchEvent( event );
        } else {
            return false;
        }
    }

    @Override
    public boolean onInterceptTouchEvent( MotionEvent event ) {
        return enabled && super.onInterceptTouchEvent( event );
    }

    public void setPagingEnabled( boolean enabled ) {
        this.enabled = enabled;
    }

    public boolean isPagingEnabled() {
        return enabled;
    }

}
