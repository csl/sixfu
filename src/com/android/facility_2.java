package com.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class facility_2 extends Activity {
	//12個範例的選單名稱和應用程式Class
	private Object[] activities = {
		"騎兵隊",				facility_2_1.class,
		"沙漠風暴",				facility_2_2.class,
		"猛獸區巴士站",		    facility_2_3.class,
		"猴子行大運",		    facility_2_4.class,
		"蒸汽火車",			    facility_2_5.class,
		"小小驛馬車",			facility_2_6.class,
		"荒野小騎俠",		    facility_2_7.class,
		"駱駝騎乘",				facility_2_8.class,
		"紅鶴鳥園",				facility_2_9.class,
		"親親園",				facility_2_10.class,
		"電玩間",				facility_2_11.class,
		
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
				Intent intent = new Intent(facility_2.this, (Class<?>)activities[position * 2 + 1]);
				startActivity(intent);
			}		
		});	
	}
}
