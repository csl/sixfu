package com.android;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.util.Log;


public class PSuggestContainer 
{

	private List<PSuggestXMLStruct> jlist_items;

	//item
	public ArrayList<HashMap<String, Object>> getLisItems() 
	{
		int i=0;
		ArrayList<HashMap<String, Object>> listitem = new ArrayList<HashMap<String,Object>>();
		
        for (PSuggestXMLStruct item : jlist_items) 
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
	
	public PSuggestXMLStruct getoneJL(int index)
	{
		return jlist_items.get(index);
	}
	
	public PSuggestContainer() 
	{
		jlist_items = new ArrayList<PSuggestXMLStruct>();
	}

	public void addRXMLItem(PSuggestXMLStruct item) 
	{
		jlist_items.add(item);
	}
}
