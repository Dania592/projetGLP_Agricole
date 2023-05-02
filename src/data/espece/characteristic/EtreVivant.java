package data.espece.characteristic;


import data.myExceptions.MortException;
import data.notion.Mortel;
import data.notion.basic.Element;
/**
 * Une être vivant est caractérisé par une durée de vie et un état de santé
 */
public abstract class EtreVivant extends Element implements Mortel{

	private static final long serialVersionUID = 1L;
	

	private int dureeVie;
	private float prixAchat ,prixVente;

	private EtatSante etatSante;
	

	public EtreVivant( int nbCase,   int dureeVie,
			float prixAchat, String reference ) {
		super(reference ,false, nbCase );
		this.dureeVie = dureeVie;
		this.prixAchat = prixAchat;
		prixVente = 100;
		this.etatSante = EtatSante.BONNE_SANTE;
	}


	public int getDureeVie() {
		return dureeVie;
	}


	public EtatSante getEtatSante() {
		return etatSante;
	}

	public void setEtatSante(EtatSante etatSante){
		this.etatSante = etatSante;
	}
	
	public void empireEtatSante() throws MortException{
		switch(etatSante){
			case BONNE_SANTE:
				etatSante = EtatSante.MALADE;
				break;
			case MALADE:
				etatSante = EtatSante.GRAVEMENT_MALADE;
				break; 
			case GRAVEMENT_MALADE:
				etatSante = EtatSante.MOURANT;
				break;
			default :
				throw new MortException(this);	
		}
	}

    public void amelioreEtatSante(){
		switch(etatSante){
			case MALADE:
				etatSante = EtatSante.BONNE_SANTE;
				break;
			case GRAVEMENT_MALADE:
				etatSante = EtatSante.MALADE;
				break;
			case MOURANT:
				etatSante = EtatSante.GRAVEMENT_MALADE;
				break;
			default :
				break;
		}
	}
    public void guerir(){
		switch(etatSante){
			case BONNE_SANTE:
				break;
			default:
				etatSante = EtatSante.BONNE_SANTE;
		}
	}
    
	public float getPrixAchat() {
		return prixAchat;
	}
	
	public float getPrixVente() {
		return prixVente;
	}

	public void setStatique() {
		super.setStatique(true);
	}

}
