package beans;

public class Client {
	private long id;
	private String nom;
	private String prenom;
	private int deleted;
	
	public Client() {
		super();
	}


	public Client(long id, String nom, String prenom, int deleted) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.deleted = deleted;
	}

	

	public Client(long id, String nom, String prenom) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
	}


	public Client(String nom, String prenom) {
		super();
		this.nom = nom;
		this.prenom = prenom;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
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


	public int getDeleted() {
		return deleted;
	}


	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}
	
	
}
