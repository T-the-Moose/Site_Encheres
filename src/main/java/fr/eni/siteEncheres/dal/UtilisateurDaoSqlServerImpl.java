package fr.eni.siteEncheres.dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import fr.eni.siteEncheres.bo.Utilisateur;

@Repository
public class UtilisateurDaoSqlServerImpl implements UtilisateurDAO {
	
	private final static String SELECT_ALL = "SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, credit  FROM UTILISATEURS";
	private final static String FIND_BY_ID = "SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, credit FROM UTILISATEURS WHERE no_utilisateur=?";
	private final static String UPDATE = "update utilisateurs set pseudo=:pseudo, nom=:nom, prenom=:prenom, email=:email, telephone=:telephone, rue=:rue, code_postal=:codePostal, ville=:ville, mot_de_passe=:motDePasse where no_utilisateur=:idUtilisateur ";
	private final static String DELETE = "update utilisateurs set activer=1 where no_utilisateur=:idUtilisateur";
	private final static String SELECT_UTILISATEUR = "SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, credit  FROM UTILISATEURS WHERE pseudo=?";
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	NamedParameterJdbcTemplate t;
	class UtilisateurMapper implements RowMapper<Utilisateur> {
		
		@Override
		public Utilisateur mapRow(ResultSet rs, int rowNum) throws SQLException {
			Utilisateur utilisateur = new Utilisateur();
			
			utilisateur.setIdUtilisateur(rs.getInt("no_utilisateur"));
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
	
	// Methode findById
	
	
	public Utilisateur read (Integer idUtilisateur) {
		t = namedParameterJdbcTemplate;
	    	    
	    Utilisateur utilisateur = t.getJdbcOperations().queryForObject(FIND_BY_ID, new UtilisateurMapper(), idUtilisateur );
	    
		return utilisateur;
	}
	
	
    public void insert(Utilisateur utilisateur ) {
    	
    	if(utilisateur.getIdUtilisateur()!=null) {
    		namedParameterJdbcTemplate.update(UPDATE,
    				new BeanPropertySqlParameterSource(utilisateur));    		
    	} else {
    		t = namedParameterJdbcTemplate;
            t.getJdbcOperations().update(
                " INSERT INTO utilisateurs ( pseudo, nom, prenom , email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur, activer ) " +
                " VALUES ( ? , ? , ? , ? , ? , ? , ? , ? , ? , 0 , 0 , 0)",
                utilisateur.getPseudo(),
                utilisateur.getNom(),
                utilisateur.getPrenom(),
                utilisateur.getEmail(),
                utilisateur.getTelephone(), 
                utilisateur.getRue(), 
                utilisateur.getCodePostal(), 
                utilisateur.getVille(), 
                utilisateur.getMotDePasse());
    	}
    	
    	
    }
	

	
	@Override
	public List<Utilisateur> findAll() {
		List<Utilisateur> listeUtilisateur = namedParameterJdbcTemplate.query(SELECT_ALL, new BeanPropertyRowMapper<>(Utilisateur.class));
		return listeUtilisateur;
	}


	@Override
	public void delete(Utilisateur utilisateur) {
		namedParameterJdbcTemplate.update(DELETE,
				new BeanPropertySqlParameterSource(utilisateur));
	}


	@Override
	public Utilisateur findUser(String username) {
		t = namedParameterJdbcTemplate;
		Utilisateur utilisateur = t.getJdbcOperations().queryForObject(SELECT_UTILISATEUR, new UtilisateurMapper(), username );
		return utilisateur;
	}
	
	

}
