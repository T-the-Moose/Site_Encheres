package fr.eni.siteEncheres.dal;

import fr.eni.siteEncheres.bo.ArticleVendu;
import fr.eni.siteEncheres.bo.Enchere;
import fr.eni.siteEncheres.bo.Utilisateur;

public interface EnchereDAO {

	Enchere read(Integer idArticle);

	void save(Enchere enchere, Integer prixEnchere, ArticleVendu articleVendu, Utilisateur utilisateur);

	Integer readAncienEncherisseur(Integer idArticle);

	Integer readAncienOffre(Integer idArticle);
}
