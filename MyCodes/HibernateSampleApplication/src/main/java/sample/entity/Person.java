package sample.entity;

import javax.persistence.Embedded;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * Inheritance Strategy:
 * ---------------------
 * SINGLE_TABLE -> "Both parent and child object(s) are stored in a single table. [Default strategy.]"
 * JOINED -> "Child table(s) are mapped to parent table by using a foreign key column and joins."
 * TABLE_PER_CLASS -> "Different unrelated tables for both parent and child objects."
 */

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {
	@Id
	private long id;
	private String name;
	@Embedded
	private ContactInfo contactInfo;
}
