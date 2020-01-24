package gamevaluate.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import gamevaluate.connectionPool.DriverManagerConnectionPool;
import gamevaluate.bean.Piattaforma;

public class PiattaformaManager implements ProductModel<Piattaforma>{
	private static final String TABLE_NAME = "piattaforma";
	
	public Piattaforma doRetrieveByKey(String code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		Piattaforma bean = new Piattaforma();
		
		String selectSQL = "SELECT * FROM "+ PiattaformaManager.TABLE_NAME + " WHERE Nome = ?";
			try {
				connection = DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);
				preparedStatement.setString(1,code);
				
				ResultSet rs = preparedStatement.executeQuery();
				
				while (rs.next()) {
					bean.setNome(rs.getString("Nome"));
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

	public Collection<Piattaforma> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		Collection<Piattaforma> products = new LinkedList<Piattaforma>();
		
		String selectSQL = "SELECT * FROM "+ PiattaformaManager.TABLE_NAME;
		if(order != null && !order.equals("")) {
			selectSQL += " ORDER BY" + order;
		}
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				Piattaforma bean = new Piattaforma();
				bean.setNome(rs.getString("Nome"));
				
				products.add(bean);
			}
		} finally {
			if(preparedStatement != null)
				preparedStatement.close();
		
			DriverManagerConnectionPool.releaseConnection(connection);
		}
		
		return (Collection<Piattaforma>) products;
	}

	public void doSave(Piattaforma piattaforma) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String insertSQL = "INSERT INTO " + PiattaformaManager.TABLE_NAME +
				" (Nome) "+
				" VALUES (?) ";
		try
		{
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
		}
		finally
		{
			try
			{
				preparedStatement.setString(1, piattaforma.getNome());
				
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

	public void doUpdate(Piattaforma piattaforma) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String updateSQL = "UPDATE " + PiattaformaManager.TABLE_NAME + " SET " +
		"Nome = ?"+
				"WHERE Nome = ?";
		try
		{
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);
		}
		finally
		{
			try
			{
				preparedStatement.setString(1, piattaforma.getNome());
				
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

	public boolean doDelete(String code) throws SQLException {
		int retvalue;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String deleteSQL = "DELETE FROM " + PiattaformaManager.TABLE_NAME + " WHERE Nome = ?";
		try
		{
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
		}
		finally
		{
			try
			{
				preparedStatement.setString(1, code);
				
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
