package process.evolution;

public enum Direction {
	STAND,
	UP_1,
	UP_2,
	DOWN_1,
	DOWN_2,
	RIGHT_1,
	RIGHT_2,
	LEFT_1,
	LEFT_2; 
	
	public Direction avancer() {
		Direction[] directions = new Direction[4];
		int choix = (int)(Math.random()*3);
		switch(this) {
			case UP_1 :
				return UP_2;
			case UP_2 :
				directions[0]=UP_1;
				directions[1]=RIGHT_1;
				directions[2]=LEFT_1;
				directions[3]=UP_1;
				return directions[choix];
			case DOWN_1 : 
				return DOWN_2;
			case DOWN_2 : 
				directions[0]=DOWN_1;
				directions[1]=RIGHT_1;
				directions[2]=LEFT_1;
				directions[3]=DOWN_1;
				return directions[choix];
			case RIGHT_1 :
				return RIGHT_2;
			case RIGHT_2 :
				directions[0]=RIGHT_1;
				directions[1]=UP_1;
				directions[2]=DOWN_1;
				directions[3]=RIGHT_1;
				return directions[choix];
			case LEFT_1 :
				return LEFT_2;
			case LEFT_2 : 
				directions[0]=LEFT_1;
				directions[1]=UP_1;
				directions[2]=DOWN_1;
				directions[3]=LEFT_1;
				return directions[choix];
			case STAND : 
				directions[0]=UP_1;
				directions[1]=RIGHT_1;
				directions[2]=LEFT_1;
				directions[3]=DOWN_1;
				return directions[choix];
			default :
				return STAND ;	
		}	
	}

}
