package fr.eni.siteEncheres.bo;

import java.sql.Date;

public class Enchere {
	
	private Date dateEnchere;
	private int montantEnchere;
	
	private int idUtilisateur;
	private int idArticle;
	
	public Enchere() {
	}
	
	
	public Enchere(int idUtilisateur, int idArticle, Date dateEnchere, int montantEnchere) {
		this.idUtilisateur = idUtilisateur;
		this.idArticle = idArticle;
		this.dateEnchere = dateEnchere;
		this.montantEnchere = montantEnchere;
	}

	public Date getDateEnchere() {
		return dateEnchere;
	}

	public void setDateEnchere(Date dateEnchere) {
		this.dateEnchere = dateEnchere;
	}

	public int getMontantEnchere() {
		return montantEnchere;
	}

	public void setMontantEnchere(int montantEnchere) {
		this.montantEnchere = montantEnchere;
	}
	
	public int getIdUtilisateur() {
		return idUtilisateur;
	}

	public void setIdUtilisateur(int idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}

	public int getIdArticle() {
		return idArticle;
	}

	public void setIdArticle(int idArticle) {
		this.idArticle = idArticle;
	}

	@Override
	public String toString() {
		return "Enchere [dateEnchere=" + dateEnchere + ", montantEnchere=" + montantEnchere + ", idUtilisateur="
				+ idUtilisateur + ", idArticle=" + idArticle + "]";
	}


}
