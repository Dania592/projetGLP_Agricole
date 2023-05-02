package data.gestion;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import data.acteur.Employee;
import data.configuration.GameConfiguration;
import data.map.Map;
import gui.gestionnaire.keys.Employees;

public class GestionnaireRH implements GestionnaireInterface, Serializable{

	private static final long serialVersionUID = 1L;
	private Map map = Map.getInstance();

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
		Employee paul = new Employee(Employees.PAUL, Employees.PAUL.getPrixAchat() , "Paul");
		paul.setImage(GameConfiguration.IMAGE_PATH+"Employee"+File.separator+"Paul.png");
		Employee julie = new Employee(Employees.JULIE,Employees.JULIE.getPrixAchat() , "Julie");
		julie.setImage(GameConfiguration.IMAGE_PATH+"Employee"+File.separator+"Julie.png");
		Employee jean = new Employee(Employees.JEAN, Employees.JEAN.getPrixAchat() , "Jean");
		jean.setImage(GameConfiguration.IMAGE_PATH+"Employee"+File.separator+"Jean.png");
		Employee harry = new Employee(Employees.HARRY,Employees.HARRY.getPrixAchat() , "Harry");
		harry.setImage(GameConfiguration.IMAGE_PATH+"Employee"+File.separator+"Harry.png");
		aRecruter.put(Employees.PAUL, paul);
		aRecruter.put(Employees.JULIE, julie);
		aRecruter.put(Employees.JEAN, jean);
		aRecruter.put(Employees.HARRY, harry);
	}
	
}
