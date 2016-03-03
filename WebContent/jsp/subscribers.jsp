<%@page import="com.mikkysoft.model.AccessType"%>
<%@page import="com.mikkysoft.model.User"%>
<%@page import="com.mikkysoft.controller.UserController"%>
<%@page import="java.util.Set"%>
<%@page import="com.mikkysoft.model.Month"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.mikkysoft.model.Subscriber"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Risala Online - Subscribers</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<%
Object modeObj = request.getAttribute("mode");
String mode = (null == modeObj) ? "" : modeObj.toString();
Object objSubscribers = request.getAttribute("subscribers");
List<Subscriber> subscribers = null;
if(null!=objSubscribers){
	subscribers = (List<Subscriber>)objSubscribers;
}
%>
<body>
<%
User user = (User)session.getAttribute("user");
String sUnitId = request.getParameter("unit");
int unitid = (null == sUnitId) ? user.getCircle().getUnitId() : Integer.parseInt(sUnitId);

%>
<div class="header">
	<div><a href="${pageContext.request.contextPath}/logout">Logout</a></div>
	<div>Welcome<span><%= " "+user.getName()%></span></div>
</div>
<%if(user.getType().equals(AccessType.UNIT)){ %>
<a href="delivery?unit=<%= unitid %>&mode=edit">Edit</a>
<% } %>

<div class="pagetitle">
 <h2><%= subscribers!=null ? "Number of subscribers found : "+subscribers.size():null %></h2>
 </div>	
<table border=1>
<tr>
<th>Subscribers</th><th>Mobile</th>
<%
for(Month month : Month.values()){
	%>
	<th><%=month %></th>
	<%
}
%>
</tr>

<%
if(subscribers!=null){
	for(Subscriber subscriber: subscribers){
		%>
		<tr>
		<td><%=subscriber.getName() %></td><td><%=subscriber.getMobile() %></td>
		<%
		for(Month month : Month.values()){
			if(subscriber.getDeliveryRecord().containsKey(month.toString())){
				%>
				<td style="color:  #556b2f">&nbsp;<b><%= subscriber.getDeliveryStatusByMonth(month.toString()) %></b>&nbsp;</td>
				<%
			}else{
				if(mode.equals("edit")){
					%>
				<td ><a href="delivery?unit=<%= unitid %>&userid=<%=subscriber.getId() %>&month=<%=month %>&mode=edit" >Pending : Click to deliver</a> </td>	
					<%
				}else{
				%>
				<td style="color: red;">&nbsp;X&nbsp;</td>
				<%
				}
			}
		}
		
		%>
		</tr>
		<%
	}
}
else{
	%>
	<%="No subscribers found" %>
	<%
}
%>


</table>
</body>
</html>