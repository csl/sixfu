package com.android;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class location_2 extends Activity {
	private SampleView mView;
    private Bitmap mBitmap;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.main);
        mBitmap = BitmapFactory.decodeResource(getResources(), 
                R.drawable.arabia1);        
        mView = new SampleView(this);
        setContentView(mView);
    }
    //View.onDraw()��k
    public class SampleView extends View {
        private Paint mPaint;
        private float imageX = 0f;
        private float imageY = 0f;
        //
        public SampleView(Context context) {
            super(context);
            mPaint = new Paint();
        }
        //onDraw() callback��k
        protected void onDraw(Canvas canvas) {
            canvas.drawColor(Color.BLACK);
            mPaint.setAntiAlias(true);
            mPaint.setColor(Color.BLUE);
            
            //�e�����
            canvas.drawBitmap(mBitmap, 
                    imageX - mBitmap.getWidth() / 2, 
                    imageY - mBitmap.getHeight() / 2, 
                    mPaint);
        }
        //���Ĳ������ť�\��
        public boolean onTouchEvent(MotionEvent event) {
            if(event.getAction() == MotionEvent.ACTION_DOWN){
                imageX = event.getX();
                imageY = event.getY();
            }
            else if(event.getAction() == MotionEvent.ACTION_MOVE){
                imageX = event.getX();
                imageY = event.getY();
            }
            else if(event.getAction() == MotionEvent.ACTION_UP){
                imageX = event.getX();
                imageY = event.getY();
            }
            //�A�yø������
            invalidate();        
            return true;
        }
        
    }
}