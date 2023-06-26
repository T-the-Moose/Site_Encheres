package fr.eni.siteEncheres.dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import fr.eni.siteEncheres.bo.Utilisateur;

@Repository
public class UtilisateurDaoSqlServerImpl implements UtilisateurDAO {
	
	private final static String SELECT_ALL = "SELECT idUtilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, credit  FROM UTILISATEURS";
	private final static String FIND_BY_ID = "SELECT idUtilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, credit FROM UTILISATEURS WHERE id=:no_utilisateur";
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	
	class UtilisateurMapper implements RowMapper<Utilisateur> {
		
		@Override
		public Utilisateur mapRow(ResultSet rs, int rowNum) throws SQLException {
			Utilisateur utilisateur = new Utilisateur();
			
			utilisateur.setIdUtilisateur(rs.getInt("idUtilisateur"));
			utilisateur.setPseudo(rs.getString("pseudo"));
			utilisateur.setNom(rs.getString("nom"));
			utilisateur.setPrenom(rs.getString("prenom"));
			utilisateur.setEmail(rs.getString("email"));
			utilisateur.setTelephone(rs.getString("telephone"));
			utilisateur.setRue(rs.getString("rue"));
			utilisateur.setCodePostal(rs.getString("code_postal"));
			utilisateur.setVille(rs.getString("ville"));
			utilisateur.setCredit(rs.getInt("credit"));
			
			return utilisateur;
		}
	}
	
	@Override
	public List<Utilisateur> findAll() {
		List<Utilisateur> listeUtilisateur = namedParameterJdbcTemplate.query(SELECT_ALL, new UtilisateurMapper());
		return listeUtilisateur;
	}
	
	@Override
	public Utilisateur read(Integer idUtilisateur) {
		MapSqlParameterSource paramSrc = new MapSqlParameterSource ("id", idUtilisateur);
		Utilisateur utilisateur = namedParameterJdbcTemplate.queryForObject(FIND_BY_ID, paramSrc, new UtilisateurMapper());
		return utilisateur;
	}
	
	
}
