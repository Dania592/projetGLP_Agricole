package data.notification;

import java.io.Serializable;

import data.time.CyclicCounter;

public class Message implements Serializable{
	private String contenu ;
	private CyclicCounter heure ; 
	private CyclicCounter minute ; 
	
	public Message(String contenu , CyclicCounter heure , CyclicCounter minute ) {
		this.contenu = contenu ;
		this.heure = heure ;
		this.minute = minute ;
	}

	public String getContenu() {
		return contenu;
	}


	public CyclicCounter getHeure() {
		return heure;
	}


	public CyclicCounter getMinute() {
		return minute;
	}
	
	
}
