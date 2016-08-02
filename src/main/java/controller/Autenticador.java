package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UsuarioDAO;
import entidades.Usuario;

/**
 * Servlet implementation class Autenticador
 */
@WebServlet({ "/autenticador", "/autenticador.do" })
public class Autenticador extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Autenticador() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession sessao = request.getSession(false);
		
		if(sessao!=null){
			sessao.invalidate();
			response.sendRedirect("login.html");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Criando objeto usuario para ser validado
		Usuario usuarioTela = new Usuario();
		usuarioTela.setUsuario(request.getParameter("txtusuario"));
		usuarioTela.setSenha(request.getParameter("txtsenha"));
		
		UsuarioDAO usuarioDAO =  new UsuarioDAO();
		
		//autenticando usuario e retornando objeto se encontrado
		Usuario usuarioAutenticado=   usuarioDAO.autenticar(usuarioTela);
		
		//testando se encontrou usuario
		if(usuarioAutenticado!=null){
				
			//Criando sessao caso nao exista ou recupendao caso ja exista
			HttpSession sessao = request.getSession();
			//setando usuarioAutenticado como atributo da sessao 
			sessao.setAttribute("usuAutenticado", usuarioAutenticado);
			
			//redirecionando no proprio para tela de seja bem vindo
			request.getRequestDispatcher("index.jsp").forward(request, response);
			
		}else{
			//Leva para o browser uma ordem para fazer um nova requisicao ao servidor
			response.sendRedirect("errologin.html");
			
		}
		
	}

}
