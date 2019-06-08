package ir.radicalcode.app.bmi.view.activity;

import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import ir.radicalcode.app.bmi.R;
import ir.radicalcode.app.bmi.utils.Font;

public class ThanksActivity extends AppCompatActivity {

    @Override
    protected void onCreate( @Nullable Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_thanks );

        Font.getInstance( this ).iranSans( findViewById( R.id.txtThanks ) );

        new Handler().postDelayed( this::finish , 2000 );
    }
}
