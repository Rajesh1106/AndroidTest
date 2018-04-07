package oak.shef.ac.uk.testrunningservicesbackgroundrelaunched;


/**
 * Created by selvaraj.r on 12/7/2017.
 */

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.text.format.DateUtils;
import android.widget.RemoteViews;
import android.widget.Toast;


import static android.content.Context.NOTIFICATION_SERVICE;

public class AlarmReceiver extends BroadcastReceiver {

    GPSTracker maplocation;
    Double latitude = 0.0, longitude = 0.0;
    Context cons;


    @Override
    public void onReceive(Context arg0, Intent arg1) {
        // For our recurring task, we'll just display a message
        Toast.makeText(arg0, "I'm running selva", Toast.LENGTH_SHORT).show();
        NotificationEventReceiver.setupAlarm(arg0);
        /*if (ActivityCompat.checkSelfPermission(arg0, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(arg0, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(arg0, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            return;
        }
        maplocation = new GPSTracker(arg0);
        if (maplocation.canGetLocation()) {
            latitude = maplocation.getLatitude();
            longitude = maplocation.getLongitude();
        }
        */
        // sendNotification();
        // Toast.makeText(arg0, ProposalListActivity.latitude+" "+ ProposalListActivity.longitude, Toast.LENGTH_SHORT).show();

    }


}