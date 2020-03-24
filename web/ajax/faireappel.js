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
//        var identifiant=document.getElementById("lg_username").value;
//        alert(identifiant);
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
                            var tab=xhr.responseXML.getElementsByTagName("matiere")
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
        var heureSaisir = parseInt(document.getElementById("heure").value);
        var min=parseInt(document.getElementById("minute").value);
        var heure=heureSaisir*60+min;
        var date  =document.getElementById("datepicker").value;
        //faire l'affichir 
	// Objet XMLHttpRequest.
	var xhr = new XMLHttpRequest();

	// Requête au serveur avec les paramètres éventuels.
        xhr.open("GET","ServletGroupe?cours="+cours+"&heure="+heure+"&date="+date,true);
	// On précise ce que l'on va faire quand on aura reçu la réponse du serveur.
	xhr.onload = function()
		{
		// Si la requête http s'est bien passée.
		if (xhr.status === 200)
			{
			// Elément html que l'on va mettre à jour.
                        var elt = document.getElementById("groupe");
			var tab = xhr.responseXML.getElementsByTagName("groupe");
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
 * affichir les etudiant selon groupe
 * @returns {undefined}
 */        
function getEtudiant ()
        {
        //recuperer la valeur
        var groupe = document.getElementById("groupe").value;
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
                        var initialise = document.getElementById("listEtudiant");
                        initialise.innerHTML="";
                        initialise.insertAdjacentHTML("beforeend","<th>Photo</th>"+
                                                      "<th style=\"display:none;\">Identifiant</th>"+
                                                      "<th>Nom</th>"+
                                                      "<th>Pr&eacutenom</th>"+
                                                      "<th>Etat Pr&eacutesent</th>");

			// Elément html que l'on va mettre à jour.
                        var elt = document.getElementById("listEtudiant");
                        
                        var tabphoto=xhr.responseXML.getElementsByTagName("photo");
			var tabid = xhr.responseXML.getElementsByTagName("id");
                        var tabnom=xhr.responseXML.getElementsByTagName("nom");
                        var tabprenom=xhr.responseXML.getElementsByTagName("prenom");
                        for ( i=0;i<tabid.length;i++)
                            {
                                elt.insertAdjacentHTML("beforeend","<tr id=\"contenu\"><td><img src=\""+tabphoto[i].firstChild.nodeValue+"\"height=\"50\" width=\"50\"alt=\"Photo\"></td>"+
                                                                    "<td style=\"display:none;\">"+tabid[i].firstChild.nodeValue+"</td>"+
                                                                    "<td>"+tabnom[i].firstChild.nodeValue+"</td>"+
                                                                    "<td>"+tabprenom[i].firstChild.nodeValue+"</td>"+
                                                                    " <td><select "+"id=\"etatPre"+(i+1)+
                                                                    "\"><option>Pr&eacutesent</option><option>Retard</option><option>Absent</option><option>AbsentJustifi&eacute</option></select></td>");
                            };
			};
		};
	
	// Envoie de la requête.
	xhr.send();
	}
        
    function creationCreneau(){
        //recuperer la valeur
        
        var cours = document.getElementById("cours").value;
        alert(cours);
        var date  =document.getElementById("datepicker").value;
        alert(date);
        var heureSaisir = parseInt(document.getElementById("heure").value);
        var min=parseInt(document.getElementById("minute").value);
        var heure=heureSaisir*60+min;
        var duree=document.getElementById("duree").value;
        var typeCours=document.getElementById("typeCours").value;
        alert(typeCours);
        
        var xhr = new XMLHttpRequest();
        xhr.open("GET","ServletCreneau"+"?cours="+cours+"&heure="+heure+"&date="+date+"&duree="+duree+"&typeCours="+typeCours,true);
            xhr.send();
        };
    
    function valider(){
        var mytable=document.getElementById("listEtudiant"); 
        alert(mytable.rows[1].cells[1].innerHTML);
        var nbEtudiant=mytable.rows.length;
        var cours = document.getElementById("cours").value;
        var date  =document.getElementById("datepicker").value;
        var heureSaisir = parseInt(document.getElementById("heure").value);
        var min=parseInt(document.getElementById("minute").value);
        var heure=heureSaisir*60+min;
        var duree=document.getElementById("duree").value;
              
        
        for(var i=1;nbEtudiant-1;i++){
            //obtenir les id et etat des etudiant dans la table

            var id=mytable.rows[i].cells[1].innerHTML;
            var idEtat="etatPre"+i;
            var obj=document.getElementById(idEtat);
            var index = obj.selectedIndex;
            var etat = obj.options[index].text;
            
            var xhr = new XMLHttpRequest();
            // Requête au serveur avec les paramètres éventuels.
                xhr.open("GET","ServletInsertEtat?id="+id+"&etat="+etat+"&cours="+cours+"&heure="+heure+"&date="+date+"&duree="+duree,true);
                
            // Envoie de la requête.
            xhr.send();           
        }

    }
    
    

     
        
        
        
        
        /**
 * Lancement après le chargement du DOM.
 */
document.addEventListener("DOMContentLoaded", () => {

	
	document.getElementById("groupe").addEventListener("change",getEtudiant);
        document.getElementById("formation").addEventListener("change",getCours);
        document.getElementById("btn_valider").addEventListener("click",valider);
        document.getElementById("typeCours").addEventListener("change",creationCreneau);
});

