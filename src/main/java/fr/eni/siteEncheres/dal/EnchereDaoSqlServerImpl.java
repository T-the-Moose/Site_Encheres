package fr.eni.siteEncheres.dal;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import fr.eni.siteEncheres.bo.ArticleVendu;
import fr.eni.siteEncheres.bo.Enchere;
import fr.eni.siteEncheres.bo.Utilisateur;

@Repository
public class EnchereDaoSqlServerImpl implements EnchereDAO {
	
	private final static String FIND_BY_ID = "SELECT no_utilisateur, no_article, date_enchere, montant_enchere FROM ENCHERES WHERE no_utilisateur=?";

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	NamedParameterJdbcTemplate t;
	
	class EnchereMapper implements RowMapper<Enchere> {
		
		@Override
		public Enchere mapRow(ResultSet rs, int rowNum) throws SQLException {
			Enchere enchere = new Enchere();
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
	
}
