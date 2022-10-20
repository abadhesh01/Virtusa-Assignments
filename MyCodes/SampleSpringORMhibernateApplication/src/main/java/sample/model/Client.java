package sample.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "sample_client")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client {
	@Id
	private long id;
	private String name;
	private String email;
	private String phone;
}
