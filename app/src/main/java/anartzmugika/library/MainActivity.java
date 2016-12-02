package anartzmugika.library;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import anartzmugika.utils.actions.DateTime;
import anartzmugika.utils.actions.NetworkUtil;
import anartzmugika.utils.layouts.MyView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MyView view = new MyView(this);

        //Load custom view to reuse
        setContentView(view);

        System.out.println(NetworkUtil.getNetworkInfo(this));
        System.out.println(NetworkUtil.isConnectToInternet(this));
        System.out.println(DateTime.getCurrentData());
        System.out.println(DateTime.isFirstMonthDay());


        //view.addText("Test with library!!!");
    }
}
