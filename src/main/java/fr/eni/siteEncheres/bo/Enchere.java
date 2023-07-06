package fr.eni.siteEncheres.bo;

import java.sql.Date;

public class Enchere {
	
	private Date dateEnchere;
	private int montantEnchere;
	
	private Integer idUtilisateur;
	private Integer idArticle;
	
	public Enchere() {
	}
	
	
	public Enchere(Integer idUtilisateur, Integer idArticle, Date dateEnchere, int montantEnchere) {
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
	
	public Integer getIdUtilisateur() {
		return idUtilisateur;
	}

	public void setIdUtilisateur(Integer idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}

	public Integer getIdArticle() {
		return idArticle;
	}

	public void setIdArticle(Integer idArticle) {
		this.idArticle = idArticle;
	}

	@Override
	public String toString() {
		return "Enchere [dateEnchere=" + dateEnchere + ", montantEnchere=" + montantEnchere + ", idUtilisateur="
				+ idUtilisateur + ", idArticle=" + idArticle + "]";
	}


}
