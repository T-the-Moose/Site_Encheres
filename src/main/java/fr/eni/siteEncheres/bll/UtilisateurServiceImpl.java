package fr.eni.siteEncheres.bll;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.eni.siteEncheres.bo.Utilisateur;
import fr.eni.siteEncheres.dal.UtilisateurDAO;

@Service("utilisateurService")
public class UtilisateurServiceImpl implements UtilisateurService {

	@Autowired
	private UtilisateurDAO utilisateurDAO;
	
	
	@Override
	public List<Utilisateur> getUtilisateur() {
		return utilisateurDAO.findAll();
	}

	@Override
	public Utilisateur findById(Integer idUtilisateur) {
		return utilisateurDAO.read(idUtilisateur);
	}
	
}
