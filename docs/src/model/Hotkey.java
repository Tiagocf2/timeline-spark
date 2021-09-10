package model;

public class Hotkey {
	private int code;
	private int modifiers;
	
	public Hotkey(){}
	
	public Hotkey(int c, int m){
		this.code = c;
		this.modifiers = m;
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public int getModifiers() {
		return modifiers;
	}
	public void setModifiers(int modifiers) {
		this.modifiers = modifiers;
	}

}
