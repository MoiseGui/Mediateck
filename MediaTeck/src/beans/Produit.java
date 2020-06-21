package beans;

public class Produit {
	private long id;
	private String designation;
	private double prix;
	private long qte_stock;
	private int deleted;
	private String mention;
	
	public Produit() {
		super();
	}


	public Produit(long id, String designation, double prix, long qte_stock) {
		super();
		this.id = id;
		this.designation = designation;
		this.prix = prix;
		this.qte_stock = qte_stock;
	}
	


	public Produit(long id, String designation, double prix, long qte_stock, int deleted) {
		super();
		this.id = id;
		this.designation = designation;
		this.prix = prix;
		this.qte_stock = qte_stock;
		this.deleted = deleted;
	}


	public Produit( String designation, double prix, long qte_stock) {
		super();
		this.designation = designation;
		this.prix = prix;
		this.qte_stock = qte_stock;

	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getDesignation() {
		return designation;
	}


	public void setDesignation(String designation) {
		this.designation = designation;
	}


	public double getPrix() {
		return prix;
	}


	public void setPrix(double prix) {
		this.prix = prix;
	}


	public long getQte_stock() {
		return qte_stock;
	}


	public void setQte_stock(long qte_stock) {
		this.qte_stock = qte_stock;
	}


	public int getDeleted() {
		return deleted;
	}


	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}


	public String getMention() {
		return mention;
	}


	public void setMention(String mention) {
		this.mention = mention;
	}
	
	
}
