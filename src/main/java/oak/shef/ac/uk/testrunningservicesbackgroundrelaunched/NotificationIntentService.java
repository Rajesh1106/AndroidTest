package oak.shef.ac.uk.testrunningservicesbackgroundrelaunched;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Address;
import android.location.Geocoder;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.text.format.DateUtils;
import android.text.format.Formatter;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;


import org.json.JSONObject;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;

import static android.content.ContentValues.TAG;


/**
 * Created by selvaraj.r on 12/7/2017.
 */

public class NotificationIntentService extends IntentService {
    private static final int NOTIFICATION_ID = 1;
    private static final String ACTION_START = "ACTION_START";
    private static final String ACTION_DELETE = "ACTION_DELETE";
    private static final String ACTION_LEFT = "left";
    private static final String ACTION_RIGHT = "right";
    private static final String SEARCH_ACTION_RUN = "RUN";

    String SEARCH_URI;
    String agentsName, latsName, longsName;

    public NotificationIntentService() {
        super(NotificationIntentService.class.getSimpleName());
    }


    public static Intent createIntentStartNotificationService(Context context) {
        Intent intent = new Intent(context, NotificationIntentService.class);
        intent.setAction(ACTION_START);
        return intent;
    }

    public static Intent createIntentDeleteNotification(Context context) {
        Intent intent = new Intent(context, NotificationIntentService.class);
        intent.setAction(ACTION_DELETE);
        return intent;
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d(getClass().getSimpleName(), "onHandleIntent, started handling a notification event");
        try {
            String action = intent.getAction();
            if (ACTION_START.equals(action)) {
                processStartNotification();
            }
            if (ACTION_DELETE.equals(action)) {
                processDeleteNotification(intent);
            }
            if (ACTION_LEFT.equals(action)) {
                Handler leftHandler = new Handler(Looper.getMainLooper());
                leftHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getBaseContext(), "You clicked the Accept button", Toast.LENGTH_LONG).show();

                    }
                });

            }
            if (ACTION_RIGHT.equals(action)) {
                Handler rightHandler = new Handler(Looper.getMainLooper());
                rightHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getBaseContext(), "You clicked the Decline button", Toast.LENGTH_LONG).show();
                    }
                });
            }
        } finally {
            WakefulBroadcastReceiver.completeWakefulIntent(intent);
        }
    }

   /* @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        switch (intent.getAction()) {
            case "left":
                Handler leftHandler = new Handler(Looper.getMainLooper());
                leftHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getBaseContext(), "You clicked the Accept button", Toast.LENGTH_LONG).show();
                        //calService();
                    }
                });

                break;

            case "right":
                Handler rightHandler = new Handler(Looper.getMainLooper());
                rightHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getBaseContext(), "You clicked the Decline button", Toast.LENGTH_LONG).show();
                    }
                });
                break;

            case "ACTION_START":
                processStartNotification();
                break;

            case "ACTION_DELETE":
                processDeleteNotification(intent);
                break;
        }
    }*/

    private void processDeleteNotification(Intent intent) {
        // Log something?
    }

    public void processStartNotification() {
        // Do something. For example, fetch fresh data from backend to create a rich notification?

        final NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setContentTitle("slic Scheduled Notification")
                .setAutoCancel(true)
                .setColor(getResources().getColor(R.color.colorAccent))
                .setContentText("This notification has been triggered by Notification Service")
                .setSmallIcon(R.mipmap.ic_launcher);

        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                NOTIFICATION_ID,
                new Intent(this, MainActivity.class),
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);
        builder.setDeleteIntent(NotificationEventReceiver.getDeleteIntent(this));

        final NotificationManager manager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(NOTIFICATION_ID, builder.build());
    }

    private void processStartNotification123() {
        Toast.makeText(getApplicationContext(), "test run Set = " + latsName + " & " + longsName + " ^ " + agentsName, Toast.LENGTH_SHORT).show();
        // getLocalIpAddress();
        //sendNotification();
        // Do something. For example, fetch fresh data from backend to create a rich notification?
        /* GPSTracker maplocation=null;
        Double latitude = 0.0, longitude = 0.0;
        if (maplocation.canGetLocation()) {
            latitude = maplocation.getLatitude();
            longitude = maplocation.getLongitude();
            Toast.makeText(getApplicationContext(), "Notify Set = " + latitude + " & " + longitude, Toast.LENGTH_SHORT).show();
            gettingAddress(latitude,longitude);
        }
*/

      /*  RemoteViews contentView = new RemoteViews(getPackageName(), R.layout.custom_collapsed);
       // contentView.setImageViewResource(R.id.image, R.mipmap.ic_launcher);
        //contentView.setTextViewText(R.id.title, "Custom notification");
        //contentView.setTextViewText(R.id.text, "This is a custom layout");*/


       /* final NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setContentTitle("Scheduled Notification")
                .setAutoCancel(true)
                .setColor(getResources().getColor(R.color.colorAccent))
                .setContentText(Login.latitude+" &&& "+ Login.longitude)
                .setSmallIcon(R.drawable.cast_ic_notification_small_icon);*/

        //.setContent(contentView)
        // .setSmallIcon(R.drawable.cast_ic_notification_small_icon);
               /* .setContentText(Login.latitude+" &&& "+ Login.longitude)
                .setColor(ContextCompat.getColor(context, R.color.colorPrimary))
                .setStyle(new NotificationCompat.BigTextStyle().bigText("Scheduled Notification"))
                .setStyle(new NotificationCompat.BigTextStyle().bigText(Login.latitude+" &&& "+Login.longitude).setSummaryText("#selva"))
                .setShowWhen(true)
                .setAutoCancel(true);*/

      /*  PendingIntent pendingIntent = PendingIntent.getActivity(this,
                NOTIFICATION_ID, new Intent(this, Login.class),
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);
        builder.setDeleteIntent(NotificationEventReceiver.getDeleteIntent(this));

        final NotificationManager manager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(NOTIFICATION_ID, builder.build());*/
    }

    public void gettingAddress(Double lats, Double longs) {
        double latitude = lats;
        double longitude = longs;
        Geocoder gc = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> addresses = gc.getFromLocation(latitude, longitude, 1);
            StringBuilder sb = new StringBuilder();
            if (addresses.size() > 0) {
                Address address = addresses.get(0);
                for (int i = 0; i < address.getMaxAddressLineIndex(); i++)
                    sb.append(address.getAddressLine(0)).append("\n");
                sb.append(address.getLocality()).append("\n");
                sb.append(address.getPostalCode()).append("\n");
                sb.append(address.getCountryName());
                Toast.makeText(getApplicationContext(), "Address => " + address.toString(), Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e) {

        }
    }


    public String getLocalIpAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        String ip = Formatter.formatIpAddress(inetAddress.hashCode());
                        Log.i(TAG, "***** IP=" + ip);
                        Toast.makeText(getApplicationContext(), "IPps => " + ip, Toast.LENGTH_SHORT).show();
                        return ip;
                    }
                }
            }
        } catch (SocketException ex) {
            Log.e(TAG, ex.toString());
        }
        return null;
    }


    private BroadcastReceiver receiverStatusEntry = new BroadcastReceiver() {
        JSONObject responseObject;
        String response, str, code;

        @Override
        public void onReceive(Context context, Intent intent) {
            response = intent.getStringExtra("httpResponse");
            System.out.println("response==>" + response);

            try {
                JSONObject jsonArray = new JSONObject(response).getJSONObject("GetLocation");
                System.out.println("Barray" + jsonArray);
                code = jsonArray.getString("ReturnCode");
                str = jsonArray.getString("UDID");
                Toast.makeText(context, str, Toast.LENGTH_SHORT)
                        .show();

            } catch (Exception e) {
                e.printStackTrace();
            }
            Toast.makeText(context, "Please try after sometime", Toast.LENGTH_SHORT)
                    .show();
        }
    };

}
