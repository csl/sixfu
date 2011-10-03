package com.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class facility_3 extends Activity {
	//12�ӽd�Ҫ����W�٩M���ε{��Class
	private Object[] activities = {
		"Ĭ�����j�_�I",				facility_3_1.class,
		"������",				    facility_3_2.class,
		"����",				        facility_3_3.class,
		"�Ѱ����",				    facility_3_4.class,
		"�]�c�Ϭ�",		            facility_3_5.class,
		"�����ڤڻP�|�Q�j�s",			facility_3_6.class,
		
		
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
				Intent intent = new Intent(facility_3.this, (Class<?>)activities[position * 2 + 1]);
				startActivity(intent);
			}		
		});	
	}
}
