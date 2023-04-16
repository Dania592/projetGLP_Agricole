package process.transaction;

import java.io.Serializable;
import java.util.HashMap;

import data.finance.Amende;
import data.finance.Charge;
import data.finance.Penalites;
import process.game.Game;

public class GestionCharges implements Serializable {
	private HashMap<String, Penalites> transactions = new HashMap<>();
	
	public void payer(Charge charge, Game game) {
		game.getBanque().debiter(charge.getMontant());
		transactions.remove(charge.getReference());
		game.getRessourcesManager().getGestionnaireFinancier().add(charge);
	}
	
	public void payer(Amende Amende, Game game) {
		game.getBanque().debiter(Amende.getMontant());
		transactions.remove(Amende.getReference());
		game.getRessourcesManager().getGestionnaireFinancier().add(Amende);
	}
	
	public void ajouterPenalite(Penalites penalite) {
		transactions.put(penalite.getReference(), penalite);
	}
	
}
