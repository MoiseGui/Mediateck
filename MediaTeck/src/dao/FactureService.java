package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Facture;

public class FactureService {
	private Connection conn;

	public FactureService(Connection connection) {
		this.conn = connection;
	}
	
	public List<Facture> findAll() {
		List<Facture> factures = new ArrayList<Facture>();

		try {
			if (conn != null) {
				String query = "select * from facture";
				PreparedStatement ps = conn.prepareStatement(query);
				ResultSet result = ps.executeQuery();
				while (result.next()) {
					Facture facture = new Facture();
					facture.setNum_fac(result.getLong(1));
					facture.setDate_fac(result.getDate(3));
					
					ClientService clientService = new ClientService(conn);
					facture.setClient(clientService.findById(result.getLong(2)));
					
					Ligne_factureService ligne_factureService = new Ligne_factureService(conn);
					facture.setLigne_factures(ligne_factureService.findByFacture(result.getLong(1)));
					
					facture.calculTotal();
					
					factures.add(facture);

				}
				ps.close();

			} else {
				System.out.println("Connection nulle dans findAll Produit");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return factures;
	}
}
