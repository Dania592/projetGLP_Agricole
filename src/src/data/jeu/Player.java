package data.jeu;

import java.util.HashMap;

import data.finance.Compte;
import data.stucture_base.Farm;

public class Player {
	
	private String name;
	private int age;
	private Compte bankAccount;
	private HashMap<String, Farm> farms; // La clé est le nom de la ferme
	//private Rang rangActuel; // l'equivalkent du titre mais si on fait les 5 fermes
	
	public Player(String name, int age, Compte bankAccount) {
		this.name = name;
		this.age = age;
		this.bankAccount = bankAccount;
		farms = new HashMap<>();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Compte getBankAccount() {
		return bankAccount;
	}
	public void setBankAccount(Compte bankAccount) {
		this.bankAccount = bankAccount;
	}
	public HashMap<String, Farm> getFarms() {
		return farms;
	}

}
