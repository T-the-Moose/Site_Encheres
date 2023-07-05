package fr.eni.siteEncheres.bll;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import fr.eni.siteEncheres.bo.Utilisateur;
import fr.eni.siteEncheres.dal.UtilisateurDAO;

@Service("utilisateurService")
public class UtilisateurServiceImpl implements UtilisateurService {

	@Autowired
	private UtilisateurDAO utilisateurDAO;
	
	private PasswordEncoder passwordEncoder;
	
	public UtilisateurServiceImpl(UtilisateurDAO utilisateurDAO, PasswordEncoder passwordEncoder) {
		this.utilisateurDAO = utilisateurDAO;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public List<Utilisateur> getUtilisateur() {
		return utilisateurDAO.findAll();
	}

	@Override
	public Utilisateur findById(Integer idUtilisateur) {
		return utilisateurDAO.read(idUtilisateur);
	}

	@Override
	public void enregistrerUtilisateur( Utilisateur utilisateur) {
		utilisateur.setMotDePasse( passwordEncoder.encode( utilisateur.getMotDePasse()) );
		utilisateurDAO.insert(utilisateur);		
	}

	@Override
	public void supprimerUtilisateur(Utilisateur utilisateur) {
		utilisateurDAO.delete(utilisateur);
		
	}

	@Override
	public Utilisateur findByUserName(String username) {
		
		return utilisateurDAO.findUser(username);
	}

	@Override
	public void retirerPoints(Integer pointsRequis, Integer idUtilisateur) {
		utilisateurDAO.deletePoints(pointsRequis, idUtilisateur);
	}

	@Override
	public void ajouterPoint(Integer sommeARecredite, Integer idAncienEncherisseur) {
		utilisateurDAO.addPoints(sommeARecredite, idAncienEncherisseur);
		
	}


		
}
