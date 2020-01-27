package gamevaluate.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import gamevaluate.bean.Genere;
import gamevaluate.connectionPool.DriverManagerConnectionPool;

public class GenereManager {
	
	private static final String TABLE_NAME = "genere";

	public Genere doRetrieveByKey(String nome) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Genere bean = new Genere();
		
		String selectSQL = "SELECT * FROM "+ TABLE_NAME + " WHERE Nome = ?";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, nome);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				bean.setNome(rs.getString("Nome"));
				bean.setDescrizione(rs.getString("Descrizione"));
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

	public Collection<Genere> doRetrieveAll(String order) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ArrayList<Genere> generi = new ArrayList<Genere>();
		
		String selectSQL = "SELECT * FROM " +  TABLE_NAME;
		
		if(order != null && !order.equals("")) {
			selectSQL += " ORDER BY" + order;
		}
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				Genere bean = new Genere();
				bean.setNome(rs.getString("Nome"));
				bean.setDescrizione(rs.getString("Descrizione"));
				generi.add(bean);	
			}
			
		} finally {
			try {
				if(preparedStatement != null)
					preparedStatement.close();
			} finally {
			DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		
		return generi;
	}


	public void doSave(Genere genere) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String insertSQL = "INSERT INTO " + TABLE_NAME + 
				" (Nome, Descrizione) VALUES " + "(?, ?)";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			
			preparedStatement.setString(1, genere.getNome());
			preparedStatement.setString(2, genere.getDescrizione());
			
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

	//IMPOSSIBILE DA ESEGUIRE
	public void doUpdate(Genere genere) throws SQLException {
	
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String updateSQL = "UPDATE " + TABLE_NAME + " SET " +
				"Nome = ?, Descrizione = ?" +
				"WHERE Nome = ?";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);
			preparedStatement.setString(1, genere.getNome());
			preparedStatement.setString(2, genere.getDescrizione());
			
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


	public boolean doDelete(String nome) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + TABLE_NAME + " WHERE Nome = ?";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setString(1, nome);
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
