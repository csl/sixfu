package com.android;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.graphics.Paint.Style;
import android.os.Environment;
import android.util.Log;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.Projection;


public class MyOverLay  extends Overlay {

	/**
	 * Stored as global instances as one time initialization is enough
	 */
    private Bitmap mBubbleIcon, mShadowIcon;
    
    private Bitmap mNowIcon;
    
    private MyGoogleMap mLocationViewers;
    
    private Paint	mInnerPaint, mBorderPaint, mTextPaint;
    
    private List<GeoPoint> gp;
	
    private int infoWindowOffsetX;
    private int infoWindowOffsetY;
    
    private boolean showWinInfo;
    
    private boolean ReadyShowRange;

    private boolean ShowTracker;    
    
    private ArrayList<GeoPoint> tracker; 

    private int mRadius=6;
    
    private MapLocation mSelectedMapLocation;  
	/**
	 * It is used to track the visibility of information window and clicked location is known location or not 
	 * of the currently selected Map Location
	 */
    
  //�غc�l, ��l��
	public MyOverLay(MyGoogleMap mLocationViewers) {
		
		this.mLocationViewers = mLocationViewers;
		
		mBubbleIcon = BitmapFactory.decodeResource(mLocationViewers.getResources(),R.drawable.bubble);
		mShadowIcon = BitmapFactory.decodeResource(mLocationViewers.getResources(),R.drawable.shadow);
		mNowIcon = BitmapFactory.decodeResource(mLocationViewers.getResources(),R.drawable.mappin_blue);
		
		gp = new ArrayList<GeoPoint>();
		tracker = new ArrayList<GeoPoint>();
		ShowTracker = false;
	}
	
	public void setTracker(boolean st)
	{
	  ShowTracker = st;
	}

  public boolean addGeoPoint(GeoPoint p)
  {
      if (p != null)
      {
        tracker.add(p);
        return true;
      }     
      
      return false;
  }

  public GeoPoint getGeoPoint(int index)
  {
      return tracker.get(index);
  }

  private MapLocation getHitMapLocation(MapView	mapView, GeoPoint	tapPoint) 
  {
  	MapLocation hitMapLocation = null;
		
  	RectF hitTestRecr = new RectF();
	Point screenCoords = new Point();
  	Iterator<MapLocation> iterator = mLocationViewers.getMapLocations().iterator();
  	while(iterator.hasNext()) {
  		MapLocation testLocation = iterator.next();
  		
  		/**
  		 * This is used to translate the map's lat/long coordinates to screen's coordinates
  		 */
  		mapView.getProjection().toPixels(testLocation.getPoint(), screenCoords);

	    	// Create a testing Rectangle with the size and coordinates of our icon
	    	// Set the testing Rectangle with the size and coordinates of our on screen icon
  		hitTestRecr.set(-mBubbleIcon.getWidth()/2,-mBubbleIcon.getHeight(),mBubbleIcon.getWidth()/2,0);
  		hitTestRecr.offset(screenCoords.x,screenCoords.y);

	    	//  At last test for a match between our Rectangle and the location clicked by the user
  		mapView.getProjection().toPixels(tapPoint, screenCoords);
  		
  		if (hitTestRecr.contains(screenCoords.x,screenCoords.y)) {
  			hitMapLocation = testLocation;
  			break;
  		}
  	}
  	
  	//  Finally clear the new MouseSelection as its process finished
  	tapPoint = null;
  	
  	return hitMapLocation; 
  }
  

	@Override
  //�B�zdraw map�W���Ϯ�
	public boolean onTap(GeoPoint p, MapView mapView)  
	{
		return true;
	}
	
  @Override
  //draw method  
	public void draw(Canvas canvas, MapView	mapView, boolean shadow) 
  {
      //�e�{�b��m
      drawNowGeoMap(canvas, mapView, shadow);
      //�e�a�Ϯy��
  	  drawMapLocations(canvas, mapView, shadow);
      //�e��T  	  
	  drawInfoWindow(canvas, mapView, shadow);

  }
    
  //�M��GPS Range�y��
  public void clearRange()
  {
    ReadyShowRange = false;
    gp.clear();
  }

    private void drawNowGeoMap(Canvas canvas, MapView mapView, boolean shadow) 
    {
      //��ܲ{�b��m
      if (mLocationViewers.nowGeoPoint != null)
      {
        Paint paint = new Paint();
        Point myScreenCoords = new Point();
        
        //��ܲ{�b��m  
        mapView.getProjection().toPixels(mLocationViewers.nowGeoPoint, myScreenCoords);
        paint.setStrokeWidth(1);
        paint.setARGB(255, 255, 0, 0);
        paint.setStyle(Paint.Style.STROKE);
  
        canvas.drawBitmap(mNowIcon, myScreenCoords.x, myScreenCoords.y, paint);
        canvas.drawText("�{�b��m", myScreenCoords.x, myScreenCoords.y, paint);
      }
    }
    
    private void drawMapLocations(Canvas canvas, MapView	mapView, boolean shadow) 
    {
		Iterator<MapLocation> iterator = mLocationViewers.getMapLocations().iterator();
		Point screenCoords = new Point();
		
		
    	while(iterator.hasNext()) {	   
    		MapLocation location = iterator.next();
    		mapView.getProjection().toPixels(location.getPoint(), screenCoords);
			
	    	if (shadow) {
	    		// Offset the shadow in the y axis as it is angled, so the base is at x=0
	    		canvas.drawBitmap(mShadowIcon, screenCoords.x, screenCoords.y - mShadowIcon.getHeight(),null);
	    	} else {
    			canvas.drawBitmap(mBubbleIcon, screenCoords.x - mBubbleIcon.getWidth()/2, screenCoords.y - mBubbleIcon.getHeight(),null);
	    	}
    	}
    	
    }
    
    private void drawInfoWindow(Canvas canvas, MapView	mapView, boolean shadow) {
    	
    		if ( shadow) {
    			//  Skip painting a shadow
    		} else {
    		  	Iterator<MapLocation> iterator = mLocationViewers.getMapLocations().iterator();
    		  	while(iterator.hasNext()) {
    		  		MapLocation testLocation = iterator.next();
    			
					//  First we need to determine the screen coordinates of the selected MapLocation
					Point selDestinationOffset = new Point();
					mapView.getProjection().toPixels(testLocation.getPoint(), selDestinationOffset);
			    	
			    	//  Setup the info window
					int INFO_WINDOW_WIDTH = 175;
					int INFO_WINDOW_HEIGHT = 40;
					
					RectF infoWindowRect = new RectF(0,0,INFO_WINDOW_WIDTH,INFO_WINDOW_HEIGHT);
					
					infoWindowOffsetX = selDestinationOffset.x-INFO_WINDOW_WIDTH/2;
					infoWindowOffsetY = selDestinationOffset.y-INFO_WINDOW_HEIGHT-mBubbleIcon.getHeight();
					infoWindowRect.offset(infoWindowOffsetX, infoWindowOffsetY);
	
					//  Drawing the inner info window
					canvas.drawRoundRect(infoWindowRect, 10, 10, getmInnerPaint());
					
					//  Drawing the border for info window
					canvas.drawRoundRect(infoWindowRect, 10, 10, getmBorderPaint());
						
					//  Draw the MapLocation's name
					int TEXT_OFFSET_X = 10;
					int TEXT_OFFSET_Y = 15;
	
					int TEXT1_OFFSET_X = 20;
					int TEXT1_OFFSET_Y = 25;
	
					canvas.drawText(testLocation.getName(), infoWindowOffsetX+TEXT_OFFSET_X,infoWindowOffsetY+TEXT_OFFSET_Y, getmTextPaint());
					canvas.drawText("�����]�I", infoWindowOffsetX+TEXT1_OFFSET_X,infoWindowOffsetY+TEXT1_OFFSET_Y, getmTextPaint());
					showWinInfo = true;
    		  	}
    	}
    }
    
    private void drawTracker(Canvas canvas, MapView mapView, boolean shadow)
    {
      if (tracker.size() == 0 ) return;
      else if (ShowTracker == false) return;
      
      if (shadow == false) 
      {      
        Projection projection = mapView.getProjection();
        
        Paint paint = new Paint(); 
        paint.setAntiAlias(true); 
        paint.setColor(Color.BLACK);
        
        //Start
        Point spoint = new Point(); 
        projection.toPixels(tracker.get(0), spoint);
        RectF startoval = new RectF(spoint.x - mRadius, spoint.y - mRadius,  
                               spoint.x + mRadius, spoint.y + mRadius); 
        canvas.drawOval(startoval, paint);
        
        //middle ~ end
          for (int i=1; i<tracker.size()-1; i++)
          {
            Point srcpoint = new Point(); 
            projection.toPixels(tracker.get(i), srcpoint);
            Point dstpoint = new Point(); 
            projection.toPixels(tracker.get(i+1), dstpoint);
  
            paint.setStrokeWidth(5);
            paint.setAlpha(120);
            canvas.drawLine(srcpoint.x, srcpoint.y, dstpoint.x, dstpoint.y, paint);
          }
        
          //EndPoint
          Point endpoint = new Point(); 
          projection.toPixels(tracker.get(tracker.size()-1), endpoint);
          RectF endoval = new RectF(endpoint.x - mRadius, endpoint.y - mRadius,  
                            endpoint.x + mRadius, endpoint.y + mRadius); 
          paint.setAlpha(255);
          canvas.drawOval(endoval, paint);
        }      
    }

	public Paint getmInnerPaint() {
		if ( mInnerPaint == null) {
			mInnerPaint = new Paint();
			mInnerPaint.setARGB(225, 50, 50, 50); //inner color
			mInnerPaint.setAntiAlias(true);
		}
		return mInnerPaint;
	}

	public Paint getmBorderPaint() {
		if ( mBorderPaint == null) {
			mBorderPaint = new Paint();
			mBorderPaint.setARGB(255, 255, 255, 255);
			mBorderPaint.setAntiAlias(true);
			mBorderPaint.setStyle(Style.STROKE);
			mBorderPaint.setStrokeWidth(2);
		}
		return mBorderPaint;
	}

	public Paint getmTextPaint() {
		if ( mTextPaint == null) {
			mTextPaint = new Paint();
			mTextPaint.setARGB(255, 255, 255, 255);
			mTextPaint.setAntiAlias(true);
		}
		return mTextPaint;
	}

	//�]�wGPS Range�y��
	public void SetPoint(GeoPoint G1, GeoPoint G2, GeoPoint G3, GeoPoint G4)
	{
    gp.add(G1);
    gp.add(G2);
    gp.add(G3);
    gp.add(G4);
    
    ReadyShowRange = true;
	}
}