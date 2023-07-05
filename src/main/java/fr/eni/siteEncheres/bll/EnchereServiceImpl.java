package fr.eni.siteEncheres.bll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.eni.siteEncheres.bo.Enchere;
import fr.eni.siteEncheres.dal.EnchereDAO;

@Service("enchereService")
public class EnchereServiceImpl implements EnchereService{
	
	@Autowired
	private EnchereDAO enchereDAO;

	@Override
	public Enchere findById(Integer idUtilisateur) {
		
		return enchereDAO.read(idUtilisateur);
	}

}
