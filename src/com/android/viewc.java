package com.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;


public class viewc extends Activity 
{
	private TextView title;
	private TextView user;
	private TextView userid;
	private TextView content;
	
	private int id;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewc);
        
        //get data form parent class
        Bundle bunde = this.getIntent().getExtras(); 
        id = bunde.getInt("id"); 
        
        title = (TextView)findViewById(R.id.title);
        user = (TextView)findViewById(R.id.user);
        userid = (TextView)findViewById(R.id.user);
        content = (TextView)findViewById(R.id.content);
        
        if (id == 1)
        {
        	title.setText(todaynews_v.now_status.h_title);
        	user.setText("");
        	userid.setText("");
        	content.setText(todaynews_v.now_status.h_content);
        }
        else if (id == 2)
        {
        	title.setText(todaynews_v.now_pss.h_date + " " +todaynews_v.now_pss.h_title);
        	user.setText(todaynews_v.now_pss.h_user);
        	userid.setText("�ϥΪ̡G");
        	content.setText(todaynews_v.now_pss.h_content);
        }

        Button button01 = (Button)viewc.this.findViewById(R.id.b_button);
        button01.setOnClickListener(new Button.OnClickListener(){
			@Override
			public void onClick(View v){
		        if (id == 1)
		        {
		        	startActivity(new Intent(viewc.this, todaynews.class));
		        	finish();
		        }
		        else
		        {
		        	startActivity(new Intent(viewc.this, advise.class));
		        	finish();
		        }
		    }
		});

        
        
        
    }
}