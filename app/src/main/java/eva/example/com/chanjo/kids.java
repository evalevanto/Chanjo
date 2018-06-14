package eva.example.com.chanjo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class kids extends AppCompatActivity {


    DbHelper hlp = new DbHelper(this,null,null,1);
    final Context context = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kids);

       SharedPreferences shrdPref = getSharedPreferences("Details",Context.MODE_PRIVATE);
       String ph = shrdPref.getString("phone","");
        Log.e("Database error","name"+ph);

        ArrayAdpt arry = new ArrayAdpt(this,R.layout.listitem,hlp.getArrayCat(ph));
        Log.e("Database error","name"+hlp.getArrayCat(ph));

//
        ListView lstVw = (ListView)findViewById(R.id.lstVw);
        lstVw.setAdapter(arry);

        lstVw.setOnItemClickListener(
                new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String kid = String.valueOf(parent.getItemAtPosition(position));
                        Toast.makeText(getApplicationContext(),kid, Toast.LENGTH_SHORT).show();



                        int i=1;
                        SharedPreferences shrdPrf = getSharedPreferences("edits",Context.MODE_PRIVATE);
                        SharedPreferences.Editor edtShrd = shrdPrf.edit();
                        edtShrd.putInt("enable",i);
                        edtShrd.apply();

                        //setContentView(R.layout.activity_prof);
                        Intent myInt = new Intent(context, Prof.class);
                        myInt.putExtra("Name",kid);
                        startActivity(myInt);


                    }
                }
        );


//        final Context context = this;


        final Intent mIntent = new Intent();

        Button b = (Button)findViewById(R.id.addKid);

        b.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        Intent myIntent = new Intent(context, add.class);
                        startActivity(myIntent);
                    }
                }
        );
        ImageButton bck = (ImageButton)findViewById(R.id.imgBck);
        bck.setOnClickListener(
                new ImageButton.OnClickListener(){
                    public void onClick(View v){
                        Intent myIntent = new Intent(context, MainActivity.class);
                        startActivity(myIntent);
                    }
                }
        );



    }

}
