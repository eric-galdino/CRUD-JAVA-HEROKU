package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.w3c.dom.ls.LSInput;

import dao.UsuarioDAO;
import entidades.Usuario;

/**
 * Servlet implementation class UsuarioController
 */
@WebServlet({ "/usucontroller.do", "/usuariocontroller", "/usucontroller" })
public class UsuarioController extends HttpServlet {
	private static final long serialVersionUID = 1L;

   
    public UsuarioController() {
    	
    	System.out.println("instanciando UsuarioController...");
       
    }


	public void init(ServletConfig config) throws ServletException {

			System.out.println("Chamando init...");
	}
	


	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		System.out.println("Chamou destroy...");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String acao =  request.getParameter("acao");
		
		//instancia do DAO
		UsuarioDAO usuarioDAO =  new UsuarioDAO();
		
		if(acao!=null && acao.equals("exc")){
			
			String id = request.getParameter("id");
			
			if(id!=null){
			
				Usuario usuExcluir =  new Usuario();
				usuExcluir.setId(Integer.parseInt(id));
				usuarioDAO.excluir(usuExcluir);
				//Pede para o navegador fazer a seguinte requisicao para 
				//causar o redirecionamento
				response.sendRedirect("usucontroller.do?acao=lis");
				
			}
			
		}else if(acao!=null && acao.equals("alt")){
			
			String id = request.getParameter("id");
			
			if(id!=null){
				
				//buscar por id				
				Usuario ususalvar = usuarioDAO.buscarPorId(Integer.parseInt(id));
				//criando atributo no request para enviar para o jsp 
				request.setAttribute("ususalvar", ususalvar);
				
				//encaminhar para o frmcadusuario
				request.getRequestDispatcher("frmcadusuario.jsp").forward(request, response);
				
			
			}
			
		}else if(acao!=null && acao.equals("lis")){
		
			//BUscando a lista do banco
			List<Usuario> lista = usuarioDAO.buscarTodos();
			
			//Colocando a lista como atributo para o jsp acesssa como parametro
			request.setAttribute("lista", lista);
			
			//Encaminhando para o jsp
			request.getRequestDispatcher("listausuarios.jsp").forward(request, response);
		} else if(acao!=null && acao.equals("cad")){
			Usuario ususalvar = new Usuario();
			ususalvar.setId(0);
			ususalvar.setNome("");
			ususalvar.setSenha("");
			ususalvar.setUsuario("");
			
			//criando atributo no request para enviar para o jsp 
			request.setAttribute("ususalvar", ususalvar);
			
			//encaminhar para o frmcadusuario
			request.getRequestDispatcher("frmcadusuario.jsp").forward(request, response);
			
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Chamou por post");
		
		//Capturando dados da tela
		String txtid =  request.getParameter("txtid"); 
		String txtnome =  request.getParameter("txtnome"); 
		String txtusuario = request.getParameter("txtusuario");
		String txtsenha =  request.getParameter("txtsenha");
		
		//Intancia de usuario
		Usuario usuario =  new Usuario();
		
		if(txtid!=null){
			usuario.setId(Integer.parseInt(txtid));
		}
		
		usuario.setNome(txtnome);
		usuario.setUsuario(txtusuario);
		usuario.setSenha(txtsenha);
		
		
		UsuarioDAO usuarioDAO=  new UsuarioDAO();		
		usuarioDAO.salvar(usuario);
		
		//Atribuicao de atributos ao request
		request.setAttribute("usu", usuario);
				
		
		//encaminha o fluxo para o JSP de saida
		request.getRequestDispatcher("saidacadusuario.jsp").forward(request, response);
		
		
		
		
//		String saida = "<HTML> \n <BODY> \n";
//		
//		 saida +=  "\n  <b>Nome:</b>"+nome + "\n  <b> <br> usuario:</b>"+ usuario + "<b> <br> Senha:</b>"+senha;
//		 
//		 saida += " \n </BODY></HTML>"; 
		
		//Imprime no browser
	//	response.getWriter().print(saida);
		
		
		
	}

}
