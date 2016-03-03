<%@page import="com.mikkysoft.model.Month"%>
<%@page import="com.mikkysoft.controller.SectorController"%>
<%@page import="com.mikkysoft.model.Unit"%>
<%@page import="com.mikkysoft.model.Sector"%>
<%@page import="java.util.List"%>
<%@page import="com.mikkysoft.controller.ZoneController"%>
<%@page import="com.mikkysoft.model.AccessType"%>
<%@page import="com.mikkysoft.model.User"%>
<%@page import="com.mikkysoft.controller.UserController"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Risala Online - Home</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>

<%
User user = (User)session.getAttribute("user");
String display = request.getAttribute("display").toString();
Object unitsObj = request.getAttribute("units");
List<Unit> units = null;
if(null != unitsObj){
	units = (List<Unit>) unitsObj;
}
Object sectorsObj = request.getAttribute("sectors");
List<Sector> sectors = null;
if(null != sectorsObj){
	sectors = (List<Sector>) sectorsObj;
}
%>
<body>
<div class="header" >
	<div><a href="${pageContext.request.contextPath}/logout">Logout</a></div>
	<div>Welcome<span><%=" "+user.getName() %></span></div>
</div>


<p id="zone">
<%if(display.equals("zone")){
	if(null != sectors){	
	%>
<div class="pagetitle">	
<h2>Available Sectors</h2>
</div>
<table>
<tr>
<th>Name</th><th>Subscribers</th>
<%
for(Month month : Month.values()){
	%>
	<th><%=month %></th>
	<%
}
%>
</tr>

<%
			for(Sector sector : sectors){
	%>
<tr>	
	<td><a href="delivery?sector=<%=sector.getUnitId() %>"><%= sector.getName() %></a></td>
	<td><%=sector.getNumberOfSubscribers() %></td>
<%
for(Month month : Month.values()){
	%>
	<td><%=sector.getDeliveredNumberByMonth(month) %></td>
	<%
}
%>
</tr>
<%	
			}

%>

<%}else{ %>
	<tr><td>No sectors found</td></tr>
<% } 
}%>


</table>
</p>

<p id="sector">
<%if(display.equals("sector")){
	if( null != units){ %>
<div class="pagetitle">	
<h2>Available Units</h2>
</div>
<table>
<tr>
<th>Name</th><th>Subscribers</th>
<%
for(Month month : Month.values()){
	%>
	<th><%=month %></th>
	<%
}
%>
</tr>

<%
			for(Unit unit : units){
	%>
<tr>	
	<td><a href="delivery?unit=<%=unit.getUnitId() %>"><%= unit.getName() %></a></td>
	<td><%=unit.getNumberOfSubscribers() %></td>
<%
for(Month month : Month.values()){
	%>
	<td><%=unit.getDeliveredNumberByMonth(month) %></td>
	<%
}
%>
</tr>
<%	
			}

%>

<%}else{ %>
	<tr><td>No units found</td></tr>
<% } 
}%>


</table>
</p>

</body>
</html>