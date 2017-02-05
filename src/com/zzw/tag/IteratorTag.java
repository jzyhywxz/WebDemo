package com.zzw.tag;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class IteratorTag extends SimpleTagSupport {
	private String collection;
	private String item;

	public String getCollection() { return collection; }
	public String getItem() { return item; }
	public void setCollection(String c) { collection=c; }
	public void setItem(String i) { item=i; }
	
	public void doTag() throws JspException, IOException {
		super.doTag();
		// 从当前jsp页面上下文获取属性
		Collection<?> items=(Collection<?>) getJspContext().getAttribute(collection);
		for(Object i : items) {
			// 给当前jsp页面上下文设置属性
			getJspContext().setAttribute(item, i);
			// 输出标签体
			getJspBody().invoke(null);
		}
	}
}
