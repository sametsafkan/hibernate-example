package com.sametsafkan.hibernate.eagerlazy.eager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EagerDemo {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.onetomany.xml")
				.addAnnotatedClass(Instructor.class).addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class).buildSessionFactory();
		int theId = 3;
		try (Session session = factory.getCurrentSession()) {
			session.beginTransaction();
			Instructor tempInstructor = session.get(Instructor.class, theId);
			System.out.println(tempInstructor);
			System.out.println(tempInstructor.getCourse());
			session.getTransaction().commit();
		} catch (Exception e) {
			log.error("Error happened while saving the records.", e);
		}
	}
}
