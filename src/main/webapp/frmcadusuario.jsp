<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="entidades.Usuario"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<script type="text/javascript" >

function validaCadastro(){
	//Acesso ao formulario
// 	nome = document.frmcadusuario.txtnome;	
// 	usuario = document.frmcadusuario.txtusuario;	
// 	senha = document.frmcadusuario.txtsenha;
	id = document.getElementById("txtid");
	nome =  document.getElementById("txtnome");
	usuario =  document.getElementById("txtusuario");
	senha =  document.getElementById("txtsenha");
	
	if(nome.value==""){
		window.alert("Nome é obrigatório!");
		nome.focus();
		return false;
	}
	if(usuario.value==""){
		window.alert("usuario é obrigatório!");
		usuario.focus();
		return false;
	}
	if(  ( id.value=="" || id.value=="0")   && senha.value==""){
		window.alert("Senha é obrigatório!");
		senha.focus();
		return false;
	}	
	return true;
}

</script>


</head>
<body>
<%

	Usuario ususalvar =  (Usuario) request.getAttribute("ususalvar");

%>


<%@include file="includes/menu.jsp" %>

	<form  name="frmcadusuario" onsubmit="return validaCadastro()" method="post" action="usucontroller.do">

		<fieldset>
			<legend>  Cadastro de Usuário</legend>

		
			<input id="txtid" type="hidden" name="txtid" value="<%=ususalvar.getId()%>" readonly="readonly">
		
			<br>
			<label> Nome: </label> <input id="txtnome" type="text" name="txtnome" value="<%=ususalvar.getNome()%>">

			<br>
			
			<label> usuario: </label> <input id="txtusuario" type="text" name="txtusuario" value="<%=ususalvar.getUsuario()%>">
			
			<br>
			
			<label> Senha:</label> 
			
			<br>
			
			<input type="password" name="txtsenha" id="txtsenha"
				value=""> <input type="submit" name="btsalvar"
				value="salvar">

		</fieldset>


	</form>

</body>
</html>