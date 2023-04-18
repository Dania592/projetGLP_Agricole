package data.structure;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import data.espece.faune.Vache;
import data.map.Map;
import process.action.place.PlaceVisitor;
import process.action.place.UnableToPerformSuchActionWithCurrentActionnable;

public class Etable extends Refuge<Vache>{
	
	private final static float PRIX_ACHAT = 50000 ;
	
	public Etable(int ligne_init, int colonne_init , String reference , Map map ) {
		super(ligne_init, colonne_init, PRIX_ACHAT , reference , map );
		
			setImage("src"+File.separator+"ressources"+File.separator+"minietable.png");
		
	}
	
	@Override
	public ArrayList<ActionnableKey> getActionnableKey(){
		ArrayList<ActionnableKey> actionnableKeys = super.getActionnableKey();
		actionnableKeys.add(ActionnableKey.ETABLE);
		return actionnableKeys;
	}

	@Override
	public <T> T launchAction(PlaceVisitor<T> visitor) throws UnableToPerformSuchActionWithCurrentActionnable {
		return visitor.action(this);
	}

	
}
