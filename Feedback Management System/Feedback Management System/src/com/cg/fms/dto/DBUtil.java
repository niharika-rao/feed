package com.cg.fms.dto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.cg.fms.exception.FMSException;

public class DBUtil {
	
public static Connection getConn() throws ClassNotFoundException, SQLException, FMSException{
		
		final String driver = "oracle.jdbc.OracleDriver";
		final String url ="jdbc:oracle:thin:@10.219.34.3:1521:orcl";		
		final String user="trg221";
		final String password="training221";
		Connection conn=null;
		
		Class.forName(driver);
		
		 try {
				conn = DriverManager.getConnection(url,user,password);
			} catch (SQLException e) {
				throw new FMSException("Could not get driver connection");				
			}	
		
		return conn;	
		
	}

}
