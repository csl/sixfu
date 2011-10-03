package com.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class fo_1 extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fo_1);
        
        Button button01 = (Button)fo_1.this.findViewById(R.id.button1);
        button01.setOnClickListener(new Button.OnClickListener(){
			@Override
			public void onClick(View v){
				startActivity(new Intent(fo_1.this, four_1.class));
			}
		});
        
        
        
    }
}