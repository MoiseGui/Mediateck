package beans;

import java.util.ArrayList;
import java.util.List;

public class Facture {
	private long num_fac;
	private Client client;
	private String date_fac;
	private double total;
	
	private List<Ligne_facture> ligne_factures;
	
	
	public Facture() {
		super();
		this.ligne_factures = new ArrayList<Ligne_facture>();
		this.client = new Client();
//		this.date_fac = new Date();
//		System.out.println(date_fac.getDate()+ "/" + (date_fac.getMonth()+1) + "/" + (date_fac.getYear()+1900));
	}

	public double getTotal() {
		return total;
	}
	
	
	public void setTotal(double total) {
		this.total = total;
	}

	public long getNum_fac() {
		return num_fac;
	}


	public void setNum_fac(long num_fac) {
		this.num_fac = num_fac;
	}


	public Client getClient() {
		return client;
	}


	public void setClient(Client client) {
		this.client = client;
	}





	public String getDate_fac() {
		return date_fac;
	}

	public void setDate_fac(String date_fac) {
		this.date_fac = date_fac;
	}

	public List<Ligne_facture> getLigne_factures() {
		return ligne_factures;
	}


	public void setLigne_factures(List<Ligne_facture> ligne_factures) {
		this.ligne_factures = ligne_factures;
	}
	
	public void calculTotal() {
		double total = 0;
		
		for (Ligne_facture ligne_facture : ligne_factures) {
			total += ligne_facture.getQte_achete() * ligne_facture.getProduit().getPrix();
		}
		
		this.total = total;
	}
	
}
