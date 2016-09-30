package com.ssa.dao;

import com.ssa.entity.Student;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ssa.dao.IStudentDAO;
import com.ssa.dao.StudentDAO;

@Transactional
@Repository
public class StudentDAO implements IStudentDAO {

    @Autowired
    private HibernateTemplate hibernateTemplate;

    @SuppressWarnings("unchecked")
    @Override
    public List<Student> getAllStudents() {
        String hql = "FROM Student as s ORDER BY s.id";
        return (List<Student>) hibernateTemplate.find(hql);
    }

	@Override
    public Student getStudentById(int studentId) {
        return (Student) hibernateTemplate.get(Student.class, studentId);
    }

	@Override
	public boolean addStudent(Student student) {
		 hibernateTemplate.saveOrUpdate(student);
		return false;
	}

	@Override
	public void updateStudent(Student student) {
//		String hql = "UPDATE Student set first_name=?, last_name=?, sat=?, gpa=? WHERE id=?";
//		hibernateTemplate.find(hql);
//		hql.setParameter(0,"Bob");
//		hibernateTemplate.update(student);
	}
	
//	public void updateStudent(Student student) throws IllegalArgumentException { 
//		  if (student == null) { 
//		   throw new IllegalArgumentException("Null Argument"); 
//		  } else { 
//		   HibernateTemplate temp = getHibernateTemplate();
//		   temp.saveOrUpdate(student); 
//		  } 
//		 } 
	

	@Override
	public void deleteStudent(int id) {
		hibernateTemplate.delete(hibernateTemplate.get(Student.class, id));
	}
}
	
