package gamevaluate.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import gamevaluate.bean.Gioco;
import gamevaluate.connectionPool.DriverManagerConnectionPool;

public class HaVotatoManager{
	private static final String TABLE_NAME = "ha_votato";

	public boolean haVotato(String username, int id_gioco) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String selectSQL = "SELECT * FROM "+ TABLE_NAME + " WHERE Username = ? AND ID_Gioco = ?";
		boolean trovato = false;
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, username);
			preparedStatement.setInt(2, id_gioco);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				trovato = true;
			}
		} finally {
			try {
				if(preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return trovato;
	}

	public void salvaVotazione(String username,int id_gioco) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String insertSQL = "INSERT INTO " + TABLE_NAME + 
				" (Username,ID_Gioco)" +
				" VALUES(?, ?)";
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, username);
			preparedStatement.setInt(2, id_gioco);
			
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

}
