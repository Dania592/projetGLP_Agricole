package data.time;

import java.io.ObjectOutputStream;
import java.io.Serializable;


public class Counter implements Serializable{
	private int value ;

	public Counter(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public void increment() {
		value++;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String toString(){
		if(value<10) {
			return "0"+value;
		}
		else {
			return ""+value;			
		}
	}
	

}
