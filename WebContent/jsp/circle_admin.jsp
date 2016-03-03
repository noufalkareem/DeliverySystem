<%@page import="com.mikkysoft.model.Circle"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<%
Object circleObj = request.getAttribute("circles");
List<Circle> circles = (circleObj==null) ? null : (List<Circle>)circleObj;
%>
<body>
<table>
<tr>
<th>Id</th><th>Name</th><th>Type</th><th>Parent</th><th>Parent Type</th>
</tr>
<%
for(Circle circle : circles){ 
%>
<tr>
<td><%=circle.getUnitId() %></td><td><%=circle.getName() %></td>
<td><%=circle.getType() %></td>
<td><%=(circle.getParent()==null)? "-" :circle.getParent().getName()%></td>
<td><%=(circle.getParent()==null)? "-" :circle.getParent().getType() %></td>
</tr>
<%
}
%>
</table>
</body>
</html>