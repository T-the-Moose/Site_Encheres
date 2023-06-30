package fr.eni.siteEncheres.bll;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.eni.siteEncheres.bo.ArticleVendu;
import fr.eni.siteEncheres.bo.Utilisateur;
import fr.eni.siteEncheres.dal.ArticleVenduDAO;

@Service("articleVenduService")
public class ArticleVenduServiceImpl implements ArticleVenduService {
	
	@Autowired
	private ArticleVenduDAO articleVenduDAO;

	@Override
	public List<ArticleVendu> getArticleVendu() {
		return articleVenduDAO.findAll();
	}

	@Override
	public ArticleVendu findById(Integer idArticle) {
		return articleVenduDAO.read(idArticle);
	}

	@Override
	public void enregistrerArticleVendu (ArticleVendu articleVendu, Utilisateur utilisateur) {
		articleVenduDAO.save(articleVendu, utilisateur);
	}
	
}
