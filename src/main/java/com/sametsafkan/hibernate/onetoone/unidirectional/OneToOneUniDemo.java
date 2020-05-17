package com.sametsafkan.hibernate.onetoone.unidirectional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OneToOneUniDemo {
	
	public static void main(String[] args) {
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.onetoone.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.buildSessionFactory();
		Instructor instructor = new Instructor("Erdem", "Yilmaz", "erdemylmz@gmail.com");
		InstructorDetail instructorDetail = new InstructorDetail("erdem_yilmaz", "Photograpy");
		Instructor instructor2 = new Instructor("Hasan", "Yilmaz", "hasanylmz@gmail.com");
		InstructorDetail instructorDetail2 = new InstructorDetail("hasan_yilmaz", "Sport");
		instructor2.setInstructorDetail(instructorDetail2);
		instructor.setInstructorDetail(instructorDetail);
		try(Session session = factory.getCurrentSession()){
			session.beginTransaction();
			session.save(instructor);
			session.save(instructor2);
			session.getTransaction().commit();
		}catch (Exception e) {
			log.error("Error happened while saving the records.", e);
		}
		
		try(Session session = factory.getCurrentSession()){
			session.beginTransaction();
			Instructor tempInstructor = session.get(Instructor.class, instructor.getId());
			session.delete(tempInstructor);
			session.getTransaction().commit();
		}catch (Exception e) {
			log.error("Error happened while deleting the record.", e);
		}
	}
	
}
