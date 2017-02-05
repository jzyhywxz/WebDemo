package com.zzw.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

public class LoginHelper {
	public enum LoginState {
		LOG_IN_SUCCESS,		// 登录成功
		PASSWORD_ERROR,		// 密码错误
		USER_NOT_EXIST,		// 用户不存在
		USER_IS_EMPTY,		// 用户名为空
		PASSWORD_IS_EMPTY,	// 密码为空
	}
	private static SimpleDateFormat sdf=
			new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public static LoginState login(HttpSession session, 
			String name, String password) throws SQLException {
		if(name==null || name.equals(""))
			return LoginState.USER_IS_EMPTY;
		if(password==null || password.equals(""))
			return LoginState.PASSWORD_IS_EMPTY;

		DBManager manager=DBManager.getInstance();
		Connection conn=manager.connect();
		
		try{
			Statement state=conn.createStatement();
			ResultSet result=state.executeQuery(
					"select password, online, date "+
							"from user where name='"+name+"';");
			
			if(result.next()) {
				if(result.getString(1).equals(password)) {
					session.setAttribute("name", name);
					session.setAttribute("online", result.getBoolean(2));
					session.setAttribute("date", result.getString(3));
					state.execute("update user set online=true, date='"+
							sdf.format(new Date())+"' where name='"+name+"';");
					return LoginState.LOG_IN_SUCCESS;
				}
				else
					return LoginState.PASSWORD_ERROR;
			}
			else
				return LoginState.USER_NOT_EXIST;
		} finally {
			manager.disconnect(conn);
		}
	}

}
