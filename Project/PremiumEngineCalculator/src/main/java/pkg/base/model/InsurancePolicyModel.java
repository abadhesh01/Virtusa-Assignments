package pkg.base.model;

import java.util.UUID;

import lombok.Data;

@Data
public class InsurancePolicyModel {
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
}
