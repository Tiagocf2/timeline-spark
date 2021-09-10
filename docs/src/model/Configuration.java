package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;

import com.melloware.jintellitype.JIntellitype;

public class Configuration {

	private static Configuration instance = null;
	private String directory = "configs.ini";
	private Hotkey[] hotkeys = new Hotkey[5];

	private Configuration() {
		readFile();
	}

	public Hotkey[] getHotkeys() {
		return hotkeys;
	}

	public void setHotkeys(Hotkey[] hotkeys) {
		this.hotkeys = hotkeys;
	}

	public static Configuration getInstance() {
		if (instance == null) {
			instance = new Configuration();
		}
		return instance;
	}

	private void readFile() {
		BufferedReader reader = null;
		FileReader file = null;
		try {
			file = new FileReader(directory);
			reader = new BufferedReader(file);
			String line = null;
			int count = 0;
			do {
				line = reader.readLine();
				if (line != null && line.charAt(0) != '*') {
					line = line.substring(line.indexOf(' ') + 1, line.length());
					hotkeys[count] = new Hotkey();
					hotkeys[count].setModifiers(Integer.parseInt((line.substring(0, line.indexOf(' ')))));
					line = line.substring(line.indexOf(' ') + 1, line.length());
					hotkeys[count].setCode(Integer.parseInt(line.substring(0, line.length())));
					count++;
				}
				
			} while (line != null && count < 5);
			file.close();
		} catch (FileNotFoundException e) {
			try{
				file.close();
				reader.close();
			}catch(Exception e2){
				
			}
			createDefaultFile();
			readFile();
			return;
		} catch (NumberFormatException e2) {
			try{
				reader.close();
				file.close();
			}catch(Exception e3){
				
			}
			createDefaultFile();
			readFile();
			return;
		} catch (Exception e3) {
			e3.printStackTrace();
		}
		try {
			file.close();
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void createDefaultFile() {
		FileWriter writer = null;
		try {
			File file = new File(directory);
			if (file.exists() && file.isFile()) {
				file.delete();
			}
			writer = new FileWriter(directory, true);
			writer.append("*Configuração das hotkeys" + "\r\n*Exemplo: hotkeyN <modificador> <codigo>"
					+ "\r\n*Use * no começo da linha para inserir comentários" + "\r\nhotkey1 0 112"
					+ "\r\nhotkey2 0 113" + "\r\nhotkey3 0 114" + "\r\nhotkey4 0 115" + "\r\nhotkey5 0 116");
		} catch (Exception e) {
			System.err.println("Erro ao escrever configs.txt");
		}
		try {
			writer.flush();
			writer.close();
		} catch (Exception e) {
			System.err.println("Erro de arquivo");
		}
	}

	public boolean writeFile() {
		FileWriter writer = null;
		try {
			File file = new File(directory);
			if (file.exists()) {
				file.delete();
			}
			writer = new FileWriter(directory, true);
			writer.append("*Configuração das hotkeys" + "\r\n*Exemplo: hotkeyN <modificador> <codigo>"
					+ "\r\n*Use * no começo da linha para inserir comentários" + "\r\nhotkey1 "
					+ hotkeys[0].getModifiers() + " " + hotkeys[0].getCode() + "\r\nhotkey2 "
					+ hotkeys[1].getModifiers() + " " + hotkeys[1].getCode() + "\r\nhotkey3 "
					+ hotkeys[2].getModifiers() + " " + hotkeys[2].getCode() + "\r\nhotkey4 "
					+ hotkeys[3].getModifiers() + " " + hotkeys[3].getCode() + "\r\nhotkey5 "
					+ hotkeys[4].getModifiers() + " " + hotkeys[4].getCode());
		} catch (Exception e) {
			System.err.println("Erro ao escrever configs.txt");
			return false;
		}
		try {
			writer.flush();
			writer.close();
		} catch (Exception e) {
			System.err.println("Erro de arquivo");
		}
		return true;
	}

	public void registerHotkeys() {
		for (int i = 0; i < 5; i++) {
			JIntellitype.getInstance().unregisterHotKey(i + 1);
			JIntellitype.getInstance().registerHotKey(i + 1, hotkeys[i].getModifiers(), hotkeys[i].getCode());
		}
	}
	
	public void unregisterHotkeys() {
		for (int i = 0; i < 5; i++) {
			JIntellitype.getInstance().unregisterHotKey(i + 1);
		}
	}
}
