/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * affichir les cours selon formation
 * @returns {undefined}
 */        
function getCours ()
        {
        //recuperer la valeur
        var formation = document.getElementById("formation").value;
        
	// Objet XMLHttpRequest.
	var xhr = new XMLHttpRequest();

	// Requête au serveur avec les paramètres éventuels.
	xhr.open("GET","ServletCours"+"?formation="+formation);

	// On précise ce que l'on va faire quand on aura reçu la réponse du serveur.
	xhr.onload = function()
		{
		// Si la requête http s'est bien passée.
		if (xhr.status === 200)
			{
			// Elément html que l'on va mettre à jour.
                            var elt = document.getElementById("cours");
                            var tab=xhr.responseXML.getElementsByTagName("matiere");
                            elt.innerHTML="";
                            for ( i=0;i<tab.length;i++)
                            {
                                elt.insertAdjacentHTML("beforeend","<option>"+tab[i].firstChild.nodeValue+"</option>");
                            }
			}
		};
	
	// Envoie de la requête.
	xhr.send();
	
	}       
        
        
    /**
 * affichir les groupe selon cours 
 * @returns {undefined}
 */        
function getGroupe ()
        {
        //recuperer la valeur
        var cours = document.getElementById("cours").value;
        var heure = document.getElementById("heure").value;
        var date  =document.getElementById("date").value;
        //faire l'affichir 
        alert("cours"+cours);
        alert("heure"+heure);
        alert("date"+date);
	// Objet XMLHttpRequest.
	var xhr = new XMLHttpRequest();

	// Requête au serveur avec les paramètres éventuels.
	xhr.open("GET","ServletGroupe"+"?cours="+cours+"&heure="+heure+"&date="+date);

	// On précise ce que l'on va faire quand on aura reçu la réponse du serveur.
	xhr.onload = function()
		{
		// Si la requête http s'est bien passée.
		if (xhr.status === 200)
			{
			// Elément html que l'on va mettre à jour.
                        var elt = document.getElementById("groupe");
			var tab = xhr.responseXML.getElementsByTagName("groupe");
                        for ( i=0;i<tab.length;i++)
                            {
                                elt.insertAdjacentHTML("beforeend","<option>"+tab[i].firstChild.nodeValue+"</option>");
                                alert(tab[i].firstChild.nodeValue);
                            }
			}
		};
	
	// Envoie de la requête.
	xhr.send();
	}     
        
    /**
 * affichir les etudiant selon groupe
 * @returns {undefined}
 */        
function getEtudiant ()
        {
        //recuperer la valeur
        var groupe = document.getElementById("groupe").value;
        //faire l'affichir 
        alert("groupe"+groupe);
        
	// Objet XMLHttpRequest.
	var xhr = new XMLHttpRequest();

	// Requête au serveur avec les paramètres éventuels.
	xhr.open("GET","ServletEtudiant"+"?groupe="+groupe);

	// On précise ce que l'on va faire quand on aura reçu la réponse du serveur.
	xhr.onload = function()
		{
		// Si la requête http s'est bien passée.
		if (xhr.status === 200)
			{
			// Elément html que l'on va mettre à jour.
                        alert("retour");
			var res = xhr.responseXML.getElementsByTagName("mdp");
                        alert(res);
			}
		};
	
	// Envoie de la requête.
	xhr.send();
	}     
    

     
        
        
        
        
        /**
 * Lancement après le chargement du DOM.
 */
document.addEventListener("DOMContentLoaded", () => {

	
//	document.getElementById("groupe").addEventListener("change",getEtudiant);
        document.getElementById("formation").addEventListener("change",getCours);
        document.getElementById("choisir_g").addEventListener("click",getGroupe);
        
});
