package fr.eni.siteEncheres.ihm;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {

    private static final String PATH = "/error";

    @RequestMapping(value = PATH)
    public String handleError(Model model) {
        // Ajoute les informations d'erreur au modèle
        //model.addAttribute("errorMessage", "Page introuvable");

        // Retourne la vue d'erreur personnalisée
        return "error";
    }


    public String getErrorPath() {
        return PATH;
    }
}