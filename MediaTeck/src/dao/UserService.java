package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.User;

public class UserService {
	private Connection conn;
	
	
	public UserService(Connection connection) {
		this.conn = connection;
	}



	public List<User> findAll(){
		List<User> users = new ArrayList<User>();
		try {
			if (conn != null) {
				String query = "select * from utilisateurs";
				PreparedStatement ps = conn.prepareStatement(query);
				ResultSet result = ps.executeQuery();
				while (result.next()) {
					User user = new User();
					user.setId(result.getLong(1));
					user.setNom(result.getString(2));
					user.setPrenom(result.getString(3));
					user.setCategorie(result.getInt(4));
					user.setUsername(result.getString(5));
					user.setPass(result.getString(6));
					
					users.add(user);
					
				}
				ps.close();

			} else {
				System.out.println("Connection nulle dans findAll");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return users;
	}
	
	public User findByUsername(String username) {
		try {
			if (conn != null) {
				String query = "select * from utilisateurs where username = ?";
				PreparedStatement ps = conn.prepareStatement(query);
				ps.setString(1, username);
				ResultSet result = ps.executeQuery();
				if (result.next()) {
					User user = new User();
					user.setId(result.getLong(1));
					user.setNom(result.getString(2));
					user.setPrenom(result.getString(3));
					user.setCategorie(result.getInt(4));
					user.setUsername(result.getString(5));
					user.setPass(result.getString(6));
					ps.close();
					return user;
				} else {
					ps.close();
					return null;
				}

			} else {
				System.out.println("Connection null in findByUsername");
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public int existByUsernameAndPass(String username, String pass) {
		try {
			if (conn != null) {
				String query = "select count(*) from utilisateurs where username = ? and pass = ?";
				PreparedStatement ps = conn.prepareStatement(query);
				ps.setString(1, username);
				ps.setString(2, pass);
				ResultSet result = ps.executeQuery();
				result.next();
				if (result.getInt(1) == 1) {
					ps.close();
					return 1;
				} else {
					ps.close();
					return -1;
				}
			} else {
				return -2;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return -3;
		}
	}
	
	public int existByUsername(String username) {
		try {
			if (conn != null) {
				String query = "select count(*) from utilisateurs where username = ?";
				PreparedStatement ps = conn.prepareStatement(query);
				ps.setString(1, username);
				ResultSet result = ps.executeQuery();
				result.next();
				int res = result.getInt(1);
//				System.out.println("resultat de recherche pour "+ username + " " +res);
				if (res == 1) {
					ps.close();
					return 1;
				} else {
					ps.close();
					return -1;
				}
			} else {
				return -2;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return -3;
		}
	}
	
	public int edit(long id, String nom, String prenom, String username, String pass) {
		try {
			if (conn != null) {
				
				String query;
				
				if(pass == null || pass.isEmpty()) {
//					System.out.println("Cas du changement simple");
					query = "update utilisateurs set nom = ?, prenom = ?, username = ? where id = ?";
				}
				else {
//					System.out.println("Cas du changement de mot de passe");
					query = "update utilisateurs set nom = ?, prenom = ?, username = ?, pass = ? where id = ?";
				}
				
				PreparedStatement ps = conn.prepareStatement(query);
				ps.setString(1, nom);
				ps.setString(2, prenom);
				ps.setString(3, username);
				
				if(pass == null || pass.isEmpty()) {
					ps.setLong(4, id);
				}
				else {
					ps.setString(4, pass);
					ps.setLong(5, id);
				}
//				System.out.println("J'exécute");
				
				int count = ps.executeUpdate();
				
//				System.out.println("J'ai exécuté "+count);
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
				System.out.println("Connection nulle dans edit User");
				return -3;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return -4;
		}
	}
	
	
	
	
	
}
