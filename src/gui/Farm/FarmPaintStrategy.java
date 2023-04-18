package gui.Farm;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import data.acteur.Fermier;
import data.configuration.GameConfiguration;
import data.espece.evolution.EvolutionAnimal;
import data.flore.terrains.Terrain;
import data.map.Case;
import data.map.Map;
import data.structure.Enclos;
import data.stucture_base.Element;
import data.stucture_base.Farm;
import data.stucture_base.Position;
import gui.gestionnaire.Gestionnaire;
import gui.gestionnaire.RoundedPanel;


/**
 * responsable de la gestion du dessin de tous les composants  
 * @author dania
 *
 */
public class FarmPaintStrategy implements Serializable {
	
	private Map map ;
	
	public void paint(Map map, Graphics graphics) {
		this.map = map ;
		Case[][] cases = map.getCases();
		for (int lineIndex = 0; lineIndex < map.getNbLignes(); lineIndex++) {
			for (int columnIndex = 0; columnIndex < map.getNbColones(); columnIndex++) {
				 Case block = cases[lineIndex ][columnIndex ];
				 graphics.setColor(new Color(000));
					 ImageIcon herbe = new ImageIcon(GameConfiguration.IMAGE_PATH+"terre.png");
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
		Position position = element.getPosition();
		int x = position.getColonne_init()*GameConfiguration.CASE_DIMENSION +  map.getX();
		int y = position.getLigne_init()*GameConfiguration.CASE_DIMENSION + map.getY();
		BufferedImage image;
		try {
			image = ImageIO.read(new File(element.getImage()));
			graphics.drawImage(image, x, y,GameConfiguration.CASE_DIMENSION*position.getNbColonne() , GameConfiguration.CASE_DIMENSION*position.getNbLigne(), null);	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

		
	public JPanel paint(Terrain terrain, HashMap<String, JLabel> actions, Map map) {
		Position position = terrain.getPosition();
		int x = position.getColonne_init()*GameConfiguration.CASE_DIMENSION +  map.getX() + 50;
		int y = position.getLigne_init()*GameConfiguration.CASE_DIMENSION + map.getY() + 50;
		RoundedPanel panelChoixTerrain = new RoundedPanel(x,y, 100, 100, null, 20, Gestionnaire.LIGHT_BROWN);
		//panelChoixTerrain.setOpaque(false);
		x = 10;
		y = 10;
		for(String titre : actions.keySet()) {
			actions.get(titre).setBounds(x, y, 80, 20);
			actions.get(titre).setForeground(Gestionnaire.DARK_GREEN);
			panelChoixTerrain.setBackground(Gestionnaire.LIGHT_BROWN);
			panelChoixTerrain.add(titre, actions.get(titre));
			y += 30;
		}
		return panelChoixTerrain;
	}
	
	
	public void paint(Enclos enclos , Graphics graphics ) {
		  
		Position position = enclos.getPosition();
		BufferedImage image ;
		
		for(int ligneIndex = position.getLigne_init() ; ligneIndex < enclos.getDimension()+position.getLigne_init(); ligneIndex ++) {
			for(int colonneIndex = position.getColonne_init() ; colonneIndex < enclos.getDimension()+position.getColonne_init(); colonneIndex ++) {
				if(ligneIndex==position.getLigne_init() && colonneIndex== position.getColonne_init()) {
					int x =  colonneIndex*GameConfiguration.CASE_DIMENSION + map.getX() ;
					int y =  ligneIndex*GameConfiguration.CASE_DIMENSION + map.getY();
					try {
						image = ImageIO.read(new File(enclos.getImages().get("bas_gauche")));
						graphics.drawImage(image, x, y,GameConfiguration.CASE_DIMENSION , GameConfiguration.CASE_DIMENSION, null);					
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				if((ligneIndex==position.getLigne_init()|| ligneIndex == position.getLigne_init()+enclos.getDimension()-1) && colonneIndex < enclos.getDimension()+position.getColonne_init()-1) {
					int x =  colonneIndex*GameConfiguration.CASE_DIMENSION + map.getX() ;
					int y =  ligneIndex*GameConfiguration.CASE_DIMENSION + map.getY();
					try {
						image = ImageIO.read(new File(enclos.getImages().get("bas_milieu")));
						graphics.drawImage(image, x, y,GameConfiguration.CASE_DIMENSION , GameConfiguration.CASE_DIMENSION, null);					
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				if(colonneIndex== position.getColonne_init() ||  colonneIndex==enclos.getDimension()+position.getColonne_init()-1) {
					int x =  colonneIndex*GameConfiguration.CASE_DIMENSION + map.getX() ;
					int y =  ligneIndex*GameConfiguration.CASE_DIMENSION + map.getY();
					try {
						image = ImageIO.read(new File(enclos.getImages().get("milieu")));
						graphics.drawImage(image, x, y,15 , GameConfiguration.CASE_DIMENSION, null);					
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		}
		
		String waterPath = GameConfiguration.IMAGE_PATH+"Enclos"+File.separator+enclos.getNiveauEau()+"_W.png";
		String foodPath = GameConfiguration.IMAGE_PATH+"Enclos"+File.separator+enclos.getNiveauEau()+"_F.png";
		Image images ;
		try {
			images = ImageIO.read(new File(waterPath));
			int x = (enclos.getPosition().getLigne_init())*GameConfiguration.CASE_DIMENSION + map.getY()+10 ; 
			int y = (enclos.getPosition().getColonne_init()+ enclos.getDimension()/2)*GameConfiguration.CASE_DIMENSION + map.getX()-20;
			graphics.drawImage(images, y, x, GameConfiguration.CASE_DIMENSION, GameConfiguration.CASE_DIMENSION ,  null);
			
		
			images = ImageIO.read(new File(foodPath));
			 x = (enclos.getPosition().getLigne_init())*GameConfiguration.CASE_DIMENSION + map.getY()+10 ; 
			 y = (enclos.getPosition().getColonne_init()+1+ enclos.getDimension()/2)*GameConfiguration.CASE_DIMENSION + map.getX()-20;
			graphics.drawImage(images, y, x, GameConfiguration.CASE_DIMENSION, GameConfiguration.CASE_DIMENSION ,  null);
		} catch (IOException e) {	
			e.printStackTrace();
		}
		
	}

	
	public void paint(Farm farm ,Graphics graphics) {
		ImageIcon buisson = new ImageIcon(GameConfiguration.IMAGE_PATH+"buisson.png");
		
		for(int ligneIndex = farm.getLigne() ; ligneIndex < farm.getDimension()+farm.getLigne() ; ligneIndex ++) {
			for(int colonneIndex = farm.getColonne() ; colonneIndex < farm.getDimension()+farm.getColonne() ; colonneIndex ++) {
				if(farm.isOnborderFarm(ligneIndex, colonneIndex)) {
					int x =  colonneIndex*GameConfiguration.CASE_DIMENSION + map.getX() ;
					int y =  ligneIndex*GameConfiguration.CASE_DIMENSION + map.getY();
					graphics.drawImage(buisson.getImage(), x ,y ,GameConfiguration.CASE_DIMENSION ,GameConfiguration.CASE_DIMENSION, null);
				}
			}
		}
	}
	
	
	public void paintLevelHeart(Enclos enclos , Graphics graphics ) {
		Position position = enclos.getPosition();
		int y = (position.getLigne_init() - 1)*GameConfiguration.CASE_DIMENSION + map.getY() ;
		int x = (position.getColonne_init() + enclos.getDimension()/2-1)*GameConfiguration.CASE_DIMENSION +map.getX();
		ImageIcon progressBar = new ImageIcon(GameConfiguration.IMAGE_PATH+"Enclos"+File.separator+enclos.getAnimalsHungerLevel()+".png");
		graphics.drawImage(progressBar.getImage(), x, y, GameConfiguration.CASE_DIMENSION*3, GameConfiguration.CASE_DIMENSION ,  null);
	}
	
	public void paintNight( Map map ,Graphics graphics ) {
		int x = map.getX();
		int y = map.getY();
		int dx = map.getNbColones()*GameConfiguration.CASE_DIMENSION;
		int dy = map.getNbLignes()*GameConfiguration.CASE_DIMENSION;
		ImageIcon night = new ImageIcon(GameConfiguration.IMAGE_PATH+"noir.png");
		graphics.drawImage(night.getImage(), x, y, dx, dy, null);
		
		
	}
	
	
}