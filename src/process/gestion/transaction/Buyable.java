package process.gestion.transaction;

import gui.gestionnaire.keys.GestionnaireKey;
import gui.gestionnaire.keys.Keys;
import process.gestion.visitor.GestionVisitor;

public interface Buyable {

	public <T> T accept(GestionVisitor<T> visitor);
	
	public float getPrixAchat();
	
	public boolean equals(Object obj);
	
	public Keys getKey();

	GestionnaireKey getGestionnaireKey();
}
