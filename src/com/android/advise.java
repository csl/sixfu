package com.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class advise extends Activity {
	//12個範例的選單名稱和應用程式Class
	private Object[] activities = {
		"小小朋友",				advise_1.class,
		"青少年族群",			advise_2.class,
		"老年族群",				advise_3.class,
		"心臟病.高血壓族群",		advise_4.class,
		"素食族群",	        	advise_5.class,
		
		
	};
	//Widgets主程式
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.advise);
		//建立5個選單名稱的陣列list
		CharSequence[] list = new CharSequence[activities.length / 2];
		for (int i = 0; i < list.length; i++) {
			list[i] = (String)activities[i * 2];
		}
		
		
		//將5個選單名稱安置在listView
		ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_list_item_1, list);
		ListView listView = (ListView)findViewById(R.id.ListView01);
		listView.setAdapter(adapter);	
		
		
		//按下選單名稱指向相關的應用程式Class
		listView.setOnItemClickListener(new OnItemClickListener() {		
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) 
			{	
				Intent intent = new Intent(advise.this, todaynews_v.class);

				Bundle bundle = new Bundle(); 
				bundle.putInt("classify", 4); 
				bundle.putInt("position", position); 
				intent.putExtras(bundle); 
				
				startActivity(intent);
				finish();
			}		
		});	
	}
}
