package eva.example.com.chanjo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

public class add extends AppCompatActivity {

    DbHelper hlpkids = new DbHelper(this,null,null,1);
    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);


        Button b = (Button)findViewById(R.id.btnOng);
        //save details to Db onClick
        b.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v) {
                        EditText edtjina = (EditText) findViewById(R.id.edtJmt);
                        DatePicker tarehe = (DatePicker) findViewById(R.id.dtPick);
                        String jina = edtjina.getText().toString().trim();
                        int day = tarehe.getDayOfMonth();
                        int mnth = tarehe.getMonth()+1;
                        int year = tarehe.getYear();
                        String lang = String.valueOf(Locale.getDefault());

                        if (jina.equals("") || day == 0 || mnth == 0 || year == 0 ) {
                            if(lang.equals("Locen_GB")){
                                Toast.makeText(getApplicationContext(), "Please enter correct details!", Toast.LENGTH_LONG).show();

                            }else {
                                Toast.makeText(getApplicationContext(), "Tafadhali ingiza details vizuri!", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            SharedPreferences shrdPref = getSharedPreferences("Details",Context.MODE_PRIVATE);
                            String ph = shrdPref.getString("phone","");
                            ContKids c = new ContKids(jina, day, mnth, year);
                            Log.d("Database error","name" +ph);
                            hlpkids.insertContact(c,ph);

                            }
                        Intent myIntent = new Intent(context, kids.class);
                        startActivity(myIntent);
                    }

                }
        );
    }
}
