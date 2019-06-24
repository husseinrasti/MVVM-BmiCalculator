package ir.radicalcode.app.bmi.view.activity;

import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import ir.radicalcode.app.bmi.R;
import ir.radicalcode.app.bmi.utils.Font;

public class AboutActivity extends AppCompatActivity {

    private Unbinder unbinder;

    @BindView(R.id.toolbar)
    ConstraintLayout toolbar;
    @BindView(R.id.txtTitleToolbar)
    TextView title;
    @BindView(R.id.txtAppFa)
    TextView txtAppFa;
    @BindView(R.id.txtAppEn)
    TextView txtAppEn;
    @BindView(R.id.txtAuthor)
    TextView txtAuthor;
    @BindView(R.id.txtFeedback)
    TextView txtFeedback;
    @BindView(R.id.txtEmail)
    TextView email;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_about );
        unbinder = ButterKnife.bind( this );

        title.setText( getString( R.string.about ) );

        Font font = Font.getInstance( this );
        font.yekan( title );
        font.iranSansBold( txtAuthor );
        font.iranSansMedium( txtAppEn );
        font.iranSansMedium( txtAppFa );
        font.iranSans( txtFeedback );
        font.iranSans( email );

        email.setText( Html.fromHtml( "<a href=\"mailto:teachcode.ir@gmail.com\">ارسال نظر و پیشنهاد</a>" ) );
        email.setMovementMethod( LinkMovementMethod.getInstance() );
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
