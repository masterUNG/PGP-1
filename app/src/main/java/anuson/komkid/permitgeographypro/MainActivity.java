package anuson.komkid.permitgeographypro;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    private EditText userEditText, passwordEditText;
    private String userString, passwordString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userEditText = (EditText) findViewById(R.id.editText);
        passwordEditText = (EditText) findViewById(R.id.editText2);

        //textview สมัครสมาชิก
        final TextView tvw4 = (TextView) findViewById(R.id.textView4);
        tvw4.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent newActivity = new Intent(MainActivity.this,Register.class);
                startActivity(newActivity);
                }
            }

        );

     }//main method

    public void clickMylogin(View view){
        userString = userEditText.getText().toString().trim();
        passwordString = passwordEditText.getText().toString().trim();

        if (userString.equals("")|| passwordString.equals("")) {
            MyAlert myAlert = new MyAlert();
            myAlert.myDialog(this, "คำเตือน"
            ,"กรุงณากรอกให้ครบ");
            } else {

        }
    }//clickMylogin

}
