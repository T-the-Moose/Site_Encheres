package fr.eni.siteEncheres.bll;

import fr.eni.siteEncheres.bo.ArticleVendu;
import fr.eni.siteEncheres.bo.Enchere;
import fr.eni.siteEncheres.bo.Utilisateur;

public interface EnchereService {
	
	Enchere findById(Integer idUtilisateur);

	void enregistrerEnchere(Enchere enchere, Integer prixEnchere, ArticleVendu articleVendu, Utilisateur utilisateur);
}
