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
	
	public static int INTERVALLE_CHARGE = 20; // une charge de propriété chaque 20 jours;
	private Clock clock = TimeManager.getInstance().getClock();
	private ArrayList<Charge> charges = new ArrayList<>();
	private int counter = 0;
	
	private FinanceManager() {
		// TODO Auto-generated constructor stub
	}
	
	public static FinanceManager instance = new FinanceManager();
	
	public static FinanceManager getInstance() {
		return instance;
	}
	
	public ArrayList<Charge> getCharges(){
		return charges;
	}
	
	public void generateCharge() {
		if (counter % 5 == 0) {
			Charge charge = new Charge(TypeCharge.ENERGIE);
			//GestionnaireFinancier.getInstance().add(charge);
			charges.add(charge);
			Messagerie.getInstance().addMessage(new Message("Charge d'énergie, consultez Gestionnaire financier", clock.getHour().getValue() , clock.getMinute().getValue()));
		} else if (counter % 10 == 0) {
			Charge charge = new Charge(TypeCharge.EAU);
			//GestionnaireFinancier.getInstance().add(charge);
			charges.add(charge);
			Messagerie.getInstance().addMessage(new Message("Charge d'eau, consultez Gestionnaire financier", clock.getHour().getValue() , clock.getMinute().getValue()));
		} else if (counter == 20) {
			Charge charge = new Charge(TypeCharge.PROPRIETE);
			//GestionnaireFinancier.getInstance().add(charge);
			charges.add(charge);
			Messagerie.getInstance().addMessage(new Message("Charge de propriété, consultez Gestionnaire financier", clock.getHour().getValue() , clock.getMinute().getValue()));
			counter = 0;
		}
	}
	
	// à appeler une fois par jour
	public void senctionner() {
		if (charges != null ) {
			for (Charge charge : charges) {
				if (charge.getDelais() == 0 && !charge.isPaid()) {
					Banque.getInstance().debiter(charge.getMontant() + (0.1*charge.getMontant()));
					charge.setPaid(true);
					charges.remove(charge);
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
		senctionner();
		generateCharge();
		System.out.println(charges.size());
	}
}
