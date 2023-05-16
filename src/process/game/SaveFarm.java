package process.game;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import data.notion.basic.Farm;
import gui.Farm.choix.Choix;

/**
 * responsable de la sauvegarde de l'etat du jeu 
 * @author dania
 *
 */
public class SaveFarm {
	
	/**
	 * enregistre l'etat de la ferme 
	 * @param fileName
	 * @param farm
	 */
	public void serializationSave(String fileName , Farm farm ) {
		
		try {
			FileOutputStream f = new FileOutputStream(fileName);
			ObjectOutputStream oos = new ObjectOutputStream(f);
			
			oos.writeObject(farm);
		
			oos.close();
			
			FileOutputStream t = new FileOutputStream("time.ser");
			ObjectOutputStream oost = new ObjectOutputStream(t);
			
			oost.writeObject(farm.getClock());
			oost.close();
			
	}
	catch (FileNotFoundException e) {
		e.printStackTrace();
	}

	catch ( IOException e) {
		e.printStackTrace();
	}
		
		 
}
	/**
	 * relie et reconstruit la ferme à partir d'un fichier binaire 
	 * @param fileName
	 * @return
	 */
	public Farm serializationRead(String fileName)  {
		Farm line ;
			 try {
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName));
				line = (Farm)ois.readObject();
				
				ois.close();
				
				return line ;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			catch ( IOException e) {
				e.printStackTrace();
		}
			 
		 return null ; 
	}
	

	/**
	 * enregistre les choix que l'utilisateur peut placer sur la map 
	 * @param fileName
	 * @param choix
	 */
	public void saveGui(String fileName , Choix choix ) {
		
		try {
			FileOutputStream f = new FileOutputStream(fileName);
			ObjectOutputStream oos = new ObjectOutputStream(f);
			
			oos.writeObject(choix);
		
			oos.close();
	}
	catch (FileNotFoundException e) {
		e.printStackTrace();
	}

	catch ( IOException e) {
		e.printStackTrace();
	}
}
	/**
	 * recupere les choix que l'utilisateur peut placer sur la map 
	 * @param fileName
	 * @return
	 */
	public Choix readGui(String fileName)  {
		Choix line ;
			 try {
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName));
				line = (Choix)ois.readObject();
				
				ois.close();
				
				return line ;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			catch ( IOException e) {
				e.printStackTrace();
		}
			 
		 return null ; 
	}
	
}
