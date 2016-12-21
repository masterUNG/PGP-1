package anuson.komkid.permitgeographypro;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

public class ListPostByUser extends AppCompatActivity {

    //Explicit
    private String mem_idString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_post_by_user);

        //Setting
        mem_idString = getIntent().getStringExtra("mem_id");
        Log.d("21decV2", "mem_id ==> " + mem_idString);

        try {

            SynPost synPost = new SynPost(ListPostByUser.this, mem_idString);
            synPost.execute();
            String s = synPost.get();
            Log.d("21decV2", "JSoN ==> " + s);

            JSONArray jsonArray = new JSONArray(s);

            final String[] titleStrings = new String[jsonArray.length()];
            final String[] endStrings = new String[jsonArray.length()];
            final String[] statusStrings = new String[jsonArray.length()];
            final String[] textStrings = new String[jsonArray.length()];
            final String[] startStrings = new String[jsonArray.length()];
            final String[] pic1Strings = new String[jsonArray.length()];
            final String[] pic2Strings = new String[jsonArray.length()];


            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);

                titleStrings[i] = jsonObject.getString("post_tiltle");
                endStrings[i] = jsonObject.getString("post_data_end");
                statusStrings[i] = jsonObject.getString("status_reserv_id");
                textStrings[i] = jsonObject.getString("post_text");
                startStrings[i] = jsonObject.getString("post_data_ster");
                pic1Strings[i] = jsonObject.getString("post_pic");
                pic2Strings[i] = jsonObject.getString("post_pic_two");

            }   // for

            ListView listView = (ListView) findViewById(R.id.livPostByUser);

            MyPostAdapter myPostAdapter = new MyPostAdapter(ListPostByUser.this,
                    titleStrings, endStrings, statusStrings);
            listView.setAdapter(myPostAdapter);


        } catch (Exception e) {
            Log.d("21decV2", "e ==> " + e.toString());
        }


    }   // Main Method

}   // Main Class
