package fr.eni.siteEncheres.bo;

public class Categorie {

	private Integer idCategorie;
	private String libelle;
	
	
	public Categorie() {
		
	}
	
	public Categorie(Integer idCategorie, String libelle) {
		this.idCategorie = idCategorie;
		this.libelle = libelle;
	}


	public Integer getIdCategorie() {
		return idCategorie;
	}


	public void setIdCategorie(Integer idCategorie) {
		this.idCategorie = idCategorie;
	}


	public String getLibelle() {
		return libelle;
	}


	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}


	@Override
	public String toString() {
		return "Categorie [idCategorie=" + idCategorie + ", libelle=" + libelle + "]";
	}
	
}
