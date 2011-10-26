package com.android;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class rfviewc extends Activity 
{
	private TextView name;
	private TextView time;
	private TextView addr;
	private TextView phone;
	private TextView menu;
	
	private int id;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rfviewc);
        
        //Get TextView form XM
        name = (TextView)findViewById(R.id.name);
        time = (TextView)findViewById(R.id.time);
        addr = (TextView)findViewById(R.id.addr);
        phone = (TextView)findViewById(R.id.phone);
        menu = (TextView)findViewById(R.id.menu);

        //Set data to TextView
        /*
       	name.setText(order.now_rfstatus.h_name);
       	time.setText(order.now_rfstatus.h_time);
       	addr.setText(order.now_rfstatus.h_addr);
       	phone.setText(order.now_rfstatus.h_phone);
       	menu.setText(order.now_rfstatus.h_menu);
        */
        Button button01 = (Button)rfviewc.this.findViewById(R.id.b_button);
        button01.setOnClickListener(new Button.OnClickListener(){
			@Override
			public void onClick(View v){
	        	startActivity(new Intent(rfviewc.this, order.class));
	        	finish();
		    }
		});

        Button button02 = (Button)rfviewc.this.findViewById(R.id.rphone);
        button02.setOnClickListener(new Button.OnClickListener(){
			@Override
			public void onClick(View v)
			{
				Intent i = new Intent( Intent.ACTION_CALL );
				//i.setData(Uri.parse("tel:" + order.now_rfstatus.h_phone));
				startActivity(i);				
		    }
		});
        
    }
}