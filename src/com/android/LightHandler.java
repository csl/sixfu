package com.android;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class LightHandler extends DefaultHandler
{
	//tag
	private final static int ID = 1;
	private final static int DATE = 2;
	private final static int CONTENT = 3;
	
	private LightXMLStruct jls;
	private LightContainer jlcs;
	
	private int type;

	public LightContainer getContainer() 
	{
		return jlcs;
	}

	public LightXMLStruct getJListStruct() 
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
		case DATE:
			jls.h_date = s;
			type = 0;
			break;
		case CONTENT:
			jls.h_content = s;
			type = 0;
			break;
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {

		if (localName.toLowerCase().equals("litem")) 
		{
			jlcs.addRXMLItem(jls);	
		}
	}

	@Override
	public void startDocument() throws SAXException 
	{
		jlcs = new LightContainer();
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException 
	{
		if (localName.toLowerCase().equals("litem")) 
		{
			jls = new LightXMLStruct();
			return;
		}
		else if (localName.toLowerCase().equals("id")) 
		{
			type = ID;
			return;
		}
		else if (localName.toLowerCase().equals("date")) 
		{
			type = DATE;
			return;
		}
		else if (localName.toLowerCase().equals("content")) 
		{
			type = CONTENT;
			return;
		}
		type = 0;
	}

}