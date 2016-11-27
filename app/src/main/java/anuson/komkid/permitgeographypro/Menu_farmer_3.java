package anuson.komkid.permitgeographypro;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Menu_farmer_3 extends Activity {

    private EditText postEditText, memberEditText, datastarEditText, dataentEditText, taryEditText;
    private String[] urlStrings = new String[]{"http://swiftcodingthai.com/gam/php_get_post.php"};
    private String[] columnpost = new String[]{
            "post_id",
            "mem_id",
            "post_tiltle",
            "post_text",
            "post_data_ster",
            "post_data_end",
            "post_view",
            "post_pic",
            "post_pic_two",
            "status_reserv_id"};
    private int post = 0;
    private String[] postStrings;
    private String[] userLoginStrings ;
    private TextView textViewpost,textViewmember,textViewdataend,textViewstatus ;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_farmer_3);

        textViewpost = (TextView) findViewById(R.id.textView24);
        textViewmember = (TextView) findViewById(R.id.textView23);
        textViewdataend =  (TextView) findViewById(R.id.textView18);
        textViewstatus = (TextView) findViewById(R.id.textView17);

        SynMember synMember = new SynMember(this);
        synMember.execute();

        //Get Valuse From
        userLoginStrings = getIntent().getStringArrayExtra("Login");
        for (int i=0;i<userLoginStrings.length;i++){
            Log.d("4novV7","userLogin"+ i +"]="+ userLoginStrings[i]);
        }//for

        textViewpost.setText(userLoginStrings[7]);
}//onCreate

    private class SynMember extends AsyncTask<Void, Void, String> {
        private Context context;
        private boolean aBoolean = true;
        public SynMember(Context context) {
            this.context = context;
        }

        @Override
        protected String doInBackground(Void... voids) {
            try {

                OkHttpClient okHttpClient = new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                Request request = builder.url(urlStrings[post]).build();
                Response response = okHttpClient.newCall(request).execute();
                return response.body().string();

            } catch (Exception e) {
                Log.d("16novV2", "e doInBack ==> " + e.toString());
                return null;
            }
        }//doInBackground
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);


            Log.d("16novV2", "JSON ==> " + s);
            try {
                JSONArray jsonArray = new JSONArray(s);
                switch (post){
                    case 0:
                        postStrings = new String[columnpost.length];
                        break;
                }
                for (int i=0;i<jsonArray.length();i+=1) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    switch (post) {
                        case 0:
                            if (userLoginStrings[0].equals(jsonObject.getString(columnpost[1]))) {
                                aBoolean = false;
                                for (int j = 0; j < columnpost.length; j += 1) {
                                    postStrings[j] = jsonObject.getString(columnpost[j]);
                                    Log.d("16novV3", "postStrings(" + j + ") = " + postStrings[j]);

                                    textViewpost.setText(postStrings[0]);
                                    textViewdataend.setText(postStrings[4]);
                                    textViewstatus.setText(postStrings[9]);
                                } //for
                                break;
                            }
                    }//switch
                }//for
                }catch (Exception e) {
                Log.d("2octV2", "e onPost ==> " + e.toString());
            }//try
        }//onPostExecute
    }//Menu_farmer_3
}
