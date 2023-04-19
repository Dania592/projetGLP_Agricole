package data.structure;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import data.espece.evolution.EvolutionAnimal;
import data.espece.Slaughtable;
import data.espece.faune.Poule;
import data.map.Map;
import data.structure.hability.Distributor;
import data.structure.hability.Productif;
import data.structure.hability.SlaughterHouseSender;
import gui.gestionnaire.keys.Structures;
import process.action.exception.being.BeingCannotPerformSuchActionException;
import process.action.exception.structure.UnableToPerformSuchActionWithCurrentActionnable;
import process.action.visitor.being.HaveNotProducedYetException;
import process.action.visitor.place.PlaceVisitor;

public class Poulallier extends Refuge<Poule> implements Productif, Distributor{
	private ArrayList<Poule> chikenToKill = new ArrayList<>(); 

	private boolean haveProduced = false;
	private final static float PRIX_ACHAT = 20000 ;
	
	public Poulallier(int ligne_init, int colonne_init,String reference , Map map ) {
		super(ligne_init, colonne_init, PRIX_ACHAT, reference , map );
		
			setImage("src"+File.separator+"ressources"+File.separator+"minimoulin.png");
		
	}

	@Override
	public ArrayList<ActionnableKey> getActionnableKey() {
		ArrayList<ActionnableKey> actionnableKeys = super.getActionnableKey();
		actionnableKeys.add(ActionnableKey.POULAILLER);
		return actionnableKeys;
	}

	@Override
	public <T> T launchAction(PlaceVisitor<T> visitor) throws UnableToPerformSuchActionWithCurrentActionnable, HaveNotProducedYetException, BeingCannotPerformSuchActionException {
		return visitor.action(this);
	}

	@Override
	public boolean haveProduced() {
		return haveProduced;
	}

	@Override
	public ArrayList<?> getTarget() {
		return getInHabitant();
	}

	@Override
	public boolean isEmpty() {
		return getInHabitant().isEmpty();
	}

	@Override
	protected int getMaxCapacity(){
		return MaxCapacity.MAX_CAPACITE_POULAILLER.getCapacity();
	}
	
	public Structures getKey() {
		return Structures.POULAILLER;
	}

}
