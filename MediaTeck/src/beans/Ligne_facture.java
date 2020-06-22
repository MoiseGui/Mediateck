package beans;

public class Ligne_facture {
	private long num_fac;
	private Produit produit;
	private int qte_achete;
	
	
	public Ligne_facture() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

	public Ligne_facture(Produit produit, int qte_achete) {
		super();
		this.produit = produit;
		this.qte_achete = qte_achete;
	}




	public Ligne_facture(long num_fac, Produit produit, int qte_achete) {
		super();
		this.num_fac = num_fac;
		this.produit = produit;
		this.qte_achete = qte_achete;
	}




	public long getNum_fac() {
		return num_fac;
	}


	public void setNum_fac(long num_fac) {
		this.num_fac = num_fac;
	}


	public Produit getProduit() {
		return produit;
	}


	public void setProduit(Produit produit) {
		this.produit = produit;
	}


	public int getQte_achete() {
		return qte_achete;
	}


	public void setQte_achete(int qte_achete) {
		this.qte_achete = qte_achete;
	}
	
	
	
}
