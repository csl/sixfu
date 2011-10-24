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
	//12�ӽd�Ҫ����W�٩M���ε{��Class
	private Object[] activities = {
		"�p�p�B��",				advise_1.class,
		"�C�֦~�ڸs",			advise_2.class,
		"�Ѧ~�ڸs",				advise_3.class,
		"��Ŧ�f.�������ڸs",		advise_4.class,
		"�����ڸs",	        	advise_5.class,
		
		
	};
	//Widgets�D�{��
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.advise);
		//�إ�5�ӿ��W�٪��}�Clist
		CharSequence[] list = new CharSequence[activities.length / 2];
		for (int i = 0; i < list.length; i++) {
			list[i] = (String)activities[i * 2];
		}
		
		
		//�N5�ӿ��W�٦w�m�blistView
		ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_list_item_1, list);
		ListView listView = (ListView)findViewById(R.id.ListView01);
		listView.setAdapter(adapter);	
		
		
		//���U���W�٫��V���������ε{��Class
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
