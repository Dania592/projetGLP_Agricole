package data.espece.evolution;

public enum EvolutionCorail{
	SQUELETTE, 
	BOURGEON, 
	CORAIL;

	//TODO Comment ça  évolue un corail : ça meurt, ça se vit sous forme de cycle comme un arbre ?
	public EvolutionCorail evolue() {
		switch(this){
			case SQUELETTE:
				return BOURGEON;
			case BOURGEON:
				return CORAIL;
			default:
				return CORAIL;
		}
	}

	
}
