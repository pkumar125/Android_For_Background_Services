package com.example.mandakini.backgroundservice;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

public class MyBackgroundService extends Service {

    private static final String TAG = MyBackgroundService.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "OnCreate "+Thread.currentThread().getName());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "OnStart "+Thread.currentThread().getName());
new MyAsyncTask().execute();
       // return super.onStartCommand(intent, flags, startId);
        return START_REDELIVER_INTENT;

    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "OnBind "+Thread.currentThread().getName());
        return null;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "OnDestory "+Thread.currentThread().getName());
    }


    class MyAsyncTask extends AsyncTask<Void, String, Void >{

        @Override
        protected void onPreExecute() {
            Log.i(TAG, "onPreExecute "+Thread.currentThread().getName());
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            int ctr = 1;
            while (ctr < 12 ){
                publishProgress( "time lapsed "+ctr+" sec");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e){
                    e.printStackTrace();

                }
              ctr++;

            }
            Log.i(TAG, "doInBackground "+Thread.currentThread().getName());
            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            Log.i(TAG, "OnProgssUpdate counter Value"+values[0]+" Thread Name "+Thread.currentThread().getName());
            Toast.makeText(MyBackgroundService.this,values[0], Toast.LENGTH_SHORT).show();
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            Log.i(TAG, "OnPostExecute "+Thread.currentThread().getName());
            super.onPostExecute(aVoid);
            stopSelf();
        }
    }
}
