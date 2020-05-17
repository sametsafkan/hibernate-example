package com.sametsafkan.hibernate.onetomany.unidirectional;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.CascadeType.DETACH;
import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.CascadeType.REFRESH;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
	
	@OneToMany(fetch = FetchType.LAZY, cascade = ALL)
	@JoinColumn(name = "course_id")
	private List<Review> reviews;
	
	public Course(String title) {
		this.title = title;
	}
	
	public void addReview(Review review) {
		if(this.reviews == null)
			this.reviews = new ArrayList<>();
		reviews.add(review);
	}
	
	@Override
	public String toString() {
		return "Course [title=" + title + "]";
	}
	
	
	
}
