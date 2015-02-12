package com.mayday.md.trigger;

import android.content.ContextWrapper;
import android.content.Intent;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;


/**
 * Created by jshultz on 2/11/15.
 */
public class GearFitTrigger {

    public void GearFitTrigger(Bundle bundle, Context context, SharedPreferences mPref) {

        HardwareTriggerReceiver hardwareTriggerReceiver = new HardwareTriggerReceiver();


        if(bundle!=null) {
            if (!hardwareTriggerReceiver.isCallActive(context)) {
                int c = mPref.getInt("numRun", 0);
                int TIME_INTERVAL = 10000;
                int TOTAL_CLICKS = 5;
                long delta = 0;
                Long eventTime = System.currentTimeMillis();
                mPref.edit().putLong("eventTime", eventTime).commit();
                Long firstEventTime = mPref.getLong("firstEventTime", 0);
                if (firstEventTime == 0) {
                    firstEventTime = eventTime;
                    mPref.edit().putLong("firstEventTime", firstEventTime).commit();
                }
                delta = eventTime - firstEventTime;
                Log.e(">>>>>>", "START_BY_CUP delta " + delta);
                if (delta < TIME_INTERVAL) {
                    c++;
                    mPref.edit().putInt("numRun",c).commit();
                    Log.e(">>>>>>", "START_BY_CUP "+c);
                    if (c >=TOTAL_CLICKS) {
                        hardwareTriggerReceiver.onActivation(context);
                        mPref.edit().putInt("numRun", 0).apply();
                        mPref.edit().putLong("firstEventTime", 0).apply();
                    }
                } else {
                    mPref.edit().putInt("numRun", 0).apply();
                    mPref.edit().putLong("firstEventTime", 0).apply();
                }
            }
        }

    }

}
