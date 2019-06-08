package ir.radicalcode.app.bmi.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import ir.radicalcode.app.bmi.R;
import ir.radicalcode.app.bmi.utils.Font;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate( @Nullable Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.splash );

        Font.getInstance( this ).yekan( findViewById( R.id.txtBmi ) );

        new Handler().postDelayed( () -> {
            Intent intent = new Intent( SplashActivity.this , StartupActivity.class );
            startActivity( intent );
            finish();
        } , 2000 );
    }
}
