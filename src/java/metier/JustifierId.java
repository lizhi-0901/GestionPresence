package metier;
// Generated Mar 18, 2020 6:08:12 PM by Hibernate Tools 4.3.1



/**
 * JustifierId generated by hbm2java
 */
public class JustifierId  implements java.io.Serializable {


     private String idPersonne;
     private String idCreneau;

    public JustifierId() {
    }

    public JustifierId(String idPersonne, String idCreneau) {
       this.idPersonne = idPersonne;
       this.idCreneau = idCreneau;
    }
   
    public String getIdPersonne() {
        return this.idPersonne;
    }
    
    public void setIdPersonne(String idPersonne) {
        this.idPersonne = idPersonne;
    }
    public String getIdCreneau() {
        return this.idCreneau;
    }
    
    public void setIdCreneau(String idCreneau) {
        this.idCreneau = idCreneau;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof JustifierId) ) return false;
		 JustifierId castOther = ( JustifierId ) other; 
         
		 return ( (this.getIdPersonne()==castOther.getIdPersonne()) || ( this.getIdPersonne()!=null && castOther.getIdPersonne()!=null && this.getIdPersonne().equals(castOther.getIdPersonne()) ) )
 && ( (this.getIdCreneau()==castOther.getIdCreneau()) || ( this.getIdCreneau()!=null && castOther.getIdCreneau()!=null && this.getIdCreneau().equals(castOther.getIdCreneau()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getIdPersonne() == null ? 0 : this.getIdPersonne().hashCode() );
         result = 37 * result + ( getIdCreneau() == null ? 0 : this.getIdCreneau().hashCode() );
         return result;
   }   


}


