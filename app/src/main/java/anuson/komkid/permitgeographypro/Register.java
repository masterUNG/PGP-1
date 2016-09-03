package anuson.komkid.permitgeographypro;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


public class Register extends Activity {

    //Explicit
    private EditText userEditText,passEditText,passConEditText,nameEditText,telEditText ;
    private String userString,passString,passConString,nameString,telString;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        //Bind Widget
        userEditText = (EditText) findViewById(R.id.editText3);
        passEditText = (EditText) findViewById(R.id.editText4);
        passConEditText = (EditText) findViewById(R.id.editText5);
        nameEditText = (EditText) findViewById(R.id.editText6);
        telEditText = (EditText) findViewById(R.id.editText7);


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
        } else if (passString.equals(passConString)) {

            confirmData();

        } else {
            MyAlert myAlert = new MyAlert();
            myAlert.myDialog(this, "รหัสไม่ตรงกัน","กรุณากรอกรหัสผ่านให้ตรงกันครับ");//เช็ครหัสผ่านให้ตรง
        }


    }//clickSaveData

    private void confirmData() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setIcon(R.drawable.nobita48);
        builder.setTitle("โปรดรวจสอบข้อมูล");
        builder.setMessage("ชื่อผู้ใช้งาน = " + userString + "\n"+
                "ชื่อ-นามสกุล = " + nameString + "\n"+
                "เบอร์โทรติดต่อ = " + telString + "\n");

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
    }

    private boolean checkSpace() {
        return userString.equals("")||
                passString.equals("")||
                passConString.equals("")||
                nameString.equals("")||
                telString.equals(""); //เมื่อมีช่องว่าง
    }

}//Main class
