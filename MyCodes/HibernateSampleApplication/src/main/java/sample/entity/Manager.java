package sample.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Manager extends Person {
	private String role;
	@Column(name = "years_of_experience")
	private int yearsOfExperience;

	public Manager(long id, String name, ContactInfo contactInfo, String role, int yearsOfExperience) {
		super(id, name, contactInfo);
		this.role = role;
		this.yearsOfExperience = yearsOfExperience;
	}

}
