package fr.eni.siteEncheres.ihm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import fr.eni.siteEncheres.bll.CategorieServiceImpl;
import fr.eni.siteEncheres.bo.Categorie;

@Component
public class StringToCategorieConverter implements Converter<String, Categorie>{

	private CategorieServiceImpl service;
	
	@Autowired
	public void setCategorieService(CategorieServiceImpl service) {
		this.service = service;
	}
	
	@Override
	public Categorie convert(String idCateogire) {
		Integer idCategorie = Integer.parseInt(idCateogire);
		return service.findById(idCategorie);
	}
	
}
