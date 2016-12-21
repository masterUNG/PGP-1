package anuson.komkid.permitgeographypro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

public class ListPostByUser extends AppCompatActivity {

    //Explicit
    private String mem_idString;
    private String[] loginStrings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_post_by_user);

        //Setting
        mem_idString = getIntent().getStringExtra("mem_id");
        Log.d("21decV2", "mem_id ==> " + mem_idString);
        loginStrings = getIntent().getStringArrayExtra("Login");

        try {

            SynPost synPost = new SynPost(ListPostByUser.this, mem_idString);
            synPost.execute();
            String s = synPost.get();
            Log.d("21decV2", "JSoN ==> " + s);

            JSONArray jsonArray = new JSONArray(s);

            final String[] titleStrings = new String[jsonArray.length()];
            final String[] endStrings = new String[jsonArray.length()];
            final String[] statusStrings = new String[jsonArray.length()];
            final String[] statusShowStrings = new String[jsonArray.length()];
            final String[] textStrings = new String[jsonArray.length()];
            final String[] startStrings = new String[jsonArray.length()];
            final String[] pic1Strings = new String[jsonArray.length()];
            final String[] pic2Strings = new String[jsonArray.length()];
            final String[] idStrings = new String[jsonArray.length()];


            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);

                titleStrings[i] = jsonObject.getString("post_tiltle");
                endStrings[i] = jsonObject.getString("post_data_end");
                statusStrings[i] = jsonObject.getString("status_reserv_id");
                statusShowStrings[i] = showStatus(statusStrings[i]);
                textStrings[i] = jsonObject.getString("post_text");
                startStrings[i] = jsonObject.getString("post_data_ster");
                pic1Strings[i] = jsonObject.getString("post_pic");
                pic2Strings[i] = jsonObject.getString("post_pic_two");
                idStrings[i] = jsonObject.getString("post_id");

            }   // for

            ListView listView = (ListView) findViewById(R.id.livPostByUser);

            MyPostAdapter myPostAdapter = new MyPostAdapter(ListPostByUser.this,
                    titleStrings, endStrings, statusShowStrings);
            listView.setAdapter(myPostAdapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    Intent intent = new Intent(ListPostByUser.this, ShowDetailByUser.class);
                    intent.putExtra("post_tiltle", titleStrings[i]);
                    intent.putExtra("post_data_end", endStrings[i]);
                    intent.putExtra("status_reserv_id", statusStrings[i]);
                    intent.putExtra("post_text", textStrings[i]);
                    intent.putExtra("post_data_ster", startStrings[i]);
                    intent.putExtra("post_pic", pic1Strings[i]);
                    intent.putExtra("post_pic_two", pic2Strings[i]);

                    intent.putExtra("Login", loginStrings);
                    intent.putExtra("idPost", idStrings[i]);

                    startActivity(intent);


                }
            });


        } catch (Exception e) {
            Log.d("21decV2", "e ==> " + e.toString());
        }


    }   // Main Method


    private String showStatus(String statusString) {

        String[] strings = new String[]{"กำลังขาย", "จอง", "สิ้นสุด"};
        int i = Integer.parseInt(statusString);

        return strings[i];
    }

}   // Main Class
