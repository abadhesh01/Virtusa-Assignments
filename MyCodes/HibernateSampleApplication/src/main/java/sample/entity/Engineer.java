package sample.entity;

import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Engineer extends Person {
	private String degree;
	private String branch;

	public Engineer(long id, String name, ContactInfo contactInfo, String degree, String branch) {
		super(id, name, contactInfo);
		this.degree = degree;
		this.branch = branch;
	}
}
