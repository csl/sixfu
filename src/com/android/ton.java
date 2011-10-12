package com.android;



import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;




public class ton extends Activity {
    /** Called when the activity is first created. */
	//private static final int SHOW_EDITOR = 0;
	private String tel;
	private String loginurl;
	static public String TAG = "SIXFU";
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cover);
		//設定Button01按鈕的操作
		Button button01 = (Button)ton.this.findViewById(R.id.button1);
		
        TelephonyManager tm = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
        tel = tm.getLine1Number();
        
        if (tel == null)
        {
        	tel = "";
        	//simcard not offer
            //顯示輸入IP的windows
            final EditText input = new EditText(this);
            input.setText(tel);
            AlertDialog.Builder alert = new AlertDialog.Builder(this);

            //openOptionsDialog(getLocalIpAddress());
            
            alert.setTitle("因simcard無提供, 無法自動取得");
            alert.setMessage("請輸入phone");
            
            // Set an EditText view to get user input 
            alert.setView(input);
            
            alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) 
            {
              try
              {
                tel = input.getText().toString();  
              }
              catch (Exception e)
              {
                e.printStackTrace();
              }
            }
            });

            alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
              public void onClick(DialogInterface dialog, int whichButton) {
                // Canceled.
              }
            });

            alert.show();      
        }
        
        
		loginurl  = (String) this.getResources().getText(R.string.url);

		button01.setOnClickListener(new Button.OnClickListener(){
			@Override
			public void onClick(View v){
				
				//login
				startActivity(new Intent(ton.this, sixfoo.class));
				ton.this.finish();
				
				Log.i(TAG, "login register: " + loginurl + "login.php?phone=" + tel);
				int rep = logintoweb(loginurl + "login.php?phone=" + tel); 
				if (rep == 0)
				{
					startActivity(new Intent(ton.this, sixfoo.class));
					ton.this.finish();
				}
			}
			
			public int logintoweb(String uriAPI)
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
		});
			
	}
}