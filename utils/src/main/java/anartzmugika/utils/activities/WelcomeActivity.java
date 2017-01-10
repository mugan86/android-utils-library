package anartzmugika.utils.activities;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Locale;

import anartzmugika.utils.Data;
import anartzmugika.utils.actions.NetworkUtil;
import anartzmugika.utils.adapter.IntroPageAdapter;
import anartzmugika.utils.R;

/***************
 * Created by anartzmugika on 8/9/16.
 */

public class WelcomeActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private IntroPageAdapter myViewPagerAdapter;
    private LinearLayout dotsLayout;
    private TextView[] dots;
    private int[] layouts;
    private Button btnSkip, btnNext, reloadButton;
    private Data prefManager;
    private LinearLayout loadDataLinearLayout;
    private boolean load_all_mountains_and_insert_in_db;

    private TextView info_about_load_data;
    private ProgressBar progress;
    private Context from_context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //GET CONNECTION STATE AND TYPE

        String connection = String.valueOf(NetworkUtil.getNetworkInfo(WelcomeActivity.this));

        /*************************************************************************
         * Set basque language config by default to show maps in basque language
         ************************************************************************/

        Locale myLocale = new Locale("eu");
        Locale.setDefault(myLocale);
        android.content.res.Configuration config = new android.content.res.Configuration();
        config.locale = myLocale;
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());

        // Checking for first time launch - before calling setContentView()
        prefManager = new Data(this);

        load_all_mountains_and_insert_in_db = false;


        // Making notification bar transparent
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        setContentView(R.layout.activity_welcome);

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        dotsLayout = (LinearLayout) findViewById(R.id.layoutDots);
        btnSkip = (Button) findViewById(R.id.btn_skip);
        btnNext = (Button) findViewById(R.id.btn_next);
        loadDataLinearLayout = (LinearLayout) findViewById(R.id.loadDataLinearLayout);

        info_about_load_data = (TextView) findViewById(R.id.info_about_load_data);
        progress = (ProgressBar) findViewById(R.id.progress);

        reloadButton = (Button) findViewById(R.id.reloadButton);
        reloadButton.setVisibility(View.GONE);

        // layouts of all welcome sliders
        // add few more layouts if you want
        layouts = new int[]{
                R.layout.main_1,
                R.layout.main_2,
                R.layout.main_3,
                R.layout.main_4};

        // adding bottom dots
        addBottomDots(0);

        // making notification bar transparent
        changeStatusBarColor();

        myViewPagerAdapter = new IntroPageAdapter(WelcomeActivity.this, layouts);
        viewPager.setAdapter(myViewPagerAdapter);
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);

        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*launchHomeScreen();*/
                openLoginApp();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // checking for last page
                // if last page home screen will be launched
                int current = getItem(+1);
                if (current < layouts.length) {
                    // move to next screen
                    viewPager.setCurrentItem(current);
                } else {
                    openLoginApp();
                }
            }
        });



        addActions();
    }

    private void addActions()
    {
        reloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                reloadButton.setVisibility(View.GONE);
                loadDataLinearLayout.setVisibility(View.GONE);
                info_about_load_data.setText("Welcome!!!");
            }
        });
    }

    private void addBottomDots(int currentPage) {
        dots = new TextView[layouts.length];

        int[] colorsActive = getResources().getIntArray(R.array.array_dot_active);
        int[] colorsInactive = getResources().getIntArray(R.array.array_dot_inactive);

        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(colorsInactive[currentPage]);
            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0)
            dots[currentPage].setTextColor(colorsActive[currentPage]);
    }

    private int getItem(int i) {
        return viewPager.getCurrentItem() + i;
    }

    private void launchHomeScreen() {

        if (!isLoad_all_mountains_and_insert_in_db())
        {
            loadDataLinearLayout.setVisibility(View.VISIBLE);
            info_about_load_data.setVisibility(View.VISIBLE);
        }
        else
        {
            //prefManager.setFirstTimeLaunch(false);
            openLoginApp();
        }

    }

    //  viewpager change listener
    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
            addBottomDots(position);

            // changing the next button text 'NEXT' / 'GOT IT'
            if (position == layouts.length - 1) {
                // last page. make button text to GOT IT
                btnNext.setText("START");
                btnSkip.setVisibility(View.GONE);
            } else {
                // still pages are left
                btnNext.setText("NEXT");
                btnSkip.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    };

    /**
     * Making notification bar transparent
     */
    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    private void openLoginApp()
    {
        //startActivity(new Intent(WelcomeActivity.this, ));
        finish();
        overridePendingTransition(0,0);
    }


    private boolean isLoad_all_mountains_and_insert_in_db()
    {
        return this.load_all_mountains_and_insert_in_db;
    }

    public void onDestroy()
    {
        super.onDestroy();
    }


}

