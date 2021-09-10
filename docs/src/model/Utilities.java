package model;

public class Utilities {
	
	public static String formatTimeValue(int t) {
		
		if(t >= 360000)
			return null;
		
		int hora = t / 3600;
		int min = (t - hora * 3600) / 60;
		int seg = t - hora * 3600 - min * 60;

		return ((hora < 10) ? "0" : "") + hora + ":" + ((min < 10) ? "0" : "") + min + ":" + ((seg < 10) ? "0" : "")
				+ seg;
	}
}
