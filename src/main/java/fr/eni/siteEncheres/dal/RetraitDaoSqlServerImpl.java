package fr.eni.siteEncheres.dal;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import fr.eni.siteEncheres.bo.Retrait;

@Repository
public class RetraitDaoSqlServerImpl implements RetraitDAO{
	
	private final static String FIND_BY_ID = "SELECT * FROM RETRAITS WHERE no_article=?";
	
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	NamedParameterJdbcTemplate t;
	
	class RetraitMapper implements RowMapper<Retrait> {

		@Override
		public Retrait mapRow(ResultSet rs, int rowNum) throws SQLException {
			Retrait retrait = new Retrait();
			
			retrait.setRue(rs.getString("rue"));
			retrait.setCode_Postal(rs.getString("code_postal"));
			retrait.setVille(rs.getString("ville"));
			
			return retrait;
		}
	}
	
	@Override
	public Retrait read(Integer idRetrait) {
		t = namedParameterJdbcTemplate;
		Retrait retrait = t.getJdbcOperations().queryForObject(FIND_BY_ID,new RetraitMapper(), idRetrait);
		System.out.println("voici le retrait = "+ retrait);
		return retrait;
	}
	@Override
	public void save(Retrait retrait) {
		
		
	}

}
