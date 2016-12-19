package alan.no1.ys121901;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import java.util.Date;

public class MyService extends Service {

    Handler handler = new Handler();
    Runnable showTime = new Runnable() {
        @Override
        public void run() {
            Log.d("SERV", new Date().toString());
            handler.postDelayed(this, 1000);
        }
    };

    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("SERV", "This is onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("SERV", "This is onStartCommand");

        handler.post(showTime);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(showTime);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
