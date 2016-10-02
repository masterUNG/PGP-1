package anuson.komkid.permitgeographypro;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.internal.Internal;

import java.io.IOException;


public class Register extends Activity {

    //Explicit
    private EditText userEditText,passEditText,passConEditText,nameEditText,telEditText ;
    private String userString,passString,passConString,nameString,telString, typeString;
    private RadioGroup radioGroup;
    private RadioButton famerRadioButton, buyerRadioButton;
    private String[] typeStrings = new String[]{"เกษตรกร","ผู้ใช้งานทั่วไป"};


    private  static final String urlPHP = "http://swiftcodingthai.com/gam/php_add_member.php";



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        //Bind Widget
        userEditText = (EditText) findViewById(R.id.editText3);
        passEditText = (EditText) findViewById(R.id.editText4);
        passConEditText = (EditText) findViewById(R.id.editText5);
        nameEditText = (EditText) findViewById(R.id.editText6);
        telEditText = (EditText) findViewById(R.id.editText7);
        radioGroup = (RadioGroup) findViewById(R.id.ragType);
        famerRadioButton = (RadioButton) findViewById(R.id.radioButton);
        buyerRadioButton = (RadioButton) findViewById(R.id.radioButton2);

        //Radio Controller
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId) {
                    case  R.id.radioButton:
                        typeString = "0";
                        break;
                    case  R.id.radioButton2:
                        typeString = "1";
                        break;

                }
            }
        });
    }//Main Method

    public void clickSaveData(View view){
        userString = userEditText.getText().toString().trim();
        passString = passEditText.getText().toString().trim();
        passConString = passConEditText.getText().toString().trim();
        nameString = nameEditText.getText().toString().trim();
        telString = telEditText.getText().toString().trim();

        //check Space
        if (checkSpace()) {
            //Have space
            MyAlert myAlert = new MyAlert();
            myAlert.myDialog(this, "มีช่องว่าง","กรุณากรอกทุกช่องครับ"); //เมื่อมีช่องว่างให้แสดง ข้อความ
        } else if (!passString.equals(passConString)) {

            MyAlert myAlert = new MyAlert();
            myAlert.myDialog(this, "รหัสไม่ตรงกัน","กรุณากรอกรหัสผ่านให้ตรงกันครับ");//เช็ครหัสผ่านให้ตรง

        } else if (!(famerRadioButton.isChecked() || buyerRadioButton.isChecked())) {

            MyAlert myAlert = new MyAlert();
            myAlert.myDialog(this, "ยังไม่เลือก Type User", "กรุณาเลือก Type User");
        }else{

                confirmData();

        }
    }//clickSaveData

    private void confirmData() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setIcon(R.drawable.dule_icon);
        builder.setTitle("โปรดรวจสอบข้อมูล");
        builder.setMessage("ชื่อผู้ใช้งาน = " + userString + "\n"+
                "ชื่อ-นามสกุล = " + nameString + "\n"+
                "เบอร์โทรติดต่อ = " + telString + "\n"+
                "สถานะ = " + typeStrings[Integer.parseInt(typeString)]);
                //โชวข้อมูลการกรอก การแจ้งเตือนครั้งสุดท้าย

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                uploadToServer();
                dialog.dismiss();
            }
        });
        builder.show();
    }//

    private void uploadToServer() {
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = new FormEncodingBuilder()
                .add("isAdd", "true")
                .add("Username", userString)
                .add("Password", passString)
                .add("Name", nameString)
                .add("telephone",telString)
                .add("type",typeString)
                .build();
        Request.Builder builder = new Request.Builder();
        Request request = builder.url(urlPHP).post(requestBody).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Log.d("4SepV1", "e==>" + e.toString());
            }

            @Override
            public void onResponse(Response response) throws IOException {
                Log.d("4SepV1", "Result ==>"+ response.body().string());
            }
        });
    }//uploadToServer

    private boolean checkSpace() {
        return userString.equals("")||
                passString.equals("")||
                passConString.equals("")||
                nameString.equals("")||
                telString.equals(""); //เมื่อมีช่องว่าง
    }

}//Main class
