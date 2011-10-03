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
	//12�ӽd�Ҫ����W�٩M���ε{��Class
	private Object[] activities = {
		"���q��",				facility_5_1.class,
		"�j�l����y�x��",		facility_5_2.class,
		"���ƭ��N",				facility_5_3.class,
		"�賡�ƨg�C��",			facility_5_4.class,
		"�K�s��",		        facility_5_5.class,
		"�����O��",			    facility_5_6.class,
		"OK�����g���]",			facility_5_7.class,
		"�Ϥ���(�q����)",		facility_5_8.class,
		"�j���u",		        facility_5_9.class,
		"3G�Ѫo��",		        facility_5_10.class,

		
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
				Intent intent = new Intent(facility_5.this, (Class<?>)activities[position * 2 + 1]);
				startActivity(intent);
			}		
		});	
	}
}
