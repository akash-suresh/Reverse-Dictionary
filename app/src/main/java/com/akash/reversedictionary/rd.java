package com.akash.reversedictionary;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static android.content.Intent.*;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class rd extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int i=0;
        StrictMode.ThreadPolicy policy = new StrictMode.
        ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        Intent intent=getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        String tokenized[]= new String[20];
        //splitting string basd on spaces
        for (String token : message.split(" ")) {
            tokenized[i++]=token;
        }
        String arg=joinPlus(tokenized,i);
      /*  String result = "";*/
/*
        final_function obj = new final_function();
        obj.execute(arg);
*/
        String result = reqHTML(arg);
        TextView textView = new TextView(this);
        textView.setTextSize(10);
        textView.setText(result);
        setContentView(textView);

    }
    //to join with +
    private String joinPlus(String tokenized[], int size){
        int i=0;
        String joinedString= new String();
        joinedString = tokenized[0];
        for(i=1;i<size;i++){
            joinedString=joinedString+ '+' +tokenized[i];
        }
        return joinedString;
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
/*
class final_function extends AsyncTask<String, Void, String> {
    public static String res_arr = "";
    private Exception exception;
    @Override
    protected String doInBackground(String... arg) {

        try {
            Document doc = Jsoup.connect("http://www.onelook.com/?w=*&loc=revfp2&clue="+arg).get();
            Elements links = doc.select("td a[href]");
            int i=0;
            for (Element link : links) {
                if(i>=0 && i<=100){
                    res_arr += link.text();
                    res_arr += "\n";
                }
                i++;
                //print(" * a: <%s>  (%s)", link.attr("abs:href"), trim(link.text(), 35));
            }
        } catch (Exception e) {
            this.exception = e;
            return null;
        }
        Log.d("Jswa",res_arr);
        return res_arr;
    }
}*/

    private String reqHTML(String arg) {
        String result = new String();
        try {
            URL url = new URL("http://www.onelook.com/?w=*&loc=revfp2&clue="+arg);
            HttpURLConnection con = (HttpURLConnection) url
                    .openConnection();
            result = readStream(con.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    private String readStream(InputStream in) {
            String htmlPage=new String("");
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new InputStreamReader(in));
                String line = "";
                int i=1;
                while ((line = reader.readLine()) != null) {
                    if(i>=49 && i<=74) {
                        htmlPage = htmlPage + line;
                    }
                    i++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        Log.d("Jswa",htmlPage);
        return htmlPage;
    }
}