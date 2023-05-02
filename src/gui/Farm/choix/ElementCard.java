
package gui.Farm.choix;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextPane;

import data.espece.FoodConsumer.HungerLevel;
import data.espece.faune.AnimalProducteur;
import data.map.Case;
import data.myExceptions.FullCapaciteException;
import data.notification.Message;
import data.notification.Messagerie;
import data.structure.Enclos;
import data.stucture_base.Element;
import data.stucture_base.Farm;
import data.time.Clock;
import gui.Farm.Board;
import gui.gestionnaire.RoundedPanel;


/**
 * la carte correspondante à un type d'élément à placer sur la map 
 *
 */
public class ElementCard extends RoundedPanel{
	/**
	 * liste d'éléments pouvant etre placé
	 */
	private ArrayList<Element> elements ;
	/**
	 * nombre d'élément totale sur la carte
	 */
	private int nbElement ;
	/**
	 * nombre d'élément présent sur la carte
	 */
	private int nbElementPresent ;
	/**
	 * la ferme du jeu 
	 */
	private Farm farm ;
	/**
	 * panel principale du jeu 
	 */
	private Board component ; 

	/**
	 * label pour image de l'élément 
	 */

	private JLabel imageLabel;
	/**
	 * label pour pouvoir positioner l'élément 
	 */
	private JLabel position;
	/**
	 * quantité de élément présent 
	 */
	private JTextPane nbElementPane;
	/**
	 * nom de la carte 
	 */
	private String nameCard;

	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;

	/**
	 * constructeur des cartes 
	 * @param elements : la liste d'éléments pouvant etre placé
	 * @param farm : ferme du jeu 
	 * @param component : panel principal du jeu 
	 */
	public ElementCard(ArrayList<Element> elements , Farm farm , Board component ) {
		super(null , 100 , Color.WHITE);
		this.farm = farm;
		this.component=component;
		this.elements = elements ;
		nameCard = elements.get(0).getClass().getSimpleName();
		nbElement = elements.size();
		nbElementPresent = elements.size();
		init();
	}

	
	/**
	 * initialisation des composants du panel 
	 */

	public void init() {
		setSize(150, 140);

		imageLabel= new JLabel(new ImageIcon(elements.get(0).getImage()));
		imageLabel.setBounds(40, 5 , 70, 70);
		add(imageLabel);


		nbElementPane = new JTextPane();
		nbElementPane.setText(nameCard+" : "+nbElementPresent+"/"+nbElement);
		nbElementPane.setBackground(Color.white);
		nbElementPane.setFont(new Font("Serif",Font.BOLD,13));
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

	/**
	 * retourne les éléments présents sur la carte 
	 * @return
	 */
	public ArrayList<Element> getElements() {
		return elements;
	}


	/**
	 * ajoute un élément à la carte 
	 * @param newElement : élément à ajouter
	 */
	public void addElement(Element newElement) {
		elements.add( newElement);
		nbElement ++;
		nbElementPresent = elements.size();
		nbElementPane.setText(nameCard+":"+nbElementPresent+"/"+nbElement);
	}

	/**
	 * supprime un élément de la carte 
	 * @param element : élément à supprimer 
	 */

	public void removeOneElement(Element element) {
		elements.remove(element);
		nbElementPresent = elements.size();
		if(nbElementPresent==0) {
			remove(position);
		}
		nbElementPane.setText(nameCard+":"+nbElementPresent+"/"+nbElement);
	}

	/**
	 * retourne le nombre d'élément totale de la carte 
	 * @return
	 */
	public int getNbElement() {
		return nbElement;
	}

	/**
	 * retourne le nombre d'éléments présents sur la carte 
	 * @return
	 */

	public int getNbElementPresent() {
		return nbElementPresent;
	}

	
	/**
	 * 
	 * Action pour placer l'élément sur la map 
	 * 
	 *
	 */

	private class MouseListenerLabel implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getComponent().equals(position)) {
				int nbE = nbElementPresent ;
				if(nbE>0) {
					Element element = elements.get(nbE -1);

					if(element instanceof AnimalProducteur) {
						//System.out.println(element.getReference());
						AnimalProducteur animal = (AnimalProducteur)element;
						try {
							animal.setStatique();
							// la date de naissance == minute de naissance (à modifier)
							animal.setNaissance(farm.getClock().getMinute().getValue());
							addAnimalToMap(animal);
						} catch (FullCapaciteException e1) {
							Message message = new Message("Plus d'espace pour\n un autre animal" , Clock.getInstance().getHour().getValue(), Clock.getInstance().getMinute().getValue());
							Messagerie.getInstance().addMessage(message);
						}
					}
					else {
						Case randomCase =randomPosition(element);
						element.setPosition(randomCase.getLigne(), randomCase.getColonne());
						if(nameCard.equals("Enclos")) {
							Enclos enclos = (Enclos) element ;
							enclos.setLastDecrementationEau(farm.getClock().getMinute().getValue());
							enclos.setLastDecrementationNourriture(farm.getClock().getMinute().getValue());
							farm.getManager().add(enclos);
						}else{
							farm.getManager().add(element);						
						}

						//removeOneElement(element);	
						component.setSelected(element);
						//System.out.println(component.getSelected().getReference());

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

	/**
	 * ajout de d'un animal sur la map et dans un enclos qui vérifie les conditions 
	 * @param animal : animal à positionner 
	 * @throws FullCapaciteException : en cas de disponibilité d'espace 
	 */
	public void addAnimalToMap(AnimalProducteur animal) throws FullCapaciteException {
		Case randomCase = randomPosition(animal);
		if(!farm.getManager().getMapManager().getElements().containsKey(animal.getReference())){
			animal.setPosition(randomCase.getLigne(), randomCase.getColonne());
			farm.getManager().add(animal);
			removeOneElement(animal);	
		}
	}

	/**
	 * donne une position random pour un élément en vérifiant la liberté de la position
	 * @param element l'element à placer 
	 * @return : la case pour générer la position de l'élément 
	 */

	public Case randomPosition(Element element ) {
		Case block = new Case(true , 0 , 0);
		Boolean libre = false ;
		while( !libre) {	
			int ligneAleatoire =  farm.getLigne() + (int)(Math.random() * (farm.getHeight()-elements.get(0).getPosition().getNbLigne()-1));
			int colonneAleatoire = farm.getColonne() + (int)(Math.random() * (farm.getWidth()-elements.get(0).getPosition().getNbColonne()-1));
			block = new Case(true, ligneAleatoire, colonneAleatoire);
			libre = farm.getManager().getMapManager().verificationLiberte(element, block);
		}
		return block;

	}

	
	/**
	 * donne une position random à un animal dans un enclos en vérifiant la disponibilité et l'espace et les conditions sur la nourriture et l'eau 
	 * @param animal : animal à positionner 
	 * @return : case à partir de laquelle générer la position  
	 * @throws FullCapaciteException en cas de d'espace insuffisant 
	 */
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
						enclos.setLastDecrementationNourriture(animal.getNaissance());
						enclos.setLastDecrementationEau(animal.getNaissance());
					}
					enclos.addAnimal(animal);
					return block;

				}

			}
		}

		throw new FullCapaciteException("vous ne disposez pas de la capacité nécessaire pour avoir un animal");


	}

	/**
	 * retir le label de positionnement pour les cartes vide 
	 */
	public void removePositionforEmpty() {
		if(nbElementPresent==0) {
			remove(position);
		}
	}

}
