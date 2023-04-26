package data.notification;

import java.io.Serializable;
import java.util.ArrayList;

public class Messagerie implements Serializable{

	private ArrayList<Message> messages ;
	private static Messagerie instance = new Messagerie();
	
	
	private  Messagerie() {
		messages = new ArrayList<>();
	}
	
	public Messagerie getInstance() {
		return instance ;
	}
	
	public void addMessage(Message message ) {
		messages.add(message);
	}
	
	public ArrayList<Message> getMessages(){
		return messages ;
	}
	
}
