package pkg.base.entity;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "insurance_policy")
@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InsurancePolicy implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5544087208731277026L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID policyId;
	private String policyName;
	private String policyType;
	private int periodOfCoverage;
	private double premiumAmount;
	private double price;
}
