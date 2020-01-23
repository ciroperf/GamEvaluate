package gamevaluate.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import gamevaluate.connectionPool.DriverManagerConnectionPool;
import gamevaluate.bean.Valutazione;

public class ValutazioneManager {
	private static final String TABLE_NAME = "valutazione";
	
	public Valutazione doRetrieveByKey(int code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		Valutazione bean = new Valutazione();
		
		String selectSQL = "SELECT * FROM "+ ValutazioneManager.TABLE_NAME + " WHERE Nome = ?";
			try {
				connection = DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);
				preparedStatement.setInt(1,code);
				
				ResultSet rs = preparedStatement.executeQuery();
				
				while (rs.next()) {
					bean.setId(rs.getInt("ID_Valutazione"));
					bean.setCoinvolgimento(Integer.parseInt(rs.getString("Coinvolgimento")));
					bean.setCreativita(Integer.parseInt(rs.getString("Creativita")));
					bean.setDifficolta(Integer.parseInt(rs.getString("Difficolta")));
					bean.setGameplay(Integer.parseInt(rs.getString("Gameplay")));
					bean.setGrafica(Integer.parseInt(rs.getString("Grafica")));
					bean.setInnovazione(Integer.parseInt(rs.getString("Innovazione")));
					bean.setRealismo(Integer.parseInt(rs.getString("Realismo")));
					bean.setRigiocabilita(Integer.parseInt(rs.getString("Rigiocabilita")));
					bean.setTrama(Integer.parseInt(rs.getString("Trama")));
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

	public Collection<Valutazione> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		Collection<Valutazione> products = new LinkedList<Valutazione>();
		
		String selectSQL = "SELECT * FROM "+ ValutazioneManager.TABLE_NAME;
		if(order != null && !order.equals("")) {
			selectSQL += " ORDER BY" + order;
		}
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				Valutazione bean = new Valutazione();
				bean.setId(rs.getInt("ID_Valutazione"));
				bean.setCoinvolgimento(Integer.parseInt(rs.getString("Coinvolgimento")));
				bean.setCreativita(Integer.parseInt(rs.getString("Creativita")));
				bean.setDifficolta(Integer.parseInt(rs.getString("Difficolta")));
				bean.setGameplay(Integer.parseInt(rs.getString("Gameplay")));
				bean.setGrafica(Integer.parseInt(rs.getString("Grafica")));
				bean.setInnovazione(Integer.parseInt(rs.getString("Innovazione")));
				bean.setRealismo(Integer.parseInt(rs.getString("Realismo")));
				bean.setRigiocabilita(Integer.parseInt(rs.getString("Rigiocabilita")));
				bean.setTrama(Integer.parseInt(rs.getString("Trama")));
				
				products.add(bean);
			}
		} finally {
			if(preparedStatement != null)
				preparedStatement.close();
		
			DriverManagerConnectionPool.releaseConnection(connection);
		}
		
		return (Collection<Valutazione>) products;
	}

	public void doSave(Valutazione product) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String insertSQL = "INSERT INTO " + ValutazioneManager.TABLE_NAME +
				" (Gameplay, Trama, Grafica, Creativita, Innovazione, Coinvolgimento, Realismo, Rigiocabilita, Difficolta) "+
				" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) ";
		try
		{
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
		}
		finally
		{
			try
			{
				preparedStatement.setString(1, String.valueOf(product.getGameplay()));
				preparedStatement.setString(2, String.valueOf(product.getTrama()));
				preparedStatement.setString(3, String.valueOf(product.getGrafica()));
				preparedStatement.setString(4, String.valueOf(product.getCreativita()));
				preparedStatement.setString(5, String.valueOf(product.getInnovazione()));
				preparedStatement.setString(6, String.valueOf(product.getCoinvolgimento()));
				preparedStatement.setString(7, String.valueOf(product.getRealismo()));
				preparedStatement.setString(8, String.valueOf(product.getRigiocabilita()));
				preparedStatement.setString(9, String.valueOf(product.getDifficolta()));
				
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

	public void doUpdate(Valutazione product) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String updateSQL = "UPDATE " + ValutazioneManager.TABLE_NAME + " SET" +
		"Gameplay = ?"+", Trama = ?"+", Grafica = ?"+", Creativita = ?"+", Innovazione = ?"+", Coinvolgimento = ?"+", Realismo = ?"+", Rigiocabilita = ?"+", Difficolta = ?"+
				"WHERE ID_Valutazione = ?";
		try
		{
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);
		}
		finally
		{
			try
			{
				preparedStatement.setInt(10, product.getId());
				preparedStatement.setString(1, String.valueOf(product.getGameplay()));
				preparedStatement.setString(2, String.valueOf(product.getTrama()));
				preparedStatement.setString(3, String.valueOf(product.getGrafica()));
				preparedStatement.setString(4, String.valueOf(product.getCreativita()));
				preparedStatement.setString(5, String.valueOf(product.getInnovazione()));
				preparedStatement.setString(6, String.valueOf(product.getCoinvolgimento()));
				preparedStatement.setString(7, String.valueOf(product.getRealismo()));
				preparedStatement.setString(8, String.valueOf(product.getRigiocabilita()));
				preparedStatement.setString(9, String.valueOf(product.getDifficolta()));
				
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

	public boolean doDelete(int code) throws SQLException {
		int retvalue;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String deleteSQL = "DELETE FROM " + ValutazioneManager.TABLE_NAME + " WHERE ID_Valutazione = ?";
		try
		{
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
		}
		finally
		{
			try
			{
				preparedStatement.setInt(1, code);
				
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
