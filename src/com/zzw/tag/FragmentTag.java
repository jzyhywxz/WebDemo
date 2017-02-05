package com.zzw.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class FragmentTag extends SimpleTagSupport {
	private JspFragment fragment;
	
	public JspFragment getFragment() { return fragment; }
	public void setFragment(JspFragment f) { fragment=f; }
	
	public void doTag() throws JspException, IOException {
		super.doTag();
		JspWriter out=getJspContext().getOut();
		out.println("<div style=\"padding:10px;border:1px solid;border-color:#000000\">");
		fragment.invoke(null);
		out.println("</div>");
	}

}
