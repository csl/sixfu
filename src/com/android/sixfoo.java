package com.android;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;

import android.os.Bundle;
import android.text.TextUtils.TruncateAt;
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
                
        String newslist="最新消息： ";
        if (light_news != null)
        {
	        int lsize = light_news.size();
	        for (int i=0; i<lsize; i++)
	        {
	        	newslist = newslist +  light_news.get(i) + "   ";
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
	            android.os.Process.killProcess(android.os.Process.myPid());
	            finish();
	    
	             break ;
	      }
	    
	      return true ;
	  }
	
}