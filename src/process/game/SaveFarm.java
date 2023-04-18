package process.game;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import data.stucture_base.Farm;
import gui.Farm.Choix;

public class SaveFarm {
	
	public void serializationSave(String fileName , Farm Farm ) {
		
		try {
			FileOutputStream f = new FileOutputStream(fileName);
			ObjectOutputStream oos = new ObjectOutputStream(f);
			
			oos.writeObject(Farm);
		
			oos.close();
	}
	catch (FileNotFoundException e) {
		e.printStackTrace();
	}

	catch ( IOException e) {
		e.printStackTrace();
	}
}
	
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
