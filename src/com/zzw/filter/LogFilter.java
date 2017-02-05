package com.zzw.filter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet Filter implementation class LogFilter
 */
@WebFilter(filterName="LogFilter", urlPatterns="/*")
public class LogFilter implements Filter {
	private static String logdir="F:\\workspace\\javaEE\\WebDemo\\res\\log\\";
	private PrintStream logger;
	private static final SimpleDateFormat sdf=
			new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		GregorianCalendar calendar=new GregorianCalendar();
		int year=calendar.get(Calendar.YEAR);
		int month=calendar.get(Calendar.MONTH);
		int day=calendar.get(Calendar.DAY_OF_MONTH);
		String logfile=logdir+year+"_"+month+"_"+day+".log";
		try {
			logger=new PrintStream(new FileOutputStream(logfile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		HttpServletRequest hrequest=(HttpServletRequest) request;
		logger.println("[RESQUEST] "+sdf.format(new Date()));
		logger.println("url="+hrequest.getScheme()+"://"+hrequest.getServerName()+":"+hrequest.getServerPort()+hrequest.getRequestURI());
		// pass the request along the filter chain
		chain.doFilter(request, response);
		logger.println("[RESPONSE] "+sdf.format(new Date()));
		logger.println("contentType="+response.getContentType());
		logger.println("==============================");
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
		if(logger!=null) {
			logger.flush();
			logger.close();
		}
	}

}
