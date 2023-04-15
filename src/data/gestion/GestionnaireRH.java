package data.gestion;

import java.util.HashMap;

import data.acteur.Employee;

public class GestionnaireRH implements GestionnaireInterface{
	
	private HashMap<String, Employee> employees = new HashMap<>();

	private static GestionnaireRH instance = new GestionnaireRH();
	
	private GestionnaireRH() {}
	
	public HashMap<String, Employee> getEmployees() {
		return employees;
	}

	public static GestionnaireRH getInstance() {
		return instance;
	}
	
	public int getSize() {
		return employees.size();
	}
	
	public String toString() {
		StringBuffer gestionnaire = new StringBuffer("\t"+ this.getClass().getSimpleName());
		gestionnaire.append("\n\t\t Employees :");
		for (Employee employe : employees.values()) {
			gestionnaire.append("\n\t\t\t"+employe.toString());
		}
		return gestionnaire.toString();
	}
	
	
}
