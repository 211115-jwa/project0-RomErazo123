package com.bicycle.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

//import com.bicycle.utils.ConnectionUtil;


public class ConnectionUtil {

	private static ConnectionUtil connUtil;
	private static Properties databaseProps;
	
	private ConnectionUtil() {
		databaseProps = new Properties();
		try {
			
			InputStream propertiesFileStream = ConnectionUtil.class.getClassLoader().getResourceAsStream(
					"database.properties");
			databaseProps.load(propertiesFileStream);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static synchronized ConnectionUtil getConnectionUtil() {
		if(connUtil == null)
			connUtil = new ConnectionUtil();
		return connUtil;
	}
	
	public Connection getConnection() {
		Connection conn = null;
		
		try {
			Class.forName(databaseProps.getProperty("drv"));
			conn = DriverManager.getConnection(
					databaseProps.getProperty("url"),
					databaseProps.getProperty("usr"),
					databaseProps.getProperty("psw"));
			
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return conn;
	}

}
