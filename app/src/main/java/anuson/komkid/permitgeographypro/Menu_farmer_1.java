package anuson.komkid.permitgeographypro;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.Toast;


public class Menu_farmer_1 extends Activity{
        final Context context = this;
        private Button button;
    @Override
     public  void  onCreate(Bundle savedInstanceState){
      super.onCreate(savedInstanceState);
      setContentView(R.layout.menu_farmer_1);

        button = (Button) findViewById(R.id.button_show_alert_dialog);
        button.setOnClickListener(new View.OnClickListener() {
            public  void onClick(View v){
                AlertDialog.Builder alert = new AlertDialog.Builder(context);
//                alert.setTitle("ปลดล็อคชื่อผู้ใช้ที่ติดสถานะ ล็อค");
                alert.setMessage("กรุณาใส่ ชื่อผู้ใช้");

                final EditText input = new EditText(context);
                alert.setView(input);


                alert.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String srt = input.getEditableText().toString();
                        Toast.makeText(context,srt, Toast.LENGTH_LONG).show();
                    }
                });
                alert.setNegativeButton("ยกเลิอก", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alertDialog = alert.create();
                alertDialog.show();
            }
        });
    }
}
