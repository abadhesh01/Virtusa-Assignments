package solution1.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Address {
	@Id
	private long id;
	@Column(name = "current_address")
	private String currentAddress;
	@Column(name = "permanent_address")
	private String permanentAddress;

	@OneToOne(mappedBy = "address", fetch = FetchType.LAZY)
	private Customer customer;

	public Address(long id, String currentAddress, String permanentAddress) {
		super();
		this.id = id;
		this.currentAddress = currentAddress;
		this.permanentAddress = permanentAddress;
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", currentAddress=" + currentAddress + ", permanentAddress=" + permanentAddress
				+ "]";
	}

}
