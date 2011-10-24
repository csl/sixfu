package com.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class todaynews extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todaynews);
                
        Button button01 = (Button)todaynews.this.findViewById(R.id.button1);
        Button button02 = (Button)todaynews.this.findViewById(R.id.button2);
        Button button03 = (Button)todaynews.this.findViewById(R.id.button3);
        Button button04 = (Button)todaynews.this.findViewById(R.id.button4);
        
        button01.setOnClickListener(new Button.OnClickListener(){
			@Override
			public void onClick(View v){
				startActivity(new Intent(todaynews.this, sixfoo.class));
			}
		});        
        button02.setOnClickListener(new Button.OnClickListener(){
			@Override
			public void onClick(View v)
			{
				Intent intent = new Intent(todaynews.this, todaynews_v.class);

				Bundle bundle = new Bundle(); 
				bundle.putInt("classify", 1); 
				intent.putExtras(bundle); 
				
				startActivity(intent);
				finish();
			}
		});
        button03.setOnClickListener(new Button.OnClickListener(){
			@Override
			public void onClick(View v){
				Intent intent = new Intent(todaynews.this, todaynews_v.class);

				Bundle bundle = new Bundle(); 
				bundle.putInt("classify", 2); 
				intent.putExtras(bundle); 
				
				startActivity(intent);
				finish();
			}
		});
        button04.setOnClickListener(new Button.OnClickListener(){
			@Override
			public void onClick(View v){
				Intent intent = new Intent(todaynews.this, todaynews_v.class);

				Bundle bundle = new Bundle(); 
				bundle.putInt("classify", 3); 
				intent.putExtras(bundle); 
				
				startActivity(intent);
				finish();
			}
		});
    }
}