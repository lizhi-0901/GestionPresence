/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function getAffecter ()
        {
        //recuperer la valeur
        var idetudiant = document.getElementById("idetudiant").value;
        alert(idetudiant);
	// Objet XMLHttpRequest.
	var xhr = new XMLHttpRequest();

	// Requête au serveur avec les paramètres éventuels.
	xhr.open("GET","ServletRespon"+"?idetudiant="+idetudiant);

	// On précise ce que l'on va faire quand on aura reçu la réponse du serveur.
	xhr.onload = function()
		{
		// Si la requête http s'est bien passée.
		if (xhr.status === 200)
			{
			// Elément html que l'on va mettre à jour.
                            alert("js");
                            
                            
                            var tab_date=xhr.responseXML.getElementsByTagName("date");
                            var tab_heure=xhr.responseXML.getElementsByTagName("heure");
                            document.getElementById("div_etu").className="etudiant_show";
                            var elt = document.getElementById("table_presence");
                            for(i=0;i <tab_date.length;i++){
                                    var tr=document.createElement("tr");//创建行
                                    for(j=0;j<2; j++){
                                        if(j===0){
                                         var td_1=document.createElement("td");//创建列
                                         td_1.innerText=tab_date[i].firstChild.nodeValue;
                                         tr.appendChild(td_1);//向行中添加子节点列
                                         }
                                        else{
                                         var td_2=document.createElement("td");//创建列
                                         td_2.innerText=tab_heure[i].firstChild.nodeValue;
                                         tr.appendChild(td_2);//向行中添加子节点列
                                        }
                                    }

                                         elt.appendChild(tr);//添加子节点tr
                                }
                            
                            
//                            for ( i=0;i<tab_date.length;i++)
//                            {
//                                alert(tab_date[i].firstChild.nodeValue);
//                                
//                                elt.insertAdjacentHTML("beforeend","<tr><td>"+tab_date[i].firstChild.nodeValue+"</td></tr>");
//                            }
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
        
        
});
        
        