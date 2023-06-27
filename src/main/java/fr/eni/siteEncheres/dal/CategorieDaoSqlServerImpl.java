package fr.eni.siteEncheres.dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import fr.eni.siteEncheres.bo.Categorie;

@Repository
public class CategorieDaoSqlServerImpl implements CategorieDAO{

	private final static String SELECT_ALL = "SELECT no_categorie, libelle FROM CATEGORIES";
	private final static String FIND_BY_ID = "SELECT * FROM CATEGORIES WHERE id=:no_categorie";
	
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	class CategorieMapper implements RowMapper<Categorie> {

		@Override
		public Categorie mapRow(ResultSet rs, int rowNum) throws SQLException {
			Categorie categorie = new Categorie();
			
			categorie.setIdCategorie(rs.getInt("no_categorie"));
			categorie.setLibelle(rs.getString("libelle"));
			
			return categorie;
		}
	}
	
	@Override
	public List<Categorie> findAll() {
		List<Categorie> listeCategorie = namedParameterJdbcTemplate.query(SELECT_ALL, new CategorieMapper());
		return listeCategorie;
	}

	@Override
	public Categorie read(Integer idCategorie) {
		MapSqlParameterSource paramSrc = new MapSqlParameterSource ("no_categorie", idCategorie);
		Categorie categorie = namedParameterJdbcTemplate.queryForObject(FIND_BY_ID, paramSrc, new CategorieMapper());
		return categorie;
	}

	

}
