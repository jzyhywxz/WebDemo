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
		// �ӵ�ǰjspҳ�������Ļ�ȡ����
		Collection<?> items=(Collection<?>) getJspContext().getAttribute(collection);
		for(Object i : items) {
			// ����ǰjspҳ����������������
			getJspContext().setAttribute(item, i);
			// �����ǩ��
			getJspBody().invoke(null);
		}
	}
}
