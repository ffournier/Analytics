package com.android2ee.tutorial.analytics;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.HashMap;

import ly.count.android.api.Countly;
import ly.count.android.api.DeviceId;

/**
 * Created by florian on 27/01/15.
 */
public class CountlyActivity extends Activity implements View.OnClickListener {

    Button btnEvent1;
    Button btnEvent2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.countly_activity);

        Countly.sharedInstance().init(this, "https://YOUR_SERVER", "YOUR_APP_KEY" , null, DeviceId.Type.OPEN_UDID);

        setUserData();

        Countly.sharedInstance().setLoggingEnabled(true);

        btnEvent1 = (Button) findViewById(R.id.countly_button_event1);
        btnEvent1.setOnClickListener(this);
        btnEvent2 = (Button) findViewById(R.id.countly_button_event2);
        btnEvent2.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Countly.sharedInstance().onStart();
    }

    @Override
    protected void onStop() {
        Countly.sharedInstance().onStop();
        super.onStop();
    }

    public void setUserData(){
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
        data.putString("byear", "1987");
        //providing any custom key values to store with user
        data.putString("country", "France");
        data.putString("city", "Paris");
        data.putString("address", "home");
        Countly.sharedInstance().setUserData(data);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.countly_button_event1:
                Countly.sharedInstance().recordEvent("test1", 1, 2);
                break;
            case R.id.countly_button_event2:
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
