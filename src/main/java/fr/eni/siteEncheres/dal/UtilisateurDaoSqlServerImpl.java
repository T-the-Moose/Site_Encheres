package fr.eni.siteEncheres.dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import fr.eni.siteEncheres.bo.Utilisateur;

@Repository
public class UtilisateurDaoSqlServerImpl implements UtilisateurDAO {
	
	private final static String SELECT_ALL = "SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, credit  FROM UTILISATEURS";
	private final static String FIND_BY_ID = "SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, credit FROM UTILISATEURS WHERE no_utilisateur=?";
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	
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
			
			System.out.println(utilisateur);
			
			return utilisateur;
		}
	}
	
	// Methode findById
	NamedParameterJdbcTemplate t;
	
	public Utilisateur read (Integer idUtilisateur) {
		t = namedParameterJdbcTemplate;
	    
	    System.out.println("L id de l uilisateur est " + idUtilisateur);
	    
	    Utilisateur utilisateur = t.getJdbcOperations().queryForObject(FIND_BY_ID, new UtilisateurMapper(), idUtilisateur );
	    
		return utilisateur;
	}
	
	
    public void insert(Utilisateur utilisateur ) {
    	t = namedParameterJdbcTemplate;
        t.getJdbcOperations().update(
            " INSERT INTO utilisateurs ( pseudo, nom, prenom , email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur ) " +
            " VALUES ( ? , ? , ? , ? , ? , ? , ? , ? , ? , 0 , 0 )", utilisateur.getPseudo(), utilisateur.getNom(), utilisateur.getPrenom(), utilisateur.getEmail(),utilisateur.getTelephone(), utilisateur.getRue(), utilisateur.getCodePostal(), utilisateur.getVille(), utilisateur.getMotDePasse());
    }
	
//	@Override
//	public Utilisateur read(Integer idUtilisateur) {
//		MapSqlParameterSource paramSrc = new MapSqlParameterSource ("no_utilisateur", idUtilisateur);
//		Utilisateur utilisateur = namedParameterJdbcTemplate.queryForObject(FIND_BY_ID, paramSrc, new UtilisateurMapper());
//		
//		System.out.println(idUtilisateur);
//		
//		return utilisateur;
//
//	}
	
	@Override
	public List<Utilisateur> findAll() {
		List<Utilisateur> listeUtilisateur = namedParameterJdbcTemplate.query(SELECT_ALL, new BeanPropertyRowMapper<>(Utilisateur.class));
		return listeUtilisateur;
	}
	
	
//	@Override
//    public Utilisateur read(Integer idUtilisateur) {    
//
//       String sql = "SELECT * FROM UTILISATEURS WHERE no_utilisateur = ?";
//
//       Utilisateur utilisateur = jdbcTemplate.queryForObject(sql, new Object[]{idUtilisateur}, new int[] { java.sql.Types.INTEGER }, new RowMapper<Utilisateur>() {
//
//       @Override
//
//       public Utilisateur mapRow(ResultSet rs, int rowNum) throws SQLException {
//           // Récupérer les valeurs des colonnes du film
//           Integer id = rs.getInt("no_utilisateur");
//           String pseudo = rs.getString("pseudo");
//           String nom = rs.getString("nom");
//           String prenom = rs.getString("prenom");
//           String email = rs.getString("email");
//           String telephone = rs.getString("telephone");
//           String rue = rs.getString("rue");
//           String codePostal = rs.getString("code_postal");
//           String ville = rs.getString("ville");
//           Integer credit = rs.getInt("credit");
//
//           // Créer et retourner l'objet Film avec les valeurs récupérées
//           return new Utilisateur(id, pseudo, nom, prenom, email, telephone, rue, codePostal, ville, credit);
//           }
//       });
//       return utilisateur;
//    }
}
