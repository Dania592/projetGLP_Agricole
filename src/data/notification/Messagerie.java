package data.notification;

import java.io.Serializable;
import java.util.ArrayList;

import data.time.CyclicCounter;
import gui.Farm.Hud;

public class Messagerie implements Serializable{

	private ArrayList<Message> messages ;
	private static Messagerie instance = new Messagerie();
	
	
	private  Messagerie() {
		messages = new ArrayList<>();
		init();
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
	
	private void init() {
		Message message = new Message ("test de \nmessagerie" , 1 ,5);
		messages.add(message);
		
		Message message2 = new Message ("test de messagerie 2" ,22,5);
		messages.add(message2);
		
	}
	
}
