package fr.eni.siteEncheres.bll;

import java.util.List;

import fr.eni.siteEncheres.bo.Categorie;

public interface CategorieService {
	
	List<Categorie> getCategorie();
	
	Categorie findById(Integer idCategorie);

}
