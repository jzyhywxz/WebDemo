package com.zzw.tag;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.DynamicAttributes;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class DynamicTag extends SimpleTagSupport implements DynamicAttributes {
	private HashMap<String, Object> attrs=new HashMap<String, Object>();

	public void doTag() throws JspException, IOException {
		super.doTag();
		if(attrs!=null && !attrs.isEmpty()) {
			JspWriter out=getJspContext().getOut();
			out.println("<ol>");
			Set<String> keys=attrs.keySet();
			Iterator<String> iter=keys.iterator();
			while(iter.hasNext()) {
				String key=iter.next();
				out.println("<li>"+key+"="+attrs.get(key)+"</li>");
			}
			out.println("</ol>");
		}
	}
	
	@Override
	public void setDynamicAttribute(String uri, String localName, Object value) throws JspException {
		attrs.put(localName, value);
	}

}
