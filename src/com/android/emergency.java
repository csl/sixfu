package com.android;

import java.io.IOException;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class emergency extends Activity {
    /** Called when the activity is first created. */
	
	private String loginurl;
	private String TAG = "emergency";
  
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.emergency);

    	loginurl  = (String) this.getResources().getText(R.string.url);

        if (MyGoogleMap.nowGeoPoint == null)
        {
        	openGPSDialog();
        }
        
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
				openOptionsDialog();
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
	
	  private void openOptionsDialog() {
		    
		    new AlertDialog.Builder(this)
		      .setTitle("注意")
		      .setMessage("是否確定撥打")
		      .setNegativeButton("不撥打",
		          new DialogInterface.OnClickListener() {
		          
		            public void onClick(DialogInterface dialoginterface, int i) 
		            {
		            }
		      	}
		      )
		   
		      .setPositiveButton("確定撥打",
		          new DialogInterface.OnClickListener() {
		          public void onClick(DialogInterface dialoginterface, int i) 
		          {
				        double geoLatitude = 0; 
				        double geoLongitude = 0;
				        
						if (MyGoogleMap.nowGeoPoint != null)
						{
					        geoLatitude = MyGoogleMap.nowGeoPoint.getLatitudeE6()/1E6; 
					        geoLongitude = MyGoogleMap.nowGeoPoint.getLongitudeE6()/1E6;
						}

						Log.i(TAG, loginurl + "setemergecy.php?");
						int rep = toweb(loginurl + "setEmeremcy.php?phone=" + ton.tel + "&gps=" + geoLatitude + "," + geoLongitude + "&status=1"); 
						if (rep == 0)
							openOptionsDialog("已送出緊急訊息，待工作人員處理");
						else
							openOptionsDialog("送出緊急訊息錯誤!!");
		          }
		      }
		      )
		      
		      .show();
		  }
	  
	  private void openGPSDialog() {
		    
		    new AlertDialog.Builder(this)
		      .setTitle("注意")
		      .setMessage("請先點選園區導覽，若您要繼續使用此功能，將無法提供現在GPS位置")
		      .setNegativeButton("離開",
		          new DialogInterface.OnClickListener() {
		          
		            public void onClick(DialogInterface dialoginterface, int i) 
		            {
		            	finish();
		            }
		      	}
		      )
		   
		      .setPositiveButton("繼續",
		          new DialogInterface.OnClickListener() {
		          public void onClick(DialogInterface dialoginterface, int i) 
		          {
		          }
		      }
		      )
		      
		      .show();
		  }
		  

		  
    //error message
    private void openOptionsDialog(String info)
	{
	    new AlertDialog.Builder(this)
	    .setTitle("message")
	    .setMessage(info)
	    .setPositiveButton("OK",
	        new DialogInterface.OnClickListener()
	        {
	         public void onClick(DialogInterface dialoginterface, int i)
	         {
	         }
	         }
	        )
	    .show();
	}
	
}