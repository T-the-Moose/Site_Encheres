package fr.eni.siteEncheres.dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import fr.eni.siteEncheres.bo.ArticleVendu;


@Repository
public class ArticleVenduDaoSqlServerImpl implements ArticleVenduDAO{

	private final static String SELECT_ALL = "SELECT no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie FROM ARTICLES_VENDUS";
	private final static String FIND_BY_ID = "SELECT * FROM ARTICLES_VENDUS WHERE id=:no_article";
	
	@Autowired
	private UtilisateurDAO utilisateurDAO;
	
	@Autowired
	private CategorieDAO categorieDAO;
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	
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
	public ArticleVendu read(Integer idArticle) {
		MapSqlParameterSource paramSrc = new MapSqlParameterSource ("no_article", idArticle);
		ArticleVendu articleVendu = namedParameterJdbcTemplate.queryForObject(FIND_BY_ID, paramSrc, new ArticleVenduMapper());
		return articleVendu;
	}
	
}
