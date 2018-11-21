package com.example.mandakini.backgroundservice;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Toast;

public class MyIntentService  extends IntentService{

    public static final String TAG = MyIntentService.class.getSimpleName();

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     */
    public MyIntentService() {

        super("MyIntentThread");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG,"onCreate "+Thread.currentThread().getName());
        Toast.makeText(this, "Task Execution Starts", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.i(TAG,"onHandle "+Thread.currentThread().getName());

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

        Intent localintent = new Intent("my.own.broadcast");
        localintent.putExtra("result",ctr);
       LocalBroadcastManager.getInstance(this).sendBroadcast(localintent);


    }

    @Override
    public void onDestroy() {
        Log.i(TAG,"onDestroy "+Thread.currentThread().getName());
        Toast.makeText(this, "Task Execution Stop", Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }
}
