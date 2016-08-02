<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="entidades.Usuario"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<%@include file="includes/menu.jsp" %>

<br>


Seja Bem vindo 

<% 
	Usuario usuAutenticado =  (Usuario)session.getAttribute("usuAutenticado");
	
%>

!
</body>
</html>