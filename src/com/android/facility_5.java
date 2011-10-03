package com.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class facility_5 extends Activity {
	//12個範例的選單名稱和應用程式Class
	private Object[] activities = {
		"採礦車",				facility_5_1.class,
		"大峽谷急流泛舟",		facility_5_2.class,
		"笑傲飛鷹",				facility_5_3.class,
		"西部瘋狂列車",			facility_5_4.class,
		"醉酒桶",		        facility_5_5.class,
		"摩天篷車",			    facility_5_6.class,
		"OK牧場射擊館",			facility_5_7.class,
		"救火隊(電玩間)",		facility_5_8.class,
		"大馬靴",		        facility_5_9.class,
		"3G老油井",		        facility_5_10.class,

		
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
				Intent intent = new Intent(facility_5.this, (Class<?>)activities[position * 2 + 1]);
				startActivity(intent);
			}		
		});	
	}
}
