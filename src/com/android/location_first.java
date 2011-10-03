package com.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;


public class location_first extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_first);
      
        Button button01 = (Button)location_first.this.findViewById(R.id.button1);
        Button button02 = (Button)location_first.this.findViewById(R.id.button2);
        Button button03 = (Button)location_first.this.findViewById(R.id.button3);
        
        
        
        button01.setOnClickListener(new Button.OnClickListener(){
			@Override
			public void onClick(View v){
				startActivity(new Intent(location_first.this, sixfoo.class));
			}
		});
        
        button02.setOnClickListener(new Button.OnClickListener(){
			@Override
			public void onClick(View v){
				startActivity(new Intent(location_first.this, facility.class));
			}
		});
        
        button03.setOnClickListener(new Button.OnClickListener(){
			@Override
			public void onClick(View v){
				startActivity(new Intent(location_first.this, location.class));
			}
		});
        
        
        
    }
}