package com.sametsafkan.hibernate.onetoone.bidirectional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OneToOneBiDemo {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.onetoone.xml")
				.addAnnotatedClass(Instructor.class).addAnnotatedClass(InstructorDetail.class).buildSessionFactory();
		Instructor instructor = new Instructor("Erdem", "Yilmaz", "erdemylmz@gmail.com");
		InstructorDetail instructorDetail = new InstructorDetail("erdem_yilmaz", "Photograpy");
		Instructor instructor2 = new Instructor("Hasan", "Yilmaz", "hasanylmz@gmail.com");
		InstructorDetail instructorDetail2 = new InstructorDetail("hasan_yilmaz", "Sport");
		instructor2.setInstructorDetail(instructorDetail2);
		instructor.setInstructorDetail(instructorDetail);
		try (Session session = factory.getCurrentSession()) {
			session.beginTransaction();
			session.save(instructor);
			session.save(instructor2);
			session.getTransaction().commit();
		} catch (Exception e) {
			log.error("Error happened while saving the records.", e);
		}

		try (Session session = factory.getCurrentSession()) {
			session.beginTransaction();
			InstructorDetail tempInstructorDetail = session.get(InstructorDetail.class, instructorDetail.getId());
			System.out.println("Instructer Detail : " + tempInstructorDetail);
			System.out.println("The associated instructer : " + tempInstructorDetail.getInstructor());
			session.getTransaction().commit();
		} catch (Exception e) {
			log.error("Error happened while deleting the record.", e);
		}

		try (Session session = factory.getCurrentSession()) {
			session.beginTransaction();
			InstructorDetail tempInstructorDetail = session.get(InstructorDetail.class, instructorDetail.getId());
			session.delete(tempInstructorDetail);
			session.getTransaction().commit();
		} catch (Exception e) {
			log.error("Error happened while deleting the record.", e);
		}
	}

}
