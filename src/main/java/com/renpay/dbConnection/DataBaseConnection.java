package com.renpay.dbConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.renpay.utils.TestInitialization;

public class DataBaseConnection extends TestInitialization {

	static Statement stmt = null;
	static Connection conn = null;
	static ResultSet rs = null;

	public DataBaseConnection() throws SQLException, ClassNotFoundException {

		String db_IP = getDBIP();
		String db_userName = getDBUserName();
		String db_Password = getDBPassword();

		Class.forName("oracle.jdbc.driver.OracleDriver");

		conn = DriverManager.getConnection("jdbc:oracle:thin:@" + db_IP + ":"+ getDBSchema(), db_userName, db_Password);
		conn.setAutoCommit(true);
		// Allocate a 'Statement' object in the Connection
		stmt = conn.createStatement();

	}

	public ResultSet executeQuery(String query) throws SQLException {
		ResultSet rset = stmt.executeQuery(query);
		return rset;
	}

	public int deleteQuery(String query) throws SQLException {
		int affectedRow = stmt.executeUpdate(query);
		return affectedRow;
	}

	public static void closeDbConnectionIfExist() {

		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (stmt != null) {

			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (conn != null) {

			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}


	private String getDBIP() {
		
		String param = null;
		param = System.getProperty("DB_Details_TM");
		if (param == null || param.contentEquals("")) {
			param = TestInitialization.getUpdatedProptiesFile().getProperty("DB_IP");
		} else {

			param = param.split(",")[0].trim();
		}
		return param;
	}
	
	
	private String getDBSchema(){
		
		
		String param = null;
		param = System.getProperty("DB_Details_TM");
		if (param == null || param.contentEquals("")) {
			param = TestInitialization.getUpdatedProptiesFile().getProperty("Db_Schema");
		} else {

			param = param.split(",")[1].trim();
		}
		return param;
	}
	
	private String getDBUserName(){
		
		
		String param = null;
		param = System.getProperty("DB_Details_TM");
		if (param == null || param.contentEquals("")) {
			param = TestInitialization.getUpdatedProptiesFile().getProperty("DB_UserName");
		} else {

			param = param.split(",")[2].trim();
		}
		return param;
	}
	
	private String getDBPassword(){
		
		String param = null;
		param = System.getProperty("DB_Details_TM");
		if (param == null || param.contentEquals("")) {
			param = TestInitialization.getUpdatedProptiesFile().getProperty("DB_Password");
		} else {

			param = param.split(",")[3].trim();
		}
		return param;
	}
	
}

