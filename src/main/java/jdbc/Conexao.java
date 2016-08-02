package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

	public static Connection getConnection() {
		Connection con = null;

		try {
			
			Class.forName("org.postgresql.Driver");
			//referencia para um objeto de conexao
			con = DriverManager.getConnection(
					"jdbc:postgresql://ec2-54-235-202-71.compute-1.amazonaws.com:5432/d5fdvh38g1mnrc",
					"wrhjnjksobijtb", "A5tKOcHLWaEzffdIxCkYIXX-dp");
			
			System.out.println("Conectado!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}

}
