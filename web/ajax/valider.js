/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function getAffecter ()
        {
        //recuperer la valeur
        var idetudiant = document.getElementById("idetudiant").value;
        var mois =document.getElementById("mois").value;
        var annee = document.getElementById("annee").value;
        alert(annee);
	// Objet XMLHttpRequest.
	var xhr = new XMLHttpRequest();

	// Requête au serveur avec les paramètres éventuels.
	xhr.open("GET","ServletRespon"+"?idetudiant="+idetudiant+"&mois="+mois+"&annee="+annee);

	// On précise ce que l'on va faire quand on aura reçu la réponse du serveur.
	xhr.onload = function()
		{
		// Si la requête http s'est bien passée.
		if (xhr.status === 200)
			{
			// Elément html que l'on va mettre à jour.
                            
                            /**
                             * recuperer les valeurs
                             */
                            var tab_date=xhr.responseXML.getElementsByTagName("date");
                            var tab_heure=xhr.responseXML.getElementsByTagName("heure");
                            var tab_absheure=xhr.responseXML.getElementsByTagName("absheure");
                            var tab_reheure=xhr.responseXML.getElementsByTagName("reheure");
                            var tab_creneau=xhr.responseXML.getElementsByTagName("idCreneau");
                            var nom=xhr.responseXML.getElementsByTagName("nom");
                            var prenom =xhr.responseXML.getElementsByTagName("prenom");
                            var photo =xhr.responseXML.getElementsByTagName("photo");    
                            
                            document.getElementById("div_etu").className="etudiant_show";
                            var elt_nom=document.getElementById("nom");
                            var elt_pnom=document.getElementById("prenom");
                            //var elt_photo=
                            /**
                             * info etudiant
                             */
                            elt_nom.innerHTML=nom[0].firstChild.nodeValue;
                            elt_pnom.innerHTML=prenom[0].firstChild.nodeValue;
                            
                            
                            /**
                             * table 
                             */
                            var tab_c=[];
                            var elt = document.getElementById("table_presence");
                            alert("tab_length"+tab_date.length);
                            for(i=0;i <tab_date.length;i++){
                                    alert("la "+i+"fois");
                                    var tr=document.createElement("tr");//创建行
                                    for(j=0;j<4; j++){
                                        if(j===0){
                                         var td_1=document.createElement("td");//创建列
                                         td_1.innerText="";
                                         td_1.innerText=tab_date[i].firstChild.nodeValue;
                                         alert("la "+i+"row "+j+" col"+tab_date[i].firstChild.nodeValue);
                                         tr.appendChild(td_1);//向行中添加子节点列
                                         }
                                        else if(j===1){
                                         var td_2=document.createElement("td");//创建列
                                         td_2.innerText="";
                                         td_2.innerText=tab_heure[i].firstChild.nodeValue;
                                         alert("la "+i+"row "+j+" col"+tab_heure[i].firstChild.nodeValue);
                                         tr.appendChild(td_2);//向行中添加子节点列
                                        }else if(j===2){
                                         var td_3=document.createElement("td");//创建列
                                         td_3.innerText="";
                                         td_3.innerText=tab_absheure[i].firstChild.nodeValue;
                                         alert("la "+i+"row "+j+" col"+tab_absheure[i].firstChild.nodeValue);
                                         tr.appendChild(td_3);//  
                                        }else{
                                         var td_4=document.createElement("td");//创建列
                                         td_4.innerText="";
                                         td_4.innerText=tab_reheure[i].firstChild.nodeValue;
                                         alert("la "+i+"row "+j+" col"+tab_reheure[i].firstChild.nodeValue);
                                         tr.appendChild(td_4);//   
                                        }
                                    }

                                         elt.appendChild(tr);//添加子节点tr
                                }
                                
//                            const divs = document.getElementsByClassName("btn_v");
//                            for (const div of divs) {
//                                alert("u click valider");
//                                div.addEventListener("click",valider(tab_c));
//                            }
//                            const dis = document.getElementsByClassName("btn_n");
//                            for (const div of dis) {
//                                alert("u click non valider");
//                                div.addEventListener("click",valider(tab_c));
//                            }  
//                            
//                            
			}
		};
	
	// Envoie de la requête.
	xhr.send();
	
	}       
        
 function valider ()
        {
          
        //recuperer la valeur
        var idetudiant=document.getElementById("idetudiant").value;
        var etavalide = document.getElementById("etatvalide").value;
        
       
        // Objet XMLHttpRequest.
	var xhr = new XMLHttpRequest();
        // Requête au serveur avec les paramètres éventuels.
	xhr.open("GET","ServletValide"+"?idetudiant="+idetudiant+"&etavalide="+etavalide);

	// On précise ce que l'on va faire quand on aura reçu la réponse du serveur.
	xhr.onload = function()
		{
		// Si la requête http s'est bien passée.
		if (xhr.status === 200)
			{
                            
//			// Elément html que l'on va mettre à jour.
//                            
//                            /**
//                             * recuperer les valeurs
//                             */
                            var num=xhr.responseXML.getElementsByTagName("num");
                            if(num!==0){
                                alert("validé");
                            }
                            
                            
			}
		};
	
	// Envoie de la requête.
	xhr.send();
	
	}            
    
       /* * Lancement après le chargement du DOM.
 */
document.addEventListener("DOMContentLoaded", () => {

	
//	document.getElementById("groupe").addEventListener("change",getEtudiant);
        document.getElementById("affecter").addEventListener("click",getAffecter);
        document.getElementById("valide").addEventListener("click",valider);
        
});
        
        