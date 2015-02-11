package com.android2ee.tutorial.analytics;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.android2ee.tutorial.analytics.R;
import com.flurry.android.FlurryAgent;

import java.util.HashMap;
import java.util.Map;

import ly.count.android.api.Countly;
import ly.count.android.api.DeviceId;

/**
 * Created by florian on 27/01/15.
 * Class Flurry
 */
public class FlurryActivity extends Activity implements View.OnClickListener {

    // key of Flurr, here put YOUR APP_KEY
    private static final String MY_FLURRY_APIKEY = "PRW22GYXSPH79FZ6MPMF";

    // button event1
    Button btnEvent1;
    // button event2
    Button btnEvent2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.flurry_activity);

        // configure Flurry
        FlurryAgent.setLogEnabled(true);

        // init Flurry
        FlurryAgent.init(this, MY_FLURRY_APIKEY);

        // set user data
        setUserData();

        // initialize button
        btnEvent1 = (Button) findViewById(R.id.flurry_button_event1);
        btnEvent1.setOnClickListener(this);
        btnEvent2 = (Button) findViewById(R.id.flurry_button_event2);
        btnEvent2.setOnClickListener(this);
    }

    /**
     * set User Data
     */
    private void setUserData() {
        FlurryAgent.setAge(18);
        FlurryAgent.setGender((byte)0);
    }

    @Override
    protected void onStart() {
        super.onStart();
        // start Flurry
        FlurryAgent.onStartSession(this, MY_FLURRY_APIKEY);
    }

    @Override
    protected void onStop() {
        super.onStop();
        // stop Flurry
        FlurryAgent.onEndSession(this);
    }



    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            // button event1
            case R.id.flurry_button_event1:
                // Capture author info & user status
                Map<String, String> articleParams = new HashMap<String, String>();
                //param keys and values have to be of String type
                articleParams.put("Author", "John Q");
                articleParams.put("User_Status", "Registered");
                //up to 10 params can be logged with each event
                FlurryAgent.logEvent("Article_Read", articleParams);
                break;
            // button event2
            case R.id.flurry_button_event2:
                // Capture author info & user status
                Map<String, String> articleParams2 = new HashMap<String, String>();
                articleParams2.put("Author", "John Q");
                articleParams2.put("User_Status", "Registered");

                //Log the timed event when the user starts reading the article
                //setting the third param to true creates a timed event
                FlurryAgent.logEvent("Article_Read", articleParams2, true);
                // End the timed event, when the user navigates away from article
                FlurryAgent.endTimedEvent("Article_Read");
                break;
            default:
                break;
        }
    }
}
