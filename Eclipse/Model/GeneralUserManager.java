package gamevaluate.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import gamevaluate.bean.GeneralUser;
import gamevaluate.connectionPool.DriverManagerConnectionPool;

public class GeneralUserManager implements ProductModel<GeneralUser>{
	
	private static final String TABLE_NAME = "generalusers";

	@Override
	public GeneralUser doRetrieveByKey(String username) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		GeneralUser bean = new GeneralUser();
		
		String selectSQL = "SELECT * FROM " +  TABLE_NAME + " WHERE Username=" + username + "";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				
				bean.setUsername(rs.getString("Username"));
				bean.setEmail(rs.getString("Email"));
				bean.setPassword(rs.getString("Password"));
				bean.setRole(rs.getInt("Role"));
				bean.setBanned(rs.getBoolean("Banned"));
			
			}
			
			if (bean.getUsername() == null)
				return null;
			
			rs.close();
		} finally {
			try {
				if(preparedStatement != null)
					preparedStatement.close();
			} finally {
			
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		
		return bean;
		
	}

	@Override
	public Collection<GeneralUser> doRetrieveAll(String order) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ArrayList<GeneralUser> generalUsers = new ArrayList<GeneralUser>();
		
		String selectSQL = "SELECT * FROM " +  TABLE_NAME;
		
		if(order != null && !order.equals("")) {
			selectSQL += " ORDER BY" + order;
		}
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				GeneralUser bean = new GeneralUser();
				bean.setUsername(rs.getString("Username"));
				bean.setEmail(rs.getString("Email"));
				bean.setPassword(rs.getString("Password"));
				bean.setRole(rs.getInt("Role"));
				bean.setBanned(rs.getBoolean("Banned"));
				generalUsers.add(bean);
			}
	
		} finally {
			try {
				if(preparedStatement != null)
					preparedStatement.close();
			} finally {
			
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		
		return generalUsers;

	}

	@Override
	public void doSave(GeneralUser user) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String insertSQL = "INSERT INTO " + TABLE_NAME +
				" (Username, Email, Password, Role, Banned) " +
				"VALUES (?, ?, ?, ?, ?)";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			
			preparedStatement.setString(1, user.getUsername());
			preparedStatement.setString(2, user.getEmail());
			preparedStatement.setString(3, user.getPassword());
			preparedStatement.setInt(4, user.getRole());
			preparedStatement.setBoolean(5, user.isBanned());
			
			preparedStatement.executeUpdate();
			
			
		} finally {
			try {
				if(preparedStatement != null)
					preparedStatement.close();
			} finally {
			
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
				
	}

	@Override
	public void doUpdate(GeneralUser user) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String updateSQL = "UPDATE " + TABLE_NAME + " SET " +
				"Username = ?, Email = ?, Password = ?, Role = ?, Banned = ? " +
				"WHERE Username = ?";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);
			preparedStatement.setString(1, user.getUsername());
			preparedStatement.setString(2, user.getEmail());
			preparedStatement.setString(3, user.getPassword());
			preparedStatement.setInt(4, user.getRole());
			preparedStatement.setBoolean(5, user.isBanned());
			preparedStatement.setString(6, user.getUsername());
			
			preparedStatement.executeUpdate();
			
		} finally {
			try {
				if(preparedStatement != null)
					preparedStatement.close();
			} finally {
			
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}

	}

	@Override
	public boolean doDelete(String username) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + TABLE_NAME + " WHERE Username = ?";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setString(1, username);
			
			result = preparedStatement.executeUpdate();
			
			
		} finally {
			try {
				if(preparedStatement != null)
					preparedStatement.close();
			} finally {
			
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		
		return (result != 0);
		
	}

}
