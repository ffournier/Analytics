package com.android2ee.tutorial.analytics;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;

import java.util.HashMap;

import ly.count.android.api.Countly;
import ly.count.android.api.DeviceId;

/**
 * Created by florian on 27/01/15.
 * Class Countlu
 */
public class CountlyActivity extends Activity implements View.OnClickListener {

    // Button event1
    Button btnEvent1;
    // button event2
    Button btnEvent2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.countly_activity);
        // initialize Countlu with OPEN_UDID, HERE put your IP_SERVER and YOUR APP_KEY
        Countly.sharedInstance().init(this, "http://192.168.0.12", "e232c8fa3ea78dea9b1406c57f155a389a913a70", null, DeviceId.Type.OPEN_UDID);
        // set User Data
        setUserData();

        // set loggingEnabled in Countly
        Countly.sharedInstance().setLoggingEnabled(true);

        // initialize button
        btnEvent1 = (Button) findViewById(R.id.countly_button_event1);
        btnEvent1.setOnClickListener(this);
        btnEvent2 = (Button) findViewById(R.id.countly_button_event2);
        btnEvent2.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        // start Countly
        Countly.sharedInstance().onStart();
    }

    @Override
    protected void onStop() {
        // stop Countly
        Countly.sharedInstance().onStop();
        super.onStop();
    }

    /**
     * Set User Data for Countly
     */
    public void setUserData(){
        // create Bundle
        Bundle data = new Bundle();
        data.putString("name", "Firstname Lastname");
        data.putString("username", "nickname");
        data.putString("email", "test@test.com");
        data.putString("organization", "Tester");
        data.putString("phone", "+123456789");
        data.putString("gender", "M");
        //provide url to picture
        //data.put("picture", "http://example.com/pictures/profile_pic.png");
        //or locally from device
        //data.put("picturePath", "/mnt/sdcard/portrait.jpg");
        data.putInt("byear", 1987);
        //providing any custom key values to store with user
        data.putString("country", "France");
        data.putString("city", "Paris");
        data.putString("address", "home");
        Countly.sharedInstance().setUserData(data);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            // button event1
            case R.id.countly_button_event1:
                // a simple event
                Countly.sharedInstance().recordEvent("test1", 1, 2);
                break;
            // button event2
            case R.id.countly_button_event2:
                // a complexe event
                HashMap<String, String> maps = new HashMap<>();
                maps.put("key1", "value1");
                maps.put("key2", "value2");
                maps.put("key3", "value3");
                Countly.sharedInstance().recordEvent("test2", maps, 1);
                break;
            default:
                break;
        }
    }
}
