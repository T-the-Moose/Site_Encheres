package fr.eni.siteEncheres.dal;

import java.util.List;

import fr.eni.siteEncheres.bo.Categorie;

public interface CategorieDAO {


	List<Categorie> findAll();

	Categorie read(Integer idCategorie);

}
