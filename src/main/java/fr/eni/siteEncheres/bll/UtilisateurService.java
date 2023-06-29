package fr.eni.siteEncheres.bll;

import java.util.List;

import fr.eni.siteEncheres.bo.Utilisateur;

public interface UtilisateurService {
	
	List<Utilisateur> getUtilisateur();

	Utilisateur findById(Integer idUtilisateur);
	
	void enregistrerUtilisateur(Utilisateur utilisateur);

	void supprimerUtilisateur(Utilisateur utilisateur);

}
