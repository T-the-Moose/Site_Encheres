package fr.eni.siteEncheres.dal;

import java.util.List;

import fr.eni.siteEncheres.bo.ArticleVendu;
import fr.eni.siteEncheres.bo.Utilisateur;

public interface ArticleVenduDAO {

	List<ArticleVendu> findAll();

	ArticleVendu read(Integer idArticle);

	void save(ArticleVendu articleVendu, Utilisateur utilisateur);

	List<ArticleVendu> findAllArticleParCat(Integer idCategorie);

}
