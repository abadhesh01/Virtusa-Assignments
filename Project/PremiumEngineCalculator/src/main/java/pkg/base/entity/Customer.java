package pkg.base.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table
@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
	@Id
	private String username;
	private String password;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "customer")
	private List<Calculation> calculations;

	public void addNewCalculation(Calculation calculation) {
		calculations.add(calculation);
	}
}
