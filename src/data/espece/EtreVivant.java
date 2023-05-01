package data.espece;


import data.map.Map;
import data.myExceptions.MortException;
import data.notion.Mortel;
import data.stucture_base.Element;

public abstract class EtreVivant extends Element implements Mortel{

	private static final long serialVersionUID = 1L;
	
	private Milieu milieu ;
	private int dureeVie;
	private float prixAchat ,prixVente;

	private EtatSante etatSante;
	

	public EtreVivant( int nbCase,  Milieu milieu, int dureeVie,
			float prixAchat, String reference ) {
		super(reference ,false, nbCase );
		this.milieu = milieu;
		this.dureeVie = dureeVie;
		this.prixAchat = prixAchat;
		prixVente = 100;
		this.etatSante = EtatSante.BONNE_SANTE;
	}


	public Milieu getMilieu() {
		return milieu;
	}


	public int getDureeVie() {
		return dureeVie;
	}


	public EtatSante getEtatSante() {
		return etatSante;
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
				break; // TODO rajouter info log
		}
	}
    public void guerir(){
		switch(etatSante){
			case BONNE_SANTE:
				break; // TODO rajouter info log
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
