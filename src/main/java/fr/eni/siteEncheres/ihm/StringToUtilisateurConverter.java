package fr.eni.siteEncheres.ihm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


import fr.eni.siteEncheres.bll.UtilisateurServiceImpl;
import fr.eni.siteEncheres.bo.Utilisateur;


@Component
public class StringToUtilisateurConverter implements Converter<String, Utilisateur>{

private UtilisateurServiceImpl service;
	
	@Autowired
	public void setCategorieService(UtilisateurServiceImpl service) {
		this.service = service;
	}
	
	@Override
	public Utilisateur convert(String idUtilisateur) {

		Integer theid = Integer.parseInt(idUtilisateur);
		return service.findById(theid);
	}
}
