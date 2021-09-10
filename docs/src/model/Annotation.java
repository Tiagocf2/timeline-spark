package model;

public class Annotation {
	
	public int time;
	public String text;
	
	public Annotation(int time, String text){
		this.time = time;
		this.text = text;
	}
	
	public String toString(){
		return "[" + Utilities.formatTimeValue(time) + "] " + text;
	}
}
