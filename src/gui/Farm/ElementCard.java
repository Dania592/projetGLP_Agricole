
package gui.Farm;

import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;

import data.espece.FoodConsumer.HungerLevel;
import data.espece.faune.AnimalProducteur;
import data.map.Case;
import data.myExceptions.FullCapaciteException;
import data.structure.Enclos;
import data.stucture_base.Element;
import data.stucture_base.Farm;



public class ElementCard extends JPanel{
	private ArrayList<Element> elements ;
	private int nbElement ;
	private int nbElementPresent ;
	private Farm farm ;
	private Board component ; 
	
	private JLabel imageLabel;
	private JLabel position;
	private JTextPane nbElementPane;
	private String nameCard;
	
	private static final long serialVersionUID = 1L;

	public ElementCard(ArrayList<Element> elements , Farm farm , Board component ) {
		this.farm = farm;
		this.component=component;
		this.elements = elements ;
		nameCard = elements.get(0).getClass().getSimpleName();
		nbElement = elements.size();
		nbElementPresent = elements.size();
		init();
	}
	
	
	public void init() {
		setSize(200, 150);
		setLayout(null);
		
		//BufferedImage imageElement = ImageIO.read(new File(elements.get(0).getImage()));
		imageLabel= new JLabel(new ImageIcon(elements.get(0).getImage()));
		imageLabel.setBounds(40, 5 , 70, 70);
		add(imageLabel);
		
		
		nbElementPane = new JTextPane();
		nbElementPane.setText(nameCard+" : "+nbElementPresent+"/"+nbElement);
		nbElementPane.setFont(new Font("Serif",Font.BOLD,13));
		nbElementPane.setBackground(getBackground());
		nbElementPane.setBounds(40, 80, 100, 20);
		nbElementPane.setEditable(false);
		add(nbElementPane);
		
		if(nbElementPresent > 0) {
			position = new JLabel(new ImageIcon("src"+File.separator+"ressources"+File.separator+"positionMap.png"));
			position.setBounds(50,100, 45, 45);
			position.addMouseListener(new MouseListenerLabel());
			position.setToolTipText("Ajouter à la map");
			add(position);			
		}
		
		
	}
	
	public ArrayList<Element> getElements() {
		return elements;
	}


	public void addElement(Element newElement) {
		elements.add( newElement);
		nbElement ++;
		nbElementPresent = elements.size();
		nbElementPane.setText(nameCard+":"+nbElementPresent+"/"+nbElement);
	}


	public void removeOneElement(Element element) {
		elements.remove(element);
		nbElementPresent = elements.size();
		if(nbElementPresent==0) {
			remove(position);
		}
		nbElementPane.setText(nameCard+":"+nbElementPresent+"/"+nbElement);
	}

	public int getNbElement() {
		return nbElement;
	}
	
	public int getNbElementPresent() {
		return nbElementPresent;
	}
	
	
	private class MouseListenerLabel implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getComponent().equals(position)) {
				int nbE = nbElementPresent ;
				if(nbE>0) {
					Element element = elements.get(nbE -1);
					
					if(element instanceof AnimalProducteur) {
						AnimalProducteur animal = (AnimalProducteur)element;
						try {
							animal.setStatique();
							// la date de naissance == minute de naissance (à modifier)
							animal.setNaissance(farm.getClock().getMinute().getValue());
							addAnimalToMap(animal);
						} catch (FullCapaciteException e1) {
							System.err.println(e1.getLocalizedMessage());
						}
					}
					else {
						Case randomCase =randomPosition(element);
						element.setPosition(randomCase.getLigne(), randomCase.getColonne());
						if(nameCard.equals("Enclos")) {
							Enclos enclos = (Enclos) element ;
							enclos.setLastDecrementation(farm.getClock().getMinute().getValue());
							farm.getManager().add(enclos);
						}
						else {
							farm.getManager().add(element);							
							
						}
						
						removeOneElement(element);	
						component.setSelected(element);
						component.getHud().removeChoix();
						component.getHud().addValidation();
						
					}
							
				}
							
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	

	public void addAnimalToMap(AnimalProducteur animal) throws FullCapaciteException {
		Case randomCase = randomPosition(animal);
		  if(!farm.getManager().getMapManager().getElements().containsKey(animal.getReference())){
			  animal.setPosition(randomCase.getLigne(), randomCase.getColonne());
				farm.getManager().add(animal);
				removeOneElement(animal);	
		  }
	
	}
	
	public Case randomPosition(Element element ) {
		Case block = new Case(true , 0 , 0);
		Boolean libre = false ;
		while( !libre) {	
			int ligneAleatoire =  farm.getLigne() + (int)(Math.random() * (farm.getDimension()-elements.get(0).getPosition().getNbLigne()-1));
			int colonneAleatoire = farm.getColonne() + (int)(Math.random() * (farm.getDimension()-elements.get(0).getPosition().getNbColonne()-1));
			block = new Case(true, ligneAleatoire, colonneAleatoire);
		   libre = farm.getManager().getMapManager().verificationLiberte(element, block);
		}
		return block;

	}
	
	public Case randomPosition(AnimalProducteur animal ) throws FullCapaciteException {
		ArrayList<Enclos> enclosdisponible = farm.getManager().getMapManager().getEnclosOnMap();
		if(enclosdisponible.size() > 0) {
			Iterator<Enclos> it = enclosdisponible.iterator();
			while(it.hasNext()) {
				Enclos enclos = it.next();
				if(enclos.getCapacite() > enclos.getAnimals().size() && enclos.getAnimalsHungerLevel()!=HungerLevel.STARVING) {
					Case block = new Case(true , 0 , 0);
					Boolean libre = false ;
					while( !libre) {	
						int ligneAleatoire =  enclos.getPosition().getLigne_init() + (int)(Math.random() * (enclos.getDimension()-elements.get(0).getPosition().getNbLigne()-1));
						int colonneAleatoire = enclos.getPosition().getColonne_init() + (int)(Math.random() * (enclos.getDimension()-elements.get(0).getPosition().getNbColonne()-1));
						block = new Case(true, ligneAleatoire, colonneAleatoire);
					   libre = farm.getManager().getMapManager().verificationLiberte(animal, block);
					}
					if(enclos.getAnimals().size()==0) {
						enclos.setLastDecrementation(animal.getNaissance()
								);
					}
					enclos.addAnimal(animal);
					return block;
					
				}
				
			}
		}
		
		throw new FullCapaciteException("vous ne disposez pas de la capacité nécessaire pour avoir un animal");
		
	
	}

	public void removePositionforEmpty() {
		if(nbElementPresent==0) {
			remove(position);
		}
	}
	
}
