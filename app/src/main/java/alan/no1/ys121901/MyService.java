package alan.no1.ys121901;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import java.util.Date;

public class MyService extends Service {
    final int NOTIFICATON_REQUEST_CODE = 123;
    NotificationManager nm;
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
        showNotification();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(showTime);
        nm.cancel(NOTIFICATON_REQUEST_CODE);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void showNotification()
    {
        Context context = getApplicationContext();
        Intent it = new Intent(context, MainActivity.class);
        PendingIntent pi = PendingIntent
                .getActivity(context, 1, it, PendingIntent.FLAG_UPDATE_CURRENT);

        nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Notification.Builder builder = new Notification.Builder(context);
        builder.setSmallIcon(R.mipmap.ic_launcher)
                .setContentText("點擊此處回到主程式")
                .setContentTitle("執行中")
                .setContentIntent(pi);
        Notification notification = builder.build();
        notification.flags |= Notification.FLAG_NO_CLEAR;
        notification.flags |= Notification.FLAG_ONGOING_EVENT;
        nm.notify(NOTIFICATON_REQUEST_CODE, notification);
    }
}
