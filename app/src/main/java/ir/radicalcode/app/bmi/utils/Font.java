package ir.radicalcode.app.bmi.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.widget.TextView;

/**
 * Created by Hossein on 7/10/2018.
 */

public class Font {

    private Typeface typeface;
    private AssetManager assetManager;

    private static Font font;

    public static Font getInstance( Context context ) {
        if ( font == null ) {
            font = new Font( context );
        }

        return font;
    }

    private Font( Context context ) {
        assetManager = context.getApplicationContext().getAssets();
    }

    public void titr( TextView view ) {
        typeface = Typeface.createFromAsset( assetManager , "fonts/titr.ttf" );
        view.setTypeface( typeface );
    }

    public void iranSans( TextView view ) {
        typeface = Typeface.createFromAsset( assetManager , "fonts/iran_sans.ttf" );
        view.setTypeface( typeface );
    }

    public void iranSansBold( TextView view ) {
        typeface = Typeface.createFromAsset( assetManager , "fonts/iran_sans_bold.ttf" );
        view.setTypeface( typeface );
    }

    public void iranSansLight( TextView view ) {
        typeface = Typeface.createFromAsset( assetManager , "fonts/iran_sans_light.ttf" );
        view.setTypeface( typeface );
    }

    public void iranSansMedium( TextView view ) {
        typeface = Typeface.createFromAsset( assetManager , "fonts/iran_sans_medium.ttf" );
        view.setTypeface( typeface );
    }

    public void iranSansUltraLight( TextView view ) {
        typeface = Typeface.createFromAsset( assetManager , "fonts/iran_sans_ultra_light.ttf" );
        view.setTypeface( typeface );
    }

    public void yekan( TextView view ) {
        typeface = Typeface.createFromAsset( assetManager , "fonts/yekan.ttf" );
        view.setTypeface( typeface );
    }

}
