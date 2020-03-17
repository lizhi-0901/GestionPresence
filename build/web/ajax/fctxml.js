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
	xhr.open("GET","ServletConnection"+"?identifiant="+identifiant+"?mdp="+mdp,true);

	// On précise ce que l'on va faire quand on aura reçu la réponse du serveur.
	xhr.onload = function()
		{
		// Si la requête http s'est bien passée.
		if (xhr.status === 200)
			{
			// Elément html que l'on va mettre à jour.
			var res = xhr.responseXML.getElementsByTagName("ret");
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