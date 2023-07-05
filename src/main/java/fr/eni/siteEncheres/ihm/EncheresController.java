package fr.eni.siteEncheres.ihm;

import java.security.Principal;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.eni.siteEncheres.bll.ArticleVenduService;
import fr.eni.siteEncheres.bll.CategorieService;
import fr.eni.siteEncheres.bll.UtilisateurService;
import fr.eni.siteEncheres.bo.ArticleVendu;
import fr.eni.siteEncheres.bo.Categorie;
import fr.eni.siteEncheres.bo.Retrait;
import fr.eni.siteEncheres.bo.Utilisateur;
import fr.eni.siteEncheres.dal.UtilisateurDAO;
import jakarta.validation.Valid;

@Controller

public class EncheresController {
	
	private UtilisateurService utilisateurService;
	private ArticleVenduService articleVenduService;
	private CategorieService categorieService;
	
	private UtilisateurDAO utilisateurDAO;
	
	
	public EncheresController(UtilisateurService utilisateurService, ArticleVenduService articleVenduService,
			CategorieService categorieService) {
		this.utilisateurService = utilisateurService;
		this.articleVenduService = articleVenduService;
		this.categorieService = categorieService;
	}
	
	
	@GetMapping({"/", "/accueil"})
	public String afficherAccueil(Model modele) {
		
		List<ArticleVendu> listeArticle = articleVenduService.getArticleVendu();
		modele.addAttribute("articleVendu", listeArticle);
		
		return "PageAccueilNonConnecte";
	}
	
	
	@GetMapping("/accueil/articleParCategorie")
	public String afficherAccueilParCategorie(@RequestParam Integer idCategorie, String filtre, Model modele, Principal principal) {
		
	    List<ArticleVendu> listeArticle;
	    
	    if (filtre != null && !filtre.isEmpty()) {
	        listeArticle = articleVenduService.getArticleVenduParCategorieEtFiltre(idCategorie, filtre);
	    } else {
	        listeArticle = articleVenduService.getArticleVenduParCategorie(idCategorie);
	    }
	  
	    modele.addAttribute("articleVendu", listeArticle);
	    
		// Pour l'affichage des points dans le header
		String username = principal.getName();
		Utilisateur utilisateur = utilisateurService.findByUserName(username);
		modele.addAttribute("utilisateur", utilisateur);
		//System.out.println(utilisateur);
	    
	    return "PageAccueilNonConnecte";
	}
	
	@GetMapping("/connexion")
	public String afficherPageConnexion() {
		return "PageConnexion";
	}
	
	@GetMapping("/inscription")
	public String afficherPageInscription(@ModelAttribute Utilisateur utilisateur) {
			return "PageCreerCompte";
	}
	
	
	@PostMapping("/profil/modifier")
	public String afficherPageInscriptionErreur(@Valid @ModelAttribute Utilisateur utilisateur,
			BindingResult validationResultat, Model modele ,Principal principal) {
		
		if(validationResultat.hasErrors()) {
			return "PageCreerCompte";
		}
		
		utilisateurService.enregistrerUtilisateur(utilisateur);
	
		// Pour l'affichage des points dans le header
		String username = principal.getName();
		utilisateur = utilisateurService.findByUserName(username);
		modele.addAttribute("utilisateur", utilisateur);
		//System.out.println(utilisateur);
		
		return "redirect:/connexion";
	}
	

	@RequestMapping("/encheres")
	public String afficherPageEncheres(Utilisateur utilisateur, Model modele, Principal principal) {
		
		Categorie categorie = categorieService.findById(1);
		modele.addAttribute("categorie", categorie);
		List<ArticleVendu> listeArticle = articleVenduService.getArticleVendu();	
		modele.addAttribute("articleVendu", listeArticle);  
		
		
		// Pour l'affichage des points dans le header
		String username = principal.getName();
		utilisateur = utilisateurService.findByUserName(username);
		modele.addAttribute("utilisateur", utilisateur);
		//System.out.println(utilisateur);
		  
		return "PagesListeEncheresConnecte";
	}
	
	
	@GetMapping("/liste-encheres/mes-ventes")
	public String afficherPageMesVentes(@RequestParam Integer idCategorie, String filtre, Model modele, Principal principal) {
		
	    List<ArticleVendu> listeArticle;
	    
	    if (filtre != null && !filtre.isEmpty()) {
	        listeArticle = articleVenduService.getArticleVenduParCategorieEtFiltre(idCategorie, filtre);
	    } else {
	        listeArticle = articleVenduService.getArticleVenduParCategorie(idCategorie);
	    }
	  
	    modele.addAttribute("articleVendu", listeArticle);
	    
		// Pour l'affichage des points dans le header
		String username = principal.getName();
		Utilisateur utilisateur = utilisateurService.findByUserName(username);
		modele.addAttribute("utilisateur", utilisateur);
		//System.out.println(utilisateur);
		
		return "PagesListeEncheresConnecte";
	}
	
	
	@GetMapping("/profil")
	public String afficherPageProfil(Model model, Principal principal) {
		
		String username = principal.getName();
		
		Utilisateur utilisateur = utilisateurService.findByUserName(username);
		 model.addAttribute("utilisateur", utilisateur);
		
		return "PageMonProfil";
	}
	
	
	@GetMapping("/modifierProfil")
	public String afficherPagesModifierMonProfil(Model model, Principal principal) {
		String username = principal.getName();	
		Utilisateur utilisateur = utilisateurService.findByUserName(username);
		 model.addAttribute("utilisateur", utilisateur);
		
		return "PageModifierMonProfil";
	}

	
	@PostMapping("/profilModifier")
	public String afficherPageProfilModifier(@Valid @ModelAttribute Utilisateur utilisateur , BindingResult validationResultat ) {
		
		if(validationResultat.hasErrors()) {
						
			return "redirect:/modifierProfil";
		}
		utilisateurService.enregistrerUtilisateur(utilisateur);
		return "redirect:/encheres";
	}
	
	
	@PostMapping("/supprimerProfil")
	public String supprimerProfilUtilisateur(Model modele, Principal principal) {
		String username = principal.getName();	
		Utilisateur utilisateur = utilisateurService.findByUserName(username);
		System.out.println(utilisateur);
		utilisateurService.supprimerUtilisateur(utilisateur);		
		return "redirect:/logout";
	}
	
	
	@GetMapping("/vendre")
	public String afficherPageVendre(Model modele, Principal principal) {
		
		// Pour l'affichage des points dans le header
		// Récupération des infos utilisateur pour affichage de retrait 
		String username = principal.getName();
		Utilisateur utilisateur = utilisateurService.findByUserName(username);
		modele.addAttribute("utilisateur", utilisateur);

		ArticleVendu articleVendu  = new ArticleVendu();
		modele.addAttribute("articleVendu", articleVendu);
		
		Retrait articleRetrait = new Retrait();
		articleRetrait.setRue(utilisateur.getRue());
		articleRetrait.setVille(utilisateur.getVille());
		articleRetrait.setCode_Postal(utilisateur.getCodePostal());
		
		modele.addAttribute("retrait", articleRetrait);
		System.out.println(utilisateur);
	    
		return "PageVendreUnArticle";
	}
	
	
	@PostMapping("/vendre/valider")
	public String afficherVendreArticle( ArticleVendu articleVendu, Utilisateur utilisateur, Model modele ,Principal principal) {
		
		// Pour l'affichage des points dans le header
				String username = principal.getName();
				utilisateur = utilisateurService.findByUserName(username);
				modele.addAttribute("utilisateur", utilisateur);
				
		articleVenduService.enregistrerArticleVendu(articleVendu, utilisateur);
		
		//System.out.println(utilisateur);
		
		return "redirect:/encheres";
	}
	
	
	@GetMapping("/vendre/modif")
	public String afficherPageEnchereNonCommencee(Utilisateur utilisateur, Model modele ,Principal principal) {
		
		
		// Pour l'affichage des points dans le header
		String username = principal.getName();
		utilisateur = utilisateurService.findByUserName(username);
		modele.addAttribute("utilisateur", utilisateur);
		//System.out.println(utilisateur);
		
		return "PageEnchereNonCommencee";
	}
	
	
	@RequestMapping("/encherir")
	public String afficherPageEncherir(@RequestParam("idArticle") Integer idArticle, Utilisateur utilisateur, Model model, Principal principal, @RequestParam("prixEnchere") int prixEnchere) {
		
		ArticleVendu articleVendu = articleVenduService.findById(idArticle);
		model.addAttribute("articleVendu", articleVendu);
		
		// Pour l'affichage des points dans le header
		String username = principal.getName();
		utilisateur = utilisateurService.findByUserName(username);
		model.addAttribute("utilisateur", utilisateur);
		
		
		
		
		
		
		
		// Récupérez l'article et l'utilisateur concernés
		ArticleVendu article = articleVenduService.findById(idArticle);
	    
	    // Vérifiez si le prix de l'enchère est supérieur au prix de départ
	    if (prixEnchere.compareTo(article.getMiseAPrix()) > 0) {
	        // Retirez les points de l'utilisateur
	        utilisateur.retirerPoints(pointsRequis);
	        
	        // Mettez à jour l'article avec le nouveau prix d'enchère
	        article.setMiseAPrix(idArticle);
	        article.setUtilisateurEnchere(utilisateur);
	        
	        // Enregistrez les modifications dans la base de données
	        articleVenduService.enregistrerArticleVendu(articleVendu, utilisateur);
	        utilisateurService.enregistrerUtilisateur(utilisateur);
	        
	        // Effectuez toute autre opération nécessaire
	        
	        return "redirect:/confirmation";
	    } else {
	        // Gérez le cas où l'enchère est invalide (par exemple, afficher un message d'erreur)
	        return "redirect:/erreur";
	    }
		
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
