<%@page import="javax.persistence.Table"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${userType}Dashboard</title>
</head>
<body style="background-color: #C7CFC9">

    <%
	  response.addHeader("Pragma", "no-cache");
	  response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
	  response.addHeader("Cache-Control", "pre-check=0, post-check=0");
	  response.setDateHeader("Expires",0);
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
					<li style="display: inline; float: center; padding: 8px;"><a
						href="#" style="color: white;"><strong>Show all
								policy</strong></a></li>
				    <% if(session.getAttribute("dashboardRequest").equals("admin")) {%>				
					<li style="display: inline; float: center; padding: 8px;"><a
						href="<%=adminBaseURL%>/addNewPolicy" style="color: white;"><strong>Add new policy</strong></a></li>
					<li style="display: inline; float: center; padding: 8px;"><a
						href="#" style="color: white;"><strong>Edit policy</strong></a></li>
					<li style="display: inline; float: center; padding: 8px;"><a
						href="#" style="color: white;"><strong>Delete policy</strong></a></li>
					<%}%>	
				</ul>
			</nav>
		</div>
	</section>
  
    <section>
          <% if(session.getAttribute("dashboardRequest") != null 
             && session.getAttribute("adminDashboardView") != null
        	 &&	session.getAttribute("dashboardRequest").equals("admin")
        	 && session.getAttribute("adminDashboardView").equals("addPolicy")) {%> 
        	 <div style="color:${headingColor}; text-align: center; font-size: 30px">
        	 <strong>Add a new policy</strong>
        	 </div>
        	 <form:form action="savePolicy" method="post" modelAttribute="policy">
        	     <div>
        	     <table style="margin-left: auto; margin-right: auto; border: 5px solid black">
        	        <tr>
        	           <td align="left"><label id="policyName"><strong style="font-size: 30px">Enter policy name:</strong></label></td>
        	           <td align="left"><form:input minlength="10" maxlength="100" path="policyName" required="required" placeholder="Enter policy name here" size="30px"/></td>
        	        </tr>
        	        <tr>
        	           <td align="left"><label id="policyType"><strong style="font-size: 30px">Enter policy type:</strong></label></td>
        	           <td align="left"><form:select path="policyType" required="required">
        	            <form:option value="" label="Select here" hidden="hidden"/>
        	            <form:option value="Life Insurance" label="Life Insurance"/>
        	            <form:option value="Medical Insurance" label="Medical Insurance"/>
        	            <form:option value="Vehicle Insurance" label="Vehicle Insurance"/>
        	           </form:select>
        	           </td>
        	        </tr>
        	        <tr>
        	           <td align="left"><label id="periodOfCoverage"><strong style="font-size: 30px">Enter policy period:</strong></label></td>
        	           <td align="left"><form:input type="number" min="1" max="3" path="periodOfCoverage" required="required"/></td>
        	        </tr>
        	        <tr>
        	           <td align="left"><label id="premiumAmount"><strong style="font-size: 30px">Enter premium amount:</strong></label></td>
        	           <td align="left"><form:input type="number" min="100000" max="1000000" path="premiumAmount" required="required"/></td>
        	        </tr>
        	        <tr>
        	           <td align="left"><label id="price"><strong style="font-size: 30px">Enter price INR:</strong></label></td>
        	           <td align="left"><form:input type="number" min="10000" max="50000" path="price" required="required"/></td>
        	        </tr>
        	     </table> 
        	     </div>
        	     <br>
        	     <div style="text-align: center">	     
        	        <form:button style="color: #06F7E9; background-color: black; height: 30px; width:50px">Add</form:button>
        	     </div>
        	     <br>
        	 </form:form>
        	 <div style="text-align: center">
        	         <strong style="color: ${messageColor}; font-size: 30px">${message}</strong>
             </div>
          <%}%>	  
    </section>

</body>
</html>