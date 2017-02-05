package com.zzw.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBManager {
	private Properties prop;
	private static DBManager instance;
	
	public static DBManager getInstance() {
		if(instance==null) {
			synchronized(DBManager.class) {
				if(instance==null)
					instance=new DBManager();
			}
		}
		return instance;
	}
	
	private DBManager() {
		prop=new Properties();
		prop.put("driver", "com.mysql.jdbc.Driver");
		prop.put("url", "jdbc:mysql://localhost:3306/cool_dictionary");
		prop.put("user", "root");
		prop.put("password", "262623");
		prop.put("useUnicode", "true");
		prop.put("characterEncoding", "UTF-8");
		prop.put("useSSL", "true");
		
		try {
			Class.forName(prop.getProperty("driver"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public Connection connect() {
		Connection conn=null;
		String url=prop.getProperty("url");
		try {
			conn=DriverManager.getConnection(url, prop);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public void disconnect(Connection conn) {
		try {
			if(conn!=null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
