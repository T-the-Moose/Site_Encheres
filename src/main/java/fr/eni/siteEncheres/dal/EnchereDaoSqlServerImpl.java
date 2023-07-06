package fr.eni.siteEncheres.dal;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import fr.eni.siteEncheres.bo.ArticleVendu;
import fr.eni.siteEncheres.bo.Enchere;
import fr.eni.siteEncheres.bo.Utilisateur;

@Repository
public class EnchereDaoSqlServerImpl implements EnchereDAO {
	
	private final static String FIND_BY_ID = "SELECT no_utilisateur, no_article, date_enchere, montant_enchere FROM ENCHERES WHERE no_article=?";
	private final static String UPDATE = "UPDATE ENCHERES SET no_utilisateur=:idUtilisateur, no_article=:idArticle, montant_enchere=:montantEnchere WHERE no_article=:idArticle";
	
	private ArticleVenduDAO articleVenduDAO;
	
	private UtilisateurDAO utilisateurDAO;

	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	
	public EnchereDaoSqlServerImpl (ArticleVenduDAO articleVenduDAO, UtilisateurDAO utilisateurDAO, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.articleVenduDAO = articleVenduDAO;
		this.utilisateurDAO = utilisateurDAO;
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}
	
	NamedParameterJdbcTemplate t;
	
	class EnchereMapper implements RowMapper<Enchere> {
		
		@Override
		public Enchere mapRow(ResultSet rs, int rowNum) throws SQLException {

			Utilisateur utilisateur = new Utilisateur();
			ArticleVendu article = new ArticleVendu();
			
			int id_utilisateur = rs.getInt("no_utilisateur");
			int id_article = rs.getInt("no_article");
			Date date_enchere = rs.getDate("date_enchere");
			int montant = rs.getInt("montant_enchere");			
			
			utilisateur.setIdUtilisateur(id_utilisateur);
			article.setIdArticle(id_article);
			
			return new Enchere(id_utilisateur, id_article, date_enchere, montant);
		}
	}


	@Override
	public Enchere read(Integer idArticle) {
		t = namedParameterJdbcTemplate;
		Enchere enchere = t.getJdbcOperations().queryForObject(FIND_BY_ID, new EnchereMapper(), idArticle );
		return enchere;
	}
	
	
	@Override
	public void save(Enchere enchere, Integer prixEnchere, ArticleVendu articleVendu, Utilisateur utilisateur) {
		
		t = namedParameterJdbcTemplate;
		
    	if(enchere.getIdArticle()!=null) {
    		
    		enchere.setMontantEnchere(prixEnchere);
    		
    		var utilisateurUpdate = utilisateur.getIdUtilisateur();
    		enchere.setIdUtilisateur(utilisateurUpdate);
    		
    		t.update(UPDATE, new BeanPropertySqlParameterSource(enchere));    		
    	} else {
	
        t.getJdbcOperations().update(
        	" INSERT INTO ENCHERES (no_utilisateur, no_article, montant_enchere) " +
        	" VALUES (?, ?, ?)",  
        	
        	utilisateur.getIdUtilisateur(),
        	articleVendu.getIdArticle(),
        	prixEnchere);
    	}
	}
}
