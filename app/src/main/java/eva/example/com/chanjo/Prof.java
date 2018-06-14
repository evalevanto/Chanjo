package eva.example.com.chanjo;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Prof extends AppCompatActivity {

    DbHelper hlp = new DbHelper(this, null, null, 1);
    final Context cont = this;
    EditText sv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prof);

        //retrieve the passed on variable Name
        Bundle bundle = getIntent().getExtras();
        String kid = bundle.getString("Name");

        //Displays the child's name
        TextView show = (TextView) findViewById(R.id.txtShw);
        show.setText(kid);

        String date = hlp.getDate(kid);
        String[] tarehe = date.split("#");

        TextView trh = (TextView) findViewById(R.id.txtDate);
        //Displays the child's D.O.B.
        String trhe = tarehe[0] + "-" + tarehe[1] + "-" + tarehe[2];
        trh.setText(trhe);
        TextView umr = (TextView) findViewById(R.id.txtUmr);
        try {
            umr.setText(getAge(trhe));

        } catch (ParseException e) {
            e.printStackTrace();
        }
        date(trhe);


        note("Zingatia...","Kumbuka kuangalia tarehe za chanjo!!!:) Okoa mtoto");
        daily();


        Button btn = (Button)findViewById(R.id.imgSve);
        btn.setOnClickListener(
                new Button.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        SharedPreferences shrdPref = getSharedPreferences("Edits",Context.MODE_PRIVATE);
                        int valu = shrdPref.getInt("enable",0);
                        Log.e("int","get"+valu);
                        while(valu < 1){
                            sv = (EditText)findViewById(R.id.editText1);
                            EditText sv1 = (EditText)findViewById(R.id.editText2);
                            EditText sv2 = (EditText)findViewById(R.id.editText3);


                            String added = sv.getText().toString()+"-"+sv1.getText().toString()+"-"+sv2.getText().toString();
                            date(added);
                            sv.setEnabled(false);
                            sv1.setEnabled(false);
                            sv2.setEnabled(false);


                            valu++;
                        }
                    }
                }
        );

        ImageButton bck = (ImageButton)findViewById(R.id.imgBck);
        bck.setOnClickListener(
                new ImageButton.OnClickListener(){

                    @Override
                    public void onClick(View v) {
                        Intent myIntent = new Intent(cont, kids.class);
                        startActivity(myIntent);
                    }
                }
        );

    }

    public void date(String trhe) {
        final TextView chnj1 = (TextView) findViewById(R.id.txtChnj1);
        chnj1.setText(trhe);
        String usdte = chnj1.getText().toString();

        TextView chnj2 = (TextView) findViewById(R.id.txtChnj2);
        try {
            chnj2.setText(chanjo2(usdte));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        TextView chnj3 = (TextView) findViewById(R.id.txtChnj3);
        try {
            chnj3.setText(chanjo3(chanjo2(usdte)));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        TextView chnj4 = (TextView) findViewById(R.id.txtChnj4);
        TextView chnj5 = (TextView) findViewById(R.id.txtChnj5);
        try {
            chnj4.setText(chanjo4(usdte));
            chnj5.setText(chanjo4(usdte));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }



    public String getAge(String umr) throws ParseException {
        SimpleDateFormat form = new SimpleDateFormat("dd-MM-yyyy");
        Date date = form.parse(umr);
        Date currDay = Calendar.getInstance().getTime();
         Long day = (currDay.getTime()/1000 - date.getTime()/1000)/86400;
        Long mnth;
        Long siku;
        Long yr;
        String lang = String.valueOf(Locale.getDefault());

        Long remain;
            yr = day/365;
            remain = day%365;
            mnth = remain/30;
            siku = remain%30;

        if(remain == 0){
            if(lang.equals("Locen_GB")) {
                Toast.makeText(getApplicationContext(), "Today is my Birthday:) !!!!!!!!!!!! ", Toast.LENGTH_LONG).show();
                note("My Birthday","Today is your child's birthday!");

            }else{
                Toast.makeText(getApplicationContext(), "Nilizaliwa leo:) !!!!!!!!!!!! ", Toast.LENGTH_LONG).show();
                note("Birthday","Leo ni siku ya mwanao kuzaliwa!");


            }


        }
        String age = "Miaka "+String.valueOf(yr)+" Miezi "+String.valueOf(mnth)+" Siku "+String.valueOf(siku);
        return age;
    }

    //calc dates for vaccination number 2
    public String chanjo2(String dob) throws ParseException {
        SimpleDateFormat form = new SimpleDateFormat("dd-MM-yyyy");
        Date date = form.parse(dob);
        Long dte = date.getTime();
        //calculates date 6 weeks from day1
        Long chnjo2 = dte + 3628800000L;
        Date chnjo = new Date();
        chnjo.setTime(chnjo2);
        String cHnjo = form.format(chnjo);
        return cHnjo;
    }

    //calc date for vaccination number 3
    public String chanjo3(String chnj) throws ParseException {
        SimpleDateFormat form = new SimpleDateFormat("dd-MM-yyyy");
        Date date = form.parse(chnj);
        Long dte = date.getTime();
        Long chnjo3 = dte + 2592000000L;
        Date chnjo = new Date();
        chnjo.setTime(chnjo3);
        String cHnjo = form.format(chnjo);
        return cHnjo;
    }

    //calc date for vaccination number 4
    public String chanjo4(String dob) throws ParseException {
        SimpleDateFormat form = new SimpleDateFormat("dd-MM-yyyy");
        Date date = form.parse(dob);
        Long dte = date.getTime();
        Long chnjo4 = dte + 23328000000L;
        Date chnjo = new Date();
        chnjo.setTime(chnjo4);
        String cHnjo = form.format(chnjo);
        return cHnjo;
    }

//    public void schedule(Notification note, int interval){
//        Intent notificationIntent = new Intent(this, NotePublish.class);
//        notificationIntent.putExtra(NotePublish.NOTIFICATION_ID, 1);
//        notificationIntent.putExtra(NotePublish.NOTIFICATION, note);
//        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
//        long futureInMillis = SystemClock.elapsedRealtime() + interval;
//        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
//        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, futureInMillis, pendingIntent);
//    }
//
//    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
//    private Notification getNote(String title,String word) {
//        Notification.Builder builder = new Notification.Builder(this);
//        builder.setContentTitle(title);
//        builder.setContentText(word);
//        builder.setSmallIcon(R.drawable.chnj);
//        return builder.build();
//    }
//
public void daily(){

    Intent myInt = new Intent(this, NotePublish.class);
    Calendar calendar = Calendar.getInstance();
    calendar.setTimeInMillis(System.currentTimeMillis());
    calendar.set(Calendar.HOUR_OF_DAY,9);
    calendar.set(Calendar.MINUTE,40);
    Calendar now = Calendar.getInstance();
    PendingIntent pnd = PendingIntent.getBroadcast(this,0,myInt,PendingIntent.FLAG_UPDATE_CURRENT);
    AlarmManager alarmMgr = (AlarmManager)this.getSystemService(Context.ALARM_SERVICE);
    if(now.after(calendar)){
        Log.d("Hey","Put another day!");
        calendar.add(Calendar
                .DATE,1);
    }
    Log.d("Hey","Expect it!");
    alarmMgr.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
            AlarmManager.INTERVAL_DAY, pnd);


}


    public void note(String t, String n) {
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.chnj)
                        .setContentTitle(t)
                        .setContentText(n);
        mBuilder.setAutoCancel(true);
        Intent resultIntent = new Intent(this, MainActivity.class);


        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        int mid = 1;
        mNotificationManager.notify(mid, mBuilder.build());
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public Notification getNote(String title,String word) {
        Notification.Builder builder = new Notification.Builder(this);
        builder.setContentTitle(title);
        builder.setContentText(word);
        builder.setSmallIcon(R.drawable.chnj);
        builder.setAutoCancel(true);
        return builder.build();
    }



    }


