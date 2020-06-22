package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Facture;
import beans.Ligne_facture;

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
					facture.setDate_fac(result.getString(3).substring(0, 10));

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

	public int save(Facture facture) {
		try {
			if (conn != null) {

				String query = "insert into facture values(seq_fac.nextval, ?, to_date(?,'YYYY-MM-DD'))";
				PreparedStatement ps = conn.prepareStatement(query, new String[] { "num_fac" });
				ps.setLong(1, facture.getClient().getId());
				ps.setString(2, facture.getDate_fac());
				int count = ps.executeUpdate();
				if (count == 1) {
					ResultSet rs1 = ps.getGeneratedKeys();
					if (rs1.next()) {
						long id = rs1.getLong(1);
						ps.close();

						for (Ligne_facture ligne : facture.getLigne_factures()) {
							String q = "insert into ligne_fac values(?, ?, ?)";
							PreparedStatement ps1 = conn.prepareStatement(q);
							ps1.setLong(1, id);
							ps1.setLong(2, ligne.getProduit().getId());
							ps1.setInt(3, ligne.getQte_achete());

							ps1.executeUpdate();
							ps1.close();
						}

						return 1;

					} else {
						ps.close();
						return -5;
					}
				} else {
					ps.close();
					return -2;
				}

			} else {
				System.out.println("Connection nulle dans Add Client");
				return -3;
			}
		} catch (SQLException e) {

//			e.printStackTrace();

			while (e != null) {
				String sqlState = e.getSQLState();
				int errorCode = e.getErrorCode();
				String message = e.getMessage();
				System.out.println("SQLState = " + sqlState);
				System.out.println("ErrorCode = " + errorCode);
				System.out.println("Message = " + message);
				e.printStackTrace();
				e = e.getNextException();
			}

			return -4;
		}
	}

	public Facture findById(long id) {
		try {
			if (conn != null) {
				String query = "select * from facture where num_fac = ?";
				PreparedStatement ps = conn.prepareStatement(query);
				ps.setLong(1, id);
				ResultSet result = ps.executeQuery();
				if (result.next()) {
					Facture facture = new Facture();
					facture.setNum_fac(result.getLong(1));
					facture.setDate_fac(result.getString(3).substring(0, 10));

					ClientService clientService = new ClientService(conn);
					facture.setClient(clientService.findById(result.getLong(2)));

					Ligne_factureService ligne_factureService = new Ligne_factureService(conn);
					facture.setLigne_factures(ligne_factureService.findByFacture(result.getLong(1)));

					facture.calculTotal();
					return facture;
				}
				ps.close();

			} else {
				System.out.println("Connection nulle dans findById facture");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public int update(long num_fac, long num_cli, String date_fac) {
		try {
			if (conn != null) {

				String query = "update facture set num_cli = ?, date_fac = to_date(?,'YYYY-MM-DD') where Num_fac = ?";
				PreparedStatement ps = conn.prepareStatement(query);
				ps.setLong(1, num_cli);
				ps.setString(2, date_fac);
				ps.setLong(3, num_fac);
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
				System.out.println("Connection nulle dans update facture");
				return -3;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return -4;
		}
	}

}
