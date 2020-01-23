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
		
		String selectSQL = "SELECT * FROM "+ ValutazioneManager.TABLE_NAME + " WHERE ID_Valutazione = ?";
			try {
				connection = DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);
				preparedStatement.setInt(1,code);
				
				ResultSet rs = preparedStatement.executeQuery();
				
				while (rs.next()) {
					bean.setId(rs.getInt("ID_Valutazione"));
					bean.setCoinvolgimento(rs.getInt("Coinvolgimento"));
					bean.setCreativita(rs.getInt("Creativita"));
					bean.setDifficolta(rs.getInt("Difficolta"));
					bean.setGameplay(rs.getInt("Gameplay"));
					bean.setGrafica(rs.getInt("Grafica"));
					bean.setInnovazione(rs.getInt("Innovazione"));
					bean.setRealismo(rs.getInt("Realismo"));
					bean.setRigiocabilita(rs.getInt("Rigiocabilita"));
					bean.setTrama(rs.getInt("Trama"));
				
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
				bean.setCoinvolgimento(rs.getInt("Coinvolgimento"));
				bean.setCreativita(rs.getInt("Creativita"));
				bean.setDifficolta(rs.getInt("Difficolta"));
				bean.setGameplay(rs.getInt("Gameplay"));
				bean.setGrafica(rs.getInt("Grafica"));
				bean.setInnovazione(rs.getInt("Innovazione"));
				bean.setRealismo(rs.getInt("Realismo"));
				bean.setRigiocabilita(rs.getInt("Rigiocabilita"));
				bean.setTrama(rs.getInt("Trama"));
				
				products.add(bean);
			}
		} finally {
			if(preparedStatement != null)
				preparedStatement.close();
		
			DriverManagerConnectionPool.releaseConnection(connection);
		}
		
		return (Collection<Valutazione>) products;
	}

	public void doSave(Valutazione valutazione) throws SQLException {
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
				preparedStatement.setInt(1, valutazione.getGameplay());
				preparedStatement.setInt(2, valutazione.getTrama());
				preparedStatement.setInt(3, valutazione.getGrafica());
				preparedStatement.setInt(4, valutazione.getCreativita());
				preparedStatement.setInt(5, valutazione.getInnovazione());
				preparedStatement.setInt(6, valutazione.getCoinvolgimento());
				preparedStatement.setInt(7, valutazione.getRealismo());
				preparedStatement.setInt(8, valutazione.getRigiocabilita());
				preparedStatement.setInt(9, valutazione.getDifficolta());
				
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

	public void doUpdate(Valutazione valutazione) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String updateSQL = "UPDATE " + ValutazioneManager.TABLE_NAME + " SET " +
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
				preparedStatement.setInt(1, valutazione.getGameplay());
				preparedStatement.setInt(2, valutazione.getTrama());
				preparedStatement.setInt(3, valutazione.getGrafica());
				preparedStatement.setInt(4, valutazione.getCreativita());
				preparedStatement.setInt(5, valutazione.getInnovazione());
				preparedStatement.setInt(6, valutazione.getCoinvolgimento());
				preparedStatement.setInt(7, valutazione.getRealismo());
				preparedStatement.setInt(8, valutazione.getRigiocabilita());
				preparedStatement.setInt(9, valutazione.getDifficolta());
				preparedStatement.setInt(10, valutazione.getId());
				
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
