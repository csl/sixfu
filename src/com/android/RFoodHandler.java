package com.android;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class RFoodHandler extends DefaultHandler
{
	//tag
	private final static int ID = 1;
	private final static int NAME = 2;
	private final static int TIME = 3;
	private final static int ADDR = 4;
	private final static int PHONE = 5;
	private final static int MENU = 6;
	
	private RFoodXMLStruct jls;
	private RFoodContainer jlcs;
	
	private int type;

	public RFoodContainer getContainer() 
	{
		return jlcs;
	}

	public RFoodXMLStruct getJListStruct() 
	{
		return jlcs.getoneJL();
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		String s = new String(ch, start, length);
		switch (type) 
		{
		case ID:
			jls.h_id = s;
			type = 0;
			break;
		case NAME:
			jls.h_name = s;
			type = 0;
			break;
		case TIME:
			jls.h_time = s;
			type = 0;
			break;
		case PHONE:
			jls.h_phone = s;
			type = 0;
			break;
		case MENU:
			jls.h_menu = s;
			type = 0;
			break;
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {

		if (localName.toLowerCase().equals("rfitem")) 
		{
			jlcs.addRXMLItem(jls);	
		}
	}

	@Override
	public void startDocument() throws SAXException 
	{
		jlcs = new RFoodContainer();
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException 
	{
		if (localName.toLowerCase().equals("rfitem")) 
		{
			jls = new RFoodXMLStruct();
			return;
		}
		else if (localName.toLowerCase().equals("id")) 
		{
			type = ID;
			return;
		}
		else if (localName.toLowerCase().equals("name")) 
		{
			type = NAME;
			return;
		}
		else if (localName.toLowerCase().equals("time")) 
		{
			type = TIME;
			return;
		}
		else if (localName.toLowerCase().equals("addr")) 
		{
			type = ADDR;
			return;
		}
		else if (localName.toLowerCase().equals("phone")) 
		{
			type = PHONE;
			return;
		}
		else if (localName.toLowerCase().equals("menu")) 
		{
			type = MENU;
			return;
		}
		type = 0;
	}

}