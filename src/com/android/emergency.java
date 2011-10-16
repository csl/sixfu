package com.android;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class emergency extends Activity {
    /** Called when the activity is first created. */
	
	private String loginurl;
	private String TAG = "emergency";
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.emergency);
        
        loginurl  = (String) this.getResources().getText(R.string.url);

        
        Button button01 = (Button)emergency.this.findViewById(R.id.button1);
        button01.setOnClickListener(new Button.OnClickListener(){
			@Override
			public void onClick(View v){
				startActivity(new Intent(emergency.this, sixfoo.class));
			}
		});

        Button button02 = (Button)emergency.this.findViewById(R.id.button2);
        button02.setOnClickListener(new Button.OnClickListener(){
			@Override
			public void onClick(View v)
			{
				Log.i(TAG, loginurl + "setemergecy.php?");
				int rep = toweb(loginurl + "setemergecy.php?phone="); 
				if (rep == 0)
				{
				}
				
			}
		});
    }
    
	public int toweb(String uriAPI)
	{
		int error = 0;
       HttpGet httpRequest = new HttpGet(uriAPI);
         
       try
        {
            HttpResponse httpResponse = new DefaultHttpClient().execute(httpRequest);
            if(httpResponse.getStatusLine().getStatusCode() == 200)
              {
              String strResult = EntityUtils.toString(httpResponse.getEntity());
              //strResult = eregi_replace("(\r\n|\r|\n|\n\r)","",strResult);
              //mTextView1.setText(strResult);
              }
            else
              {
              //mTextView1.setText("Error Response: "+httpResponse.getStatusLine().toString());
              }
          }
          catch (ClientProtocolException e)
          {
            //mTextView1.setText(e.getMessage().toString());
            e.printStackTrace();
            error = 1;
          }
          catch (IOException e)
          {
            //mTextView1.setText(e.getMessage().toString());
            e.printStackTrace();
            error = 1;
          }
          catch (Exception e)
          {
            //mTextView1.setText(e.getMessage().toString());
            e.printStackTrace();
            error = 1;
          }

         return error;
	}
    
}