package fr.eni.siteEncheres.bll;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.eni.siteEncheres.bo.Categorie;
import fr.eni.siteEncheres.dal.CategorieDAO;

@Service("categorieService")
public class CategorieServiceImpl implements CategorieService{
	
	@Autowired
	private CategorieDAO categorieDAO;
	
	@Override
	public List<Categorie> getCategorie() {
		return categorieDAO.findAll();
	}

	@Override
	public Categorie findById(Integer idCategorie) {
		return categorieDAO.read(idCategorie);
	}

}
