package eva.example.com.chanjo;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.view.View;
import android.content.Context;
import android.content.Intent;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.Locale;

public class
Sign extends AppCompatActivity {

    DbHelper helper = new DbHelper(this,null,null,1);
    Button btnS;
    ImageButton bck;
    EditText edtUser;
    EditText edtPhn;
    EditText edtPass;
    EditText conPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);


        edtUser = (EditText) findViewById(R.id.edtUser);
        edtPhn = (EditText) findViewById(R.id.edtPhone);
        edtPass = (EditText) findViewById(R.id.edtPass);
        conPass = (EditText) findViewById(R.id.edtPass2);
        btnS = (Button) findViewById(R.id.btnSign);
        bck = (ImageButton)findViewById(R.id.imgBack);
        final Context context = this;


        bck.setOnClickListener(
                new ImageButton.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        Intent myIntent = new Intent(context, MainActivity.class);
                        startActivity(myIntent);
                    }
                }
        );
        btnS.setOnClickListener(
                //saves details into Db onClick
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        String name = edtUser.getText().toString().trim();
                        String phone = edtPhn.getText().toString().trim();
                        String pass = edtPass.getText().toString().trim();
                        String pass2 = conPass.getText().toString().trim();
                        String lang = String.valueOf(Locale.getDefault());

                        Log.d("Sas","Loc"+lang);


                        if (name.equals("") || phone.equals("") || pass.equals("")) {
                            if(lang.equals("Locen_GB")) {
                                Toast.makeText(getApplicationContext(), "Please fill all the fields!", Toast.LENGTH_LONG).show();
                            }else{
                                Toast.makeText(getApplicationContext(), "Jaza maelezo yote!", Toast.LENGTH_LONG).show();

                            }
                        } else if (pass.equals(pass2) == false) {
                            if(lang.equals("Locen_GB")) {
                                Toast.makeText(getApplicationContext(), "Passwords don't match", Toast.LENGTH_LONG).show();
                            }else{
                                Toast.makeText(getApplicationContext(), "Maneno ya siri hayafanani!", Toast.LENGTH_LONG).show();

                            }
                        } else {
                            //sets info into contact class
                            Contact c = new Contact(name,phone,pass);
                            Log.d("Database error","name"+name);
                            helper.insertContact(c);

                            if(lang.equals("Locen_GB")){
                                Toast.makeText(getApplicationContext(), "Added correctly!", Toast.LENGTH_LONG).show();

                            }else {
                                Toast.makeText(getApplicationContext(), "Imeongezwa vilivyo!", Toast.LENGTH_LONG).show();
                            }
                            //sends notification on sign up
                            note();
                            Intent myIntent = new Intent(context, MainActivity.class);
                            startActivity(myIntent);

                        }
                    }
                }
        );
    }

    //notification method
    public void note() {
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.chnj)
                        .setContentTitle("Chanjo")
                        .setContentText("Umefungua Akaunti ya Chanjo...Karibu! :)");
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
        int mid = 0;
        mNotificationManager.notify(mid, mBuilder.build());
    }




}
