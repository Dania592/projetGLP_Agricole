package data.structure;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.text.html.HTMLDocument.Iterator;

import data.espece.Slaughtable;
import data.espece.faune.Animal;
import data.espece.faune.Vache;
import data.map.Map;
import data.structure.hability.Distributor;
import data.structure.hability.SlaughterHouseSender;
import process.action.exception.being.BeingCannotPerformSuchActionException;
import process.action.exception.structure.UnableToPerformSuchActionWithCurrentActionnable;
import process.action.visitor.being.HaveNotProducedYetException;
import process.action.visitor.place.PlaceVisitor;

public class Etable extends Refuge<Vache> implements SlaughterHouseSender, Distributor{
	private ArrayList<Vache> cowToKill = new ArrayList<>();
	private final static float PRIX_ACHAT = 50000 ;
	
	public Etable(int ligne_init, int colonne_init , String reference , Map map ) {
		super(ligne_init, colonne_init, PRIX_ACHAT , reference , map );
		try {
			setImage(ImageIO.read(new File("src"+File.separator+"ressources"+File.separator+"minietable.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public ArrayList<ActionnableKey> getActionnableKey(){
		ArrayList<ActionnableKey> actionnableKeys = super.getActionnableKey();
		actionnableKeys.add(ActionnableKey.ETABLE);
		return actionnableKeys;
	}

	@Override
	public <T> T launchAction(PlaceVisitor<T> visitor) throws UnableToPerformSuchActionWithCurrentActionnable, HaveNotProducedYetException, BeingCannotPerformSuchActionException {
		return visitor.action(this);
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
	protected int getMaxCapacity() {
		return MaxCapacity.MAX_CAPACITE_MAISON.getCapacity();
	}

	@Override
	public <T extends Slaughtable> void addToSlaughter(T animalToAdd) {
		
	}

	@Override
	public ArrayList<?> getAnimalToSlaugther() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'getAnimalToSlaugther'");
	}



}
