package beans;

public class ClientSAV {
	private long num_cli;
	private String nom;
	private String prenom;
	private double chiffre;
	private String categorie;
	
	public ClientSAV() {
		super();
	}


	public ClientSAV(long num_cli, String nom, String prenom, String categorie) {
		super();
		this.nom = nom;
		this.num_cli = num_cli;
		this.prenom = prenom;
		this.categorie = categorie;
	}

	

	public ClientSAV(String nom, String prenom) {
		super();
		this.nom = nom;
		this.prenom = prenom;
	}


	public long getNum_cli() {
		return num_cli;
	}


	public void setNum_cli(long num_cli) {
		this.num_cli = num_cli;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public String getPrenom() {
		return prenom;
	}


	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}


	public String getCategorie() {
		return categorie;
	}


	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}


	public double getChiffre() {
		return chiffre;
	}


	public void setChiffre(double chiffre) {
		this.chiffre = chiffre;
	}

	
}
