package fr.eni.siteEncheres.bll;

import java.util.List;

import fr.eni.siteEncheres.bo.ArticleVendu;
import fr.eni.siteEncheres.bo.Utilisateur;

public interface ArticleVenduService {
	
	List<ArticleVendu> getArticleVendu();
	
	ArticleVendu findById(Integer idArticle);

	void enregistrerArticleVendu(ArticleVendu articleVendu, Utilisateur utilisateur);

	List<ArticleVendu> getArticleVenduParCategorie(Integer idCategorie);
	
	List<ArticleVendu> getArticleVenduParCategorieEtFiltre(Integer idCategorie, String filtre);

}
