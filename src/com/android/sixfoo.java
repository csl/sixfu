package com.android;



import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;




public class sixfoo extends Activity 
{
    /** Called when the activity is first created. */
	//private static final int SHOW_EDITOR = 0;
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		//設定Button01按鈕的操作
		Button button01 = (Button)sixfoo.this.findViewById(R.id.button1);
		Button button03 = (Button)sixfoo.this.findViewById(R.id.button3);
		Button button04 = (Button)sixfoo.this.findViewById(R.id.button4);
		Button button05 = (Button)sixfoo.this.findViewById(R.id.button5);
		Button button06 = (Button)sixfoo.this.findViewById(R.id.button6);
		Button button07 = (Button)sixfoo.this.findViewById(R.id.button7);
		
		button01.setOnClickListener(new Button.OnClickListener(){
			@Override
			public void onClick(View v){
				
				startActivity(new Intent(sixfoo.this, todaynews.class));
			}
		});
		button03.setOnClickListener(new Button.OnClickListener(){
			@Override
			public void onClick(View v){
				startActivity(new Intent(sixfoo.this, global.class));
			}
		});
		button04.setOnClickListener(new Button.OnClickListener(){
			@Override
			public void onClick(View v){
				startActivity(new Intent(sixfoo.this, location_first.class));
			}
		});
		button05.setOnClickListener(new Button.OnClickListener(){
			@Override
			public void onClick(View v){
				startActivity(new Intent(sixfoo.this, advise.class));
			}
		});
		button06.setOnClickListener(new Button.OnClickListener(){
			@Override
			public void onClick(View v){
				startActivity(new Intent(sixfoo.this, order.class));
			}
		});
		button07.setOnClickListener(new Button.OnClickListener(){
			@Override
			public void onClick(View v){
				startActivity(new Intent(sixfoo.this, emergency.class));
			}
		});		
	
	}
}