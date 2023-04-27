package data.notification;

import java.io.Serializable;
import java.util.ArrayList;

import data.time.CyclicCounter;

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
	}
	
	public ArrayList<Message> getMessages(){
		return messages ;
	}
	
	public void removeMessages() {
		messages= new ArrayList<>();
	}
	
	private void init() {
		Message message = new Message ("test de messagerie" , new CyclicCounter(10,10,0),new CyclicCounter(22,42,5));
		messages.add(message);
		
		Message message1 = new Message ("test de messagerie 1" ,new CyclicCounter(12,10,0),new CyclicCounter(2,42,5));
		messages.add(message1);
		
		Message message2 = new Message ("test de messagerie 2" , new CyclicCounter(0,10,0),new CyclicCounter(10,42,5));
		messages.add(message2);
		
	}
	
}
