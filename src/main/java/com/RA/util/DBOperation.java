package com.RA.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Class for all database related operation
 * 
 * @author Manish
 *
 */
public class DBOperation {
	Connection conn;
	Statement st;
	ResultSet rs;

	public DBOperation() {
		try {
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection("jdbc:h2:~/test", "admin", "admin");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * accept list id as param and return query result in response
	 * 
	 * @param id
	 * @return
	 */
	public String getItemDetails(String id) {
		try {
			st = conn.createStatement();
			rs = st.executeQuery("select * from devices where id=" + id);
			while (rs.next()) {
				return rs.getString("DEVICEID");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}
}
