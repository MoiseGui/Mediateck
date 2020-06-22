package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Ligne_facture;
import beans.Produit;

public class Ligne_factureService {
	private Connection conn;

	public Ligne_factureService(Connection connection) {
		this.conn = connection;
	}
	
	
	public List<Ligne_facture> findByFacture(long num_fac) {
		List<Ligne_facture> ligne_factures = new ArrayList<Ligne_facture>();

		try {
			if (conn != null) {
				String query = "select * from ligne_fac where num_fac = ?";
				PreparedStatement ps = conn.prepareStatement(query);
				ps.setLong(1, num_fac);
				ResultSet result = ps.executeQuery();
				while (result.next()) {
					Ligne_facture ligne_facture = new Ligne_facture();
					ligne_facture.setNum_fac(result.getLong(1));
					ligne_facture.setQte_achete(result.getInt(3));
					
					ProduitService produitService = new ProduitService(conn);
					
					Produit produit = produitService.findById(result.getLong(2));
					
					ligne_facture.setProduit(produit);
					
					
					ligne_factures.add(ligne_facture);

				}
				ps.close();

			} else {
				System.out.println("Connection nulle dans findAll Produit");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ligne_factures;
	}
	
	public int deleteById(long num_fac, long num_prod, int qte) {
		try {
			if (conn != null) {
				String query = "delete from ligne_fac where num_fac = ? and num_prod = ?";
				PreparedStatement ps = conn.prepareStatement(query);
				ps.setLong(1, num_fac);
				ps.setLong(2, num_prod);
				int count = ps.executeUpdate();
				if (count == 1) {
					ps.close();
					
					ProduitService produitService = new ProduitService(conn);
					
					if(produitService.addQte(num_prod, qte) >= 0) {
						return 1;
					}
					else {
						return -1;
					}
					
				} else if(count == 0) {
					ps.close();
					return 0;
				}
				else {
					ps.close();
					return -3;
				}

			} else {
				System.out.println("Connection nulle dans delete ligne_facture");
				return -4;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return -5;
		}
	}
	
	
	public int update(long num_fac, long num_prod, int qte) {
		try {
			if (conn != null) {

				String query = "update ligne_fac set qte_achete = qte_achete + ? where Num_fac = ? and num_prod = ?";
				PreparedStatement ps = conn.prepareStatement(query);
				ps.setInt(1, qte);
				ps.setLong(2, num_fac);
				ps.setLong(3, num_prod);
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
				System.out.println("Connection nulle dans update Client");
				return -3;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return -4;
		}
	}

	public int add(long num_fac, long num_prod, int qte) {
		try {
			if (conn != null) {

				String query = "insert into ligne_fac values(?, ?, ?)";
				PreparedStatement ps = conn.prepareStatement(query);
				ps.setLong(1, num_fac);
				ps.setLong(2, num_prod);
				ps.setInt(3, qte);
				int count = ps.executeUpdate();
				if (count == 1) {
					ps.close();
					return 1;
				} else {
					ps.close();
					return -2;
				}

			} else {
				System.out.println("Connection nulle dans Add ligne_fac");
				return -3;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return -4;
		}
	}
	
}
