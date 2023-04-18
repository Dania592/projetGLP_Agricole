package data.flore.terrains;

import java.io.Serializable;
import java.util.HashMap;

import javax.swing.JLabel;

public class TerrainActions implements Serializable {
	private HashMap<String, JLabel> actions = new HashMap<>();
	private String[] actionsTab;
	
	public TerrainActions() {
		actionsTab = new String[4];
		actionsTab[0] = "Labourer";
		actionsTab[0] = "Planter";
		actionsTab[0] = "Arrose";
		actionsTab[0] = "Récolter";
	}
	
	public void setActions() {
		for ( int i = 0; i < actionsTab.length; i++) {
			actions.put(actionsTab[i], new JLabel(actionsTab[i]));
		}
	}
	
}
