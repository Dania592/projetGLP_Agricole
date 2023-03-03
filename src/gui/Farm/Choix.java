package gui.Farm;


import java.util.HashMap;

import data.stucture_base.Element;

public class Choix {
	private HashMap<String, Element> composants ;
	
	public Choix() {
		composants = new HashMap<>();
	}
	
	// reste à developper pour ajouter l'element si un element du meme type 
	// n'exist pas et sinon incrementer le nombre 
	public void addElement(Element element) {
		if(!composants.containsKey(element.getReference())) {
			composants.put(element.getReference(), element);
		}
	}
	
	public HashMap<String, Element> getComposants(){
		return composants ;
	}
	
	public void removeElement(Element element) {
		if(!composants.containsKey(element.getReference())) {
			composants.remove(element.getReference());
		}
	}
}
