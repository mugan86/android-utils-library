package anartzmugika.library;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import anartzmugika.utils.tools.Chronometer;

/**
 * Created by anartzmugika on 16/1/17.
 */

public class ChronometerActivity extends AppCompatActivity{

    private Chronometer chronometer;
    private Button start_stop_chronoButton;
    private Activity context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_chronometer);

        this.context = ChronometerActivity.this;

        chronometer = (Chronometer) findViewById(R.id.chronometer);
        start_stop_chronoButton = (Button) findViewById(R.id.start_stop_chronoButton);
        start_stop_chronoButton.setText("Start");

        start_stop_chronoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (start_stop_chronoButton.getText().toString().equals("Start"))
                {
                    chronometer.start();
                    start_stop_chronoButton.setText("Stop");
                }
                else
                {
                    start_stop_chronoButton.setText("Start");
                    chronometer.stop();
                }
            }
        });

    }
}
