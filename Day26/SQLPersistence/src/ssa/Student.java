package ssa;

import java.util.*;
import java.sql.*;

public class Student {

	int id;
	String firstName;
	String lastName;
	double gpa;
	int sat;
	
	@Override
	public String toString() {
		String outpt = String.format("%d %-16s %10.2f %6d",
				this.id,
				this.firstName + " " + this.lastName,
				this.gpa,
				this.sat);
		return outpt;
	}
	
	Student() {
		
	}
}
