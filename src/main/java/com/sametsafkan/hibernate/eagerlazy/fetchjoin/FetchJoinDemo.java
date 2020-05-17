package com.sametsafkan.hibernate.eagerlazy.fetchjoin;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FetchJoinDemo {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.onetomany.xml")
				.addAnnotatedClass(Instructor.class).addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class).buildSessionFactory();
		int theId = 3;
		try (Session session = factory.getCurrentSession()) {
			session.beginTransaction();
			Query<Instructor> query = session.createQuery("select i from Instructor i JOIN FETCH i.course where i.id=:theId", Instructor.class);
			query.setParameter("theId", theId);
			Instructor tempInstructor = query.getSingleResult();
			System.out.println(tempInstructor);
			System.out.println(tempInstructor.getCourse());
			session.getTransaction().commit();
		} catch (Exception e) {
			log.error("Error happened while saving the records.", e);
		}
	}
}
