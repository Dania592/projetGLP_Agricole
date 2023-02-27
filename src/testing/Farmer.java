package testing;

import java.awt.Image;

import data.configuration.GameConfiguration;

public class Farmer {
	private int x ;
	private int y ;
	private Image farmerImage ;
	
	public Farmer(int x , int y , Image farmerImage) {
		this.x=x;
		this.y=y;
		this.farmerImage=farmerImage;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Image getFarmerImage() {
		return farmerImage;
	}

	public void setFarmerImage(Image farmerImage) {
		this.farmerImage = farmerImage;
	}
	
	public boolean isOnTop(){
		return y == 10;
	}
	
	public boolean isOnBottom() {
		return y == GameConfiguration.WINDOW_HEIGHT-10;
	}
	
	public boolean isOnRightBorder() {
		return x == GameConfiguration.WINDOW_WIDTH-10;
	}
	public boolean isOnLeftBorder() {
		return x ==10;
	}
	
	public boolean onBorder() {
		return isOnLeftBorder() || isOnRightBorder() || isOnBottom() || isOnTop() ;
	}

	
}
