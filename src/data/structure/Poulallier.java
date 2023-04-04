package data.structure;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import data.espece.faune.Poule;
import data.map.Map;
import process.action.place.PlaceVisitor;
import process.action.place.UnableToPerformSuchActionWithCurrentActionnable;

public class Poulallier extends Refuge<Poule>{

	private final static float PRIX_ACHAT = 20000 ;
	
	public Poulallier(int ligne_init, int colonne_init,String reference , Map map ) {
		super(ligne_init, colonne_init, PRIX_ACHAT, reference , map );
		try {
			setImage(ImageIO.read(new File("src"+File.separator+"ressources"+File.separator+"minimoulin.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public ArrayList<ActionnableKey> getActionnableKey(){
		ArrayList<ActionnableKey> actionnableKeys = super.getActionnableKey();
		actionnableKeys.add(ActionnableKey.POULAILLER);
		return actionnableKeys;
	}
	@Override
	public <T> T launchAction(PlaceVisitor<T> visitor) throws UnableToPerformSuchActionWithCurrentActionnable {
		return visitor.action(this);
	}
	
}
