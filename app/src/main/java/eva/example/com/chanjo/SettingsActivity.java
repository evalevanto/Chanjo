package eva.example.com.chanjo;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Locale;

public class SettingsActivity extends AppCompatActivity {
    final Context cont = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Button hk = (Button)findViewById(R.id.btnHk);
        RadioGroup rg = (RadioGroup)findViewById(R.id.rdGrp);
        hk.setOnClickListener(
                new Button.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        Intent myIn = new Intent(cont, MainActivity.class);
                        startActivity(myIn);
                    }
                }
        );
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                                          public void onCheckedChanged(RadioGroup group, int checkedId) {
                                              switch (checkedId) {
                                                  case R.id.rdEn:
                                                      Locale locale = new Locale("en");
                                                      Locale.setDefault(locale);
                                                      Configuration config = new Configuration();
                                                      config.locale = locale;
                                                      getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
                                                      Toast.makeText(getBaseContext(),"English", Toast.LENGTH_SHORT).show();
                                                      break;

                                                  case R.id.rdSw:
                                                      Toast.makeText(getBaseContext(),"Swahili", Toast.LENGTH_SHORT).show();
                                                      Locale loc = new Locale("sw");
                                                      Locale.setDefault(loc);
                                                      Configuration conf = new Configuration();
                                                      conf.locale = loc;
                                                      getBaseContext().getResources().updateConfiguration(conf, getBaseContext().getResources().getDisplayMetrics());
                                                      Toast.makeText(getBaseContext(),"Swahili", Toast.LENGTH_SHORT).show();
                                                      break;


                                              }
                                          }
                                      }
        );

    }
}
