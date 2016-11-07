package anuson.komkid.permitgeographypro;

import android.app.Activity;
import android.content.DialogInterface;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.support.v7.app.AlertDialog;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;


public class Menu_farmer_2 extends Activity{

        //Explicit
        private String[] userLoginStrings;



        private EditText tiltleEditText,textEditText;
        private String tiltleString,textString,memberString;
        private  static final String urlPHP ="http://swiftcodingthai.com/gam/php_add_post.php";
        private Spinner spnITEM;
        private TextView textView ;


        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_farmer_2);

            //ฺฺBind Widget
            tiltleEditText = (EditText) findViewById(R.id.editText19);
            textEditText = (EditText) findViewById(R.id.editText21);
            textView = (TextView) findViewById(R.id.textView16);

            //Get Valuse From
            userLoginStrings = getIntent().getStringArrayExtra("Login");

            //Check userLogin
            for (int i = 0; i < userLoginStrings.length; i++) {
                Log.d("4novV3", "userLogin" + i + "]=" + userLoginStrings[i]);
                }//for

            // Show Text
            textView.setText(userLoginStrings[7]);


    }//onCreate
    public void clickSaveData(View view){

        tiltleString    = tiltleEditText.getText().toString().trim();
        textString      = textEditText.getText().toString().trim();

        //Get Valuse From
        userLoginStrings = getIntent().getStringArrayExtra("Login");
        memberString    = userLoginStrings[0];

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
            }
        });
        builder.show();
    }//confirmData
    private void uploadToServer(){
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = new FormEncodingBuilder()
                .add("isAdd", "true")
                .add("post_tiltle", tiltleString)
                .add("post_text", textString)
                .add("post_member", memberString)
                .build();
        Request.Builder builder = new Request.Builder();
        Request request = builder.url(urlPHP).post(requestBody).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Log.d("4SepV2", "e==>" + e.toString());
            }

            @Override
            public void onResponse(Response response) throws IOException {
                Log.d("4SepV2","Result==>"+ response.body().string());

            }
        });

    }//uploadToServer

}//Main Method
