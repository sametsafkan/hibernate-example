package com.sametsafkan.hibernate.onetomany.bidirectional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OneToManyDemo {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.onetomany-bi.xml")
				.addAnnotatedClass(Instructor.class).addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class).buildSessionFactory();
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
		Course course2 = new Course("Spring Masterclass");
		try (Session session = factory.getCurrentSession()) {
			session.beginTransaction();
			Instructor tempInstructor = session.get(Instructor.class, instructorDetail.getId());
			tempInstructor.addCourse(course1);
			tempInstructor.addCourse(course2);
			session.save(course1);
			session.save(course2);
			session.getTransaction().commit();
		} catch (Exception e) {
			log.error("Error happened while deleting the record.", e);
		}

		try (Session session = factory.getCurrentSession()) {
			session.beginTransaction();
			Instructor tempInstructor = session.get(Instructor.class, instructor.getId());
			tempInstructor.getCourse().stream().forEach(System.out::println);
			session.getTransaction().commit();
		} catch (Exception e) {
			log.error("Error happened while deleting the record.", e);
		}
		
		try (Session session = factory.getCurrentSession()) {
			session.beginTransaction();
			Course tempCourse = session.get(Course.class, course1.getId());
			System.out.println(tempCourse.getInstructor());
			session.getTransaction().commit();
		} catch (Exception e) {
			log.error("Error happened while deleting the record.", e);
		}

	}

}
