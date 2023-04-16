package data.time;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Counter implements Serializable{
	private int value = 0;

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
	
	 private void writeObject(ObjectOutputStream out) throws IOException {	
		 out.defaultWriteObject();
		 System.out.println(value);
	}
	 
	 private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		 in.defaultReadObject();
		 System.out.println(value);
	 	
	 }

}
