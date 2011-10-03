package com.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class fi_3 extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fi_3);
        
        Button button01 = (Button)fi_3.this.findViewById(R.id.button1);
        button01.setOnClickListener(new Button.OnClickListener(){
			@Override
			public void onClick(View v){
				startActivity(new Intent(fi_3.this, five_3.class));
			}
		});
        
        
        
    }
}