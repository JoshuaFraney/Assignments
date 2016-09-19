package ssa;

import java.util.*;
import java.sql.*;

public class Majors extends HashMap<Integer, Major>{
	private static final long serialVersionUID = 1L;
	Db db = null;
	
	private void makeConnection() {
		try{
			Properties prop = new Properties();
			prop.load(new java.io.FileInputStream("src/ssa/sqldb.properties"));
			String url = prop.getProperty("url");
			String user = prop.getProperty("user");
			String pwd = prop.getProperty("pwd");
			db = new Db(url,user,pwd);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	public Major createNew() {
		Major major = new Major();
		return major;
	}
	
	public Majors() {
		super();
		makeConnection();
	}
	
	private Major loadNew(ResultSet rs) {
		Major major = new Major();
		try {
			major.id = rs.getInt("id");
			major.description = rs.getString("description");
			major.req_sat = rs.getInt("req_sat");
			return major;
		}catch(SQLException ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public List<Major> select(String sql) {
		ArrayList<Major> majors = new ArrayList<Major>();
		try {
			ResultSet rs = db.SqlQuery(sql);
			while(rs.next()) {
				Major major = loadNew(rs);
				majors.add(major);
			}
			return majors;
		} catch (Exception ex) { 
			ex.printStackTrace(); 
			}
		return null;
	}
	
	public List<Major> getAll() {
		return select("Select * from major;");
	}
	
	public Major getById(int id) {
		List<Major> majors = select("Select * from major where id = " + id);
		if(majors.isEmpty())
			return null;
		return majors.get(0);
	}
	
	public void insert(Major major) {
		String sql = String.format("Insert major (description, sat) values ('%s', '%d')", major.description, major.req_sat);
		try {
			db.SqlUpdate(sql);
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	public void update(Major major) {
		String sql = String.format("Update major set description = '%s', req_sat = '%d' where id = '%d'" , major.description,
				major.req_sat, major.id);
		try {
			db.SqlUpdate(sql);
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	public void delete(Major major) {
		String sql = String.format("Delete from major where id = '%d'", major.id);
		try {
			db.SqlUpdate(sql);
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	
}

