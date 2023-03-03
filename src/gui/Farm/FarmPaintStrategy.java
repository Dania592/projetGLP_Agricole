package gui.Farm;

import java.awt.Color;

import java.awt.Graphics;
import java.io.File;

import javax.swing.ImageIcon;

import data.configuration.GameConfiguration;
import data.map.Case;
import data.map.Map;
import data.stucture_base.Element;
import data.stucture_base.Farm;
import data.stucture_base.Position;


/**
 * responsable de la gestion du dessin de tous les composants  
 * @author dania
 *
 */
public class FarmPaintStrategy {
	
	private Map map ;
	
	public void paint(Map map, Graphics graphics) {
		this.map = map ;
		Case[][] cases = map.getCases();
		for (int lineIndex = 0; lineIndex < map.getNbLignes(); lineIndex++) {
			for (int columnIndex = 0; columnIndex < map.getNbColones(); columnIndex++) {
				 Case block = cases[lineIndex ][columnIndex ];
				 graphics.setColor(new Color(000));
					 ImageIcon herbe = new ImageIcon("src"+File.separator+"ressources"+File.separator+"terre.png");
					 int x = block.getColonne()*GameConfiguration.CASE_DIMENSION + map.getX();
					 int y = block.getLigne()*GameConfiguration.CASE_DIMENSION + map.getY();		 
					 graphics.drawImage(herbe.getImage(),x,y,GameConfiguration.CASE_DIMENSION , GameConfiguration.CASE_DIMENSION, null);
					 
//					 graphics.setColor(Color.black);
//					 graphics.drawLine(x, y, x, y+ GameConfiguration.CASE_DIMENSION);
//					 graphics.drawLine(x, y, x+ GameConfiguration.CASE_DIMENSION, y);
//					 graphics.drawLine(x, y, x+ GameConfiguration.CASE_DIMENSION, y);
//					 graphics.drawLine(x+ GameConfiguration.CASE_DIMENSION, y, x+ GameConfiguration.CASE_DIMENSION, y+ GameConfiguration.CASE_DIMENSION);
//					 graphics.drawLine(x, y+GameConfiguration.CASE_DIMENSION, x +GameConfiguration.CASE_DIMENSION, y+GameConfiguration.CASE_DIMENSION);
			}
		}
	}
	
	
	public void paint( Element element , Graphics graphics) {
		ImageIcon icone = element.getImage();
		Position position = element.getPosition();
		int x = position.getColonne_init()*GameConfiguration.CASE_DIMENSION +  map.getX();
		int y = position.getLigne_init()*GameConfiguration.CASE_DIMENSION + map.getY();
		graphics.drawImage(icone.getImage(), x, y,GameConfiguration.CASE_DIMENSION*position.getNbColonne() , GameConfiguration.CASE_DIMENSION*position.getNbLigne(), null);	
	}
	
	
//	public ImageIcon getImage(String reference) {
//		switch(reference) {
//		case "fermier":
//			return new ImageIcon("src"+File.separator+"ressources"+File.separator+"stand.png");
//				
//		case "grange":
//			return new ImageIcon("src"+File.separator+"ressources"+File.separator+"grange1.png");
//			
//		case "etable":
//			return new ImageIcon("src"+File.separator+"ressources"+File.separator+"maison.png");
//			
//		case "entrepot":
//			return new ImageIcon("src"+File.separator+"ressources"+File.separator+"grange.png");
//			
//		case "poulallier":
//			return new ImageIcon("src"+File.separator+"ressources"+File.separator+"moulin.png");
//			
//		case "ma1":
//			return new ImageIcon("src"+File.separator+"ressources"+File.separator+"tracteur2.png");
//		
//		case "ma0":
//			return new ImageIcon("src"+File.separator+"ressources"+File.separator+"etable.png");
//		
//		case "et0":
//			return new ImageIcon("src"+File.separator+"ressources"+File.separator+"camion.png");
//		
//		case "en0":
//			return new ImageIcon("src"+File.separator+"ressources"+File.separator+"maison.png");
//		
//		case "po0":
//			return new ImageIcon("src"+File.separator+"ressources"+File.separator+"moulin.png");
//			
//		default:
//			return new ImageIcon("src"+File.separator+"ressources"+File.separator+"terre.png");
//		}
//				
//	}
	
	public void paint(Farm farm ,Graphics graphics) {
		ImageIcon buisson = new ImageIcon("src"+File.separator+"ressources"+File.separator+"buisson.png");
		
		for(int ligneIndex = farm.getLigne() ; ligneIndex < farm.getDimension()+farm.getLigne() ; ligneIndex ++) {
			for(int colonneIndex = farm.getColonne() ; colonneIndex < farm.getDimension()+farm.getColonne() ; colonneIndex ++) {
				if(borderFarm(ligneIndex, colonneIndex, farm)) {
					int x =  colonneIndex*GameConfiguration.CASE_DIMENSION + farm.getManager().getMapManager().getMap().getX() ;
					int y =  ligneIndex*GameConfiguration.CASE_DIMENSION + farm.getManager().getMapManager().getMap().getY();
					graphics.drawImage(buisson.getImage(), x ,y ,GameConfiguration.CASE_DIMENSION ,GameConfiguration.CASE_DIMENSION, null);
					map.getCase(ligneIndex , colonneIndex).setLibre(false);
				}
			}
		}
	}
	
	public Boolean borderFarm(int ligne , int colonne , Farm farm ) {
		return ( ligne == farm.getLigne()) || (colonne == farm.getColonne()) || ( ligne == (farm.getLigne()+farm.getDimension()-1)) || (colonne == (farm.getColonne()+farm.getDimension()-1)) ;
	}
	
	
}
