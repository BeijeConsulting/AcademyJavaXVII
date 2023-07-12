<%@page import="org.hibernate.internal.build.AllowSysOut"%>
<%@page import="it.beije.suormary.web.ceccarelli.Contact"%>
<%@page import="it.beije.suormary.web.ceccarelli.Contact2"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div>
<label>LISTA CONTATTI</label>
	<!-- con hbm -->
	<%-- <%
		List<Contact> contacts = (List<Contact>) (session.getAttribute("contacts"));
		
		if(contacts== null) {
		%> <h1 style="text-color: red">NON CI SONO CONTATTI DA MOSTRARE</h1>	
	<%
	}else {
		for(Contact c : contacts){
			%><p><%=c.toString() %></p><%
		}
	}
	 
	%> --%>
	<!-- con JDBC lista contatti nuovi-->
	<%--<% 
		List<Contact2> contacts2 = (List<Contact2>) (session.getAttribute("contactNew"));
		if(contacts2 ==null){
			%> <h1 style ="text-color: red"> NON CI SONO CONTATTI CON LA CLASSE NUOVA DA MOSTRARE</h1>
			
			
		<%}	else {
			for(Contact2 c2 : contacts2){
				%><p><%=c2.toString() %></p><%
			}
		}
		
		%> --%>
		
		<!-- con JDBC lista contatti nuovi con la JOIN-->
	<% 
		List<Contact2> contactsJOIN = (List<Contact2>) (session.getAttribute("contactNewJOIN"));
		if(contactsJOIN ==null){
			%> <h1 style ="text-color: red"> NON CI SONO CONTATTI (join) CON LA CLASSE NUOVA DA MOSTRARE</h1>
			
			
		<%}	else {
			for(Contact2 c2 : contactsJOIN){
				%><p><%=c2.toString() %></p><%
			}
		}
		
		%>
</div>

</body>
</html>