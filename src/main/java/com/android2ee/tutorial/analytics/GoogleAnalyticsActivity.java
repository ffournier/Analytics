package com.android2ee.tutorial.analytics;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android2ee.tutorial.analytics.tool.TrackerName;
import com.google.android.gms.analytics.ExceptionReporter;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Logger;
import com.google.android.gms.analytics.StandardExceptionParser;
import com.google.android.gms.analytics.Tracker;
import com.google.android.gms.analytics.ecommerce.Product;
import com.google.android.gms.analytics.ecommerce.ProductAction;
import com.google.android.gms.analytics.ecommerce.Promotion;

import java.io.IOException;
import java.util.Date;


/**
 * Created by florian on 27/01/15.
 * Class GoogleAnalytics
 */
public class GoogleAnalyticsActivity extends Activity implements View.OnClickListener {

    // Screen Name
    private static final String SCREEN_NAME_GA = "GoogleAnalytics Screen Activity";
    private static final String SCREEN_NAMETEST_GA = "GoogleAnalytics Test Screen";

    // button session
    Button btnSession;
    // button create crash
    Button btnCreateCrash;
    // button intercept crash
    Button btnInterceptCrash;
    // button screen
    Button btnScreen;
    // button custom event
    Button btnCustom;
    // button event1
    Button btnEvent1;
    // button event2
    Button btnEvent2;
    // button social
    Button btnSocial;
    // button social
    Button btnUserTime;
    // button campaign
    Button btnCampaign;
    // button transaction
    Button btnTranscation;
    // button promotion
    Button btnPromotion;

    // start timer
    boolean startTimer;
    // date
    Date mDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ga_activity);

        // initialize variable startTimer
        startTimer = false;

        // initialize buttons
        btnSession = (Button) findViewById(R.id.ga_button_session);
        btnSession.setOnClickListener(this);
        btnCreateCrash = (Button) findViewById(R.id.ga_button_createcrash);
        btnCreateCrash.setOnClickListener(this);
        btnInterceptCrash = (Button) findViewById(R.id.ga_button_interceptcrash);
        btnInterceptCrash.setOnClickListener(this);
        btnScreen = (Button) findViewById(R.id.ga_button_screen);
        btnScreen.setOnClickListener(this);
        btnCustom = (Button) findViewById(R.id.ga_button_custom);
        btnCustom.setOnClickListener(this);
        btnEvent1 = (Button) findViewById(R.id.ga_button_event1);
        btnEvent1.setOnClickListener(this);
        btnEvent2 = (Button) findViewById(R.id.ga_button_event2);
        btnEvent2.setOnClickListener(this);
        btnSocial = (Button) findViewById(R.id.ga_button_socialinteraction);
        btnSocial.setOnClickListener(this);
        btnUserTime = (Button) findViewById(R.id.ga_button_userTiming);
        btnUserTime.setOnClickListener(this);
        btnCampaign = (Button) findViewById(R.id.ga_button_campaign);
        btnCampaign.setOnClickListener(this);
        btnTranscation = (Button) findViewById(R.id.ga_button_transaction);
        btnTranscation.setOnClickListener(this);
        btnPromotion = (Button) findViewById(R.id.ga_button_promotion);
        btnPromotion.setOnClickListener(this);

        // Set the log level to verbose.
        GoogleAnalytics.getInstance(this).getLogger()
                .setLogLevel(Logger.LogLevel.VERBOSE);

        Log.d(getClass().getCanonicalName(), "on Create");
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Get tracker.
        Tracker t = ((MyApplication) getApplication()).getTracker(
                TrackerName.GLOBAL_TRACKER);

        // Set screen name.
        t.setScreenName(SCREEN_NAME_GA);

        // Send a screen view.
        t.send(new HitBuilders.AppViewBuilder().build());

        Log.i(getClass().getCanonicalName(), "on Resume");
    }

    @Override
    public void onClick(View v) {
        // Get tracker.
        Tracker t = ((MyApplication) getApplication()).getTracker(
                TrackerName.GLOBAL_TRACKER);
        Tracker tCommerce = ((MyApplication) getApplication()).getTracker(
                TrackerName.ECOMMERCE_TRACKER);
        switch(v.getId()) {
            // button session
            case R.id.ga_button_session:

               // Start a new session with the hit.
                t.send(new HitBuilders.AppViewBuilder()
                        .setNewSession()
                        .build());
                break;
            // button create crash
            case R.id.ga_button_createcrash:
                // Build and send exception.
                IOException e = new IOException("Exception create by me");
                Log.w(getClass().getCanonicalName(), "Exception create by me");
                t.send(new HitBuilders.ExceptionBuilder().setDescription(
                        new StandardExceptionParser(this, null)
                                .getDescription(Thread.currentThread().getName(), e))
                        .setFatal(true)
                        .build());
                break;
            // button intercept crash
            case R.id.ga_button_interceptcrash:
                Thread.UncaughtExceptionHandler myHandler = new ExceptionReporter(
                        t,                                        // Currently used Tracker.
                        Thread.getDefaultUncaughtExceptionHandler(),      // Current default uncaught exception handler.
                        getApplicationContext());                                         // Context of the application.

                // Make myHandler the new default uncaught exception handler.
                Thread.setDefaultUncaughtExceptionHandler(myHandler);
                OutOfMemoryError e1 = new OutOfMemoryError("Exception generate by me");
                Log.e(getClass().getCanonicalName(), "Exception generate by me");
                throw e1;
            // button screen
            case R.id.ga_button_screen:
                // Set screen name.
                t.setScreenName(SCREEN_NAMETEST_GA);
                // Send a screen view.
                t.send(new HitBuilders.AppViewBuilder().build());
                break;
            // button custom event
            case R.id.ga_button_custom:
                t.send(new HitBuilders.EventBuilder()
                        .setCategory("categrory 1")
                        .setAction("action 2")
                        .setLabel("event custom")
                        .setValue(5)
                        .build());
                break;
            // button event1
            case R.id.ga_button_event1:
                t.send(new HitBuilders.EventBuilder()
                        .setCategory("categrory 1")
                        .setAction("action 2")
                        .setLabel("event 1")
                        .build());
                break;
            // button event2
            case R.id.ga_button_event2:
                t.send(new HitBuilders.EventBuilder()
                        .setCategory("categrory 2")
                        .setAction("action 1")
                        .setLabel("event 2")
                        .build());
                break;
            // button social interaction
            case R.id.ga_button_socialinteraction:
            // Build and send social interaction.
                t.send(new HitBuilders.SocialBuilder()
                        .setNetwork("Facebook")
                        .setAction("Share")
                        .setTarget("Article")
                        .build());
                break;
            // button user timing
            case R.id.ga_button_userTiming:
                // test if a timer was started
                if (startTimer) {
                    // if yes we send event
                    btnUserTime.setText("Start User Timing");
                    long time = new Date().getTime() - mDate.getTime();
                    // Build and send timing.
                    t.send(new HitBuilders.TimingBuilder()
                            .setCategory("category 3")
                            .setValue(time)
                            .setVariable("User Timer")
                            .setLabel("The User Time")
                            .build());
                } else {
                    // we start timer , and save date
                    mDate = new Date();
                    btnUserTime.setText("Send User Timing");
                }
                startTimer = !startTimer;
                break;
            // button campaign
            case R.id.ga_button_campaign:
                String campaignData = "http://examplepetstore.com/index.html?" +
                        "utm_source=email&utm_medium=email_marketing&utm_campaign=summer" +
                        "&utm_content=email_variation_1";

                // Campaign data sent with this hit.
                t.send(new HitBuilders.ScreenViewBuilder()
                        .setCampaignParamsFromUrl(campaignData)
                        .build());

                break;
            // button transaction
            case R.id.ga_button_transaction:
                // The product being viewed.
                Product viewedProduct =  new Product()
                        .setId("P12345")
                        .setName("Android Product")
                        .setCategory("Category Product")
                        .setBrand("Google")
                        .setVariant("Black")
                        .setPosition(1);
                ProductAction productAction = new ProductAction(ProductAction.ACTION_DETAIL);
                HitBuilders.ScreenViewBuilder builderTranscation = new HitBuilders.ScreenViewBuilder()
                        .addImpression(viewedProduct, "Product")
                        .addProduct(viewedProduct)
                        .setProductAction(productAction);
                t.send(builderTranscation.build());
                break;
            // button promotion
            case R.id.ga_button_promotion:
                Promotion promotion = new Promotion()
                        .setId("PROMO_1234")
                        .setName("Summer Sale")
                        .setCreative("summer_banner2")
                        .setPosition("banner_slot1");
                ProductAction promoClickAction = new ProductAction(Promotion.ACTION_CLICK);
                HitBuilders.EventBuilder builderPromotion = new HitBuilders.EventBuilder()
                        .addPromotion(promotion)
                        .setProductAction(promoClickAction)
                        .setCategory("Internal Promotions")
                        .setAction("click")
                        .setLabel("Summer Sale");
                tCommerce.send(builderPromotion.build());
                break;
            default:
                break;
        }

    }
}
