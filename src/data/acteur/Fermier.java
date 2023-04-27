package data.acteur;

import java.io.File;
import java.util.Date;

import data.configuration.GameConfiguration;
import data.finance.Compte;
import data.map.Map;


public class Fermier extends Personne {

	private Date dateNaissance ;
	private String package_path ; 
	private Compte bankAccount;
	public Fermier(String nom, int ligne, int colonne , Date dateNaissance ,String reference , Map map ) {
		super(nom, ligne, colonne , reference , map);
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
