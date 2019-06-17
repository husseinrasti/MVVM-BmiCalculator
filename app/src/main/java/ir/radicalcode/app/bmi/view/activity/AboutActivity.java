package ir.radicalcode.app.bmi.view.activity;

import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import ir.radicalcode.app.bmi.R;

public class AboutActivity extends AppCompatActivity {

    private Unbinder unbinder;

    @BindView(R.id.txtEmail)
    TextView email;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_about );
        unbinder = ButterKnife.bind( this );

        email.setText( Html.fromHtml( "<a href=\"mailto:teachcode.ir@gmail.com\">ارسال نظر و پیشنهاد</a>" ) );
        email.setMovementMethod( LinkMovementMethod.getInstance() );
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
