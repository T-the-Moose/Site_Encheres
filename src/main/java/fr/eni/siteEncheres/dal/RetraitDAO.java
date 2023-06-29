package fr.eni.siteEncheres.dal;

import fr.eni.siteEncheres.bo.Retrait;

public interface RetraitDAO {

	Retrait read(Integer idRetrait);

	void save(Retrait retrait);
}
