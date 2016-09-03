package anuson.komkid.permitgeographypro;

import android.app.Activity;
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
        }

    }//clickSaveData

    private boolean checkSpace() {
        return userString.equals("")||
                passString.equals("")||
                passConString.equals("")||
                nameString.equals("")||
                telString.equals(""); //เมื่อมีช่องว่าง
    }

}//Main class
