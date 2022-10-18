package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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
public class Student {

	@Id
	@Column(name = "student_id")
	private long studentID;
	private String name;
	private String phone;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "mapped_table",
	joinColumns = { @JoinColumn(name = "s_id") }, 
	inverseJoinColumns = { @JoinColumn(name = "c_id") })
	private List<Course> courses;

	@Override
	public String toString() {
		return "Student [studentID=" + studentID + ", name=" + name + ", phone=" + phone + "]";
	}

	public Student(long studentID, String name, String phone) {
		super();
		this.studentID = studentID;
		this.name = name;
		this.phone = phone;
		courses = new ArrayList<Course>();
	}

}
