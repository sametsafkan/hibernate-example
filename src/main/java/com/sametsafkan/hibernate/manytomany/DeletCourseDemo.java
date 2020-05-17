package com.sametsafkan.hibernate.manytomany;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DeletCourseDemo {
	public static void main(String[] args) {
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.manytomany.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.addAnnotatedClass(Review.class)
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		try (Session session = factory.getCurrentSession()) {
			session.beginTransaction();
			Course course1 = session.get(Course.class, 13); 
			session.delete(course1);
			session.getTransaction().commit();
		} catch (Exception e) {
			log.error("Error happened while deleting the record.", e);
		}
	}
}
