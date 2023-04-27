package data.finance;

public enum TypeCharge {
	ENERGIE,
	EAU,
	PROPRIETE;
	
	TypeCharge() {}
	
	public String toString() {
		return name().substring(0, 1).toUpperCase() + name().substring(1).toLowerCase();
	}
}
