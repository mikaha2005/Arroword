package com.example.project;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Button btnStart;
    EditText name;
    public String userName;
    public static final String NOTIFICATION_CHANNEL_ID = "10001" ;
    private final static String default_notification_channel_id = "default" ;
    //final Calendar myCalendar = Calendar. getInstance () ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (getIntent().getBooleanExtra("EXIT", false)) {
            finish();
        }
        btnStart = (Button)findViewById(R.id.btn);
        name=(EditText)findViewById(R.id.name);
        //Date date = myCalendar.getTime() ;
        scheduleNotification(getNotification()) ;
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                builder.setTitle(name.getText().toString()+", do you want to start?");
                builder.setCancelable(true);
                builder.setPositiveButton("Yes!", new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(MainActivity.this, GameActivity.class);
                        intent.putExtra("UserName",name.getText().toString());
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.cancel();
                    }
                });

                AlertDialog dialog= builder.create();
                dialog.show();

/*                Intent intent = new Intent(MainActivity.this, GameActivity.class);
                //intent.putExtra("myName",name.getText().toString());
                startActivity(intent);*/
            }
        });
        userName=name.getText().toString();

    }

    private void scheduleNotification (Notification notification) {
        Intent notificationIntent = new Intent( this, MyNotificationPublisher.class ) ;
        notificationIntent.putExtra(MyNotificationPublisher.NOTIFICATION_CHANNEL_NAME , 1 ) ;
        notificationIntent.putExtra(MyNotificationPublisher.NOTIFICATION , notification) ;
        PendingIntent pendingIntent = PendingIntent.getBroadcast ( this, 0 , notificationIntent , PendingIntent.FLAG_IMMUTABLE ) ;

        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE ) ;
        Calendar myCalendar = Calendar.getInstance () ;
        myCalendar.setTimeInMillis(System.currentTimeMillis());
        myCalendar.set(Calendar.HOUR, 0);
        myCalendar.set(Calendar.MINUTE, 0);
        myCalendar.set(Calendar.SECOND, 5);
        alarmManager.set(AlarmManager.RTC_WAKEUP, myCalendar.getTimeInMillis(), pendingIntent);
        //alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, System.currentTimeMillis()+3000, 5000, pendingIntent);
        assert alarmManager != null;
        //alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP , delay , pendingIntent) ;
/*        long futureInMillis = SystemClock. elapsedRealtime () + 5000 ;
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context. ALARM_SERVICE ) ;
        assert alarmManager != null;
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP , futureInMillis , pendingIntent) ;*/
    }
    private Notification getNotification () {
        NotificationCompat.Builder builder = new NotificationCompat.Builder( this, default_notification_channel_id ) ;
        builder.setContentTitle( "Scheduled Notification" ) ;
       // builder.setContentText(content) ;
        builder.setSmallIcon(R.drawable.ic_launcher_foreground ) ;
        builder.setAutoCancel( true ) ;
        builder.setChannelId( NOTIFICATION_CHANNEL_ID ) ;
        return builder.build() ;
    }
  /*  DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet (DatePicker view , int year , int monthOfYear , int dayOfMonth) {
            myCalendar .set(Calendar.YEAR , year) ;
            myCalendar .set(Calendar.MONTH , monthOfYear) ;
            myCalendar .set(Calendar.DAY_OF_MONTH , dayOfMonth) ;
            myCalendar.set(Calendar.HOUR_OF_DAY, 14);
            myCalendar.set(Calendar.MINUTE, 0);
            myCalendar.set(Calendar.SECOND, 0);
            updateLabel() ;
        }
    } ;
    public void setDate (View view) {
        new DatePickerDialog(MainActivity. this, date ,
                myCalendar .get(Calendar.YEAR) ,
                myCalendar .get(Calendar.MONTH) ,
                myCalendar .get(Calendar.DAY_OF_MONTH)
        ).show() ;
    }
    private void updateLabel () {
        String myFormat = "dd/MM/yy" ; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat , Locale.getDefault ()) ;
        Date date = myCalendar.getTime() ;
        btnDate .setText(sdf.format(date)) ;
        scheduleNotification(getNotification(btnDate.getText().toString()) , date.getTime()) ;
    }*/

    public void exitMain()
    {
        MainActivity.this.finish();
    }



}
