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
	//12�ӽd�Ҫ����W�٩M���ε{��Class
	private Object[] activities = {
		"�j����",				facility_4_1.class,
		"�j�㯫",				facility_4_2.class,
		"���s���I",				facility_4_3.class,
		"���L��",				facility_4_4.class,
		"�j���s",		        facility_4_5.class,
		"�W���",			    facility_4_6.class,
		"���ج����ֶ�",		    facility_4_7.class,
		"�j���S",		        facility_4_8.class,
		
	};
	//Widgets�D�{��
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.facility);
		//�إ�8�ӿ��W�٪��}�Clist
		CharSequence[] list = new CharSequence[activities.length / 2];
		for (int i = 0; i < list.length; i++) {
			list[i] = (String)activities[i * 2];
		}
		//�N8�ӿ��W�٦w�m�blistView
		ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_list_item_1, list);
		ListView listView = (ListView)findViewById(R.id.ListView01);
		listView.setAdapter(adapter);
		//���U���W�٫��V���������ε{��Class
		listView.setOnItemClickListener(new OnItemClickListener() {		
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {	
				Intent intent = new Intent(facility_4.this, (Class<?>)activities[position * 2 + 1]);
				startActivity(intent);
			}		
		});	
	}
}
