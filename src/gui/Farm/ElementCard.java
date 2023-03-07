package gui.Farm;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;

import data.map.Case;
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
	
	private static final long serialVersionUID = 1L;

	public ElementCard(ArrayList<Element> elements , Farm farm , Board component ) {
		this.farm = farm;
		this.component=component;
		this.elements = elements ;
		nbElement = elements.size();
		nbElementPresent = elements.size();
		init();
	}
	
	
	public void init() {
		setSize(new Dimension(150, 145));
		setLayout(null);
		
		BufferedImage imageElement = elements.get(0).getImage();
		imageLabel= new JLabel(new ImageIcon(imageElement));
		imageLabel.setBounds(40, 5 , 70, 70);
		add(imageLabel);
		
		
		nbElementPane = new JTextPane();
		nbElementPane.setText(elements.get(0).getClass().getSimpleName()+" : "+nbElementPresent+"/"+nbElement);
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
		nbElementPresent++;
		nbElementPane.setText(elements.get(0).getClass().getSimpleName()+":"+nbElementPresent+"/"+nbElement);
	}


	public void removeOneElement() {
		nbElementPresent--;
		nbElementPane.setText(elements.get(0).getClass().getSimpleName()+":"+nbElementPresent+"/"+nbElement);
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
					Case randomCase = randomPosition(elements.get(nbE -1));
					
					if(!farm.getManager().getMapManager().getElements().containsKey(elements.get(nbE -1).getReference())) {
						elements.get(nbE -1).setPosition(randomCase.getLigne(), randomCase.getColonne());
						farm.getManager().add(elements.get(nbE -1));
						removeOneElement();					
					}
					
				}
				component.setSelected(elements.get(nbE -1));
				component.getHud().removeChoix();
				component.getHud().addValidation();
							
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
	

	
	public Case randomPosition(Element element ) {
		Case block = new Case(true , 0 , 0);
		Boolean libre = false ;
		while( !libre) {	
			int ligneAleatoire =  farm.getLigne() + (int)(Math.random() * (farm.getDimension()-elements.get(0).getPosition().getNbLigne()-1));
			int colonneAleatoire = farm.getColonne() + (int)(Math.random() * (farm.getDimension()-elements.get(0).getPosition().getNbColonne()-1));
			block = new Case(true, ligneAleatoire, colonneAleatoire);
		   libre = farm.getManager().getMapManager().verificationLiberte(elements.get(0), block);
		}
		return block;

	}
	// changer le nom de la methode 
	public void update() {
		if(nbElementPresent==0) {
			remove(position);
		}
	}
	
}
