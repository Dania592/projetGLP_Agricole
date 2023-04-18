package data.time;


public class CyclicCounter extends BoundedCounter {

	public CyclicCounter(int value, int max, int min) {
		super(value, max, min); 
	}

	public CyclicCounter(int max){
		super(max);
	}

	@Override
	public void increment() {
		if (getValue() < getMax()) {
			super.increment();
		} else {
			setValue(getMin());
		}
	}
	
}
