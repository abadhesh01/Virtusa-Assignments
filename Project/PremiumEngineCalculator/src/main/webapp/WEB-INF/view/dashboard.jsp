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
<title>${userType}Dashboard</title>
<style type="text/css">
a {
	text-decoration: none;
}

#tableStyle {
	font-family: sans-serif;
	border-collapse: collapse;
	width: 100%
}

#tableStyle td, #tableStyle th {
	border: 1px solid black;
	padding: 8px;
}

#tableStyle th {
	padding-top: 12px;
	padding-bottom: 12px;
	text-align: left;
	background-color: ${headingColor};
	color:white;
}

#tableStyle tr:nth-child(odd) {
	background-color: #7DC4F0;
}

#tableStyle tr:nth-child(even) {
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
								all policies</strong></a></li>
					<li style="display: inline; float: center; padding: 8px;"><a
						href="<%=adminBaseURL%>/addNewPolicy" style="color: white;"><strong>Add
								new policy</strong></a></li>
					<li style="display: inline; float: center; padding: 8px;"><a
						href="#" style="color: white;"><strong>Edit policy</strong></a></li>
					<li style="display: inline; float: center; padding: 8px;"><a
						href="#" style="color: white;"><strong>Delete policy</strong></a></li>
					<%
					}
					%>

					<%
					if (session.getAttribute("dashboardRequest").equals("customer")) {
					%>
					<li style="display: inline; float: center; padding: 8px;"><a
						href="<%=customerBaseURL%>/showAllPolicies" style="color: white;"><strong>Show
								all policy</strong></a></li>
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
					<th>Operation 1</th>
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
							type="button" value="Update" style="color: ${headingColor}"></a></td>
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
								max="1000000" path="premiumAmount" required="required" /></td>
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
			<br>
		</form:form>
		<div style="text-align: center">
			<strong style="color: ${messageColor}; font-size: 30px">${message}</strong>
		</div>
		<%
		}
		%>
	</section>

</body>
</html>