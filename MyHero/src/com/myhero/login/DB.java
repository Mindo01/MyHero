package com.myhero.login;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DB {
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://127.0.0.1/test";
	// 에러 체크
	static final String DB_ADD = "?autoReconnect=true&useSSL=false";

	// Database credentials
	static final String USER = "root";
	static final String PASS = "root";

	public static Connection getConnetion() throws ClassNotFoundException, SQLException {
		Connection conn = null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL + DB_ADD, USER, PASS);
		} catch (Exception e) {

		}
		return conn;
	}

}
