package beans;

public class User {
	private long id;
	private String nom;
	private String prenom;
	private int categorie;
	private String username;
	private String pass;
	
	
	public User() {
		
	}


	public User(long id, String nom, String prenom, int categorie, String username, String pass) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.categorie = categorie;
		this.username = username;
		this.pass = pass;
	}


	public User(String nom, String prenom, int categorie, String username, String pass) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.categorie = categorie;
		this.username = username;
		this.pass = pass;
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


	public int getCategorie() {
		return categorie;
	}


	public void setCategorie(int categorie) {
		this.categorie = categorie;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPass() {
		return pass;
	}


	public void setPass(String pass) {
		this.pass = pass;
	}
	
	
	
}
