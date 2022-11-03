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
}
