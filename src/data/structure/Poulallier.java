package data.structure;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import data.map.Map;

public class Poulallier  extends Refuge{

	private final static float PRIX_ACHAT = 20000 ;
	
	public Poulallier(int ligne_init, int colonne_init,String reference , Map map ) {
		super(ligne_init, colonne_init, PRIX_ACHAT, reference , map );
		try {
			setImage(ImageIO.read(new File("src"+File.separator+"ressources"+File.separator+"minimoulin.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
