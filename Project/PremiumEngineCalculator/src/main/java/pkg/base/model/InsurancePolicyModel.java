package pkg.base.model;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
