package data.structure;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import data.map.Map;

public class Etable extends Refuge {
	
	private final static float PRIX_ACHAT = 50000 ;
	
	public Etable(int ligne_init, int colonne_init , String reference , Map map ) {
		super(ligne_init, colonne_init, PRIX_ACHAT , reference , map );
		try {
			setImage(ImageIO.read(new File("src"+File.separator+"ressources"+File.separator+"minietable.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

}
