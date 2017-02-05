package com.zzw.tag;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class EmailTag extends SimpleTagSupport {
	private String from;
	private String to;
	private Date date;
	private String message;
	private static final SimpleDateFormat sdf=
			new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public String getFrom() { return from; }
	public String getTo() { return to; }
	public Date getDate() { return date; }
	public String getMessage() { return message; }
	
	public void setFrom(String f) { from=f; }
	public void setTo(String t) { to=t; }
	public void setDate(Date d) { date=d; }
	public void setMessage(String m) { message=m; }
	
	public void doTag() throws JspException, IOException {
		super.doTag();
		JspWriter out=getJspContext().getOut();
		out.write("<div style=\"padding:10px;border:1px solid;border-color:#ff7f00;color:#ff7f00\">");
		out.write("<b>FROM</b>: "+from+"<br>");
		out.write("<b>TO</b>&nbsp;&nbsp: "+to+"<br>");
		out.write("<b>DATE</b>: "+sdf.format(date)+"<br>");
		out.write("<p>"+message+"</p>");
		out.write("</div>");
	}

}
