package com.android;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.util.Log;


public class TodayNewsListContainer 
{

	private List<TodayNewsXMLStruct> jlist_items;

	//item
	public ArrayList<HashMap<String, Object>> getLisItems() 
	{
		int i=0;
		ArrayList<HashMap<String, Object>> listitem = new ArrayList<HashMap<String,Object>>();
		
        for (TodayNewsXMLStruct item : jlist_items) 
		{	
        	if (item == null) continue;
        	
			HashMap<String, Object> map = new HashMap<String, Object>();
			//Log.i("VALUE", item.getWid());
			map.put("ItemTitle", item.h_date + " - " + item.h_title);			
			map.put("ItemText", item.h_id);

			listitem.add(map);
		}
		return listitem;
	}
	
	public TodayNewsXMLStruct getoneJL()
	{
		return jlist_items.get(0);
	}
	
	public TodayNewsListContainer() 
	{
		jlist_items = new ArrayList<TodayNewsXMLStruct>();
	}

	public void addRXMLItem(TodayNewsXMLStruct item) 
	{
		jlist_items.add(item);
	}
}
