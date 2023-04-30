package data.finance;

import gui.gestionnaire.keys.Employees;

/**
 * determine les charges d'une structure 
 * @author dania
 */

public class Charge extends Penalites{

	private static final long serialVersionUID = 1L;
	
	private TypeCharge type;
	
	public Charge(TypeCharge type) {
		this.type = type;
		if (type.equals(TypeCharge.SALAIRES)) {
			super.setMontant(Employees.getTotalSalaryToPay());
		}
	}

	public String getType() {
		return type.toString();
	}
	
	public void setType(TypeCharge type) {
		this.type = type;
	}
				
}