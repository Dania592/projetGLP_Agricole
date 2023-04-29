package data.notification;

import java.io.Serializable;

import data.time.Clock;
import data.time.CyclicCounter;
import gui.Farm.MainGuiTest;

public class Message implements Serializable {
	private String contenu;
	private int heure;
	private int minute;

	public enum BasicMessage{

		IS_DEAD_FROM_DESEASE("est mort !"),
		IS_DEAD_FROM_DYSHYDRATION("est mort de déhydratation !"),
		FIELD_NEED_WATER("Un terrain à besoin d'eau !"),
		FIELD_NEED_TO_BE_HARVEST("Vite récupérer votre production !"),
		FIELD_DESHYDRATE("Terrain est pourri");
		;

		String basicMessage;

		private BasicMessage(String basicMessage) {
			this.basicMessage = basicMessage;
		}

		public String getBasicMessage() {
			return basicMessage;
		}

	}

	public Message(String contenu, int heure, int minute) {
		this.contenu = contenu;
		this.heure = heure;
		this.minute = minute;
	}

	public Message(BasicMessage contenu, int heure, int minute) {
		this(contenu.getBasicMessage(), heure, minute);
	}

	public String getContenu() {
		return contenu;
	}

	public int getHeure() {
		return heure;
	}

	public int getMinute() {
		return minute;
	}

}
