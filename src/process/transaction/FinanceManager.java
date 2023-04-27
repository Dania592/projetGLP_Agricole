package process.transaction;

import java.util.ArrayList;

import data.finance.Banque;
import data.finance.Charge;
import data.finance.TypeCharge;
import data.notification.Message;
import data.notification.Messagerie;
import data.time.Clock;
import process.time.TimeManager;

public class FinanceManager {
	
	public static String EOL = System.getProperty("line.separator"); // end of line
	public static int INTERVALLE_CHARGE = 20; // une charge de propriété chaque 20 jours;
	private Clock clock = TimeManager.getInstance().getClock();
	private ArrayList<Charge> charges = new ArrayList<>();
	private int counter = 0;
	
	private FinanceManager() {
	}
	
	public static FinanceManager instance = new FinanceManager();
	
	public static FinanceManager getInstance() {
		return instance;
	}
	
	public ArrayList<Charge> getCharges(){
		return charges;
	}
	
	public void generateCharge() {
		if (counter % 2 == 0) {
			Charge charge = new Charge(TypeCharge.ENERGIE);
			charges.add(charge);
			Messagerie.getInstance().addMessage(new Message("Moment de payer " + EOL + "l'énergie!", clock.getHour().getValue() , clock.getMinute().getValue()));
		}
		if (counter % 5 == 0) {
			Charge charge = new Charge(TypeCharge.SALAIRES);
			charges.add(charge);
			Messagerie.getInstance().addMessage(new Message("Payez les employés !", clock.getHour().getValue() , clock.getMinute().getValue()));
		}
		if (counter % 10 == 0) {
			Charge charge = new Charge(TypeCharge.EAU);
			charges.add(charge);
			Messagerie.getInstance().addMessage(new Message("L'eau aussi est " + EOL + "payante!", clock.getHour().getValue() , clock.getMinute().getValue()));
		}
		if (counter == 20) {
			Charge charge = new Charge(TypeCharge.PROPRIETE);
			charges.add(charge);
			Messagerie.getInstance().addMessage(new Message("Pour être propriétaire  " + EOL + "il faut payer!", clock.getHour().getValue() , clock.getMinute().getValue()));
			counter = 0;
		}
	}
	
	// à appeler une fois par jour
	public void senctionner() {
		if (charges != null ) {
			for (int i = 0; i < charges.size(); i++) {
				Charge charge = charges.get(i);
				if ((charge.getDelais() == 0) && !charge.isPaid()) {
					Banque.getInstance().debiter(charge.getMontant() + (0.1*charge.getMontant()));
					charge.setPaid(true);
					charges.remove(charge);
					Messagerie.getInstance().addMessage(new Message("Pénalités pour retard  " + EOL + "de paiement", clock.getHour().getValue(), clock.getMinute().getValue()));
				} else {
					charge.setDelais(charge.getDelais() - 1);
				}
			}
		}
	}
	
	public void remove(Charge charge) {
		if (charges != null) {
			charges.remove(charge);	
		}
	}
	
	public void incrementCounter() {
		counter++;
		generateCharge();
		senctionner();
	}
}
