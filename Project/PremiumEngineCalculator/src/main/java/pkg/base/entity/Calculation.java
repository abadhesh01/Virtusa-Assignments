package pkg.base.entity;

import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pkg.base.model.InsurancePolicyModel;

@Table
@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Calculation {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID calculationId;
	private UUID policyId;
	private String policyName;
	private String policyType;
	private int periodOfCoverage;
	private double premiumAmount;
	private double price;
	private String vehicleType;
	private double vehiclePrice;
	private int vehicleAge;
	private String personStage;
	private String personSmokes;
	private String personDrinks;
	private String personHasSeriousDisease;
	private double finalPrice;
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Customer customer;

	public Calculation(InsurancePolicyModel model) {
		super();
		this.policyId = model.getPolicyId();
		this.policyName = model.getPolicyName();
		this.policyType = model.getPolicyType();
		this.periodOfCoverage = model.getPeriodOfCoverage();
		this.premiumAmount = model.getPremiumAmount();
		this.price = model.getPrice();
		this.vehicleType = model.getVehicleType();
		this.vehiclePrice = model.getVehiclePrice();
		this.vehicleAge = model.getVehicleAge();
		this.personStage = model.getPersonStage();
		this.personSmokes = model.getPersonSmokes();
		this.personDrinks = model.getPersonDrinks();
		this.personHasSeriousDisease = model.getPersonHasSeriousDisease();
		this.finalPrice = model.getFinalPrice();
	}
}
