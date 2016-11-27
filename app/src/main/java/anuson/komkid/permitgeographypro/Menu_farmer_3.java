package anuson.komkid.permitgeographypro;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

public class Menu_farmer_3 extends Activity{

    //Explicit
    private ListView listView;
    private String[] loginStrings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_farmer_3);

        //Bind Widget
        listView = (ListView) findViewById(R.id.livPost);

        //Get Value From Intent
        loginStrings = getIntent().getStringArrayExtra("Login");

        try {

            SynPost synPost = new SynPost(Menu_farmer_3.this, loginStrings[0]);
            synPost.execute();

            String strJSON = synPost.get();
            Log.d("27novV3", "JSON ==> " + strJSON);

            JSONArray jsonArray = new JSONArray(strJSON);

            String[] titleStrings = new String[jsonArray.length()];
            String[] endStrings = new String[jsonArray.length()];
            String[] statusStrings = new String[jsonArray.length()];

            for (int i=0;i<jsonArray.length();i++) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);

                titleStrings[i] = jsonObject.getString("post_tiltle");
                endStrings[i] = jsonObject.getString("post_data_end");
                statusStrings[i] = jsonObject.getString("status_reserv_id");

            }   // for

            MyPostAdapter myPostAdapter = new MyPostAdapter(Menu_farmer_3.this,
                    titleStrings, endStrings, statusStrings);
            listView.setAdapter(myPostAdapter);

        } catch (Exception e) {
            Log.d("27novV3", "e menu3 ==> " + e.toString());
        }

    }   // Main Method

}   // Main Class