package process.transaction;

import gui.gestionnaire.GestionnaireKey;
import gui.gestionnaire.keys.Keys;
import process.visitor.GestionVisitor;

public interface Buyable {

	public <T> T accept(GestionVisitor<T> visitor);
	
	public float getPrixAchat();
	
	public boolean equals(Object obj);
	
	public Keys getKey();

	GestionnaireKey getGestionnaireKey();
}
