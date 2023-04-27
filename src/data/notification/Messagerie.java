package data.notification;

import java.io.Serializable;
import java.util.ArrayList;

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
		Message message = new Message ("test de messagerie" , 00,22);
		messages.add(message);
		
		Message message1 = new Message ("test de messagerie 1" , 0,21);
		messages.add(message1);
		
		Message message2 = new Message ("test de messagerie 2" , 15,02);
		messages.add(message2);
		
	}
	
}
