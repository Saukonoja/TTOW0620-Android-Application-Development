package fi.jamk.handson38;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private int notification_ID=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void launchNotification(View v){
        //create a pending intent to open a web browser
        Intent actionIntent = new Intent(Intent.ACTION_VIEW);
        actionIntent.setData(Uri.parse("http://www.jamk.fi"));

        PendingIntent actionPendingIntent = PendingIntent.getActivity(this, 0, actionIntent, 0);

        // create a new notification
        Notification notification = new Notification.Builder(this)
                .setCategory(Notification.CATEGORY_MESSAGE)
                .setContentTitle("Test notification")
                .setContentText("Click here to see our wep page!")
                .setSmallIcon(R.drawable.ic_bug)
                .setAutoCancel(true)
                .setContentIntent(actionPendingIntent)
                .setVibrate(new long[]{1000, 1000, 1000, 4000, 3000, 2000, 1000, 500, 200, 100})
                .setVisibility(Notification.VISIBILITY_PUBLIC).build();

        // connect phone notification manager
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        //create a new notification
        notificationManager.notify(notification_ID, notification);



    }
}
