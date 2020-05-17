package com.sametsafkan.hibernate.manytomany;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ManyToManyDemo {

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
			Course course1 = new Course("Spring Masterclass");
			session.beginTransaction();
			session.save(course1);
			Student student1 = new Student("Hasan", "Yilmaz", "yilmazhasan@gmail.com");
			Student student2 = new Student("Mehmet", "Yilmaz", "yilmazmehmet@gmail.com");
			course1.addStudent(student1);
			course1.addStudent(student2);
			session.save(student1);
			session.save(student2);
			session.getTransaction().commit();
		} catch (Exception e) {
			log.error("Error happened while deleting the record.", e);
		}
	}
}
