package fr.eni.siteEncheres.ihm;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EncheresController {
	
	@GetMapping
	public String afficherAccueil() {
		return "index";
	}

}
