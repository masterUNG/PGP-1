package anuson.komkid.permitgeographypro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //textview สมัครสมาชิก
        final TextView tvw4 = (TextView) findViewById(R.id.textView4);
        tvw4.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent newActivity = new Intent(MainActivity.this,Register.class);
                startActivity(newActivity);
            }
        }
        );
    }
}
