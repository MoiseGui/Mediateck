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

	public List<Client> findAll() {
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

	public int deleteById(long id) {
		try {
			if (conn != null) {

				String query1 = "delete from Ligne_Fac where num_Fac in (select Num_Fac from facture where Num_cli = ?)";
				PreparedStatement ps_delete_lign_fac = conn.prepareStatement(query1);
				ps_delete_lign_fac.setLong(1, id);
				int first = ps_delete_lign_fac.executeUpdate();
				if (first >= 0) {
					ps_delete_lign_fac.close();
					
					String query2 = "delete from facture where Num_Cli = ?";
					PreparedStatement ps_delete_facture = conn.prepareStatement(query2);
					ps_delete_facture.setLong(1, id);
					int second = ps_delete_facture.executeUpdate();
					if (second >= 0) {
						ps_delete_facture.close();
						
						String query = "delete from client where Num_Cli = ?";
						PreparedStatement ps = conn.prepareStatement(query);
						ps.setLong(1, id);
						int count = ps.executeUpdate();
						if (count == 1) {
							ps.close();
							return 1;
						} else if(count == 0) {
							ps.close();
							return 0;
						}
						else {
							ps.close();
							return -3;
						}
						
					} else {
						ps_delete_facture.close();
						return -2;
					}
					
				} else {
					ps_delete_lign_fac.close();
					return -1;
				}
				

			} else {
				return -4;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return -5;
		}
	}

	public Client findById(long id) {
		try {
			if (conn != null) {
				String query = "select * from client where Num_Cli = ?";
				PreparedStatement ps = conn.prepareStatement(query);
				ps.setLong(1, id);
				ResultSet result = ps.executeQuery();
				if (result.next()) {
					Client client = new Client();
					client.setId(result.getLong(1));
					client.setNom(result.getString(2));
					client.setPrenom(result.getString(3));
					return client;
				}
				ps.close();

			} else {
				System.out.println("Connection nulle dans findById Client");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public int update(long id, String nom, String prenom) {
		try {
			if (conn != null) {
				
				String query = "update client set nom = ?, prenom = ? where Num_Cli = ?";
				PreparedStatement ps = conn.prepareStatement(query);
				ps.setString(1, nom);
				ps.setString(2, prenom);
				ps.setLong(3, id);
				int count = ps.executeUpdate();
				if (count == 1) {
					ps.close();
					return 1;
				} else if(count == 0) {
					ps.close();
					return 0;
				}
				else {
					ps.close();
					return -2;
				}

			} else {
				System.out.println("Connection nulle dans findAll Client");
				return -3;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return -4;
		}
	}

	public int add(String nom, String prenom) {
		try {
			if (conn != null) {
				
				String query = "insert into client values(seq_cli.nextval, ?, ?)";
				PreparedStatement ps = conn.prepareStatement(query);
				ps.setString(1, nom);
				ps.setString(2, prenom);
				int count = ps.executeUpdate();
				if (count == 1) {
					ps.close();
					return 1;
				}
				else {
					ps.close();
					return -2;
				}

			} else {
				System.out.println("Connection nulle dans Add Client");
				return -3;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return -4;
		}
	}
}
