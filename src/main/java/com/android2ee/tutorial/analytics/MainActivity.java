package com.android2ee.tutorial.analytics;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by florian on 27/01/15.
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        Button buttonGa = (Button) findViewById(R.id.main_button_ga);
        buttonGa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GoogleAnalyticsActivity.class);
                startActivity(intent);
            }
        });

        Button buttonCountly = (Button) findViewById(R.id.main_button_countly);
        buttonCountly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CountlyActivity.class);
                startActivity(intent);
            }
        });
    }
}
