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

