package com.ssa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssa.entity.Student;
import com.ssa.service.IStudentService;

@CrossOrigin
@Controller
@RequestMapping("/")
public class StudentController {
	
	@Autowired
	private IStudentService studentService;
	
//	@RequestMapping("/home")
//	public String home() {
//		return "home";
//	}
	
	@RequestMapping(value= "/student/{id}", method = RequestMethod.GET)
    public ResponseEntity<Student> getStudentById(@PathVariable("id") Integer id) {
        Student student = studentService.getStudentById(id);
        return new ResponseEntity<Student>(student, HttpStatus.OK);
    }
	
	@RequestMapping(value= "/student", method = RequestMethod.GET)
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> student = studentService.getAllStudents();
        return new ResponseEntity<List<Student>>(student, HttpStatus.OK);
    }
	
	
	@RequestMapping(value= "/student/delete/{id}", method = RequestMethod.GET)
    public ResponseEntity<Student> deleteStudent(@PathVariable("id") Integer id) {
        studentService.deleteStudent(id);
        return new ResponseEntity<Student>(HttpStatus.OK);
    }
	  
	// Error saying GET method not accepted
//	    @RequestMapping(value = "/student/delete/{id}", method = RequestMethod.DELETE)
//	    public ResponseEntity<Student> deleteStudent(@PathVariable("id") Integer id) {
//	        Assert.isTrue((((Student) studentService).getId() > 0), "Delete should have a valid primary key");
//
//	        int stdId = ((Student) studentService).getId();
//
//	        return deleteStudent(id);
//	    }
	
//	@RequestMapping(value="/student/delete/{id}", method = RequestMethod.DELETE)
//	@ResponseBody
//	public void deleteStudent(@PathVariable int id) {
//	  studentService.deleteStudent(id);
//	}
	
	@RequestMapping(value= "/student/add/", method = RequestMethod.GET)
    public ResponseEntity<Void> studentPerson(@RequestBody Student student) {
        boolean flag = studentService.addStudent(student);
        if(flag == false){
        	return new ResponseEntity<Void>(HttpStatus.CONFLICT);
    }
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("MyResponseHeader", "MyValue");
		return new ResponseEntity<Void>(responseHeaders, HttpStatus.CREATED);
}
}
	

