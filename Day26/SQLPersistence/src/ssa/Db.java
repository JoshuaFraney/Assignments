package ssa;

import java.sql.*;
import java.util.*;

public class Db {
	String url = null;
	String user = null;
	String pwd = null;
	public Connection conn = null;
	
	public Db(String url, String user, String pwd) throws SQLException {
		conn = DriverManager.getConnection(url, user, pwd);
}
	public void close() throws SQLException {
		if(conn != null) {
			conn.close();
			conn = null;
			}
	}
		public ResultSet SqlQuery(String sql) throws SQLException {
			Statement stmt = conn.createStatement();
			try {
				ResultSet rs = stmt.executeQuery(sql);
				return rs;
			} catch(SQLException ex) { 
				throw ex; 
				}
		}
		
		public int SqlUpdate(String sql) throws SQLException {
			Statement stmt = conn.createStatement();
			try {
				int changedRec = stmt.executeUpdate(sql);
				return changedRec;
			} catch(SQLException ex) { 
				throw ex; 
				}
		}
	}
