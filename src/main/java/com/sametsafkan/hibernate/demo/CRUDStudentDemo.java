package com.sametsafkan.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.sametsafkan.hibernate.demo.entity.Student;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class CRUDStudentDemo {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration()
				.configure()//or we can use .configure("hibernate.cfg.xml");
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		Student studentErdem = new Student("Erdem", "Yilmaz", "erdemylmz@abc.com");
		Student studentMahmut = new Student("Mahmut", "Yilmaz", "mahmutylmz@abc.com");
		Student studentAslan = new Student("Aslan", "Yilmaz", "aslanylmz@abc.com");
		Student studentHasan = new Student("Hasan", "Yilmaz", "hasanylmz@abc.com");
		//Hibernate - Create Records
		try(Session session = factory.getCurrentSession()){
			session.beginTransaction();
			log.info("Creating students...");
			session.save(studentErdem);
			session.save(studentMahmut);
			session.save(studentAslan);
			session.save(studentHasan);
			session.getTransaction().commit();
		}catch (Exception e) {
			log.error("Error happened while saving the records.", e);
		}
		
		//Hibernate - Read Records
		try(Session session = factory.getCurrentSession()){
			session.beginTransaction();
			Student newErdem = session.get(Student.class, studentErdem.getId());
			Student newMahmut = session.get(Student.class, studentMahmut.getId());
			Student newAslan = session.get(Student.class, studentAslan.getId());
			Student newHasan = session.get(Student.class, studentHasan.getId());
			
			System.out.println(newErdem);
			System.out.println(newMahmut);
			System.out.println(newAslan);
			System.out.println(newHasan); 
			session.getTransaction().commit();
		}catch (Exception e) {
			log.error("Error happened while reading records.", e);
		}
		
		//Hibernate - Read Records With Query
		try(Session session = factory.getCurrentSession()){
			session.beginTransaction();
			List<Student> students = session.createQuery("from Student").getResultList();
			students.stream().forEach(System.out::println);
			List<Student> studentsErdem = session.createQuery("from Student s where s.firstName = \'Erdem\'").getResultList();
			studentsErdem.stream().forEach(System.out::println);
			session.getTransaction().commit();
		}catch (Exception e) {
			log.error("Error happened while reading records.", e);
		}
		
		//Hibernate - Update Record
		try(Session session = factory.getCurrentSession()){
			session.beginTransaction();
			Student tempMahmut = session.get(Student.class, studentMahmut.getId());
			tempMahmut.setEmail("mahmutyilmaz@gmail.com");
			session.getTransaction().commit();
		}catch (Exception e) {
			log.error("Error happened while updating record.", e);
		}
		
		try(Session session = factory.getCurrentSession()){
			session.beginTransaction();
			Student tempMahmut = session.get(Student.class, studentMahmut.getId());
			System.out.println(tempMahmut);
			session.getTransaction().commit();
		}catch (Exception e) {
			log.error("Error happened while reading record.", e);
		}
		
		//Hibernate - Delete Record
		try(Session session = factory.getCurrentSession()){
			session.beginTransaction();
			Student tempMahmut = session.get(Student.class, studentMahmut.getId());
			session.delete(tempMahmut);
			session.getTransaction().commit();
		}catch (Exception e) {
			log.error("Error happened while deleting record.", e);
		}
		
		try(Session session = factory.getCurrentSession()){
			session.beginTransaction();
			Student tempMahmut = session.get(Student.class, studentMahmut.getId());
			System.out.println(tempMahmut == null ? "Does not found Mahmut...":tempMahmut);
			session.getTransaction().commit();
		}catch (Exception e) {
			log.error("Error happened while reading record.", e);
		}
		
		//Hibernate - Delete Record With Query
		try(Session session = factory.getCurrentSession()){
			session.beginTransaction();
			session.createQuery("delete Student s where id=" + studentAslan.getId()).executeUpdate();
			session.getTransaction().commit();
		}catch (Exception e) {
			log.error("Error happened while deleting record.", e);
		}
		
		try(Session session = factory.getCurrentSession()){
			session.beginTransaction();
			Student tempAslan = session.get(Student.class, studentAslan.getId());
			System.out.println(tempAslan == null ? "Does not found Aslan...":tempAslan);
			session.getTransaction().commit();
		}catch (Exception e) {
			log.error("Error happened while reading record.", e);
		}
	}

}
