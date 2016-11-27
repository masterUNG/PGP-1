package anuson.komkid.permitgeographypro;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class Menu_farmer_3 extends Activity {

    //Exelicit
    private ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_farmer_3);

        listView = (ListView) findViewById(R.id.livPost);


    }//Main Method
}//Main Class
