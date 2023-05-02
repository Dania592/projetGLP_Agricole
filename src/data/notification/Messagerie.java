package data.notification;

import java.io.Serializable;
import java.util.ArrayList;

import gui.Farm.Hud;

public class Messagerie implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Message> messages ;
	private static Messagerie instance = new Messagerie();
	
	
	private  Messagerie() {
		messages = new ArrayList<>();
	}
	
	public static Messagerie getInstance() {
		return instance ;
	}
	
	public void addMessage(Message message ) {
		messages.add(message);
		Hud.notification();
		
	}
	
	public ArrayList<Message> getMessages(){
		return messages ;
	}
	
	public void removeMessages() {
		messages= new ArrayList<>();
	}

	
}
