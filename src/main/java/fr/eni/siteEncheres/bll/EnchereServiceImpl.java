package fr.eni.siteEncheres.bll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.eni.siteEncheres.bo.ArticleVendu;
import fr.eni.siteEncheres.bo.Enchere;
import fr.eni.siteEncheres.bo.Utilisateur;
import fr.eni.siteEncheres.dal.EnchereDAO;

@Service("enchereService")
public class EnchereServiceImpl implements EnchereService{
	
	@Autowired
	private EnchereDAO enchereDAO;

	@Override
	public Enchere findById(Integer idUtilisateur) {
		return enchereDAO.read(idUtilisateur);
	}

	@Override
	public void enregistrerEnchere(Enchere enchere, Integer prixEnchere, ArticleVendu articleVendu, Utilisateur utilisateur) {
		enchereDAO.save(enchere, prixEnchere, articleVendu, utilisateur);
		
	}
	
}
