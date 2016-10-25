package anuson.komkid.permitgeographypro;

import android.app.ActionBar;
import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

/**
 * Created by komkrid on 28/9/2559.
 */
@SuppressWarnings("deprecation")
public class Menu_farmer extends TabActivity{
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_farmer);

            TabHost tabHost = (TabHost)findViewById(android.R.id.tabhost);

            TabHost.TabSpec tab1 = tabHost.newTabSpec("ABA UM");
            TabHost.TabSpec tab2 = tabHost.newTabSpec("ABA DOIS");
            TabHost.TabSpec tab3 = tabHost.newTabSpec("ABA TRES");

            //tab1.setIndicator("Tab1");
            tab1.setIndicator("",getResources().getDrawable(R.mipmap.ic_launcher));
            tab1.setContent(new Intent(this, Menu_farmer_1.class));

            tab2.setIndicator("ลงประกาศ");
            //tab2.setIndicator("",getResources().getDrawable(R.mipmap.ic_launcher));
            tab2.setContent(new Intent(this, Menu_farmer_2.class));

            tab3.setIndicator("รายการประกาศ");
            //tab3.setIndicator("",getResources().getDrawable(R.mipmap.ic_launcher));
            tab3.setContent(new Intent(this, Menu_farmer_3.class));

            tabHost.addTab(tab1);
            tabHost.addTab(tab2);
            tabHost.addTab(tab3);



        }
}
