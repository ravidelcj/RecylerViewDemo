package ezio.recylerviewdemo;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.afollestad.materialdialogs.MaterialDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.BufferOverflowException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static List<String> list;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        btn= (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyAsync async=new MyAsync();
                async.execute();
                startActivity(new Intent(MainActivity.this,Recycler.class));
            }
        });

    }

    //ASYNC TASK


    public class MyAsync extends AsyncTask<Void,Void,Void>
    {
        String res;
        MaterialDialog dialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            list=new ArrayList<String>();
            dialog=new MaterialDialog.Builder(MainActivity.this).title("Loading").content("Loading...").progress(true,0).show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            String str="http://api.androidhive.info/contacts/";
            try {
                URL url=new URL(str);
                HttpURLConnection connection= (HttpURLConnection) url.openConnection();
                InputStream is = connection.getInputStream();
                BufferedReader reader=new BufferedReader(new InputStreamReader(is));
                StringBuilder buffer=new StringBuilder();
                Log.d("Ravi","Reached");
                String line = null;
                while((line=reader.readLine())!=null)
                {
                    buffer.append(line);
                }
                is.close();
                res=buffer.toString();
               // Log.d("Ravi",res);
            } catch (Exception e) {
                //Log.d("Ravi",e.toString());
            }
            try {
                jsonParse();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        private void jsonParse() throws JSONException {

            Log.d("Ravi","JSON REACHED");
            JSONObject obj=new JSONObject(res);
            JSONArray mrr=obj.getJSONArray("contacts");
            for(int i=0;i<mrr.length();i++)
            {
                Log.d("Ravi","JSON REACHE D"+i);
                JSONObject obj1=mrr.getJSONObject(i);
                list.add((String) obj1.get("name"));
            }

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            dialog.hide();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
