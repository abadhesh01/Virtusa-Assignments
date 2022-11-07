<%@page import="pkg.base.entity.Calculation"%>
<%@page import="java.util.ArrayList"%>
<%@page import="pkg.base.entity.InsurancePolicy"%>
<%@page import="java.util.List"%>
<%@page import="org.apache.catalina.tribes.ChannelSender"%>
<%@page import="javax.persistence.Table"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${userType} Dashboard</title>
<style type="text/css">
a {
	text-decoration: none;
}

#tableStyle {
	font-family: sans-serif;
	border-collapse: separate;
	width: 100%
}

#tableStyle td, #tableStyle th {
	border: 1px solid black;
	text-align: center;
	padding: 8px;
}

#tableStyle th {
	padding-top: 12px;
	padding-bottom: 12px;
	text-align: center;
	background-color: ${headingColor};
    color:white;
}

#tableStyle tr:nth-child(odd) {
	background-color: #7DC4F0;
}

#tableStyle tr:nth-child(even) {
	background-color: #E2F07D;
}

#tableStyle2 {
	font-family: sans-serif;
	border-collapse: separate;
	width: 100%
}

#tableStyle2 td, #tableStyle2 th {
	border: 1px solid black;
	text-align: left;
	padding: 8px;
}

#tableStyle2 th {
	padding-top: 12px;
	padding-bottom: 12px;
	text-align: center;
	background-color: ${headingColor};
    color:white;
}

#tableStyle2 tr:nth-child(odd) {
	background-color: #7DC4F0;
}

#tableStyle2 tr:nth-child(even) {
	background-color: #E2F07D;
}
</style>
</head>
<body style="background-color: #C7CFC9">

	<%
	response.addHeader("Pragma", "no-cache");
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
	response.addHeader("Cache-Control", "pre-check=0, post-check=0");
	response.setDateHeader("Expires", 0);
	%>

	<section id="dashboardHeanding"
		style="background-color: ${headingColor}; color:white; text-align: center; font-size: 50px">
		<div id="heading" style="font-size: 50px; text-align: center">
			<strong>${userType} Dashboard</strong>
		</div>
		<div style="font-size: 20px">
			<table style="border: 1; width: 100%">
				<tr>
					<td align="left"><strong>${userName}</strong></td>
					<td align="right"><form:form action="${onLogout}"
							method="post" modelAttribute="login">
							<form:button>
								<strong style="color: ${headingColor}">Logout</strong>
							</form:button>
						</form:form></td>
				</tr>
			</table>
		</div>
	</section>

	<%
	String customerBaseURL = "http://localhost:9090/PremiumEngineCalculator/home/customer";
	String adminBaseURL = "http://localhost:9090/PremiumEngineCalculator/home/admin";
	%>

	<section>
		<div>
			<nav style="background-color: ${headingColor};">
				<ul>

					<%
					if (session.getAttribute("dashboardRequest").equals("admin")) {
					%>
					<li style="display: inline; float: center; padding: 8px;"><a
						href="<%=adminBaseURL%>/showAllPolicies" style="color: white;"><strong>Show
								all policies/Refresh</strong></a></li>
					<li style="display: inline; float: center; padding: 8px;"><a
						href="<%=adminBaseURL%>/addNewPolicy" style="color: white;"><strong>Add
								new policy</strong></a></li>
					<%
					}
					%>

					<%
					if (session.getAttribute("dashboardRequest").equals("customer")) {
					%>
					<li style="display: inline; float: center; padding: 8px;"><a
						href="<%=customerBaseURL%>/showAllPolicies" style="color: white;"><strong>Show
								all policies/Refresh</strong></a></li>
					<li style="display: inline; float: center; padding: 8px;"><a
						href="<%=customerBaseURL%>/showAllCalculations"
						style="color: white;"><strong>Show all calculations</strong></a></li>
					<%
					}
					%>

				</ul>
			</nav>
		</div>
	</section>

	<section>

		<div style="text-align: center; font-size: 20px">
			<strong style="color: ${dashboardMessageBarColor}">${dashboardMessageBar}</strong>
		</div>

		<%
		if (session.getAttribute("dashboardRequest") != null && session.getAttribute("adminDashboardView") != null
				&& session.getAttribute("customerDashboardView") != null
				&& session.getAttribute("dashboardRequest").equals("admin")
				&& session.getAttribute("adminDashboardView").equals("default")) {
		%>

		<div style="text-align: center">
			<strong style="font-size: 40px; color:${headingColor}">Policy
				List</strong>
		</div>
		<div>
			<table id="tableStyle">
				<tr>
					<th>Policy Name</th>
					<th>Policy Type</th>
					<th>Period of Coverage</th>
					<th>Premium Amount</th>
					<th>Price</th>
					<th>Operations</th>
				</tr>
				<%
				List<InsurancePolicy> insurancePolicies = (List<InsurancePolicy>) session.getAttribute("insurancePolicies");
				for (InsurancePolicy policy : insurancePolicies) {
				%>
				<tr>
					<td><%=policy.getPolicyName()%></td>
					<td><%=policy.getPolicyType()%></td>
					<td><%=policy.getPeriodOfCoverage()%></td>
					<td><%=policy.getPremiumAmount()%></td>
					<td><%=policy.getPrice()%></td>
					<td><a
						href="<%=adminBaseURL%>/updatePolicy/<%=policy.getPolicyId()%>"><input
							type="button" value="Update" style="color: ${headingColor}"></a>
						<a href="<%=adminBaseURL%>/deletePolicy/<%=policy.getPolicyId()%>"><input
							type="button" value="Delete" style="color: ${headingColor}"></a></td>
				</tr>
				<%
				}
				%>
			</table>
		</div>
		<%
		} else if (session.getAttribute("dashboardRequest") != null && session.getAttribute("adminDashboardView") != null
				&& session.getAttribute("customerDashboardView") != null
				&& session.getAttribute("dashboardRequest").equals("customer")
				&& session.getAttribute("customerDashboardView").equals("default")) {
		%>

		<div style="text-align: center">
			<strong style="font-size: 40px; color:${headingColor}">Policy
				List</strong>
		</div>
		<div>
			<table id="tableStyle">
				<tr>
					<th>Policy Name</th>
					<th>Policy Type</th>
					<th>Period of Coverage</th>
					<th>Premium Amount</th>
					<th>Price</th>
					<th>Calculator</th>
				</tr>

				<%
				List<InsurancePolicy> insurancePolicies = (List<InsurancePolicy>) session.getAttribute("insurancePolicies");
				for (InsurancePolicy policy : insurancePolicies) {
				%>
				<tr>
					<td><%=policy.getPolicyName()%></td>
					<td><%=policy.getPolicyType()%></td>
					<td><%=policy.getPeriodOfCoverage()%></td>
					<td><%=policy.getPremiumAmount()%></td>
					<td><%=policy.getPrice()%></td>
					<td><a
						href="<%=customerBaseURL%>/calculatePremium/<%=policy.getPolicyId()%>"><input
							type="button" value="Calculate" style="color: ${headingColor}"></a></td>
				</tr>
				<%
				}
				%>

			</table>

		</div>

		<%
		} else if (session.getAttribute("dashboardRequest") != null && session.getAttribute("adminDashboardView") != null
				&& session.getAttribute("customerDashboardView") != null
				&& session.getAttribute("dashboardRequest").equals("admin")
				&& (session.getAttribute("adminDashboardView").equals("addPolicy")
				|| session.getAttribute("adminDashboardView").equals("updatePolicy"))) {

		String adminDashboardView = (String) session.getAttribute("adminDashboardView");
		String formHeading = null;
		String action = null;
		String method = null;
		String submitButtonName = null;
		if (adminDashboardView.equals("addPolicy")) {
			formHeading = "Add a new policy";
			action = "savePolicy";
			method = "post";
			submitButtonName = "Add";
		} else if (adminDashboardView.equals("updatePolicy")) {
			formHeading = "Update a policy";
			action = "savePolicyUpdate";
			method = "post";
			submitButtonName = "Update";
		} else {
			formHeading = "Default";
			action = "login";
			method = "get";
			submitButtonName = "Default";
		}
		%>
		<div
			style="color:${headingColor}; text-align: center; font-size: 30px">
			<strong><%=formHeading%></strong>
		</div>
		<form:form action="<%=action%>" method="<%=method%>"
			modelAttribute="policy">
			<div>
				<table
					style="margin-left: auto; margin-right: auto; border: 5px solid black">
					<%
					if (adminDashboardView.equals("updatePolicy")) {
					%>
					<tr>
						<td align="left"><label id="policyId"><strong
								style="font-size: 30px">Policy id:</strong></label></td>
						<td align="left"><form:input path="policyId"
								required="required" size="35px" style="color: red"
								readonly="true" /></td>
					</tr>
					<%
					}
					%>
					<tr>
						<td align="left"><label id="policyName"><strong
								style="font-size: 30px">Enter policy name:</strong></label></td>
						<td align="left"><form:input minlength="10" maxlength="100"
								path="policyName" required="required"
								placeholder="Enter policy name here" size="35px" /></td>
					</tr>
					<tr>
						<td align="left"><label id="policyType"><strong
								style="font-size: 30px">Enter policy type:</strong></label></td>
						<td align="left"><form:select path="policyType"
								required="required">
								<form:option value="" label="Select here" hidden="hidden" />
								<form:option value="Life Insurance" label="Life Insurance" />
								<form:option value="Medical Insurance" label="Medical Insurance" />
								<form:option value="Vehicle Insurance" label="Vehicle Insurance" />
							</form:select></td>
					</tr>
					<tr>
						<td align="left"><label id="periodOfCoverage"><strong
								style="font-size: 30px">Enter policy period:</strong></label></td>
						<td align="left"><form:input type="number" min="1" max="3"
								path="periodOfCoverage" required="required" /></td>
					</tr>
					<tr>
						<td align="left"><label id="premiumAmount"><strong
								style="font-size: 30px">Enter premium amount:</strong></label></td>
						<td align="left"><form:input type="number" min="100000"
								max="10000000" path="premiumAmount" required="required" /></td>
					</tr>
					<tr>
						<td align="left"><label id="price"><strong
								style="font-size: 30px">Enter price INR:</strong></label></td>
						<td align="left"><form:input type="number" min="10000"
								max="50000" path="price" required="required" /></td>
					</tr>
				</table>
			</div>
			<br>
			<div style="text-align: center">
				<form:button
					style="color: #06F7E9; background-color: black; height: 30px; width:60px"><%=submitButtonName%></form:button>
			</div>
		</form:form>
		<br>
		<%
		} else if (session.getAttribute("dashboardRequest") != null && session.getAttribute("adminDashboardView") != null
				&& session.getAttribute("customerDashboardView") != null
				&& session.getAttribute("dashboardRequest").equals("customer")
				&& session.getAttribute("customerDashboardView").equals("calculatePremium")) {
		%>
		<div
			style="color:${headingColor}; text-align: center; font-size: 30px">
			<strong>Premium Calculator</strong>
		</div>
		<div>
			<form:form action="calculateOrSaveCalculation" method="post"
				modelAttribute="policy">
				<table
					style="margin-left: auto; margin-right: auto; border: 5px solid black;">
					<tr>
						<td align="left"><strong style="font-size: 20px; color: red">Policy
								Id:</strong></td>
						<td align="right"><form:input path="policyId" size="35px"
								style="color : red" readonly="true"></form:input></td>
					</tr>
					<tr>
						<td align="left"><strong style="font-size: 20px; color: red">Policy
								Name:</strong></td>
						<td align="right"><form:input path="policyName"
								style="color : red" readonly="true"></form:input></td>
					</tr>
					<tr>
						<td align="left"><strong style="font-size: 20px; color: red">Policy
								Type:</strong></td>
						<td align="right"><form:input path="policyType"
								style="color : red" readonly="true" /></td>
					</tr>
					<tr>
						<td align="left"><strong style="font-size: 20px; color: red">Period
								of Coverage:</strong></td>
						<td align="right"><form:input path="periodOfCoverage"
								style="color : red" readonly="true" /></td>
					</tr>
					<tr>
						<td align="left"><strong style="font-size: 20px; color: red">Premium
								Amount:</strong></td>
						<td align="right"><form:input path="premiumAmount"
								style="color : red" readonly="true" /></td>
					</tr>
					<tr>
						<td align="left"><strong style="font-size: 20px; color: red">Price
								INR:</strong></td>
						<td align="right"><form:input path="price"
								style="color : red" readonly="true" /></td>
					</tr>
					<%
					if (session.getAttribute("policyType").equals("Life Insurance")
							|| session.getAttribute("policyType").equals("Medical Insurance")) {
					%>
					<tr>
						<td align="left"><strong
							style="font-size: 20px; color: black;">Enter your stage:</strong></td>
						<td align="right"><form:select path="personStage"
								required="required">
								<form:option value="" label="Select here" hidden="hidden" />
								<form:option value="Young" label="Young(18-39 years of age)" />
								<form:option value="Middle Aged"
									label="Middle Aged(40-59 years of age)" />
								<form:option value="Old" label="Old(60 years of age and above)" />
							</form:select></td>
					</tr>
					<tr>
						<td align="left"><strong
							style="font-size: 20px; color: black">Do you smoke?:</strong></td>
						<td align="right"><form:radiobuttons path="personSmokes"
								items="${choice}" required="required" /></td>
					</tr>
					<tr>
						<td align="left"><strong
							style="font-size: 20px; color: black;">Do you drink?:</strong></td>
						<td align="right"><form:radiobuttons path="personDrinks"
								items="${choice}" required="required" /></td>
					</tr>
					<tr>
						<td align="left"><strong
							style="font-size: 20px; color: black;">Do you have any
								serious/chronic disease?: </strong></td>
						<td align="right"><form:radiobuttons
								path="personHasSeriousDisease" items="${choice}"
								required="required" /></td>
					</tr>
					<%
					} else if (session.getAttribute("policyType").equals("Vehicle Insurance")) {
					%>
					<tr>
						<td align="left"><strong
							style="font-size: 20px; color: black;">Enter the type of
								your vehicle: </strong></td>
						<td align="right"><form:radiobuttons path="vehicleType"
								items="${choice}" required="required" /></td>
					</tr>
					<tr>
						<td align="left"><strong
							style="font-size: 20px; color: black;">Enter the price
								of your vehicle: </strong></td>
						<td align="right"><form:input type="number" min="65000"
								max="10000000" path="vehiclePrice" required="required" /></td>
					</tr>
					<tr>
						<td align="left"><strong
							style="font-size: 20px; color: black;">Enter the age of
								your vehicle: </strong></td>
						<td align="right"><form:input type="number" min="0" max="35"
								path="vehicleAge" required="required" /></td>
					</tr>
					<%
					}
					%>
					<tr>
						<td align="left"><strong
							style="font-size: 20px; color: green;">Final Price INR:
						</strong></td>
						<td align="right"><form:input type="number" path="finalPrice"
								readonly="true" style="color: green" /></td>
					</tr>
					<tr>
						<td align="left"><form:button name="calculate"
								style="color: #06F7E9; background-color: black; height: 30px; width: 355px">Calculate</form:button></td>
						<td align="right"><form:button name="save"
								style="color: #06F7E9; background-color: black; height: 30px; width: 355px">Save Calculation</form:button></td>
					</tr>
				</table>
			</form:form>
		</div>
		<div align="center">
			<br> <input type="button" value="Print" onclick="window.print()"
				style="color: #06F7E9; background-color: black; height: 30px; width: 50px" />
		</div>
		<%
		} else if (session.getAttribute("dashboardRequest") != null && session.getAttribute("adminDashboardView") != null
				&& session.getAttribute("customerDashboardView") != null
				&& session.getAttribute("dashboardRequest").equals("customer")
				&& session.getAttribute("customerDashboardView").equals("showAllCalculations")) {

		List<Calculation> calculations = (List<Calculation>) session.getAttribute("calculations");
		%>
		<%
		if (calculations.isEmpty()) {
		%>
		<div align="center" style="font-size: 30px;">
			<strong style="color: red"> Empty! :(<br> You have not
				saved any calculations yet!
			</strong>
		</div>
		<%
		} else {
		%>
		<div style="text-align: center">
			<strong style="font-size: 30px; color:${headingColor}">Your
				Calculation(s) <br> <br>
			</strong>
		</div>
		<div style="text-align: center">
			<a href="<%=customerBaseURL%>/deleteAllCalculations"> <input
				type="button" name="Delete All Calculations"
				value="Delete All Calculations"
				style="color: #06F7E9; background-color: black; height: 30px; width: 147px" />
			</a> <br>
			<br> <input type="button" name="Print" value="Print"
				onclick="window.print()"
				style="color: #06F7E9; background-color: black; height: 25px; width: 147px" />
			<br>
			<br>
		</div>
		<%
		int serialNumber = 0;
		for (Calculation calculation : calculations) {
		%>

		<div id="calculation<%=++serialNumber%>" style="text-align: left;">
			<table id="tableStyle2">
				<tr>
					<td><strong>Serial Number</strong></td>
					<td><%=serialNumber%></td>
				</tr>
				<tr>
					<td><strong>Policy Id</strong></td>
					<td><%=calculation.getPolicyId()%></td>
				</tr>
				<tr>
					<td><strong>Policy Name</strong></td>
					<td><%=calculation.getPolicyName()%></td>
				</tr>
				<tr>
					<td><strong>Policy Type</strong></td>
					<td><%=calculation.getPolicyType()%></td>
				</tr>
				<tr>
					<td><strong>Period of Coverage</strong></td>
					<td><%=calculation.getPeriodOfCoverage()%></td>
				</tr>
				<tr>
					<td><strong>Premium Amount</strong></td>
					<td><%=calculation.getPremiumAmount()%></td>
				</tr>
				<tr>
					<td><strong style="color: #11908E;">Policy Price INR</strong></td>
					<td><%=calculation.getPrice()%></td>
				</tr>
				<%
				if (calculation.getPolicyType().equals("Life Insurance") || calculation.getPolicyType().equals("Medical Insurance")) {
				%>
				<tr>
					<td><strong>Person Stage</strong></td>
					<td><%=calculation.getPersonStage()%></td>
				</tr>
				<tr>
					<td><strong>Does the person Smoke?</strong></td>
					<td><%=calculation.getPersonSmokes()%></td>
				</tr>
				<tr>
					<td><strong>Does the person Drink?</strong></td>
					<td><%=calculation.getPersonDrinks()%></td>
				</tr>
				<tr>
					<td><strong>Does the person has any serious disease?</strong></td>
					<td><%=calculation.getPersonHasSeriousDisease()%></td>
				</tr>
				<%
				}
				%>
				<%
				if (calculation.getPolicyType().equals("Vehicle Insurance")) {
				%>
				<tr>
					<td><strong>Vehicle Type</strong></td>
					<td><%=calculation.getVehicleType()%></td>
				</tr>
				<tr>
					<td><strong>Vehicle Price</strong></td>
					<td><%=calculation.getVehiclePrice()%></td>
				</tr>
				<tr>
					<td><strong>Vehicle Age</strong></td>
					<td><%=calculation.getVehicleAge()%></td>
				</tr>
				<%
				}
				%>
				<tr>
					<td><strong style="color: #AB1494;">Final Price INR</strong></td>
					<td><%=calculation.getFinalPrice()%></td>
				</tr>
				<tr>
					<td><a
						href="<%=customerBaseURL%>/deleteCalculation/<%=calculation.getCalculationId()%>">
							<input type="button" name="Delete" value="Delete"
							style="color: #06F7E9; background-color: black; height: 30px; width: 60px" />
					</a></td>
				</tr>
			</table>
		</div>
		<%
		for (int count = 1; count <= 5; count++) {
		%>
		<br>
		<%
		}
		%>
		<%
		}
		}
		%>

		<%
		}
		%>
		<div style="text-align: center">
			<strong style="color: ${messageColor}; font-size: 30px">${message}</strong>
		</div>
	</section>

</body>
</html>