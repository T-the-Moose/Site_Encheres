package fr.eni.siteEncheres.bll;

import java.util.List;

import fr.eni.siteEncheres.bo.ArticleVendu;
import jakarta.validation.Valid;

public interface ArticleVenduService {
	
	List<ArticleVendu> getArticleVendu();
	
	ArticleVendu findById(Integer idArticle);

	void enregistrerArticleVendu(ArticleVendu articleVendu, Integer idUtilisateur);


}
