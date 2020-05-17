package com.sametsafkan.hibernate.eagerlazy.lazy;

import static javax.persistence.CascadeType.DETACH;
import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.CascadeType.REFRESH;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "course")
@NoArgsConstructor
@Getter
@Setter
public class Course {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "title")
	private String title;
	
	@ManyToOne(cascade = {MERGE, PERSIST, DETACH, REFRESH})
	@JoinColumn(name = "instructor_id")
	private Instructor instructor;
	
	public Course(String title) {
		this.title = title;
	}
	
	@Override
	public String toString() {
		return "Course [title=" + title + "]";
	}
	
	
	
}
