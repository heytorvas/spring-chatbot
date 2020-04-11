package repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ChatBotRepository {

	public static Connection getInstance() {
		Connection conn = null;

		try {
			// registrando o driver do postgres
			Class.forName("org.postgresql.Driver");
			// estabelecendo um conexao com o banco
			conn = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/projeto_int", "admin", "123456789");
			// findSinonimo(instancia da conexao, String x);

			System.out.println("se chegou aqui, heytwo");
		} catch (ClassNotFoundException e) {
			System.out.println("Falha ao registrar o Driver de banco");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Falha ao conectar ao banco de dados");
			e.printStackTrace();
		}
		return conn;
	}

	private Connection conn = null;

	public Connection getConnection() {
		if (conn == null)
			conn = getInstance();
		return conn;
	}

	public void closeConnection() {
		try {
			getConnection().close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conn = null;
		}
	}

	public String findAnswer(String message) {

		PreparedStatement stat = null;

		if (message.equals("heytwo")) {

			try {
				stat = getConnection().prepareStatement("SELECT * FROM table");
				ResultSet rs = stat.executeQuery();

				while (rs.next()) {
					return rs.getString("coluna");
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					stat.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}
		return "falha de conexao com o banco";
	}

}
