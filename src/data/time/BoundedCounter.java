package data.time;


public class BoundedCounter extends Counter {
	private int max;
	private int min;

	public BoundedCounter(int value, int max, int min) {
		super(value);
		this.max = max;
		this.min = min;
	}
	
	public void setMax(int max) {
		this.max = max;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public BoundedCounter(int max){
		this(0, max, 0);
	}

	@Override
	public void increment() {
		if (getValue() < max) {
			super.increment();
		}
	}

	public int getMax() {
		return max;
	}

	public int getMin() {
		return min;
	}

	
	public void reset(){
		setValue(getMin());
	}
	
	

}
