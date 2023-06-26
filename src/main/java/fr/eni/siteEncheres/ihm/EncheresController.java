package fr.eni.siteEncheres.ihm;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EncheresController {
	
	@GetMapping({"/", "/accueil"})
	public String afficherAccueil() {
		return "PageAccueilNonConnecte";
	}
	
	@GetMapping("/connexion")
	public String afficherPageConnexion() {
		return "PageConnexion";
	}
	
	@GetMapping("/inscription")
	public String afficherPageInscription() {
		return "PageCreerCompte";
	}
	
	@GetMapping("/encheres")
	public String afficherPageEncheres() {
		return "PageListeEncheresConnecte";
	}
	
	@GetMapping("/liste-encheres/mes-ventes")
	public String afficherPageMesVentes() {
		return "PageListeEncheresMesVentes";
	}
	
	@GetMapping("/profil")
	public String afficherPageProfil() {
		return "PageMonProfil";
	}
	
	@GetMapping("/vendre")
	public String afficherPageVendre() {
		return "PageVendreUnActicle";
	}
	
	@GetMapping("/vendre/modif")
	public String afficherPageEnchereNonCommencee() {
		return "PageEnchereNonCommencee";
	}
	
	@GetMapping("/encherir")
	public String afficherPageEncherir() {
		return "PageEncherir";
	}
	
	@GetMapping("/acquisition")
	public String afficherPageAcquisition() {
		return "PageAcquisition";
	}
	
	@GetMapping("/ma-vente-fini")
	public String afficherPageMaFinVente() {
		return "PageDetailMaVenteFinEnchere";
	}

}
