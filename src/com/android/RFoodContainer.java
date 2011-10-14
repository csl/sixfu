package com.android;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.util.Log;


public class RFoodContainer 
{

	private List<RFoodXMLStruct> jlist_items;

	//item
	public ArrayList<HashMap<String, Object>> getLisItems() 
	{
		int i=0;
		ArrayList<HashMap<String, Object>> listitem = new ArrayList<HashMap<String,Object>>();
		
        for (RFoodXMLStruct item : jlist_items) 
		{	
        	if (item == null) continue;
        	
			HashMap<String, Object> map = new HashMap<String, Object>();
			//Log.i("VALUE", item.getWid());
			map.put("ItemTitle", item.h_name + " - " + item.h_time);			
			map.put("ItemText", item.h_id);

			listitem.add(map);
		}
		return listitem;
	}
	
	public RFoodXMLStruct getoneJL(int index)
	{
		return jlist_items.get(index);
	}
	
	public RFoodContainer() 
	{
		jlist_items = new ArrayList<RFoodXMLStruct>();
	}

	public void addRXMLItem(RFoodXMLStruct item) 
	{
		jlist_items.add(item);
	}
}
