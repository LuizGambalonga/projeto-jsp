package conexao;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnection {

	private static String url = "jdbc:postgresql://localhost:5432/curso-jsp?autoReconnect=true";
	private static String password ="postgres";
	private static String user ="postgres";
	
	private static Connection connection = null;
	
	static {
		conectar();
	}
	
	public SingleConnection() {
		conectar();
		
	}
	
	private static void conectar() {
		try {
			if(connection ==null) {
				try {
					Class.forName("org.postgresql.Driver");
				} catch (Exception e) {
					e.printStackTrace();
				}
				connection = DriverManager.getConnection(url, user, password);
				connection.setAutoCommit(false);
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		return connection;
	}
}
