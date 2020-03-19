/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function connecter ()
	{
        
        var elt = document.getElementById("lg_username");
        var identifiant = elt.value;
        var  mdp= document.getElementById("lg_password").value;
        alert(identifiant);
        alert(mdp);
	// Objet XMLHttpRequest.
	var xhr = new XMLHttpRequest();

	// Requête au serveur avec les paramètres éventuels.
	xhr.open("GET","ServletConnection"+"?identifiant="+identifiant+"&mdp="+mdp);

	// On précise ce que l'on va faire quand on aura reçu la réponse du serveur.
	xhr.onload = function()
		{
		// Si la requête http s'est bien passée.
		if (xhr.status === 200)
			{
			// Elément html que l'on va mettre à jour.
                        var msg =document.getElementById("espace_msg");
			var res = xhr.responseXML.getElementsByTagName("msg");
                        for ( i=0;i<res.length;i++)
                        {
                                 msg.insertAdjacentHTML('beforeend',"<p>"+res[i].firstChild.nodeValue+"</p>") ;
                        }
			}
		};
	
	// Envoie de la requête.
	xhr.send();
	}
        
/**
 * affichir les cours selon formation
 * @returns {undefined}
 */        
function getCours ()
        {
        //recuperer la valeur
        var elt = document.getElementById("lg_username").value;
        //faire l'affichir 
        alert(elt);
        
	// Objet XMLHttpRequest.
	var xhr = new XMLHttpRequest();

	// Requête au serveur avec les paramètres éventuels.
	xhr.open("GET","ServletEtudiant"+"?identifiant="+identifiant);

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
 * affichir les groupe selon cours 
 * @returns {undefined}
 */        
function getGroupe ()
        {
        //recuperer la valeur
        var elt = document.getElementById("lg_username").value;
        //faire l'affichir 
        alert(elt);
        
	// Objet XMLHttpRequest.
	var xhr = new XMLHttpRequest();

	// Requête au serveur avec les paramètres éventuels.
	xhr.open("GET","ServletEtudiant"+"?identifiant="+identifiant);

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
 * affichir les etudiant selon groupe
 * @returns {undefined}
 */        
function getEtudiant ()
        {
        //recuperer la valeur
        var elt = document.getElementById("lg_username").value;
        //faire l'affichir 
        alert(elt);
        
	// Objet XMLHttpRequest.
	var xhr = new XMLHttpRequest();

	// Requête au serveur avec les paramètres éventuels.
	xhr.open("GET","ServletEtudiant"+"?identifiant="+identifiant);

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

	document.getElementById("btn_login").addEventListener("click",connecter);
	
        
});