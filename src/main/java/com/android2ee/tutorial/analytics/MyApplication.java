package com.android2ee.tutorial.analytics;

import android.app.Application;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;

import java.util.HashMap;

import com.android2ee.tutorial.analytics.tool.TrackerName;

/**
 * Created by florian on 27/01/15.
 * Class Application
 */
public class MyApplication extends Application {

    // The following line should be changed to include the correct property id.
    private static final String PROPERTY_ID = "UA-XXXXX-Y";

    // Trackers Map
    HashMap<TrackerName, Tracker> mTrackers = new HashMap<TrackerName, Tracker>();


    @Override
    public void onCreate() {
        super.onCreate();
    }

    /**
     * Get Tracker for Google Analytics
     * @param trackerId (APP_TRACKER, GLOBAL_TRACKER, COMMERCER_TRACKER
     * @return
     */
    synchronized Tracker getTracker(TrackerName trackerId) {
        // get the right tracker in depends of tracker id
        if (!mTrackers.containsKey(trackerId)) {

            GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
            Tracker t = (trackerId == TrackerName.APP_TRACKER) ? analytics.newTracker(PROPERTY_ID)
                    : (trackerId == TrackerName.GLOBAL_TRACKER) ? analytics.newTracker(R.xml.global_tracker)
                    : analytics.newTracker(R.xml.ecommerce_tracker);
            mTrackers.put(trackerId, t);

        }
        return mTrackers.get(trackerId);
    }
}
