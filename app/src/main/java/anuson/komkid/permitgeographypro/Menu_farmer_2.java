package anuson.komkid.permitgeographypro;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.BoolRes;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.support.v7.app.AlertDialog;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class Menu_farmer_2 extends Activity{

        //Explicit
        private String[] userLoginStrings;
        private EditText tiltleEditText,textEditText;
        private String tiltleString,textString,memberString,datatimeString,endTimeString;
        private  static final String urlPHP ="http://swiftcodingthai.com/gam/php_add_post.php";
        private Spinner spnITEM;
        private TextView textView ;
        private  int dayAnTnt;



        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_farmer_2);

            //ฺฺBind Widget
            tiltleEditText = (EditText) findViewById(R.id.editText19);
            textEditText = (EditText) findViewById(R.id.editText21);
            textView = (TextView) findViewById(R.id.textView16);
            spnITEM = (Spinner) findViewById(R.id.spinner);

            //

            //Get Valuse From
            userLoginStrings = getIntent().getStringArrayExtra("Login");

            //Check userLogin
            for (int i = 0; i < userLoginStrings.length; i++) {
                Log.d("4novV3", "userLogin" + i + "]=" + userLoginStrings[i]);
                }//for

            // Show Text
            textView.setText(userLoginStrings[7]);

            // Current Date
            Calendar c = Calendar.getInstance();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String formatteDate = df.format(c.getTime());
            //txtResult


            datatimeString = formatteDate;
            Log.d("27novV1", "dataTimeString ==>" + datatimeString);


            //Create Spinner
            createSpinner();
    }//onCreate
    private void createSpinner(){
        String[] strings = getResources().getStringArray(R.array.dayLeng);
        final int[] ints = new int[]{1,2,3,4,5,6,7};


        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(Menu_farmer_2.this,
                android.R.layout.simple_list_item_1, strings);

        spnITEM.setAdapter(stringArrayAdapter);

        spnITEM.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                dayAnTnt = ints[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                dayAnTnt= ints[0];
            }

        });
    }//createSpinner

    public void clickSaveDataMemuFarm2(View view){




        tiltleString    = tiltleEditText.getText().toString().trim();
        textString      = textEditText.getText().toString().trim();

        //Get Valuse From
        userLoginStrings = getIntent().getStringArrayExtra("Login");
        memberString    = userLoginStrings[0];

        //ค่าวันที่ จำนวนเหลือวัน Post data End
        Log.d("27novV2","dayAnInt==>" + dayAnTnt);
        String[] strings = datatimeString.split("-");
        for(int i =0;i<strings.length;i++){
            Log.d("27novV2","strings(" + ") ==>" + strings[i]);
        }
        int intEnd = dayAnTnt + Integer.parseInt(strings[2]);
        endTimeString = strings[0] + "-" + strings[1]+ "-" + Integer.toString(intEnd);
        Log.d("27novV2","endTimeString ==>" + endTimeString);



        if (checkSpace()) {
            MyAlert myAlert = new MyAlert();
            myAlert.myDialog(this, "มีช่องว่าง", "กรุณากรอกทุกช่องครับ"); //เมื่อมีช่องว่างให้แสดง ข้อความ);

        } else {
            confirmData();
        }//if
    }//clickSaveData
    private boolean checkSpace(){
        return tiltleString.equals("")||
                textString.equals("");
    }//checkSpac
    private void confirmData() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setIcon(R.drawable.dule_icon);
        builder.setTitle("ยืนยันการลงประกาศ");

        builder.setNegativeButton("ยกเลิก", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setPositiveButton("ยืนยัน", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                uploadToServer();
                dialog.dismiss();
            }
        });
        builder.show();
    }//confirmData

    private class MyPostData extends AsyncTask<Void, Void, String> {

        //Explicit
        private Context context;

        public MyPostData(Context context) {
            this.context = context;
        }

        @Override
        protected String doInBackground(Void... params) {

            try {
                OkHttpClient okHttpClient = new OkHttpClient();
                RequestBody requestBody = new FormEncodingBuilder()
                        .add("isAdd", "true")
                        .add("post_tiltle", tiltleString)
                        .add("post_text", textString)
                        .add("mem_id", memberString)
                        .add("post_data_ster", datatimeString)
                        .add("post_data_end", endTimeString)
                        .build();
                Request.Builder builder = new Request.Builder();
                Request request = builder.url(urlPHP).post(requestBody).build();
                Response response = okHttpClient.newCall(request).execute();
                return response.body().string();


            } catch (Exception e) {
                Log.d("27novV1","e doIn"+ e.toString());
                return null;
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Log.d("27novV1","Result ==>"+s);
            if (Boolean.parseBoolean(s)) {
                //Reslt OK
                Intent intent = new Intent(context,Menu_farmer_3.class);
                intent.putExtra("Login", userLoginStrings);
                startActivity(intent);
            } else {
                //Result Error
                Toast.makeText(context, "Cannot Update Data", Toast.LENGTH_SHORT).show();
            }

        }
    }//MypsotData


    private void uploadToServer(){
        MyPostData myPostData = new MyPostData(Menu_farmer_2.this);
        myPostData.execute();

    }//uploadToServer

}//Main Method
