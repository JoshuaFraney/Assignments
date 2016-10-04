package ssa;

import java.util.*;
import java.sql.*;

public class Mainline {

	public static void main(String[] args) {
		System.out.println("Id" + "  " + "Student Name" + "           " +  "GPA" +  "    " +  "SAT");
		System.out.println("--" + "  " + "------------" + "           " +  "----" + "   " +  "----");
		
	    Students students = new Students();
	    // retrieve a single student with id = 1
	    Student aStudent = students.getById(1);
	    // display the student
	    System.out.println(aStudent); // displays the student data in a formatted way
	    
	    // retrieve all the students into a collection
	    //ArrayList<Student> allStudents = (ArrayList<Student>) students.getAll();
	    ArrayList<Student> allStudents = (ArrayList<Student>) students.select("select * from student where gpa between"
	    		+ " 2 and 3 order by gpa"); //retrieve all the students in the selected gpa range and ordered by gpa
	    // iterate through the collection and display each student data in a formatted way
	    for(Student student : allStudents) {
	        System.out.println(student);
	        
	        
	    }

	}

}
