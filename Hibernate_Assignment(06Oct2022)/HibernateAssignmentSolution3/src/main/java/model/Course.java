package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Course {

	@Id
	@Column(name = "course_id")
	private long courseID;
	private String name;

	@ManyToMany(mappedBy = "courses", fetch = FetchType.LAZY)
	List<Student> students;

	@Override
	public String toString() {
		return "Course [courseID=" + courseID + ", name=" + name + "]";
	}

	public Course(long courseID, String name) {
		super();
		this.courseID = courseID;
		this.name = name;
		students = new ArrayList<Student>();
	}

}
