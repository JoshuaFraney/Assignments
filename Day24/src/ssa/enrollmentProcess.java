package ssa;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


class Mainline {
	
	public static db db = null;

	static void runEnrollment() throws FileNotFoundException, IOException, SQLException {
	try {
		enrollmentProcess.makeConnection();
		
		Students students = new Students();
		students.add(new Student("Adam","Zapel",1200,3.0,"Finance"));
		students.add(new Student("Graham","Krakir",500,2.5,"General Studies"));
		students.add(new Student("Ella","Vader",800,3.0,"Accounting"));
		students.add(new Student("Stanley","Kupp",1350,3.3,"Engineering"));
		students.add(new Student("Lou","Zar",950,3.0,"Education"));

		for(Student student : students) {
			Student.enrollStudent(200, "Adam","Zapel",1200,3.0);
			Student.enrollStudent(210,"Graham","Krakir",500,2.5);
			Student.enrollStudent(220,"Ella","Vader",800,3.0);
			Student.enrollStudent(230,"Stanley","Kupp",1350,3.3);
			Student.enrollStudent(240,"Lou","Zar",950,3.0);

			Student.assignMajor(200, 3);
			Student.assignMajor(210, 7);
			Student.assignMajor(220, 2);
			Student.assignMajor(230, 5);
			Student.assignMajor(240, 6);

			Student.assignClass();
		}
		
	} catch(Exception ex) {
		ex.printStackTrace();
	} finally {
		db.close();
	}
}
	public static void main(String[] args) throws FileNotFoundException, IOException, SQLException {
		(new Mainline()).runEnrollment();
	}
	}



public class enrollmentProcess {

	public static Connection myConn = null;
	public static PreparedStatement myStmt = null;
	public static Statement myStmt3 = null;
	public static  ResultSet myRs = null;
	
	public PreparedStatement pstmt = null;
	static int sect;
	static String subj;
	static int minimumSat;
	static int studentSat;
	static String major = null;
	static String reqSat = null;
	static db db = null;
	int id = -1;
	String firstName = null;
	String lastName = null;
	int sat = -1;
	double gpa = -1.0;
	String majorRequested = null;
	int majorId = -1;
	
	public static void main(String[] args) throws SQLException, FileNotFoundException, IOException {
		Student.enrollStudent(200,"Adam","Zapel",1200,3.0);
		Student.enrollStudent(210,"Graham","Krakir",500,2.5);
		Student.enrollStudent(220,"Ella","Vader",800,3.0);
		Student.enrollStudent(230,"Stanley","Kupp",1350,3.3);
		Student.enrollStudent(240,"Lou","Zarr",950,3.0);
		Student.newClass(100, 50101);
		Student.newClass(100, 60221);
		Student.newClass(200, 10103);
		Student.newClass(200, 20201);
		Student.newClass(200, 40312);
		Student.newClass(200, 30202);
		
		Student.newClass(210, 10102);
		Student.newClass(210, 50231);
		Student.newClass(210, 40311);
		Student.newClass(210, 50101);

		Student.newClass(220, 10101);
		Student.newClass(220, 10203);
		Student.newClass(220, 60351);
		Student.newClass(220, 50102);

		Student.newClass(230, 20401);
		Student.newClass(230, 10203);
		Student.newClass(230, 30202);
		Student.newClass(230, 50231);

		Student.newClass(240, 50102);
		Student.newClass(240, 60351);
		Student.newClass(240, 60353);
		Student.newClass(240, 40442);


		//Mainline.runEnrollment();
		//getData();
		//fetchData();
		dispData(); 
		//displayData(200,3);
	}
public static void display() throws SQLException, FileNotFoundException, IOException {

		System.out.println("Education System - Enrollment Process");
		System.out.println("=====================================");
		System.out.println("");
		while(myRs.next()) {
			String fName = myRs.getString("first_name");
			String lName = myRs.getString("last_name");
			double gpa = myRs.getDouble("gpa");
			int sat = myRs.getInt("sat");
			String major = myRs.getString("description");
			int reqSat = myRs.getInt("req_sat");
			int studentId = myRs.getInt("student.id");
			
//			String fName = myRs.getString("fName");
//			String lName = myRs.getString("lName");
//			double gpa = myRs.getDouble("gpa");
//			int sat = myRs.getInt("sat");
//			String major = myRs.getString("major");
//			int reqSat = myRs.getInt("reqSat");
			
			
			int majId = myRs.getInt("major_id");
			
			
			PreparedStatement myStmt4 = myConn.prepareStatement("select * from student_class_relationship where student_id = ?");
			myStmt4.setInt(1, studentId);
			ResultSet myRs4 = null;
			
			
			PreparedStatement myStmt2 = myConn.prepareStatement("select * from student_class_relationship join class on student_class_relationship.class_id = class.id "
					+ "where student_class_relationship.student_id = ?;");
			myStmt2.setInt(1, studentId);
			
		    ResultSet myRs2 = null;
		    myRs2 = myStmt2.executeQuery();
			
			System.out.printf("Enrolled %s %s as a new student. \n", fName, lName);
			System.out.printf("%s %s has an SAT score of %d \n", fName, lName, sat);
			System.out.printf("Assigned %s %s to the %s major which requires an SAT score of %d \n", fName, lName, major, reqSat);
			System.out.printf("Enrolled %s %s in the following four classes: \n", fName, lName);
			
			
			myStmt3 = myConn.createStatement();
		    
			while(myRs2.next()) {
				
				String subj = myRs2.getString("subject");
				int sect = myRs2.getInt("section");
				
				//System.out.println(majId + " " + clsId);
				int clsId = -1;
				myRs4 = myStmt4.executeQuery();
				myRs4.next();	
				clsId = myRs4.getInt("class_id");
				
	            ResultSet myRs3 = myStmt3.executeQuery("select * from major_class_relationship where major_id = '"
	                       + majId + "' and class_id = '" + clsId + "';");
			
				if(myRs3.next()) {
				System.out.printf(subj +" "+ sect + " required for major.\n");
				} else {
	                   System.out.printf(subj +" "+ sect + " elective (not required for major)\n");

				}
				
	           }
			System.out.println("");
			}
			
//			System.out.printf(subj + sect + " required for major.\n");
//			System.out.printf(subj + sect + " required for major.\n");
//			System.out.printf(subj + sect + " elective (not required for major).\n");
//			System.out.printf(subj + sect + " elective (not required for major).\n");
		
			// Tip to line up the columns
			// System.out.println("%-20s", fName + lName)
	}
	
private static void dispData() throws SQLException, FileNotFoundException, IOException {
	try{
		makeConnection();
		myStmt= myConn.prepareStatement("select * from student join major on student.major_id = major.id;");
		//myStmt.setInt(1, 100);
		myRs = myStmt.executeQuery();
		display();
	}catch(Exception ex) {
		ex.printStackTrace();
	}finally{
		close();
	}
}
//private static void displayData(int studentId, int majorId) throws SQLException {
//	try{
//		
//		makeConnection();
//		myStmt= myConn.prepareStatement("select a.id as studId, a.first_name as fName, a.last_name as lName, a.sat as sat, a.gpa as gpa, a.major_id"
//				+ " as majorId, b.id as majId, b.description as major, b.req_sat as reqSat, c.id as cId, c.subject as subject, c.section as section,"
//				+ "d.student_id as scrStud, d.class_id as scrClass from student a, major b, class c, student_class_relationship d where a.id = ? "
//				+ "and b.id =? and d.student_id = ? and d.student_id = a.id;");
//		myStmt.setInt(1, studentId);
//		myStmt.setInt(2, majorId);
//		myStmt.setInt(3, studentId);
//
//		myRs = myStmt.executeQuery();
//		display();
//	}catch(Exception ex) {
//		ex.printStackTrace();
//	}finally{
//		close();
//	}
//}
	private static void getData() throws SQLException {
		try{
			makeConnection();
			myStmt= myConn.prepareStatement("select student.first_name, student.last_name,student.gpa, student.sat, major.req_sat, major.description from student join major on student.major_id=major.id where student.id > ?");
			myStmt.setInt(1, 0);
			myRs = myStmt.executeQuery();
			
			display();

		}catch(Exception ex) {
			ex.printStackTrace();
			
		}finally{
		
			close();

		}
	}
	private static void fetchData() throws SQLException {
		
try {
			// Make Connection
			makeConnection();
			// Statement
			//myStmt = (PreparedStatement)myConn.prepareStatement("select * from student, major, class where student.id = ?");
			myStmt = myConn.prepareStatement("select table1.*,class.id from (select student.*,major_class_relationship."
					+"class_id from student join major_class_relationship on student.major_id= major_c"
					+"lass_relationship.major_id) as table1 join class on table1.class_id=class.id where major_id > ?;");
			
			// Set Parameter Values
			myStmt.setInt(1, 0);
			// Execute the Query
			myRs = myStmt.executeQuery();
			
			display();

		}catch(Exception ex) {
			ex.printStackTrace();
			
		}finally{
		
			close();
	}
}

	static void makeConnection() throws FileNotFoundException, IOException, SQLException {
		
		Properties prop = new Properties();
		prop.load(new FileInputStream("src/ssa/enrollment.properties"));
		String dburl = prop.getProperty("dburl");
		String user = prop.getProperty("user");
		String pass = prop.getProperty("password");
		myConn = (Connection)DriverManager.getConnection(dburl, user, pass);
	}

	private static void deleteData() throws SQLException {
		
		try{
			makeConnection();
			myStmt = myConn.prepareStatement("delete from student where id = ?");
			
			myStmt.setInt(1, 10);
			
			myStmt.executeUpdate();
			
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			close();
		}
	}

	public static void close() throws SQLException{
		if(myConn != null)
			myConn.close();
		if(myStmt != null)
			myStmt.close();
		if(myRs != null)
			myRs.close();
	}
		}

 class Student {
	 
	    public static Connection myConn = null;
		public static PreparedStatement myStmt = null;
		public static  ResultSet myRs = null;
		public PreparedStatement pstmt = null;
		static int minimumSat;
		static int studentSat;
		static db db = null;
		static int id = -1;
		String firstName = null;
		String lastName = null;
		int sat = -1;
		double gpa = -1.0;
		String majorRequested = null;
		static int majorId = -1;
		
		private static void makeConnection() throws FileNotFoundException, IOException, SQLException {
			
			Properties prop = new Properties();
			prop.load(new FileInputStream("src/ssa/enrollment.properties"));
			String dburl = prop.getProperty("dburl");
			String user = prop.getProperty("user");
			String pass = prop.getProperty("password");
			myConn = (Connection)DriverManager.getConnection(dburl, user, pass);
		}
		
		public static void close() throws SQLException{
			if(myConn != null)
				myConn.close();
			if(myStmt != null)
				myStmt.close();
			if(myRs != null)
				myRs.close();
		}
		
		public static void assignMajor(int studentId, int majorId) throws SQLException{
			try{
				makeConnection();
				myStmt = myConn.prepareStatement("select sat from student where id = ?");
				myStmt.setInt(1, studentId);
				myRs = myStmt.executeQuery();
				while(myRs.next()){
					studentSat = myRs.getInt("sat");
				}
				makeConnection();
				myStmt = myConn.prepareStatement("select req_sat from major where id = ?");
				myStmt.setInt(1, majorId);
				myRs = myStmt.executeQuery();
				while(myRs.next()){
					 minimumSat = myRs.getInt("req_sat");
				}
				if(studentSat >= minimumSat){
				myStmt = myConn.prepareStatement("update student set major_id = ? where id = ?");
				myStmt.setInt(1, majorId);
				myStmt.setInt(2, studentId);
				myStmt.executeUpdate();
				}
				else {
					System.out.println("Unable to enroll in the requested major due to lack of SAT requirements.");
				}
			}catch(Exception ex){
				ex.printStackTrace();
			}finally{
				close();
			}
		}
		
		public static void enrollStudent(int id, String first_name, String last_name, int sat, double gpa) throws SQLException {
			try{
				makeConnection();
				
				myStmt = myConn.prepareStatement("insert into student (id, first_name, last_name, sat, gpa) "
						+ "values (?,?,?,?,?)");
				
				myStmt.setInt(1, id);
				myStmt.setString(2, first_name);
				myStmt.setString(3, last_name);
				myStmt.setInt(4, sat);
				myStmt.setDouble(5, gpa);

				myStmt.executeUpdate();
				
			}catch(Exception ex){
				ex.printStackTrace();
			}finally{
				close();
			}
		}
		public static void newClass(int student_id, int class_id) throws SQLException, FileNotFoundException, IOException {
			try {
				makeConnection();
				myStmt = myConn.prepareStatement("insert into student_class_relationship (student_id,class_id) values (?,?);");
				myStmt.setInt(1, student_id);
				myStmt.setInt(2, class_id);
				myStmt.executeUpdate();
				} catch(SQLException ex) {
					ex.printStackTrace();
				} finally {
					close();
				}
			}
		
		public static boolean assignClass() throws SQLException {
			try {
				String sql = "select m.description as 'Major', c.Id as 'classId', "
						+ " concat(c.subject,c.section) as 'Class' "
						+ " from major m "
						+ " join major_class_relationship mc "
						+ " 	on m.id = mc.major_id "
						+ " join class c " 
						+ " 	on c.id = mc.class_id where m.id = ?";

				ResultSet rs = db.getSqlResultSet(sql, majorId);
				int idx = 0;
				ArrayList<String> classIds = new ArrayList<String>();
				while(db.myRs.next()) { 
					int classId = db.myRs.getInt("classId");
					classIds.add(String.valueOf(classId));
					idx++;
					if(idx == 2)
						break;
				}
				
				sql = "SELECT id from class where id not in (" + String.join(",", classIds) + ")";
				rs = db.getSqlResultSet(sql);
				while(rs.next()) { 
					int classId = rs.getInt(1);
					classIds.add(String.valueOf(classId));
					idx++;
					if(idx == 4)
						break;
				}
				
				sql = "INSERT student_class_relationship (student_id, class_id) values (?,?)";
				db.pstmt = db.myConn.prepareStatement(sql);
				db.pstmt.setInt(1, id);
				for(String classId : classIds) {
					db.pstmt.setInt(2, Integer.parseInt(classId));
					int recsAffected = db.pstmt.executeUpdate();
					if(recsAffected != 1) {
						throw new Exception("Class was not scheduled for Student!");
					}
				}

			} catch (Exception ex) {
				ex.printStackTrace();
			}
			return true;
		}
		public Student(String firstName, String lastName, int sat, double gpa, String majorRequested) {
			this.firstName = firstName;
			this.lastName = lastName;
			this.sat = sat;
			this.gpa = gpa;
			this.majorRequested = majorRequested;
			this.db = enrollmentProcess.db;
		}
 }

 class db {
	 
	 public static Connection myConn = null;
		public static PreparedStatement myStmt = null;
		public static  ResultSet myRs = null;
		public PreparedStatement pstmt = null;
		static int minimumSat;
		static int studentSat;
		db db = null;
		
		private static void makeConnection() throws FileNotFoundException, IOException, SQLException {
			
			Properties prop = new Properties();
			prop.load(new FileInputStream("src/ssa/enrollment.properties"));
			String dburl = prop.getProperty("dburl");
			String user = prop.getProperty("user");
			String pass = prop.getProperty("password");
			myConn = (Connection)DriverManager.getConnection(dburl, user, pass);
		}
		
		public ResultSet getSqlResultSet(String sql, Object... parms ) throws SQLException {
			if(!isConnected())
				return null;
			PreparedStatement pstmt = myConn.prepareStatement(sql);
			for(int idx = 0; idx < parms.length; idx++)
				pstmt.setObject(idx+1, parms[idx]);
			myRs = pstmt.executeQuery();
			return myRs;
		}
		public ResultSet getSqlResultSet(PreparedStatement pstmt) throws SQLException {
			if(!isConnected())
				return null;
			myRs = pstmt.executeQuery();
			return myRs;
		}
		public ResultSet getSqlResultSet(String sql) throws SQLException {
			if(!isConnected())
				return null;
			pstmt = myConn.prepareStatement(sql);
			myRs = getSqlResultSet(pstmt);
			return myRs;
		}
		private boolean isConnected() {
			if(this.myConn == null)
				return false;
			return true;
		}
		public static void close() throws SQLException{
			if(myConn != null)
				myConn.close();
			if(myStmt != null)
				myStmt.close();
			if(myRs != null)
				myRs.close();
		}
		public db() throws SQLException, FileNotFoundException, IOException {
			
			makeConnection();
		}
		
 }

	 class Students extends ArrayList<Student> {

	 }
 