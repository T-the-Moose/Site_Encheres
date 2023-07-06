package fr.eni.siteEncheres.dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import fr.eni.siteEncheres.bo.ArticleVendu;
import fr.eni.siteEncheres.bo.Utilisateur;


@Repository
public class ArticleVenduDaoSqlServerImpl implements ArticleVenduDAO{

	private final static String SELECT_ALL = "SELECT no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie FROM ARTICLES_VENDUS";
	private final static String SELECT_ALL_BY_CAT = "SELECT * FROM ARTICLES_VENDUS WHERE no_categorie = ?"; 
	private final static String FIND_BY_ID = "SELECT * FROM ARTICLES_VENDUS WHERE no_article=?";
	private final static String SELECT_MES_VENTES = "SELECT * FROM ARTICLES_VENDUS WHERE no_utilisateur = ?";

	
	private UtilisateurDAO utilisateurDAO;
	
	private CategorieDAO categorieDAO;
	
	private RetraitDAO retraitDAO;
	
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	public ArticleVenduDaoSqlServerImpl (UtilisateurDAO utilisateurDAO, CategorieDAO categorieDAO, RetraitDAO retraitDAO,
			NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.utilisateurDAO = utilisateurDAO;
		this.categorieDAO = categorieDAO;
		this.retraitDAO = retraitDAO;
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}
	NamedParameterJdbcTemplate t;
	
	class ArticleVenduMapper implements RowMapper<ArticleVendu> {

		@Override
		public ArticleVendu mapRow(ResultSet rs, int rowNum) throws SQLException {
			ArticleVendu articleVendu = new ArticleVendu();
			
			articleVendu.setIdArticle(rs.getInt("no_article"));
			articleVendu.setNomArticle(rs.getString("nom_article"));
			articleVendu.setDescription(rs.getString("description"));
			articleVendu.setDateDebutEncheres(rs.getDate("date_debut_encheres"));
			articleVendu.setDateFinEncheres(rs.getDate("date_fin_encheres"));
			articleVendu.setMiseAPrix(rs.getInt("prix_initial"));
			articleVendu.setPrixVente(rs.getInt("prix_vente"));
			
			// Récupération id Utilisateur
			articleVendu.setUtilisateur(utilisateurDAO.read(rs.getInt("no_utilisateur")));

			// Récupération id Categorie
			articleVendu.setCategorie(categorieDAO.read(rs.getInt("no_categorie")));
			
			return articleVendu;
		}
	}

	@Override
	public List<ArticleVendu> findAll() {
		List<ArticleVendu> listeArticle = namedParameterJdbcTemplate.query(SELECT_ALL, new ArticleVenduMapper());
		return listeArticle;
	}
	

	@Override
	public List<ArticleVendu> findAllArticleParCat(Integer idCategorie) {
		List<ArticleVendu> listeArticleParCat = namedParameterJdbcTemplate.getJdbcOperations().query(SELECT_ALL_BY_CAT, new ArticleVenduMapper(), idCategorie);
		return listeArticleParCat;
	}
	
	@Override
	public List<ArticleVendu> findAllArticleParCatEtFiltre (Integer idCategorie, String filtre) {
		String requeteFiltree = SELECT_ALL_BY_CAT;
		if (filtre != null && !filtre.isEmpty()) {
			requeteFiltree += " AND nom_article LIKE '%" + filtre + "%'";
		}
		List<ArticleVendu> listeArticleParCat = namedParameterJdbcTemplate.getJdbcOperations().query(requeteFiltree, new ArticleVenduMapper(), idCategorie);
		
		return listeArticleParCat;
	}
	
	
	@Override
	public ArticleVendu read(Integer idArticle) {
		t = namedParameterJdbcTemplate;
		ArticleVendu articleVendu = t.getJdbcOperations().queryForObject(FIND_BY_ID,new ArticleVenduMapper(), idArticle);
		System.out.println("voici = " + articleVendu);
		return articleVendu;
	}


	@Override
	public void save(ArticleVendu articleVendu, Utilisateur utilisateur) {
		t = namedParameterJdbcTemplate;
        t.getJdbcOperations().update(
        	" INSERT INTO articles_vendus (nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie) " +
        	" VALUES (?, ?, ?, ?, ?, ?, ?, ?)",  
        	articleVendu.getNomArticle(), 
        	articleVendu.getDescription(),
        	articleVendu.getDateDebutEncheres(), 
        	articleVendu.getDateFinEncheres(),
        	articleVendu.getMiseAPrix(),
        	articleVendu.getPrixVente(),
        	utilisateur.getIdUtilisateur(),
        	articleVendu.getCategorie().getIdCategorie()); 
	}

}

    