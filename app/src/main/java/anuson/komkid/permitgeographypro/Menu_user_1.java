package anuson.komkid.permitgeographypro;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


public class Menu_user_1 extends FragmentActivity {
//สไลท์ภาพ
    MyPageAdapter adapter;
    ViewPager pager;

    private ImageView imageView;
    private String[] userLoginStrings;
    private TextView userTextView,nameTextView,telTextView;



        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_user_1);

//สไลท์ภาพ
            adapter = new MyPageAdapter(getSupportFragmentManager());
            pager = (ViewPager) findViewById(R.id.pager);
            pager.setAdapter(adapter);

            //ฺฺBind Widget
            userTextView = (TextView) findViewById(R.id.textView8);
            nameTextView = (TextView) findViewById(R.id.textView9);
            telTextView = (TextView) findViewById(R.id.textView10);

            //Get Valuse From
            userLoginStrings = getIntent().getStringArrayExtra("Login");

            //Check userLogin
            for (int i = 0; i < userLoginStrings.length; i++) {
                Log.d("4novV2", "userLogin" + i + "]=" + userLoginStrings[i]);
            }//for
            //Show Text
            userTextView.setText("ID ผู้ใช้ :" + userLoginStrings[1]);
            nameTextView.setText("ชื่อ นามสกุล :" + userLoginStrings[3]);
            telTextView.setText("เบอร์โทรติดต่อ : "+ userLoginStrings[6]);


    }
}
