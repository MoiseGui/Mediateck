package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.ClientSAV;

public class ClientSAVService {
	private Connection conn;

	public ClientSAVService(Connection connection) {
		this.conn = connection;
	}

	public List<ClientSAV> findAll() {
		List<ClientSAV> ClientSAVs = new ArrayList<ClientSAV>();

		try {
			if (conn != null) {
				String query = "select * from v_chiffre_affaire";
				PreparedStatement ps = conn.prepareStatement(query);
				ResultSet result = ps.executeQuery();
				while (result.next()) {
					ClientSAV ClientSAV = new ClientSAV();
					ClientSAV.setNum_cli(result.getLong(1));
					ClientSAV.setNom(result.getString(2));
					ClientSAV.setPrenom(result.getString(3));
					ClientSAV.setChiffre(result.getDouble(4));
					ClientSAV.setCategorie(result.getString(5));

					ClientSAVs.add(ClientSAV);

				}
				ps.close();

			} else {
				System.out.println("Connection nulle dans findAll ClientSAV");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ClientSAVs;
	}

	

	public ClientSAV findById(long id) {
		try {
			if (conn != null) {
				String query = "select * from v_chiffre_affaire where Num_Cli = ?";
				PreparedStatement ps = conn.prepareStatement(query);
				ps.setLong(1, id);
				ResultSet result = ps.executeQuery();
				if (result.next()) {
					
					ClientSAV ClientSAV = new ClientSAV();
					ClientSAV.setNum_cli(result.getLong(1));
					ClientSAV.setNom(result.getString(2));
					ClientSAV.setPrenom(result.getString(3));
					ClientSAV.setChiffre(result.getDouble(4));
					ClientSAV.setCategorie(result.getString(5));

					return ClientSAV;
				}
				ps.close();

			} else {
				System.out.println("Connection nulle dans findById ClientSAV");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	

	
}
