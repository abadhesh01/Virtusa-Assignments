package sample.entity;

import javax.persistence.Embeddable;
import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactInfo {
	private String phone;
	private String email;
}
