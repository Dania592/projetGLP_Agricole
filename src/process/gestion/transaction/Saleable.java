package process.gestion.transaction;

import process.gestion.visitor.GestionVisitor;

public interface Saleable {

	public <T> T accept(GestionVisitor<T> visitor);
	
	public String getReference();
	
	public float getPrixVente();
	
}
