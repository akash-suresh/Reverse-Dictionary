package com.akash.reversedictionary;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


import org.w3c.dom.Text;

import static android.content.Intent.*;


public class rd extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int i=0;
        super.onCreate(savedInstanceState);
        Intent intent=getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        String tokenized[]= new String[20];
        //splitting string basd on spaces
        for (String token : message.split(" ")) {
            tokenized[i++]=token;
        }
        TextView textView = new TextView(this);
        textView.setTextSize(40);
        textView.setText(joinPlus(tokenized,i));
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
}
