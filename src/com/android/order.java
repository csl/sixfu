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
	//12�ӽd�Ҫ����W�٩M���ε{��Class
	private Object[] activities = {
		"�����]�N�Q�u_�����",				o_1.class,
		"���ԧB�Ӯc_���ԤB�\�U",				t_1.class,
		"�n�ӥ��v_���s���\��",				fo_1.class,
		"�n�ӥ��v_���A��",				    fo_2.class,
		"����j�賡_�~����",		            fi_1.class,
		"����j�賡_�B�N�O��",			    fi_2.class,
		"����j�賡_�ְO�p�p",				fi_3.class,
		"����j�賡_�����]",		            fi_4.class,
		
	};
	//Widgets�D�{��
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.order);
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
				Intent intent = new Intent(order.this, (Class<?>)activities[position * 2 + 1]);
				startActivity(intent);
			}		
		});	
	}
}
