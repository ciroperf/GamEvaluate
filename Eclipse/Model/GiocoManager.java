package gamevaluate.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import gamevaluate.bean.Gioco;
import gamevaluate.connectionPool.DriverManagerConnectionPool;

public class GiocoManager {
	
	private static final String TABLE_NAME = "gioco";


	public Gioco doRetrieveByKey(int code) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Gioco bean = new Gioco();
		
		String selectSQL = "SELECT * FROM "+ TABLE_NAME + " WHERE ID_Gioco = ?";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, code);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				bean.setId(rs.getInt("ID_Gioco"));
				bean.setNome(rs.getString("Nome"));
				bean.setDescrizione(rs.getString("Descrizione"));
				bean.setImmagine(rs.getString("Immagine"));
				bean.setGenere(rs.getString("Genere"));
				bean.setPiattaforma(rs.getString("Piattaforma"));
				bean.setValutazione(rs.getInt("ID_Valutazione"));
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

	public Collection<Gioco> doRetrieveAll(String order) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ArrayList<Gioco> giochi = new ArrayList<Gioco>();
		String selectSQL = "SELECT * FROM " +  TABLE_NAME;
		
		if(order != null && !order.equals("")) {
			selectSQL += " ORDER BY" + order;
		}
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				Gioco bean = new Gioco();
				bean.setId(rs.getInt("ID_Gioco"));
				bean.setNome(rs.getString("Nome"));
				bean.setDescrizione(rs.getString("Descrizione"));
				bean.setImmagine(rs.getString("Immagine"));
				bean.setGenere(rs.getString("Genere"));
				bean.setPiattaforma(rs.getString("Piattaforma"));
				bean.setValutazione(rs.getInt("ID_Valutazione"));
				giochi.add(bean);
			}
			
		} finally {
			try {
				if(preparedStatement != null)
					preparedStatement.close();
			} finally {
			DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		
		return giochi;
	}

	public void doSave(Gioco gioco) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String insertSQL = "INSERT INTO " + TABLE_NAME + 
				" (Nome, Descrizione, Immagine, Genere, Piattaforma)" +
				" VALUES(?, ?, ?, ?, ?, ?)";
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, gioco.getNome());
			preparedStatement.setString(2, gioco.getDescrizione());
			preparedStatement.setString(3, gioco.getImmagine());
			preparedStatement.setString(4, gioco.getGenere());
			preparedStatement.setString(5, gioco.getPiattaforma());
			
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
	
	public int getLastId() throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int id = -1;
		
		String selectSQL = "SELECT max(ID_Gioco) from gamevaluate.gioco;";
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				id = rs.getInt("max(ID_Gioco)");
			}
		} finally {
			try {
				if(preparedStatement != null)
					preparedStatement.close();
			} finally {
			DriverManagerConnectionPool.releaseConnection(connection);
			}
		}

		return id;

	}

	public void doUpdate(Gioco gioco) throws SQLException {
		
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String updateSQL = "UPDATE " + TABLE_NAME + " SET " +
				"ID_Gioco = ?, Nome = ?, Descrizione = ?, Immagine = ?, Genere ?" +
				"Piattaforma = ?, ID_Valutazione = ?" +
				"WHERE ID_Gioco = ?";
		

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);
			preparedStatement.setInt(1, gioco.getId());
			preparedStatement.setString(2, gioco.getNome());
			preparedStatement.setString(3, gioco.getDescrizione());
			preparedStatement.setString(4, gioco.getImmagine());
			preparedStatement.setString(5, gioco.getGenere());
			preparedStatement.setString(6, gioco.getPiattaforma());
			preparedStatement.setInt(7, gioco.getValutazione());
			preparedStatement.setInt(8, gioco.getId());

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

	public boolean doDelete(int code) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + TABLE_NAME + " WHERE ID_Gioco = ?";
		

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, code);
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
