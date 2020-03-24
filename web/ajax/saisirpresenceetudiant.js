/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Saisir les Presence pour un etudiant de maniere individuelle
 */
     
/**
 *  affichir les creneaux selon matiere date et typeActivite
 * @return {undefined}
 */ 
        function getCreneaux ()
        {
        //recuperer la valeure
        
        var heure = document.getElementById("heureDeb").value;
        var minute =document.getElementById("minute").value;
        var typeCreneau = document.getElementById("typeCreneau").value;
        var d  =document.getElementById("datepicker").value;
        var presence=document.getElementById("presence").value;
                
     
	
        // Objet XMLHttpRequest.
	var xhr = new XMLHttpRequest();

	// Requête au serveur avec les paramètres éventuels.
	xhr.open("GET","ServletSaisir"+"?heure="+heure+"&typeCreneau="+typeCreneau+"&date="+d+"&minute="+minute+"&presence"+presence);

	// On précise ce que l'on va faire quand on aura reçu la réponse du serveur.
	xhr.onload = function()
		{
		// Si la requête http s'est bien passée.
		if (xhr.status === 200)
			{
			// Elément html que l'on va mettre à jour.
                          var nom = document.getElementById("nom");
                          var prenom =document.getElementById("prenom");
                          var msg =document.getElementById("espace");
                          var nom=xhr.responseXML.getElementsByTagName("nom");
                          var prenom=xhr.responseXML.getElementsByTagName("prenom");
                          var type = xhr.responseXML.getElementsByTagName("typeCreneau");
                          var etatprsence=xhr.responseXML.getElementsByTagName("presence");
                          nom.innerHTML=nom[0].firstChild.nodeValue;
                          prenom.innerHTML= prenom[0].firstChild.nodeValue; 
                          msg.innerHTML=type[0].firstChild.nodeValue+" "+etatprsence[0].firstChild.nodeValue;
			}
		};
	
	// Envoie de la requête.
	xhr.send();
	}     
 /**
 * Lancement après le chargement du DOM.
 */
        document.addEventListener("DOMContentLoaded", () => 
        {
            
            document.getElementById("btn_valider").addEventListener("click",getCreneaux);
            
        });

