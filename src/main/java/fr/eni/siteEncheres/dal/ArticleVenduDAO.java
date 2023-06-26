package fr.eni.siteEncheres.dal;

import java.util.List;

import fr.eni.siteEncheres.bo.ArticleVendu;

public interface ArticleVenduDAO {

	List<ArticleVendu> findAll();

	ArticleVendu read(Integer idArticle);

}
