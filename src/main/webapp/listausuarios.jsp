<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="entidades.Usuario"%>
<%@page import="java.util.List"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%@include file="includes/menu.jsp" %>



<h1>
Lista de Usuários


</h1>

<a href="usucontroller.do?acao=cad"> Novo</a>

<table border="1">
	<tr>
		<th> Nome</th>
		<th> usuario </th>
		<th> Senha </th>
		<th> Opção </th>
	</tr>
	<%
	List<Usuario> lista=	(List<Usuario>) request.getAttribute("lista");

	for (Usuario u: lista){
// 	for(int i=0;i<lista.size(); i++){			
// 		Usuario u =  lista.get(i);	
	%>
	
	<tr>
		<td> <%= u.getNome() %> </td>
		<td> <%= u.getUsuario() %> </td>
		<td> <%= u.getSenha() %> </td>
		<td>  <a href="usucontroller.do?acao=exc&id=<%= u.getId() %>"> Excluir </a> /  <a href="usucontroller.do?acao=alt&id=<%= u.getId() %>">Alterar</a> </td>
	</tr>

	<%
		}
	%>

</table>

</body>
</html>