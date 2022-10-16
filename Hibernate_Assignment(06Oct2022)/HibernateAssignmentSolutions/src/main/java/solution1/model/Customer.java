package solution1.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class Customer {
	@Id
	private long id;
	private String phone;
	private String email;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Address address;

	public Customer(long id, String phone, String email) {
		super();
		this.id = id;
		this.phone = phone;
		this.email = email;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", phone=" + phone + ", email=" + email + "]";
	}

}
