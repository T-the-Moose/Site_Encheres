package fr.eni.siteEncheres.ihm;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import fr.eni.siteEncheres.bll.ArticleVenduService;
import fr.eni.siteEncheres.bll.CategorieService;
import fr.eni.siteEncheres.bll.UtilisateurService;
import fr.eni.siteEncheres.bo.ArticleVendu;
import fr.eni.siteEncheres.bo.Utilisateur;

@Controller
public class EncheresController {
	
	private UtilisateurService utilisateurService;
	private ArticleVenduService articleVenduService;
	private CategorieService categorieService;
	
	
	public EncheresController(UtilisateurService utilisateurService, ArticleVenduService articleVenduService,
			CategorieService categorieService) {
		this.utilisateurService = utilisateurService;
		this.articleVenduService = articleVenduService;
		this.categorieService = categorieService;
	}
	
	
	@GetMapping({"/", "/accueil"})
	public String afficherAccueil(Model modele) {
		
		// Pas sûr de la récup de tout les articles pour afficher nomArticle/miseAPrix/dateFinEncheres/utilisateur
		List<ArticleVendu> listeArticle = articleVenduService.getArticleVendu();
		modele.addAttribute("articleVendu", listeArticle);
		
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
		return "PagesListeEncheresConnecte";
	}
	
	@GetMapping("/liste-encheres/mes-ventes")
	public String afficherPageMesVentes() {
		return "PageListeEncheresMesVentes";
	}
	
	@GetMapping("/profil")
	public String afficherPageProfil(Integer idUtilisateur, Model modele) {
		
		Utilisateur utilisateur = utilisateurService.findById(idUtilisateur);
		 modele.addAttribute("utilisateur", utilisateur);
		
		return "PageMonProfil";
	}
	
	@GetMapping("/modifierProfil")
	public String afficherPagesModifierMonProfil() {
		return "PageModifierMonProfil";
	}
	
	@GetMapping("/vendre")
	public String afficherPageVendre() {
		return "PageVendreUnArticle";
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
