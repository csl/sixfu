package com.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class order extends Activity {
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
	//Widgets主程式
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.order);
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
				Intent intent = new Intent(order.this, (Class<?>)activities[position * 2 + 1]);
				startActivity(intent);
			}		
		});	
	}
}
