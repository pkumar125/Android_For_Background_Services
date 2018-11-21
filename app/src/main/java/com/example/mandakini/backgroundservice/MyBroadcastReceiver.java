package com.example.mandakini.backgroundservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MyBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {
            Intent i = new Intent(context, MyJobIntentService.class);
            i.putExtra("sleepTime", 12);
            MyJobIntentService.enqueueWork(context, i);
//			context.startService(i);
        }
    }
}
