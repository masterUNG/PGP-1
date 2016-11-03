package anuson.komkid.permitgeographypro;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;


public class Menu_user_1 extends FragmentActivity {
//สไลท์ภาพ
    MyPageAdapter adapter;
    ViewPager pager;
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_user_1);

//สไลท์ภาพ
            adapter = new MyPageAdapter(getSupportFragmentManager());
            pager = (ViewPager) findViewById(R.id.pager);
            pager.setAdapter(adapter);
    }
}
