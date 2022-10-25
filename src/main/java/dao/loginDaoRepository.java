package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conexao.SingleConnection;
import model.LoginModel;

public class loginDaoRepository {
	private Connection connection;
	
	public loginDaoRepository() {
		connection = SingleConnection.getConnection();
	}
	
	public boolean validarAutenticacao(LoginModel login) {
		String sql = "select * from model_login where login = ? and senha = ?";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, login.getLogin());
			ps.setString(2, login.getSenha());
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				return true;
			}
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		
		return false;
	}
}
