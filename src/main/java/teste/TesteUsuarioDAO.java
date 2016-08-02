package teste;

import java.util.List;

import dao.UsuarioDAO;
import entidades.Usuario;

public class TesteUsuarioDAO {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		//testCadastrar();
		//testAlterar();
		//testExcluir();
		//testSalvar();
		//testBuscarPorId();
		//testBuscarTodos();
		
		testAutenticar();
	}

	private static void testCadastrar() {
		//instancia que ser� um registro no banco
		Usuario usuario1 =  new Usuario();
		usuario1.setNome("Jos�");
		usuario1.setSenha("890");
		usuario1.setUsuario("jos");
		
		//instancia que ser� um registro no banco
		Usuario usuario2 =  new Usuario();
		usuario2.setNome("ANA");
		usuario2.setSenha("898");
		usuario2.setUsuario("ana");
		
		//instancia do objeto que faz o CRUD
		UsuarioDAO usuarioDAO=  new UsuarioDAO();
		
		usuarioDAO.cadastrar(usuario1);
		usuarioDAO.cadastrar(usuario2);
	}
	
	private static void  testAlterar( ){
		
		Usuario usuario1 =  new Usuario();
		usuario1.setId(7);
		usuario1.setNome("Marta");	
		
		UsuarioDAO usuDAO = new UsuarioDAO();
		usuDAO.alterar(usuario1);
	}
	
	private static void  testExcluir( ){
		
		Usuario usuario1 =  new Usuario();
		usuario1.setId(10);
		UsuarioDAO usuDAO = new UsuarioDAO();
		usuDAO.excluir(usuario1);
			
	}
	
	
	private static void  testSalvar( ){
		
		Usuario usuario1 =  new Usuario();
		usuario1.setId(10);
		usuario1.setNome("Marta");	
		
		UsuarioDAO usuDAO = new UsuarioDAO();
		usuDAO.salvar(usuario1);
	}
	
	
	private static void testBuscarPorId(){
		UsuarioDAO usuDAO = new UsuarioDAO();
		
		Usuario usuario = usuDAO.buscarPorId(4);
		
		if(usuario!=null)
		System.out.println(usuario.getId() +" " +usuario.getNome() + " "+ usuario.getUsuario() + " " + usuario.getSenha());
		
		
	}


	private static void testBuscarTodos(){
		UsuarioDAO usuDAO = new UsuarioDAO();
		
		List<Usuario> lista = usuDAO.buscarTodos();
		
		for(Usuario usuario:lista)
		System.out.println(usuario.getId() +" " +usuario.getNome() + " "+ usuario.getUsuario() + " " + usuario.getSenha());
		
		
	}
	
	
	private static void testAutenticar(){
		UsuarioDAO usuDAO = new UsuarioDAO();
		
		Usuario usuAut =  new Usuario();
		usuAut.setUsuario("ana");
		usuAut.setSenha("898");
		
		Usuario usuRetorno = usuDAO.autenticar(usuAut);
		
		if(usuRetorno!=null)
		System.out.println(usuRetorno.getId() +" " +usuRetorno.getNome() + " "+ usuRetorno.getUsuario() + " " + usuRetorno.getSenha());
		else
				System.out.println("Usuario nao encontrado!");
		
	}
}
