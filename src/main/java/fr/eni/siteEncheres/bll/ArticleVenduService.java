package fr.eni.siteEncheres.bll;

import java.util.List;

import fr.eni.siteEncheres.bo.ArticleVendu;

public interface ArticleVenduService {
	
	List<ArticleVendu> getArticleVendu();
	
	ArticleVendu findById(Integer idArticle);

}
