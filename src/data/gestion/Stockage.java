package data.gestion;

import process.gestion.visitor.GestionVisitor;

public interface Stockage {

	<T> T accept(GestionVisitor<T> visitor);
	
}