package ssa;

import java.sql.*;
import java.util.*;

public class Students extends HashMap<Integer, Student> {
	
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

	public Student createNew() {
		Student stud = new Student();
		return stud;
	}
	
	public Students() {
		super();
		makeConnection();
	}
	
	private Student loadNew(ResultSet rs) {
		Student stud = new Student();
		try {
			stud.id = rs.getInt("id");
			stud.firstName = rs.getString("first_name");
			stud.lastName = rs.getString("last_name");
			stud.gpa = rs.getDouble("gpa");
			stud.sat = rs.getInt("sat");
			return stud;
		}catch(SQLException ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public List<Student> select(String sql) {
		ArrayList<Student> studs = new ArrayList<Student>();
		try {
			ResultSet rs = db.SqlQuery(sql);
			while(rs.next()) {
				Student stud = loadNew(rs);
				studs.add(stud);
			}
			return studs;
		} catch (Exception ex) { 
			ex.printStackTrace(); 
			}
		return null;
	}
	
	public List<Student> getAll() {
		return select("Select * from student;");
	}
	
	public Student getById(int id) {
		List<Student> studs = select("Select * from student where id = " + id);
		if(studs.isEmpty())
			return null;
		return studs.get(0);
	}
	
	public void insert(Student student) {
		String sql = String.format("Insert student (first_name, last_name, gpa, sat) values ('%s', '%s', '%f', '%d')", student.firstName,
				student.lastName, student.gpa, student.sat);
		try {
			db.SqlUpdate(sql);
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	public void update(Student student) {
		String sql = String.format("Update student set first_name = '%s', last_name = '%s', gpa = '%f', sat = '%d' where id = '%d'" , student.firstName,
				student.lastName, student.gpa, student.sat, student.id);
		try {
			db.SqlUpdate(sql);
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	public void delete(Student student) {
		String sql = String.format("Delete from student where id = '%d'", student.id);
		try {
			db.SqlUpdate(sql);
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	
}
