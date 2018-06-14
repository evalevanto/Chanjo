package eva.example.com.chanjo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button b = (Button)findViewById(R.id.btn1);
        Button c = (Button)findViewById(R.id.btn2);
        Button d = (Button)findViewById(R.id.btn3);
        Button out = (Button)findViewById(R.id.btnOut);
        //ImageButton ds = (ImageButton)findViewById(R.id.imgBtn);
        final Context context = this;





//        ds.setOnClickListener(
//                new ImageButton.OnClickListener(){
//                    @Override
//                    public void onClick(View v) {
//                        Intent myIntent = new Intent(context, SettingsActivity.class);
//                        startActivity(myIntent);
//                    }
//                }
//        );

        b.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        Intent myIntent = new Intent(context, about.class);
                        startActivity(myIntent);
                    }
                }
        );

        c.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        Intent myIntent = new Intent(context, kids.class);
                        startActivity(myIntent);
                    }
                }
        );

        d.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        Intent myIntent = new Intent(context, cdc.class);
                        startActivity(myIntent);
                    }
                }
        );

        out.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        Intent myInt = new Intent(context, MainActivity.class);
                        startActivity(myInt);
                    }
                }
        );

    }
}
