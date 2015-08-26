package com.intuit.cto.fds.dash.web.shared.rpc.dto;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

public class XMLDateAdapter extends XmlAdapter<XMLGregorianCalendar, Date> {

	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

	@Override
	public XMLGregorianCalendar marshal(Date v) throws Exception {
		GregorianCalendar gCalendar = new GregorianCalendar();
		gCalendar.setTime(v);
		XMLGregorianCalendar xmlCalendar = null;
		xmlCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(gCalendar);
		return xmlCalendar;

	}

	@Override
	public Date unmarshal(XMLGregorianCalendar v) throws Exception {
		return v.toGregorianCalendar().getTime();
	}

	
	 /*  @Override
	    public String marshal(Date v) throws Exception {
	        return dateFormat.format(v);
	    }

	    @Override
	    public Date unmarshal(String v) throws Exception {
	        return dateFormat.parse(v);
	    }*/

}
