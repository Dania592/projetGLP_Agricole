package data.acteur;

import java.io.File;
import java.util.Date;

import data.configuration.GameConfiguration;
import data.finance.Compte;
import data.map.Map;

/**
 * Class de donnée représentant le fermier 
 * 
 *
 */
public class Fermier extends Personne {
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
	 * @param nom 
	 * @param ligne 
	 * @param colonne
	 * @param dateNaissance
	 * @param reference
	 * @param map
	 */
	public Fermier(String nom , Date dateNaissance ,String reference ) {
		super(nom,  reference );
		this.dateNaissance=dateNaissance;
		package_path = GameConfiguration.IMAGE_PATH+"Fermier"+File.separator+"personnage0" ;
		setImage(package_path+File.separator+"stand.png");	
		bankAccount = Compte.getInstance();
	}

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public Compte getBankAccount() {
		return bankAccount;
	}
	
	@Override
	public int getMaxHourOfWork() {
		return Personne.MAX_HOUR_OF_WORK_FARMER;
	}
	
	public void changeAvatar(String path) {
		package_path = path ;
		setImage(path+"stand.png");
	}
	
	public String getPackageImage() {
		return package_path;
	}

}
