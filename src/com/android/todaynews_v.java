package com.android;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;


public class todaynews_v extends Activity {

	static public String TAG = "todaynews";

	//XML use	
	private ArrayList<HashMap<String, Object>> w_list;

	private ListView show_view;
	private TextView showmsg;
	
    private TodayNewsListContainer tnls;
    private PSuggestContainer pss;
    public static TodayNewsXMLStruct now_status;
    public static PSuggestXMLStruct now_pss;
    private String szToday;
    
    private int classify;
    private int position;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todaynews_preference);
        
    	String loginurl  = (String) this.getResources().getText(R.string.url);
    	
        //get current data
    	Date today = Calendar.getInstance().getTime();
    	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        szToday = df.format(today);  
        
        //get data form parent class
        Bundle bunde = this.getIntent().getExtras(); 
        classify = bunde.getInt("classify");
        String uriAPI = "";

        showmsg = (TextView)findViewById(R.id.textView1);
        
        if (classify == 4)
        {
            position = bunde.getInt("position");
            uriAPI = loginurl + "getPS.php?classify=" + (position+1);
            Log.i(TAG, uriAPI);
            showmsg.setText("遊園建議");
            
        }
        else
        {
            uriAPI = loginurl + "getTodayList.php?cdate=" + szToday + "&classify=" + classify;        	
        }
        
        if (classify == 1)
            showmsg.setText("今日維修設施");
        else if (classify == 2)
            showmsg.setText("今日優惠");
        else if (classify == 3)
            showmsg.setText("其他最新");

        URL url = null;

        try{
            url = new URL(uriAPI);
            SAXParserFactory spf = SAXParserFactory.newInstance();
            SAXParser sp = spf.newSAXParser();

            XMLReader xr = sp.getXMLReader();
            //Using handler for xml
            if (classify == 4)
            {
            	PSuggestHandler myHandler = new PSuggestHandler();
	            xr.setContentHandler(myHandler);
	            //open connection
	           	xr.parse(new InputSource(url.openStream()));
	            w_list = myHandler.getContainer().getLisItems();
	            pss = myHandler.getContainer();            
            }
            else
            {
	            TodayNewsListHandler myHandler = new TodayNewsListHandler();
	            xr.setContentHandler(myHandler);
	            //open connection
	           	xr.parse(new InputSource(url.openStream()));
	            w_list = myHandler.getContainer().getLisItems();
	            tnls = myHandler.getContainer();
            }
        }
        catch(Exception e){
        	
        	//openOptionsDialog("login fail:"+e);
        }

        //Display: create ListView class
        show_view = (ListView)findViewById(R.id.listview);
        
        if (w_list != null)
        {
	        SimpleAdapter listitemAdapter=new SimpleAdapter(this,  
	        										w_list, 
	        										R.layout.no_listview_style,
	        										new String[]{"ItemTitle","ItemText"}, 
	        										new int[]{R.id.topTextView,R.id.bottomTextView}  
	        										);  
	        
	        show_view.setAdapter(listitemAdapter);
        }
        
        show_view.setOnItemClickListener(new OnItemClickListener() 
        {          
        	   @Override  
        	   public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,  
        	     long arg3) 
        	   {
        		   //arg2 -> reading id
                   if (classify == 4)
                   {
           		    now_pss = pss.getoneJL(arg2); 
	   				Intent intent = new Intent(todaynews_v.this, viewc.class);
	
					Bundle bundle = new Bundle(); 
					bundle.putInt("id", 2); 
					intent.putExtras(bundle); 
					
					startActivity(intent);
                	finish();
                   }
                   else
                   {
        		    now_status = tnls.getoneJL(arg2); 
	   				Intent intent = new Intent(todaynews_v.this, viewc.class);
	
					Bundle bundle = new Bundle(); 
					bundle.putInt("id", 1); 
					intent.putExtras(bundle); 
					
					startActivity(intent);
                	finish();
                   }
        	   }  
        });

        Button button01 = (Button)todaynews_v.this.findViewById(R.id.button1);
        button01.setOnClickListener(new Button.OnClickListener(){
			@Override
			public void onClick(View v){
				
                if (classify == 4)
                {
                	startActivity(new Intent(todaynews_v.this, advise.class));
                	finish();
                }
                else
                {
                	startActivity(new Intent(todaynews_v.this, todaynews.class));
                	finish();
                }
			}
		});
        

    }
    
    
}