package gui.Farm.farmer;

import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import gui.gestionnaire.GeneralPaintStrategy;
import gui.gestionnaire.RoundedPanel;

public class PanelPersonnage extends RoundedPanel{

	private String path_image ; 
	private JLabel image ;
	public PanelPersonnage(String path_image) {
		super(null,30, GeneralPaintStrategy.MEDIUM_BROWN);
		this.path_image=path_image;
		
	}
	
	public String getPath_image() {
		return path_image;
	}
	public void setPath_image(String path_image) {
		this.path_image = path_image;
	}
	
	
	
	
	
	

}
