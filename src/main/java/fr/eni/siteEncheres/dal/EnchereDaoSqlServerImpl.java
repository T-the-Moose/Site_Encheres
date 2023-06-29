package fr.eni.siteEncheres.dal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class EnchereDaoSqlServerImpl implements EnchereDAO {
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	String query = "SELECT t1.*, t2.nom FROM table1 t1 INNER JOIN table2 t2 ON t1.id = t2.table1_id";

}
