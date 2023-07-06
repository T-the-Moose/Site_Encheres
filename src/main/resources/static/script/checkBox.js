function activerCheckbox() {
  var choix = document.getElementsByName("achatvente");

  for (var i = 0; i < choix.length; i++) {
    choix[i].addEventListener('change', function() {
      if (this.checked) {
        if (this.value === "achats") {
          document.getElementById("encheresOuverte").disabled = false;
          document.getElementById("mesEncheresEnCours").disabled = false;
          document.getElementById("mesEncheresRemportees").disabled = false;
          document.getElementById("mesVentesEnCours").disabled = true;
          document.getElementById("ventesNoDebutees").disabled = true;
          document.getElementById("ventesTerminees").disabled = true;
          
          // Décocher les cases à cocher des ventes
          document.getElementById("mesVentesEnCours").checked = false;
          document.getElementById("ventesNoDebutees").checked = false;
          document.getElementById("ventesTerminees").checked = false;
        } else if (this.value === "ventes") {
          document.getElementById("encheresOuverte").disabled = true;
          document.getElementById("mesEncheresEnCours").disabled = true;
          document.getElementById("mesEncheresRemportees").disabled = true;  
          document.getElementById("mesVentesEnCours").disabled = false;
          document.getElementById("ventesNoDebutees").disabled = false;
          document.getElementById("ventesTerminees").disabled = false;
          
          // Décocher les cases à cocher des achats
          document.getElementById("encheresOuverte").checked = false;
          document.getElementById("mesEncheresEnCours").checked = false;
          document.getElementById("mesEncheresRemportees").checked = false;
        }
      }
    });
  }
}

// Appeler la fonction pour activer le gestionnaire d'événements
activerCheckbox();

function afficherApercuImage(event) {
  var input = event.target;
  if (input.files && input.files[0]) {
    var reader = new FileReader();

    reader.onload = function(e) {
      var apercu = document.getElementById("apercuImage");
      apercu.src = e.target.result;
      apercu.style.display = "block";
    };

    reader.readAsDataURL(input.files[0]);
  }
}
// date 

 document.addEventListener("DOMContentLoaded", function() {
        var date1Input = document.getElementById("debutEnchere");
        var date2Input = document.getElementById("finEnchere");

        // Set the minimum value for date2 based on date1
        date1Input.addEventListener("change", function() {
            var date1 = new Date(date1Input.value);
            var date2Min = new Date(date1.getTime() + (24 * 60 * 60 * 1000)); // Add one day (24 hours)
            date2Input.min = date2Min.toISOString().split("T")[0];
        });
    });


function afficherFinEnchere() {
    var dateFinEncheresElement = document.getElementById("dateFinEncheres");
    
    var propositionElement = document.querySelector('.block-description.proposition-fin');
    var propositionElement2 = document.querySelector('.block-description.proposition-debut');
    
    
    if (dateFinEncheresElement) {
        var dateFinEncheresValue = new Date(dateFinEncheresElement.textContent);
        var dateActuelle = new Date();

        if (dateActuelle > dateFinEncheresValue) {

            if (propositionElement2) {
                propositionElement2.style.display = 'none';
            }
        } else {

            if (propositionElement) {
                propositionElement.style.display = 'none';
                propositionElement2.style.display = 'flex';
            }
        }
    }
}


document.addEventListener("DOMContentLoaded", function() {
    afficherFinEnchere();
});

document.addEventListener("DOMContentLoaded", function() {
  var formulaire = document.getElementById("encherir"); // Remplacez "encherir" par l'ID de votre formulaire

  formulaire.addEventListener("submit", function(e) {
    var maProposition = parseInt(document.getElementById("maProposition").value); // Remplacez "prixEnchere" par l'ID de votre champ de prix d'enchère
    //var meilleurOffre = parseInt(document.getElementById("meilleurOffre").dataset.meilleurOffreTh); // Remplacez "meilleurOffre" par l'ID de votre champ de meilleure offre
    var meilleurOffreElement = document.getElementById("meilleurOffre");
	var meilleurOffre = parseInt(meilleurOffreElement.textContent);

	var creditUtilisateurElement = document.getElementById("creditUtilisateur");
	var creditUtilisateurText = creditUtilisateurElement.textContent;
	var creditUtilisateur = parseInt(creditUtilisateurText);
    
    if ((maProposition > meilleurOffre) && (maProposition <= creditUtilisateur)) {
      // Effectuez les actions nécessaires en cas de succès de l'enchère
		var erreurMessage = document.getElementById("erreurMessage");
      		erreurMessage.innerHTML = "Votre enchère est bonne !!!";
      		erreurMessage.style.display = "block";
      // Supprimez le message d'erreur s'il existe
       erreurMessage = document.getElementById("erreurMessage");
      erreurMessage.style.display = "none";

      // Envoie le formulaire
      formulaire.submit();
    } else {
		if(meilleurOffre> maProposition){
			erreurMessage = document.getElementById("erreurMessage");
      		erreurMessage.innerHTML = "Votre enchère doit être supérieure au montant de la meilleure offre !!!";
      		erreurMessage.style.display = "block";
		} else {
			erreurMessage = document.getElementById("erreurMessage");
      		erreurMessage.innerHTML = "Votre offre est suppèrieure à votre crédit !!!";
      		erreurMessage.style.display = "block";
		}
      

      // Empêche l'envoi du formulaire
      e.preventDefault();
    }
  });
});










