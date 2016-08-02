package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

import entidades.Usuario;
import jdbc.Conexao;

public class UsuarioDAO {

	private Connection con;

	public UsuarioDAO() {
		con = Conexao.getConnection();
	}

	public void cadastrar(Usuario usuario) { // new Usuario(','jao',123)

		// Comando SQL

		String sql = "insert into usuario (nome, usuario, senha) values (?,?,md5(?))";

		// Statments

		try {
			//Constroi o  PreparedStatement como SQL
			PreparedStatement preparadorSQL = con.prepareStatement(sql);
			preparadorSQL.setString(1, usuario.getNome());//Pega o indice 1 do sql acima a primeiro interroga��o recenbendo a string escrita do formul�rio
			preparadorSQL.setString(2, usuario.getUsuario());
			preparadorSQL.setString(3, usuario.getSenha()); 
			// Commit no banco
			preparadorSQL.execute();//Execulta o sql no banco de dados
			preparadorSQL.close();//Fecha o preparador
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void alterar(Usuario usuario) { //

		// Comando SQL

		String sql = "update usuario set nome=?, usuario=? , senha=md5(?) where id=?";

		// Statments

		try {
			PreparedStatement preparadorSQL = con.prepareStatement(sql);
			preparadorSQL.setString(1, usuario.getNome());
			preparadorSQL.setString(2, usuario.getUsuario());
			preparadorSQL.setString(3, usuario.getSenha());
			preparadorSQL.setInt(4, usuario.getId());
			// Commit no banco
			preparadorSQL.execute();
			preparadorSQL.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void excluir(Usuario usuario) { //

		// Comando SQL

		String sql = "delete from usuario where id=?";

		// Statments

		try {
			PreparedStatement preparadorSQL = con.prepareStatement(sql);

			preparadorSQL.setInt(1, usuario.getId());
			// Commit no banco
			preparadorSQL.execute();
			preparadorSQL.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void salvar(Usuario usuario){
		
		if(usuario.getId()!=null && usuario.getId()>0){
			alterar(usuario);
		}else{
			cadastrar(usuario);
		}
		
	} 
	
	public Usuario buscarPorId(int id){
		// Comando SQL
				String sql = "select * from usuario where id=?";
				// Statments
				try {
					PreparedStatement preparadorSQL = con.prepareStatement(sql);
					preparadorSQL.setInt(1, id);
					// Commit no banco
					ResultSet res = preparadorSQL.executeQuery();
					
					//Tirando do Resultset e colocando no objeto usuario
					if(res.next()){
						
						Usuario usuario =  new Usuario();
						usuario.setId(res.getInt("id") );
						usuario.setNome(res.getString("nome") );
						usuario.setUsuario(res.getString("usuario"));
						usuario.setSenha(res.getString("senha"));
						return usuario;
					}
					
					preparadorSQL.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
	}
	
	public List<Usuario> buscarTodos(){
			
		//instancia da lista
		List<Usuario> lista =  new ArrayList<Usuario>();
		// Comando SQL
				String sql = "select * from usuario order by nome";
				// Statments
				try {
					PreparedStatement preparadorSQL = con.prepareStatement(sql);
					// Commit no banco
					ResultSet res = preparadorSQL.executeQuery();
					//Tirando do Resultset e colocando no objeto usuario
					while(res.next()){
						Usuario usuario =  new Usuario();
						usuario.setId(res.getInt("id") );
						usuario.setNome(res.getString("nome") );
						usuario.setUsuario(res.getString("usuario"));
						usuario.setSenha(res.getString("senha"));
						lista.add(usuario);
					}
					preparadorSQL.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return lista;
	}
	
	public Usuario autenticar(Usuario usuario){
		// Comando SQL
				String sql = "select * from usuario where usuario=? and senha=md5(?)";
				// Statments
				try {
					PreparedStatement preparadorSQL = con.prepareStatement(sql);
					preparadorSQL.setString(1, usuario.getUsuario());
					preparadorSQL.setString(2, usuario.getSenha());
					
					// Commit no banco
					ResultSet res = preparadorSQL.executeQuery();
					
					//Tirando do Resultset e colocando no objeto usuario
					if(res.next()){
						
						Usuario usu =  new Usuario();
						usu.setId(res.getInt("id") );
						usu.setNome(res.getString("nome") );
						usu.setUsuario(res.getString("usuario"));
						usu.setSenha(res.getString("senha"));
						return usu;
					}
					
					preparadorSQL.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
	}

}
