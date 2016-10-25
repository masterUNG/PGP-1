package anuson.komkid.permitgeographypro;

import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

/**
 * Created by komkrid on 28/9/2559.
 */
@SuppressWarnings("deprecation")
public class Menu_user extends TabActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_user);

        TabHost tabHost = (TabHost)findViewById(android.R.id.tabhost);

        TabHost.TabSpec tab1 = tabHost.newTabSpec("ABA UM");
        TabHost.TabSpec tab2 = tabHost.newTabSpec("ABA DOIS");
        TabHost.TabSpec tab3 = tabHost.newTabSpec("ABA TRES");
        TabHost.TabSpec tab4 = tabHost.newTabSpec("ABA MAPS");

        //tab1.setIndicator("Tab1");
        tab1.setIndicator("",getResources().getDrawable(R.mipmap.ic_launcher));
        tab1.setContent(new Intent(this, Menu_user_1.class));

        tab2.setIndicator("ค้นหาสวน");
        //tab2.setIndicator("",getResources().getDrawable(R.mipmap.ic_launcher));
        tab2.setContent(new Intent(this, Menu_user_2.class));

        tab3.setIndicator("การจอง");
        //tab3.setIndicator("",getResources().getDrawable(R.mipmap.ic_launcher));
        tab3.setContent(new Intent(this, Menu_user_3.class));

        tab4.setIndicator("ยอดนิยม");
        //tab3.setIndicator("",getResources().getDrawable(R.mipmap.ic_launcher));
        tab4.setContent(new Intent(this, Register_user.class));


        tabHost.addTab(tab1);
        tabHost.addTab(tab2);
        tabHost.addTab(tab3);
        tabHost.addTab(tab4);


    }
}
