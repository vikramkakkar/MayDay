package com.mayday.md.trigger;

import android.media.AudioManager;
import com.mayday.md.alert.PanicAlert;

import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;


import static android.content.Intent.ACTION_SCREEN_OFF;
import static android.content.Intent.ACTION_SCREEN_ON;

public class HardwareTriggerReceiver extends BroadcastReceiver {
    private static final String TAG = HardwareTriggerReceiver.class.getName();
    //    private MultiClickEvent multiClickEvent;
    protected MultiClickEvent multiClickEvent;

    public HardwareTriggerReceiver() {
        resetEvent();
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e(">>>>>>>", "in onReceive of HWReceiver");

        String action = intent.getAction();
        Log.e(">>>>>>>", "in onReceive of HWReceiver context "+context);
        Log.e(">>>>>>>", "in onReceive of HWReceiver intent "+intent);

        if (!isCallActive(context) && isScreenLocked(context) && (action.equals(ACTION_SCREEN_OFF) || action.equals(ACTION_SCREEN_ON))) {
            multiClickEvent.registerClick(System.currentTimeMillis());
            if (multiClickEvent.isActivated()) {
                onActivation(context);
                resetEvent();
            }
        }
    }

    public void onActivation(Context context) {
        Log.e(">>>>>>>", "in onActivation of HWReceiver");
        activateAlert(new PanicAlert(context));
    }

    void activateAlert(PanicAlert panicAlert) {
//        panicAlert.start();
        panicAlert.activate();
    }

    protected void resetEvent() {
        multiClickEvent = new MultiClickEvent();
    }

    PanicAlert getPanicAlert(Context context) {
        return new PanicAlert(context);
    }

    private boolean isScreenLocked(Context context) {
        KeyguardManager keyguardManager = (KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE);
        return keyguardManager.inKeyguardRestrictedInputMode();
    }

    public boolean isCallActive(Context context) {
        AudioManager manager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        return manager.getMode() == AudioManager.MODE_IN_CALL;
    }
}
