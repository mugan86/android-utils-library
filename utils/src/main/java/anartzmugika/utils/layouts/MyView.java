package anartzmugika.utils.layouts;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;

import anartzmugika.utils.R;
import anartzmugika.utils.actions.AppInfo;

/**
 * Created by anartzmugika on 23/11/16.
 *
 * Example to manage 'MyView' from MainActivity
 */

public class MyView extends LinearLayout {

    private TextView titleTextView, descriptionTextView;
    private Button changeButton, resetButton;
    private Context context;
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
        resetButton.setEnabled(false);
    }

    private void addActions()
    {
        changeButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                titleTextView.setText("Change: " + new Random().nextInt(100 - 50 + 1) + 50);
                resetButton.setEnabled(true);
                AppInfo.goToGooglePlayAppPage(context);
            }
        });

        resetButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                titleTextView.setText("Test with library!!!");
                resetButton.setEnabled(false);
            }
        });
    }

    public void addText(String text)
    {
        titleTextView.setText(text);
    }


}