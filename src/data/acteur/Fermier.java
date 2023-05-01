package data.acteur;

import java.io.File;
import java.util.Date;

import data.configuration.GameConfiguration;
import data.finance.Compte;

/**
 * Class de donnée représentant le fermier 
 * 
 *
 */
public class Fermier extends Personne {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Date de naissance du fermier pour le calcule d'age 
	 */
	private Date dateNaissance ;
	/**
	 * Chemin vers les images du fermier selon son avatar
	 */
	private String package_path ;
	/**
	 * Compte bancaire du fermier contenant le solde du jeu 
	 */
	private Compte bankAccount;
	/**
	 * Constructeur du fermier 
	 * @param nom : nom du fermier 
	 * @param dateNaissance : date de naissance du fermier 
	 * @param reference : reference pour le positionner sur la map
	 */
	public Fermier(String nom , Date dateNaissance ,String reference ) {
		super(nom,  reference );
		this.dateNaissance=dateNaissance;
		package_path = GameConfiguration.IMAGE_PATH+"Fermier"+File.separator+"personnage0" ;
		setImage(package_path+File.separator+"stand.png");	
		bankAccount = Compte.getInstance();
	}

	/**
	 * retourne la date de naissance du fermier 
	 * @return
	 */
	public Date getDateNaissance() {
		return dateNaissance;
	}
	/**
	 * retourne le compte bancaire du fermier 
	 * @return
	 */
	public Compte getBankAccount() {
		return bankAccount;
	}
	
	/**
	 * retourne le nombre maximal d'heure que peut travailler le fermier 
	 */
	@Override
	public int getMaxHourOfWork() {
		return Personne.MAX_HOUR_OF_WORK_FARMER;
	}
	/**
	 * modifie le path vers l'avatar du fermier 
	 * @param path : nouveau chemin
	 */
	public void changeAvatar(String path) {
		package_path = path ;
		setImage(path+"stand.png");
	}
	/**
	 * retourne le chemin vers le dossier avec les images de l'avatar du fermier 
	 * @return
	 */
	public String getPackageImage() {
		return package_path;
	}

}
