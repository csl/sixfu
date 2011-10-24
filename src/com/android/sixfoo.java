package com.android;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;

import android.os.Bundle;
import android.text.TextUtils.TruncateAt;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class sixfoo extends ListActivity 
{
    /** Called when the activity is first created. */
	//private static final int SHOW_EDITOR = 0;
	private TextView txtMarquee;
	private ListView lv;
	ArrayList<String> light_news;
	String loginurl;
	String tel;
	
	private static final int MENU_EXIT = Menu.FIRST;
	
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		txtMarquee = (TextView)findViewById(R.id.txtMarquee);
		
    	String loginurl  = (String) this.getResources().getText(R.string.url);

    	//get current data
    	Date today = Calendar.getInstance().getTime();
    	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String szToday = df.format(today);
        
		//get light news
        String uriAPI = loginurl + "getTodayLight.php?date=" + szToday;
   
        URL url = null;
        try{
            url = new URL(uriAPI);
            SAXParserFactory spf = SAXParserFactory.newInstance();
            SAXParser sp = spf.newSAXParser();

            XMLReader xr = sp.getXMLReader();
            LightHandler myHandler = new LightHandler();
            xr.setContentHandler(myHandler);
            //open connection
           	xr.parse(new InputSource(url.openStream()));
           	light_news = myHandler.getContainer().getLigtht();
        }
        catch(Exception e){
        	//openOptionsDialog("login fail:"+e);
        }
                
        String newslist="最新消息：\n";
        if (light_news != null)
        {
	        int lsize = light_news.size();
	        for (int i=0; i<lsize; i++)
	        {
	        	newslist = newslist +  light_news.get(i) + "\n";
	        }
        }
        txtMarquee.setText(newslist);
        
		//ListView
		CustomerListAdapter adapter = new CustomerListAdapter(this);
		ContentListElement element;

		adapter.addSectionHeaderItem("使用者：遊客");

		ArrayList<ListElement> elements = new ArrayList<ListElement>();

		element = new ContentListElement();
		element.setTitle("今日消息");
		elements.add(element);

		element = new ContentListElement();
		element.setTitle("全區地圖");
		elements.add(element);
		
		element = new ContentListElement();
		element.setTitle("飲食資訊");
		elements.add(element);

		element = new ContentListElement();
		element.setTitle("遊園建議");
		elements.add(element);

		element = new ContentListElement();
		element.setTitle("緊急聯絡");
		elements.add(element);

		element = new ContentListElement();
		element.setTitle("園區導覽");
		elements.add(element);

		element = new ContentListElement();
		element.setTitle("結束程式");
		elements.add(element);
		
		adapter.addList(elements);

		this.setListAdapter(adapter);
		
		//lv.setFocusable(false);
	}
	
	  public boolean onCreateOptionsMenu(Menu menu)
	  {
	    super.onCreateOptionsMenu(menu);
	    
	    menu.add(0 , MENU_EXIT, 1 , "EXIT")
	    .setAlphabeticShortcut('E');
	  return true;  
	  }
	  
	  @Override
	  public boolean onOptionsItemSelected(MenuItem item)
	  {
	    switch (item.getItemId())
	      { 
	          case MENU_EXIT:
	            //SendGPSData("23.1,123.6");
					loginurl  = (String) this.getResources().getText(R.string.url);
					tel = ton.tel;

					int rep = logintoweb(loginurl + "login.php?phone=" + tel + "&loginout=1"); 
					if (rep == 0)
					{
			            android.os.Process.killProcess(android.os.Process.myPid());
						this.finish();
					}
	             break ;
	      }
	    
	      return true ;
	  }
	  
		protected void onListItemClick(ListView l, View v, int position, long id) 
		{
			Log.d("DEBUG", "press " + position);

			if (position == 1)
			{
				Intent intent = new Intent();
				intent.setClass(sixfoo.this, todaynews.class);
		
				startActivity(intent);
				finish();
			}
			else if (position==2)
			{
				Intent intent = new Intent();
				intent.setClass(sixfoo.this,global.class);
		
				startActivity(intent);
				finish();
			}
			else if (position == 3)
			{
				Intent intent = new Intent();
				intent.setClass(sixfoo.this,order.class);
		
				startActivity(intent);
				finish();
			}
			else if (position == 4)
			{
				Intent intent = new Intent();
				intent.setClass(sixfoo.this,advise.class);
		
				startActivity(intent);
				finish();
			}
			else if (position == 5)
			{
				Intent intent = new Intent();
				intent.setClass(sixfoo.this,emergency.class);
		
				startActivity(intent);
				finish();
			}
			else if (position == 6)
			{
				Intent intent = new Intent();
				intent.setClass(sixfoo.this,MyGoogleMap.class);
		
				startActivity(intent);
				finish();
			}
			else if (position == 7)
			{
				loginurl  = (String) this.getResources().getText(R.string.url);
				tel = ton.tel;

				int rep = logintoweb(loginurl + "login.php?phone=" + tel + "&loginout=1"); 
				if (rep == 0)
				{
		            android.os.Process.killProcess(android.os.Process.myPid());
					this.finish();
				}
				
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
}