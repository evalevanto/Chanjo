package eva.example.com.chanjo;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.content.res.ConfigurationHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    DbHelper helper = new DbHelper(this, null, null, 1);

    private static final String TAG = "LevantoCode";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//
//        Configuration config = new Configuration(getResources().getConfiguration());
//        config.locale = Locale.ENGLISH ;
//        getResources().updateConfiguration(config,getResources().getDisplayMetrics());


        TextView ask = (TextView) findViewById(R.id.txtAsk);
        Button logIn = (Button) findViewById(R.id.btnLog);
        ImageButton ds = (ImageButton)findViewById(R.id.imgBtn);
        final Context context = this;

        ds.setOnClickListener(
                new ImageButton.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        Intent myIntent = new Intent(context, SettingsActivity.class);
                        startActivity(myIntent);
                    }
                }
        );



        ask.setOnClickListener(
                new TextView.OnClickListener(){
                    public void onClick(View v){
                        Intent myIntent = new Intent(context, Sign.class);
                        startActivity(myIntent);
                    }
                }
        );

        logIn.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){

                        EditText Phn = (EditText)findViewById(R.id.edtPhn);
                        String Phne = Phn.getText().toString();
                        String lang = String.valueOf(Locale.getDefault());


                        EditText pas = (EditText)findViewById(R.id.edtPaS);
                        String PaSs = pas.getText().toString();
                        String password = helper.searchPass(Phne);
                        //Implementing shared preferences
                        SharedPreferences shrdPrf = getSharedPreferences("Details",Context.MODE_PRIVATE);
                        SharedPreferences.Editor edtShrd = shrdPrf.edit();
                        edtShrd.putString("phone",Phne);
                        edtShrd.apply();
                        if (PaSs.equals(password)){
                            //note();
                            Intent myIntent = new Intent(context, home.class);
                            startActivity(myIntent);
                        }else{
                            if(lang.equals("Locen_GB")) {
                                Toast.makeText(getApplicationContext(), "Credentials are not matching!", Toast.LENGTH_LONG).show();
                            }else{
                                Toast.makeText(getApplicationContext(), "Maelezo hazifanani!", Toast.LENGTH_LONG).show();

                            }
                        }
                    }
                }
        );

    }

//    public void note() {
//        NotificationCompat.Builder mBuilder =
//                new NotificationCompat.Builder(this)
//                        .setSmallIcon(R.drawable.chnj)
//                        .setContentTitle("Chanjo")
//                        .setContentText("Umefungua Akaunti ya Chanjo...Karibu! :)");
//        Intent resultIntent = new Intent(this, home.class);
//
//
//        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
//        stackBuilder.addParentStack(home.class);
//        stackBuilder.addNextIntent(resultIntent);
//        PendingIntent resultPendingIntent =
//                stackBuilder.getPendingIntent(
//                        0,
//                        PendingIntent.FLAG_UPDATE_CURRENT
//                );
//        mBuilder.setContentIntent(resultPendingIntent);
//        NotificationManager mNotificationManager =
//                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//        int mid = 0;
//        mNotificationManager.notify(mid, mBuilder.build());
//    }




}
