package data.notification;

import java.io.Serializable;

import data.time.Clock;
import data.time.CyclicCounter;

public class Message implements Serializable{
	private String contenu ;
	private int heure ; 
	private int minute ; 
	
	public Message(String contenu , int heure , int minute ) {
		this.contenu = contenu ;
		this.heure = heure ;
		this.minute = minute;
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
