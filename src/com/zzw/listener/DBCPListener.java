package com.zzw.listener;

import java.util.Enumeration;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.zzw.util.DBCPManager;

/**
 * Application Lifecycle Listener implementation class DBCPListener
 *
 */
@WebListener
public class DBCPListener implements ServletContextListener {

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    	ServletContext application=sce.getServletContext();
    	Properties prop=new Properties();
    	Enumeration<String> names=application.getInitParameterNames();
    	while(names.hasMoreElements()) {
    		String name=names.nextElement();
    		prop.put(name, application.getInitParameter(name));
    	}
    	DBCPManager manager=DBCPManager.getInstance(prop);
    	application.setAttribute("manager", manager);
    	System.out.println("11111");
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    	ServletContext application=sce.getServletContext();
    	application.removeAttribute("dbcpmanager");
    }
	
}
