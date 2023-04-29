package gui.gestionnaire.keys;

import gui.gestionnaire.GestionnaireKey;
import process.visitor.KeyVisitor;

public interface Keys {

	float getPrixAchat();
	
	float getPrixVente();
	
	public <T> T accept(KeyVisitor<T> visitor, int quantity);
	
	public GestionnaireKey getGestionnaireKey();

	public String name();

}
