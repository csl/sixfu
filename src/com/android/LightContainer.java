package com.android;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.util.Log;


public class LightContainer 
{

	private List<LightXMLStruct> jlist_items;

	//item
	public ArrayList<String> getLigtht() 
	{
		int i=0;
		
		ArrayList<String> light = new ArrayList<String>();
		
        for (LightXMLStruct item : jlist_items) 
		{	
        	if (item == null) continue;
        	
			//Log.i("VALUE", item.getWid());
			light.add(item.h_content);

		}
		return light;
	}
	
	public LightXMLStruct getoneJL()
	{
		return jlist_items.get(0);
	}
	
	public LightContainer() 
	{
		jlist_items = new ArrayList<LightXMLStruct>();
	}

	public void addRXMLItem(LightXMLStruct item) 
	{
		jlist_items.add(item);
	}
}
