package com.example.mandakini.backgroundservice;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.JobIntentService;
import android.util.Log;
import android.widget.Toast;

public class MyJobIntentService extends JobIntentService {
    public static final String TAG = MyJobIntentService.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG,"onCreate "+Thread.currentThread().getName());
        Toast.makeText(this, "Task Execution Starts", Toast.LENGTH_SHORT).show();
    }

    public static void enqueueWork(Context context, Intent intent) {
        enqueueWork(context, MyJobIntentService.class, 17, intent);
    }


    @Override
    protected void onHandleWork(@NonNull Intent intent) {
        Log.i(TAG,"onHandleWork "+Thread.currentThread().getName());

        int duration = intent.getIntExtra("sleepTime", -1);

        int ctr = 1;
        while (ctr < duration ){
            Log.i( TAG,"time lapsed "+ctr+" sec");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e){
                e.printStackTrace();

            }
            ctr++;

        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"onDestroy "+Thread.currentThread().getName());
        Toast.makeText(this, "Task Execution Stop", Toast.LENGTH_SHORT).show();
    }
}
