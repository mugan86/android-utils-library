package anartzmugika.utils.layouts;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Random;

import anartzmugika.utils.R;
import anartzmugika.utils.actions.IntentsManage;
import anartzmugika.utils.activities.WebViewActivity;
import anartzmugika.utils.activities.WelcomeActivity;

/***************************************************************************************************
 * Created by anartzmugika on 23/11/16. Update by Anartz on 2017-01-11
 *
 * Example to manage 'MyView' from MainActivity
 **************************************************************************************************/

public class MyView extends LinearLayout {

    private TextView titleTextView, descriptionTextView;
    private Button changeButton, resetButton, show_servirace_main_pageButton, welcomeButton;
    private Context context;
    private TouchImageView touchImageView;
    public MyView(Context context) {
        super(context);
        initialize(context);
        startComponents();
        addActions();
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize(context);
        startComponents();
        addActions();
    }

    private void initialize(Context context){
        inflate(context, R.layout.my_view, this);
        this.context = context;
        startComponents();
        addActions();
    }

    private void startComponents()
    {
        titleTextView = (TextView) findViewById(R.id.titleTextView);
        descriptionTextView = (TextView) findViewById(R.id.descriptionTextView);
        changeButton = (Button) findViewById(R.id.changeButton);
        resetButton = (Button) findViewById(R.id.resetButton);
        show_servirace_main_pageButton = (Button) findViewById(R.id.show_servirace_main_pageButton);
        welcomeButton = (Button) findViewById(R.id.welcomeButton);
        resetButton.setEnabled(false);
        touchImageView = (TouchImageView) findViewById(R.id.touchImageView);

        Picasso.with(context).load("http://i.imgur.com/DvpvklR.png").into(touchImageView);
    }

    private void addActions()
    {
        changeButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                titleTextView.setText("Change: " + new Random().nextInt(100 - 50 + 1) + 50);
                resetButton.setEnabled(true);
                //AppInfo.goToGooglePlayAppPage(context);
            }
        });

        resetButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                titleTextView.setText("Test with library!!!");
                resetButton.setEnabled(false);
            }
        });

        show_servirace_main_pageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentsManage.openWebViewIntent(context, WebViewActivity.class, "http://www.servirace.com");
            }
        });

        welcomeButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentsManage.openIntent(context, WelcomeActivity.class);
            }
        });
    }

    public void addText(String text)
    {
        titleTextView.setText(text);
    }


}