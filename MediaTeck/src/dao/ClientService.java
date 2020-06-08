package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Client;

public class ClientService {
	private Connection conn;
	
	
	public ClientService(Connection connection) {
		this.conn = connection;
	}
	
	public List<Client> findAll(){
		List<Client> clients = new ArrayList<Client>();
		
		try {
			if (conn != null) {
				String query = "select * from client";
				PreparedStatement ps = conn.prepareStatement(query);
				ResultSet result = ps.executeQuery();
				while (result.next()) {
					Client client = new Client();
					client.setId(result.getLong(1));
					client.setNom(result.getString(2));
					client.setPrenom(result.getString(3));
					
					
					clients.add(client);
					
				}
				ps.close();

			} else {
				System.out.println("Connection nulle dans findAll Client");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return clients;
	}
}
