package com.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;


public class location extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location);
      
        Button button01 = (Button)location.this.findViewById(R.id.button1);
        ImageButton imageButton3 = (ImageButton)location.this.findViewById(R.id.imageButton3);
        ImageButton imageButton4 = (ImageButton)location.this.findViewById(R.id.imageButton4);
        ImageButton imageButton1 = (ImageButton)location.this.findViewById(R.id.imageButton1);
        ImageButton imageButton2 = (ImageButton)location.this.findViewById(R.id.imageButton2);
        ImageButton imageButton5 = (ImageButton)location.this.findViewById(R.id.imageButton5);
        
        
        button01.setOnClickListener(new Button.OnClickListener(){
			@Override
			public void onClick(View v){
				startActivity(new Intent(location.this, location_first.class));
			}
		});
        
        imageButton3.setOnClickListener(new Button.OnClickListener(){
			@Override
			public void onClick(View v){
				startActivity(new Intent(location.this, location_1.class));
			}
		});
        
        imageButton4.setOnClickListener(new Button.OnClickListener(){
			@Override
			public void onClick(View v){
				startActivity(new Intent(location.this, location_2.class));
			}
		});
        
        imageButton1.setOnClickListener(new Button.OnClickListener(){
			@Override
			public void onClick(View v){
				startActivity(new Intent(location.this, location_3.class));
			}
		});
        
        imageButton2.setOnClickListener(new Button.OnClickListener(){
			@Override
			public void onClick(View v){
				startActivity(new Intent(location.this, location_4.class));
			}
		});
        
        imageButton5.setOnClickListener(new Button.OnClickListener(){
			@Override
			public void onClick(View v){
				startActivity(new Intent(location.this, location_5.class));
			}
		});
        
    }
}