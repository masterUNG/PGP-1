package anuson.komkid.permitgeographypro;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.view.View;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

public class MainActivity extends AppCompatActivity {


    private EditText userEditText, passwordEditText;
    private String userString, passwordString;
    private String[] urlStrings = new  String []{
            "http://swiftcodingthai.com/gam/php_get_member_user.php"
            ,"http://swiftcodingthai.com/gam/php_get_member_farmer.php"};
    private RadioGroup radioGroup;
    private int index = 0;
    private  String[] columnUserStrings = new String[]{
            "mem_u_id",
            "mem_u_user",
            "mem_u_pass",
            "mem_u_name",
            "mem_u_add",
            "tmem_u_mail",
            "mem_u_tel",
            "mem_u_key"};
    private  String[] columnfarmerStrings = new String[]{
            "mem_id",
            "mem_user",
            "mem_pass",
            "mem_name",
            "mem_add",
            "mem_tel",
            "mem_key",
            "mem_farm_name",
            "mem_farm_type",
            "mem_farm_area",
            "mem_farm_latitude",
            "mem_farm_longtitude",
            "mem_farm_add",
            "mem_pictures",};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userEditText = (EditText) findViewById(R.id.editText);
        passwordEditText = (EditText) findViewById(R.id.editText2);
        radioGroup = (RadioGroup) findViewById(R.id.ragUser);

        //R
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int i) {
                switch (i) {
                    case R.id.radioButton: // for User
                        index = 0;
                        break;
                    case  R.id.radioButton2: //for farmer
                        index = 1;
                        break;
                }
            }
        });

        //textview สมัครสมาชิก
        final TextView tvw4 = (TextView) findViewById(R.id.textView4);
        tvw4.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent newActivity = new Intent(MainActivity.this,Register_user.class);
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
            Log.d("2octV2","index ==>" + index);
            SynMember synMember = new SynMember(this);
            synMember.execute();

        }
    }//clickMylogin

    private class  SynMember extends  AsyncTask<Void, Void, String>{
        private Context context;

        public SynMember(Context context) {
            this.context = context;
        }
        @Override
        protected String doInBackground(Void... params) {
            try {

                OkHttpClient okHttpClient = new OkHttpClient();
                Request.Builder builder = new  Request.Builder();
                Request request = builder.url(urlStrings[index]).build();
                Response response = okHttpClient.newCall(request).execute();
                return  response.body().string();

            }catch (Exception e){
                Log.d("2octV2","e doInback==>" + e.toString());
                return null;
            }

        }//doinback

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Log.d("2octV2","JSON ==>" + s);

            try{

            }catch (Exception e){
                Log.d("2octV2"," e onPost" + e.toString());
            }
        }
    }//SynMember

}//main Class
