package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Produit;

	public class ProduitService {
		private Connection conn;

		public ProduitService(Connection connection) {
			this.conn = connection;
		}
		
		public List<Produit> findAll() {
			List<Produit> produits = new ArrayList<Produit>();

			try {
				if (conn != null) {
					String query = "select * from produit where deleted = 0";
					PreparedStatement ps = conn.prepareStatement(query);
					ResultSet result = ps.executeQuery();
					while (result.next()) {
						Produit produit = new Produit();
						produit.setId(result.getLong(1));
						produit.setDesignation(result.getString(2));
						produit.setPrix(result.getDouble(3));
						produit.setQte_stock(result.getLong(4));
						produit.setDeleted(result.getInt(5));
						
						produits.add(produit);

					}
					ps.close();

				} else {
					System.out.println("Connection nulle dans findAll Produit");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			return produits;
		}

		public int deleteById(long id) {
			try {
				if (conn != null) {
					String queryVerif = "select deleted from produit where Num_prod = ?";
					PreparedStatement psVerif = conn.prepareStatement(queryVerif);
					psVerif.setLong(1, id);
					ResultSet rs = psVerif.executeQuery();
					if (rs.next() && rs.getInt(1) == 1) {
						return 0;
					}

					else {
						String query = "update produit set deleted = 1 where Num_prod = ?";
						PreparedStatement ps = conn.prepareStatement(query);
						ps.setLong(1, id);
						int count = ps.executeUpdate();
						if (count == 1) {
							ps.close();
							return 1;
						} else if (count == 0) {
							ps.close();
							return 0;
						} else {
							ps.close();
							return -2;
						}

					}

				} else {
					System.out.println("Connection nulle dans delete produit");
					return -3;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return -4;
			}
		}

		public Produit findById(long id) {
			try {
				if (conn != null) {
					String query = "select * from produit where Num_prod = ?";
					PreparedStatement ps = conn.prepareStatement(query);
					ps.setLong(1, id);
					ResultSet result = ps.executeQuery();
					if (result.next()) {
						Produit produit = new Produit();
						produit.setId(result.getLong(1));
						produit.setDesignation(result.getString(2));
						produit.setPrix(result.getDouble(3));
						produit.setQte_stock(result.getLong(4));
						
						return produit;
					}
					ps.close();

				} else {
					System.out.println("Connection nulle dans findById produit");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
		}

		public int update(long id, String designation, double prix, long qte_stock) {
			try {
				if (conn != null) {

					String query = "update produit set designation = ?, prix = ?, qte_stock = ? where Num_prod = ?";
					PreparedStatement ps = conn.prepareStatement(query);
					ps.setString(1, designation);
					ps.setDouble(2, prix);
					ps.setLong(3, qte_stock);
					ps.setLong(4, id);
					int count = ps.executeUpdate();
					if (count == 1) {
						ps.close();
						return 1;
					} else if (count == 0) {
						ps.close();
						return 0;
					} else {
						ps.close();
						return -2;
					}

				} else {
					System.out.println("Connection nulle dans findAll produit");
					return -3;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return -4;
			}
		}

		public int add(String designation, double prix, long qte_stock) {
			try {
				if (conn != null) {

					String query = "insert into produit values(seq_prod.nextval, ?,?, ?, 0)";
					PreparedStatement ps = conn.prepareStatement(query);
					ps.setString(1, designation);
					ps.setDouble(2, prix);
					ps.setLong(3, qte_stock);
					
					int count = ps.executeUpdate();
					if (count == 1) {
						ps.close();
						return 1;
					} else {
						ps.close();
						return -2;
					}

				} else {
					System.out.println("Connection nulle dans Add produit");
					return -3;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return -4;
			}
		}

}
