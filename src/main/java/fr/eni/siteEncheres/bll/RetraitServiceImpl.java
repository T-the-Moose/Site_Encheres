package fr.eni.siteEncheres.bll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.eni.siteEncheres.bo.Retrait;
import fr.eni.siteEncheres.dal.RetraitDAO;

@Service("retraitService")
public class RetraitServiceImpl implements RetraitService{

	@Autowired
	private RetraitDAO retraitDAO;
	

}
