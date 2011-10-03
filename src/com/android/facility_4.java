package com.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class facility_4 extends Activity {
	//12個範例的選單名稱和應用程式Class
	private Object[] activities = {
		"大海怪",				facility_4_1.class,
		"大怒神",				facility_4_2.class,
		"火山歷險",				facility_4_3.class,
		"巨嘴鳥",				facility_4_4.class,
		"大海盜",		        facility_4_5.class,
		"獨木舟",			    facility_4_6.class,
		"侏儸紀失樂園",		    facility_4_7.class,
		"大海嘯",		        facility_4_8.class,
		
	};
	//Widgets主程式
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.facility);
		//建立8個選單名稱的陣列list
		CharSequence[] list = new CharSequence[activities.length / 2];
		for (int i = 0; i < list.length; i++) {
			list[i] = (String)activities[i * 2];
		}
		//將8個選單名稱安置在listView
		ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_list_item_1, list);
		ListView listView = (ListView)findViewById(R.id.ListView01);
		listView.setAdapter(adapter);
		//按下選單名稱指向相關的應用程式Class
		listView.setOnItemClickListener(new OnItemClickListener() {		
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {	
				Intent intent = new Intent(facility_4.this, (Class<?>)activities[position * 2 + 1]);
				startActivity(intent);
			}		
		});	
	}
}
