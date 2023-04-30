package data.gestion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import data.acteur.Employee;
import data.map.Map;
import gui.gestionnaire.keys.Employees;

public class GestionnaireRH implements GestionnaireInterface, Serializable{

	private static final long serialVersionUID = 1L;

	private HashMap<Employees, Employee> employees = new HashMap<>();
	private HashMap<Employees, Employee> aRecruter = new HashMap<>();
	
	
	private GestionnaireRH() {
		initialize();
	}
	
	private static GestionnaireRH instance = new GestionnaireRH();
	
	public void reset() {
		employees.clear();
		initialize();
	}
	
	public HashMap<Employees, Employee> getAll(){
		HashMap<Employees, Employee> newHashMap = new HashMap<>();
		newHashMap.putAll(employees);
		newHashMap.putAll(aRecruter);
		return newHashMap;
	}
	
	public HashMap<Employees, Employee> getEmployees() {
		return employees;
	}
	
	public HashMap<Employees, Employee> getARecruter() {
		return aRecruter;
	}

	public static GestionnaireRH getInstance() {
		return instance;
	}
	
	public int getSize() {
		return employees.size();
	}
	
	public int getARecruterSize() {
		return aRecruter.size();
	}
	
	public void recruter(Employees employee) {
		Employee recrut = aRecruter.get(employee);
		if (!recrut.getRecrute()) {
			employees.put(employee, recrut);	
			aRecruter.remove(employee, recrut);
			recrut.settRecrute(true);
		}
	}
	
	public void licencier(Employees employee) {
		Employee recrut = aRecruter.get(employee);
		if (recrut.getRecrute()) {
			recrut.settRecrute(false);
			aRecruter.put(recrut.getNom(), recrut);	
			employees.remove(recrut.getNom());
		}
	}
	
	public void licencier(Employee employee) {
		if (employee.getRecrute()) {
			employee.settRecrute(false);
			aRecruter.put(employee.getNom(), employee);	
			employees.remove(employee.getNom());
		}
	}
	
	public void licencier() {
		ArrayList<Employee> aLicencier = new ArrayList<Employee>(employees.values());
		int i = (int) (Math.random() * aLicencier.size());
		if (aLicencier.size() > i) {
			licencier(aLicencier.get(i));
		}
	}
	
	public String toString() {
		StringBuffer gestionnaire = new StringBuffer("\t"+ this.getClass().getSimpleName());
		gestionnaire.append("\n\t\t Employees :");
		for (Employee employe : employees.values()) {
			gestionnaire.append("\n\t\t\t"+employe.toString());
		}
		return gestionnaire.toString();
	}
	
	public void initialize() {
		Map map = new Map(5, 5, 0, 0);
		aRecruter.put(Employees.PAUL, new Employee(Employees.PAUL, 0, 0,Employees.PAUL.getPrixAchat() , null, map));
		aRecruter.put(Employees.JULIE, new Employee(Employees.JULIE, 0, 0,Employees.JULIE.getPrixAchat() , null, map));
		aRecruter.put(Employees.JEAN, new Employee(Employees.JEAN, 0, 0,Employees.JEAN.getPrixAchat() , null, map));
		aRecruter.put(Employees.HARRY, new Employee(Employees.HARRY, 0, 0,Employees.HARRY.getPrixAchat() , null, map));
	}
	
	public void printEmployees() {
		System.out.println("Employeeeeeeeee *********************************");
		for (Employees employee : employees.keySet()) {
			System.out.println(employee.toString());
		}
	}
	
	public void printRecruts() {
		System.out.println("Recruts *********************************");
		for (Employees employee : aRecruter.keySet()) {
			System.out.println(employee.toString());
		}
	}
	
}
