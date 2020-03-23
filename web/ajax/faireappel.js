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
function compterEtudiantList(){
    
    var elt=document.getElementById("listEtudiant");
    var nbEtudiant=elt.rows.length;
    
    return nbEtudiant;
};   
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
			// Elément html que l'on va mettre à jour.
                        var nbAvant=compterEtudiantList();
                        var elt = document.getElementById("listEtudiant");
                        var tabphoto=xhr.responseXML.getElementsByTagName("photo");
			var tabid = xhr.responseXML.getElementsByTagName("id");
                        var tabnom=xhr.responseXML.getElementsByTagName("nom");
                        var tabprenom=xhr.responseXML.getElementsByTagName("prenom");
                        for ( i=0;i<tabid.length;i++)
                            {
                                elt.insertAdjacentHTML("beforeend","<tr id=\"contents\"><td><img src=\""+tabphoto[i].firstChild.nodeValue+"\"height=\"50\" width=\"50\"alt=\"Photo\"></td>"+
                                                                    "<td>"+tabid[i].firstChild.nodeValue+"</td>"+
                                                                    "<td>"+tabnom[i].firstChild.nodeValue+"</td>"+
                                                                    "<td>"+tabprenom[i].firstChild.nodeValue+"</td>"+
                                                                    " <td><select "+"id=\"etatPre"+(nbAvant-1)+
                                                                    "\"><option>Pr&eacutesent</option><option>Retard</option><option>Absent</option><option>AbsentJustifi&eacute</option></select></td>");
//                                                                    "<td><input type=\"checkbox\" id=\"present\" name=\"present\" checked></td>"+
//                                                                    "<td><input type=\"checkbox\" id=\"retard\" name=\"retard\"></td>"+
//                                                                    "<td><input type=\"checkbox\" id=\"abs\" name=\"abs\"></td>"+
//                                                                    "<td><input type=\"checkbox\" id=\"absj\" name=\"absj\"></td>");
                                nbAvant=nbAvant+1;
                            }
			}
		};
	
	// Envoie de la requête.
	xhr.send();
	}
        
        
    function iniListEtudient(){
        var elt = document.getElementById("listEtudiant");
        elt.innerHTML="";
        elt.insertAdjacentHTML("beforeend","<th>Photo</th>"+
                "<th>Identifiant</th>"+
                "<th>Nom</th>"+
                "<th>Pr&eacutenom</th>"+
                "<th>Etat Pr&eacutesent</th>");
    }
    
    
    function valider(){
        var mytable=document.getElementById("listEtudiant"); 
        var nbEtudiant=mytable.rows.length;
        var cours = document.getElementById("cours").value;
        var date  =document.getElementById("datepicker").value;
        var heureSaisir = parseInt(document.getElementById("heure").value);
        var min=parseInt(document.getElementById("minute").value);
        var heure=heureSaisir*60+min;
        
        for(var i=1;nbEtudiant;i++){
            //obtenir les id et etat des etudiant dans la table
            var id=mytable.rows[i].cells[1].innerHTML;
            var idEtat="etatPre"+(i-1);
            var obj=document.getElementById(idEtat);
            var index = obj.selectedIndex;
            var etat = obj.options[index].text;
            
            var xhr = new XMLHttpRequest();
            // Requête au serveur avec les paramètres éventuels.
            xhr.open("GET","ServletInsertEtat?cours="+cours+"&heure="+heure+"&date="+date+"&id="+id+"&etat="+etat,true);
	// Envoie de la requête.
	xhr.send();           
        }

    }
    
    

     
        
        
        
        
        /**
 * Lancement après le chargement du DOM.
 */
document.addEventListener("DOMContentLoaded", () => {

	
	document.getElementById("btn_afficher").addEventListener("click",getEtudiant);
        document.getElementById("formation").addEventListener("change",getCours);
        document.getElementById("minute").addEventListener("change",getGroupe);
        document.getElementById("btn_initialiser").addEventListener("click",iniListEtudient);
        document.getElementById("btn_valider").addEventListener("click",valider);
});
