package gamevaluate.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import gamevaluate.connectionPool.DriverManagerConnectionPool;
import gamevaluate.bean.Recensione;

public class RecensioneManager {
	private static final String TABLE_NAME = "recensione";
	
	public Recensione doRetrieveByKey(int gioco, String username, String data) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		Recensione bean = new Recensione();
		
		String selectSQL = "SELECT * FROM "+ RecensioneManager.TABLE_NAME + " WHERE ID_Gioco = ? AND Username = ? AND Data = ?";
			try {
				connection = DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);
				preparedStatement.setInt(1,gioco);
				preparedStatement.setString(2,username);
				preparedStatement.setString(3,data);
				
				ResultSet rs = preparedStatement.executeQuery();
				
				while (rs.next()) {
					bean.setTesto(rs.getString("Testo"));
					bean.setGioco(rs.getInt("ID_Gioco"));
					bean.setData(rs.getString("Data"));
					bean.setUsername(rs.getString("Username"));
				}
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

	public Collection<Recensione> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		Collection<Recensione> products = new ArrayList<Recensione>();
		
		String selectSQL = "SELECT * FROM "+ RecensioneManager.TABLE_NAME;
		if(order != null && !order.equals("")) {
			selectSQL += " ORDER BY" + order;
		}
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				Recensione bean = new Recensione();
				bean.setTesto(rs.getString("Testo"));
				bean.setData(rs.getString("Data"));
				bean.setUsername(rs.getString("Username"));
				bean.setGioco(rs.getInt("ID_Gioco"));
				
				products.add(bean);
			}
		} finally {
			if(preparedStatement != null)
				preparedStatement.close();
		
			DriverManagerConnectionPool.releaseConnection(connection);
		}
		
		return products;
	}

	public void doSave(Recensione recensione) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String insertSQL = "INSERT INTO " + RecensioneManager.TABLE_NAME +
				" (Testo, ID_Gioco, Data, Username) "+
				" VALUES (?, ?, NOW(), ?) ";
		try
		{
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
		}
		finally
		{
			try
			{
				preparedStatement.setString(1, recensione.getTesto());
				preparedStatement.setInt(2, recensione.getGioco());
				preparedStatement.setString(3, recensione.getUsername());
				
				preparedStatement.executeUpdate();
			}
			finally
			{
				if(preparedStatement != null)
					preparedStatement.close();
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		
	}

	public void doUpdate(Recensione recensione) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String updateSQL = "UPDATE " + RecensioneManager.TABLE_NAME + " SET " +
		"Testo = ?"+", ID_Gioco = ?"+", Data = ?"+", Username = ?"+
				"WHERE ID_Gioco = ? AND Data = ? AND Username = ?";
		try
		{
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);
		}
		finally
		{
			try
			{
				preparedStatement.setString(1, recensione.getTesto());
				preparedStatement.setInt(2, recensione.getGioco());
				preparedStatement.setString(3, recensione.getData());
				preparedStatement.setString(4, recensione.getUsername());
				preparedStatement.setInt(5, recensione.getGioco());
				preparedStatement.setString(6, recensione.getData());
				preparedStatement.setString(7, recensione.getUsername());
				
				preparedStatement.executeUpdate();
			}
			finally
			{
				if(preparedStatement != null)
					preparedStatement.close();
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		
	}

	public boolean doDelete(String data, int gioco, String username) throws SQLException {
		int retvalue;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String deleteSQL = "DELETE FROM " + RecensioneManager.TABLE_NAME + " WHERE ID_Gioco = ? AND Data = ? AND Username = ?";
		try
		{
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
		}
		finally
		{
			try
			{
				preparedStatement.setInt(1, gioco);
				preparedStatement.setString(2, data);
				preparedStatement.setString(3, username);
				
				retvalue = preparedStatement.executeUpdate();
			}
			finally
			{
				if(preparedStatement != null)
					preparedStatement.close();
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return retvalue != 0;
	}

}