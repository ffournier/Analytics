package com.android2ee.tutorial.analytics;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by florian on 27/01/15.
 * Class MainActivity
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        // intialize button
        Button buttonGa = (Button) findViewById(R.id.main_button_ga);
        buttonGa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Google Analytics
                Intent intent = new Intent(MainActivity.this, GoogleAnalyticsActivity.class);
                startActivity(intent);
            }
        });

        Button buttonCountly = (Button) findViewById(R.id.main_button_countly);
        buttonCountly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Countly
                Intent intent = new Intent(MainActivity.this, CountlyActivity.class);
                startActivity(intent);
            }
        });

        Button buttonFlurry = (Button) findViewById(R.id.main_button_flurry);
        buttonFlurry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Flurry
                Intent intent = new Intent(MainActivity.this, FlurryActivity.class);
                startActivity(intent);
            }
        });
    }
}
