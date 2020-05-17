package com.sametsafkan.hibernate.onetomany.unidirectional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OneToManyDemo {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.onetomany-uni.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.addAnnotatedClass(Review.class)
				.buildSessionFactory();
		Instructor instructor = new Instructor("Erdem", "Yilmaz", "erdemylmz@gmail.com");
		InstructorDetail instructorDetail = new InstructorDetail("erdem_yilmaz", "Photograpy");
		instructor.setInstructorDetail(instructorDetail);
		try (Session session = factory.getCurrentSession()) {
			session.beginTransaction();
			session.save(instructor);
			session.getTransaction().commit();
		} catch (Exception e) {
			log.error("Error happened while saving the records.", e);
		}

		Course course1 = new Course("Java Masterclass");
		Review review1 = new Review("Very Good!");
		Review review2 = new Review("This Course is awesome!");
		course1.addReview(review1);
		course1.addReview(review2);
		try (Session session = factory.getCurrentSession()) {
			session.beginTransaction();
			Instructor tempInstructor = session.get(Instructor.class, instructorDetail.getId());
			tempInstructor.addCourse(course1);
			session.save(course1);
			session.getTransaction().commit();
		} catch (Exception e) {
			log.error("Error happened while deleting the record.", e);
		}
	}

}
