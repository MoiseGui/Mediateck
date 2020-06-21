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
}
