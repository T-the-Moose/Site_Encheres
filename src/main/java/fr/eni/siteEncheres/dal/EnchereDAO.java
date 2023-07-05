package fr.eni.siteEncheres.dal;

import fr.eni.siteEncheres.bo.Enchere;

public interface EnchereDAO {

	Enchere read(Integer idArticle);
}
