package ssa;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class MajorMethods {

	public static void createMajor() {
		SessionFactory factory=new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Major.class)
				.buildSessionFactory();
		
		Session session=factory.getCurrentSession();
		
		try{ 
			Major mjr1 = new Major("Astronomy", 1000);
			Major mjr2 = new Major("Biology", 1150);
			Major mjr3 = new Major("Medicine", 1400);
			Major mjr4 = new Major("PingPong", 200);
			Major mjr5 = new Major("Business Ethics", 850);

			session.beginTransaction();

			session.save(mjr1);
			session.save(mjr2);
			session.save(mjr3);
			session.save(mjr4);
			session.save(mjr5);
			
			System.out.println("New Majors: " + mjr1 + mjr2 + mjr3 + mjr4 + mjr5);
		
			session.getTransaction().commit();

		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			factory.close();
		}
	}
	
	public static void updateMajor() {
		SessionFactory factory=new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Major.class)
				.buildSessionFactory();
		
		Session session=factory.getCurrentSession();
		
		try{
			//set id to show where to update
			int majorId=4;
			
			//begin transaction
			session.beginTransaction();

			Major major = session.get(Major.class, majorId);
			major.setDescription("Mathematics");
			
			session.getTransaction().commit();

			session = factory.getCurrentSession();
			session.beginTransaction();
			session.createQuery("Update Major set req_sat=600 where id=7").executeUpdate();
			
			System.out.println("Updated " + majorId + "from 'Math' to 'Mathematics' and "
					+ "the Required Sat(req_sat) of 'General Studies' to 600 from 500");
			
			session.getTransaction().commit();
			
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			factory.close();
		}
	}
	
	public static void deleteMajor() {
		SessionFactory factory=new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Major.class)
				.buildSessionFactory();
		
		Session session=factory.getCurrentSession();
		
		try{
			//set id for major to be deleted
			int majorId=11;
			//begin transaction
			session.beginTransaction();
			//query major
			Major major = session.get(Major.class, majorId);
			session.delete(major);
			
			//alternate way to delete
			session.createQuery("delete Major where id=12").executeUpdate();
			
			System.out.println("Deleting Major " + majorId + "(Ping Pong) and Major 12(Business Ethics)");
			
			session.getTransaction().commit();
			
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			factory.close();
		}
	}
	
	public static void displayMajor() {
		SessionFactory factory=new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Major.class)
				.buildSessionFactory();
		
		Session session=factory.getCurrentSession();
		
		try{
			session.beginTransaction();
			//query student select all from student
			List<Major> majors= session.createQuery("from Major").list();
			
			//display student
			display(majors);
			
//			display single student
//			students=session.createQuery("from Student s where s.first_name='Amsal'").list();
//			System.out.println("Single Record");
//			display(students);
			
			session.getTransaction().commit();
			
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			factory.close();
		
		}
	}

	private static void display(List<Major> majors) {
		for(Major mjr: majors)
			System.out.println(mjr);
	}
	
	public static void createMajor(String description, int req_sat) {
		SessionFactory factory=new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Major.class)
				.buildSessionFactory();
		
		Session session=factory.getCurrentSession();
		
		try{ 
			Major major = new Major(description, req_sat);

			session.beginTransaction();

			session.save(major);
			
			System.out.println("New Majors: " + major);
		
			session.getTransaction().commit();

		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			factory.close();
		}
	}
	
	public static void updateMajor(int majorId, String description, int req_sat) {
		SessionFactory factory=new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Major.class)
				.buildSessionFactory();
		
		Session session=factory.getCurrentSession();
		
		try{
			//set id to show where to update
						
			//begin transaction
			session.beginTransaction();

			Major major = session.get(Major.class, majorId);
			major.setDescription(description);
			major.setId(majorId);
			major.setReq_sat(req_sat);
			
			session.getTransaction().commit();
			
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			factory.close();
		}
	}
	
	public static void deleteMajor(int majorId) {
		SessionFactory factory=new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Major.class)
				.buildSessionFactory();
		
		Session session=factory.getCurrentSession();
		
		try{
			//begin transaction
			session.beginTransaction();
			//query major
			Major major = session.get(Major.class, majorId);
			session.delete(major);
			
			session.getTransaction().commit();
			
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			factory.close();
		}
	}

}
