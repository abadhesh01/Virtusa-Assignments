package pkg.base.model;

import lombok.Data;

@Data
public class InsurancePolicyModel {
	private String policyName;
	private String policyType;
	private int periodOfCoverage;
	private double premiumAmount;
	private double price;
}
