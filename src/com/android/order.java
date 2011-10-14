package com.android;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class order extends Activity 
{
	//12個範例的選單名稱和應用程式Class
	private Object[] activities = {
		"中央魔術噴泉_金鳳樓",				o_1.class,
		"阿拉伯皇宮_阿拉丁餐廳",				t_1.class,
		"南太平洋_火山用餐區",				fo_1.class,
		"南太平洋_海鮮屋",				    fo_2.class,
		"美國大西部_漢堡店",		            fi_1.class,
		"美國大西部_冰淇淋店",			    fi_2.class,
		"美國大西部_福記小廚",				fi_3.class,
		"美國大西部_牛排館",		            fi_4.class,
		
	};
	
	static public String TAG = "todaynews";

	//XML use	
	private ArrayList<HashMap<String, Object>> w_list;

	private ListView show_view;
	private TextView showmsg;
	
    private RFoodContainer rfs;
    public static RFoodXMLStruct now_rfstatus;
    private String szToday;
    
    private int classify;
    private int position;

    
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.order);
		
    	String loginurl  = (String) this.getResources().getText(R.string.url);
        String uriAPI = loginurl + "getRF.php";        	

        URL url = null;

        try{
            url = new URL(uriAPI);
            SAXParserFactory spf = SAXParserFactory.newInstance();
            SAXParser sp = spf.newSAXParser();

            XMLReader xr = sp.getXMLReader();
            //Using handler for xml
            RFoodHandler myHandler = new RFoodHandler();
            xr.setContentHandler(myHandler);
            //open connection
           	xr.parse(new InputSource(url.openStream()));
            w_list = myHandler.getContainer().getLisItems();
            rfs = myHandler.getContainer();
        }
        catch(Exception e){
        	
        	//openOptionsDialog("login fail:"+e);
        }

        //Display: create ListView class
        show_view = (ListView)findViewById(R.id.listview);
        
        SimpleAdapter listitemAdapter=new SimpleAdapter(this,  
        										w_list, 
        										R.layout.no_listview_style,
        										new String[]{"ItemTitle","ItemText"}, 
        										new int[]{R.id.topTextView,R.id.bottomTextView}  
        										);  
        
        show_view.setAdapter(listitemAdapter);              
        show_view.setOnItemClickListener(new OnItemClickListener() 
        {          
        	   @Override  
        	   public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,  
        	     long arg3) 
        	   {
        		    now_rfstatus = rfs.getoneJL(arg2); 
	   				Intent intent = new Intent(order.this, rfviewc.class);
	
					Bundle bundle = new Bundle(); 
					bundle.putInt("id", 1); 
					intent.putExtras(bundle); 
					
					startActivity(intent);
                	finish();
        	   }  
        });
	}
}
