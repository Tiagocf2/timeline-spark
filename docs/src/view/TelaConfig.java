package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.Configuration;
import model.Hotkey;

public class TelaConfig extends JFrame implements KeyListener, ActionListener, FocusListener, WindowListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JButton btnOk, btnCancel, btnDefault;
	JLabel lblTitle, lblStart, lblPause, lblStop, lblAdd, lblSave, lblCtrl, lblAlt, lblShift;
	JTextField txtKey1, txtKey2, txtKey3, txtKey4, txtKey5;
	JCheckBox boxCtrlKey1, boxCtrlKey2, boxCtrlKey3, boxCtrlKey4, boxCtrlKey5;
	JCheckBox boxAltKey1, boxAltKey2, boxAltKey3, boxAltKey4, boxAltKey5;
	JCheckBox boxShiftKey1, boxShiftKey2, boxShiftKey3, boxShiftKey4, boxShiftKey5;

	Hotkey[] hotkeys = new Hotkey[5];
	Color colorActive, colorInactive;

	public TelaConfig() {
		setSize(345, 310); // -5X -37Y
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		setLayout(null);
		setTitle("Configurações");
		setIconImage(new ImageIcon(Class.class.getResource("/view/config.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
		setLocationRelativeTo(null);
		
		btnOk = new JButton();
		btnCancel = new JButton();
		btnDefault = new JButton();
		lblTitle = new JLabel();
		lblStart = new JLabel();
		lblPause = new JLabel();
		lblStop = new JLabel();
		lblAdd = new JLabel();
		lblSave = new JLabel();
		lblCtrl = new JLabel();
		lblAlt = new JLabel();
		lblShift = new JLabel();
		txtKey1 = new JTextField();
		txtKey2 = new JTextField();
		txtKey3 = new JTextField();
		txtKey4 = new JTextField();
		txtKey5 = new JTextField();
		boxCtrlKey1 = new JCheckBox();
		boxCtrlKey2 = new JCheckBox();
		boxCtrlKey3 = new JCheckBox();
		boxCtrlKey4 = new JCheckBox();
		boxCtrlKey5 = new JCheckBox();
		boxAltKey1 = new JCheckBox();
		boxAltKey2 = new JCheckBox();
		boxAltKey3 = new JCheckBox();
		boxAltKey4 = new JCheckBox();
		boxAltKey5 = new JCheckBox();
		boxShiftKey1 = new JCheckBox();
		boxShiftKey2 = new JCheckBox();
		boxShiftKey3 = new JCheckBox();
		boxShiftKey4 = new JCheckBox();
		boxShiftKey5 = new JCheckBox();

		Font def = new Font("Arial", 0, 12);
		colorActive = new Color(255, 255, 255, 255);
		colorInactive = new Color(150, 150, 150, 255);

		lblTitle.setSize(200, 20);
		lblTitle.setLocation(10, 10);
		lblTitle.setText("Hotkeys de atalho");
		lblTitle.setFont(new Font("Arial", Font.BOLD, 12));

		lblStart.setSize(100, 20);
		lblStart.setLocation(lblTitle.getLocation().x, lblTitle.getLocation().y + lblStart.getSize().height + 20);
		lblStart.setText("Começar:");
		lblStart.setFont(def);

		lblPause.setSize(lblStart.getSize().width, lblStart.getSize().height);
		lblPause.setLocation(lblTitle.getLocation().x, lblStart.getLocation().y + lblPause.getSize().height + 20);
		lblPause.setText("Pausar:");
		lblPause.setFont(def);

		lblStop.setSize(lblStart.getSize().width, lblStart.getSize().height);
		lblStop.setLocation(lblTitle.getLocation().x, lblPause.getLocation().y + lblStop.getSize().height + 20);
		lblStop.setText("Parar:");
		lblStop.setFont(def);

		lblAdd.setSize(lblStart.getSize().width, lblStart.getSize().height);
		lblAdd.setLocation(lblTitle.getLocation().x, lblStop.getLocation().y + lblAdd.getSize().height + 20);
		lblAdd.setText("Anotação:");
		lblAdd.setFont(def);

		lblSave.setSize(lblStart.getSize().width, lblStart.getSize().height);
		lblSave.setLocation(lblTitle.getLocation().x, lblAdd.getLocation().y + lblSave.getSize().height + 20);
		lblSave.setText("Salvar:");
		lblSave.setFont(def);

		txtKey1.setSize(100, 20);
		txtKey1.setLocation(lblStart.getLocation().x + 70, lblStart.getLocation().y);
		txtKey1.addKeyListener(this);
		txtKey1.setEditable(false);
		txtKey1.setBackground(colorInactive);
		txtKey1.addFocusListener(this);

		txtKey2.setSize(txtKey1.getSize().width, txtKey1.getSize().height);
		txtKey2.setLocation(lblPause.getLocation().x + 70, lblPause.getLocation().y);
		txtKey2.addKeyListener(this);
		txtKey2.setEditable(false);
		txtKey2.setBackground(colorInactive);
		txtKey2.addFocusListener(this);

		txtKey3.setSize(txtKey1.getSize().width, txtKey1.getSize().height);
		txtKey3.setLocation(lblStop.getLocation().x + 70, lblStop.getLocation().y);
		txtKey3.addKeyListener(this);
		txtKey3.setEditable(false);
		txtKey3.setBackground(colorInactive);
		txtKey3.addFocusListener(this);

		txtKey4.setSize(txtKey1.getSize().width, txtKey1.getSize().height);
		txtKey4.setLocation(lblAdd.getLocation().x + 70, lblAdd.getLocation().y);
		txtKey4.addKeyListener(this);
		txtKey4.setEditable(false);
		txtKey4.setBackground(colorInactive);
		txtKey4.addFocusListener(this);

		txtKey5.setSize(txtKey1.getSize().width, txtKey1.getSize().height);
		txtKey5.setLocation(lblSave.getLocation().x + 70, lblSave.getLocation().y);
		txtKey5.addKeyListener(this);
		txtKey5.setEditable(false);
		txtKey5.setBackground(colorInactive);
		txtKey5.addFocusListener(this);

		btnOk.setSize(50, 20);
		btnOk.setLocation(10, this.getHeight() - btnOk.getSize().height - 37);
		btnOk.setText("Ok");
		btnOk.setFont(new Font("Arial", Font.BOLD, 10));
		btnOk.addActionListener(this);

		btnDefault.setSize(80, btnOk.getSize().height);
		btnDefault.setLocation(this.getWidth() / 2 - btnOk.getSize().width, btnOk.getLocation().y);
		btnDefault.setText("Padrão");
		btnDefault.setFont(new Font("Arial", Font.BOLD, 10));
		btnDefault.addActionListener(this);

		btnCancel.setSize(btnDefault.getSize().width, btnOk.getSize().height);
		btnCancel.setLocation(this.getWidth() - btnDefault.getSize().width - 15, btnOk.getLocation().y);
		btnCancel.setText("Cancelar");
		btnCancel.setFont(new Font("Arial", Font.BOLD, 10));
		btnCancel.addActionListener(this);

		lblCtrl.setSize(40, 20);
		lblCtrl.setLocation(txtKey1.getLocation().x + txtKey1.getSize().width + 20, lblTitle.getLocation().y);
		lblCtrl.setText("Ctrl");

		lblAlt.setSize(lblCtrl.getSize().width, lblCtrl.getSize().height);
		lblAlt.setLocation(lblCtrl.getLocation().x + lblCtrl.getSize().width + 15, lblCtrl.getLocation().y);
		lblAlt.setText("Alt");

		lblShift.setSize(lblCtrl.getSize().width, lblCtrl.getSize().height);
		lblShift.setLocation(lblAlt.getLocation().x + lblAlt.getSize().width + 5, lblAlt.getLocation().y);
		lblShift.setText("Shift");

		boxCtrlKey1.setSize(20, 20);
		boxCtrlKey1.setLocation(lblCtrl.getLocation().x, txtKey1.getLocation().y);

		boxCtrlKey2.setSize(20, 20);
		boxCtrlKey2.setLocation(lblCtrl.getLocation().x, txtKey2.getLocation().y);

		boxCtrlKey3.setSize(20, 20);
		boxCtrlKey3.setLocation(lblCtrl.getLocation().x, txtKey3.getLocation().y);

		boxCtrlKey4.setSize(20, 20);
		boxCtrlKey4.setLocation(lblCtrl.getLocation().x, txtKey4.getLocation().y);

		boxCtrlKey5.setSize(20, 20);
		boxCtrlKey5.setLocation(lblCtrl.getLocation().x, txtKey5.getLocation().y);

		boxAltKey1.setSize(20, 20);
		boxAltKey1.setLocation(lblAlt.getLocation().x - 5, txtKey1.getLocation().y);

		boxAltKey2.setSize(20, 20);
		boxAltKey2.setLocation(lblAlt.getLocation().x - 5, txtKey2.getLocation().y);

		boxAltKey3.setSize(20, 20);
		boxAltKey3.setLocation(lblAlt.getLocation().x - 5, txtKey3.getLocation().y);

		boxAltKey4.setSize(20, 20);
		boxAltKey4.setLocation(lblAlt.getLocation().x - 5, txtKey4.getLocation().y);

		boxAltKey5.setSize(20, 20);
		boxAltKey5.setLocation(lblAlt.getLocation().x - 5, txtKey5.getLocation().y);

		boxShiftKey1.setSize(20, 20);
		boxShiftKey1.setLocation(lblShift.getLocation().x, txtKey1.getLocation().y);

		boxShiftKey2.setSize(20, 20);
		boxShiftKey2.setLocation(lblShift.getLocation().x, txtKey2.getLocation().y);

		boxShiftKey3.setSize(20, 20);
		boxShiftKey3.setLocation(lblShift.getLocation().x, txtKey3.getLocation().y);

		boxShiftKey4.setSize(20, 20);
		boxShiftKey4.setLocation(lblShift.getLocation().x, txtKey4.getLocation().y);

		boxShiftKey5.setSize(20, 20);
		boxShiftKey5.setLocation(lblShift.getLocation().x, txtKey5.getLocation().y);

		add(lblTitle);
		add(lblStart);
		add(lblPause);
		add(lblStop);
		add(lblAdd);
		add(lblSave);
		add(txtKey1);
		add(txtKey2);
		add(txtKey3);
		add(txtKey4);
		add(txtKey5);
		add(btnOk);
		add(btnDefault);
		add(btnCancel);
		add(lblCtrl);
		add(lblAlt);
		add(lblShift);
		add(boxCtrlKey1);
		add(boxCtrlKey2);
		add(boxCtrlKey3);
		add(boxCtrlKey4);
		add(boxCtrlKey5);
		add(boxAltKey1);
		add(boxAltKey2);
		add(boxAltKey3);
		add(boxAltKey4);
		add(boxAltKey5);
		add(boxShiftKey1);
		add(boxShiftKey2);
		add(boxShiftKey3);
		add(boxShiftKey4);
		add(boxShiftKey5);

		setVisible(true);
		
		for(Hotkey h : hotkeys){
			h = new Hotkey();
		}
		
		readConfig();
		
		boxCtrlKey1.requestFocus();
		this.addWindowListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnOk)) {
			if(!validateHotkeys()){
				JOptionPane.showMessageDialog(this, "Não foi possivel salvar as configurações\nHotkeys duplicadas ou inválidas!", "Hotkeys Invalidas", JOptionPane.ERROR_MESSAGE,
						new ImageIcon(new ImageIcon(Class.class.getResource("/view/alert.png")).getImage()
								.getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
			}else{
				registerHotkeys();
				this.dispose();
			}		
		} else if (e.getSource().equals(btnDefault)) {
			setDefault();
		} else if (e.getSource().equals(btnCancel)) {
			this.dispose();
		}

	}

	private boolean validateHotkeys() {
		boolean flag = true;
		JTextField[] txts = getTextFieldComponents();
		for(int i = 0; i < txts.length; i++){
			for(int j = i+1; j < txts.length; j++){
				if(txts[i].getText().equals(txts[j].getText())){
					flag = false;
				}
			}
			if(txts[i].getText().equals("INVÁLIDO")){
				flag = false;
			}
		}
		return flag;
	}

	// ALT = 1, CTRL = 2, SHIFT = 4
	private void registerHotkeys() {
		hotkeys[0].setModifiers(((boxCtrlKey1.isSelected()) ? 2 : 0) + ((boxAltKey1.isSelected()) ? 1 : 0)
				+ ((boxShiftKey1.isSelected()) ? 4 : 0));
		hotkeys[1].setModifiers(((boxCtrlKey2.isSelected()) ? 2 : 0) + ((boxAltKey2.isSelected()) ? 1 : 0)
				+ ((boxShiftKey2.isSelected()) ? 4 : 0));
		hotkeys[2].setModifiers(((boxCtrlKey3.isSelected()) ? 2 : 0) + ((boxAltKey3.isSelected()) ? 1 : 0)
				+ ((boxShiftKey3.isSelected()) ? 4 : 0));
		hotkeys[3].setModifiers(((boxCtrlKey4.isSelected()) ? 2 : 0) + ((boxAltKey4.isSelected()) ? 1 : 0)
				+ ((boxShiftKey4.isSelected()) ? 4 : 0));
		hotkeys[4].setModifiers(((boxCtrlKey5.isSelected()) ? 2 : 0) + ((boxAltKey5.isSelected()) ? 1 : 0)
				+ ((boxShiftKey5.isSelected()) ? 4 : 0));
		Configuration.getInstance().setHotkeys(hotkeys);
		if(!Configuration.getInstance().writeFile()){
			JOptionPane.showMessageDialog(this, "Erro ao salvar as configurações!", "Erro!", JOptionPane.ERROR_MESSAGE,
					new ImageIcon(new ImageIcon(Class.class.getResource("/view/alert.png")).getImage()
							.getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
		}
		Configuration.getInstance().registerHotkeys();
	}

	void setDefault() {
		txtKey1.setText("F1");
		txtKey2.setText("F2");
		txtKey3.setText("F3");
		txtKey4.setText("F4");
		txtKey5.setText("F5");
		
		for(int i = 0; i < 5; i++){
			hotkeys[i].setCode(112 + i);
			hotkeys[i].setModifiers(0);
		}

		boxCtrlKey1.setSelected(false);
		boxCtrlKey2.setSelected(false);
		boxCtrlKey3.setSelected(false);
		boxCtrlKey4.setSelected(false);
		boxCtrlKey5.setSelected(false);

		boxAltKey1.setSelected(false);
		boxAltKey2.setSelected(false);
		boxAltKey3.setSelected(false);
		boxAltKey4.setSelected(false);
		boxAltKey5.setSelected(false);

		boxShiftKey1.setSelected(false);
		boxShiftKey2.setSelected(false);
		boxShiftKey3.setSelected(false);
		boxShiftKey4.setSelected(false);
		boxShiftKey5.setSelected(false);
	}
	
	private void readConfig(){
		hotkeys = Configuration.getInstance().getHotkeys();
		txtKey1.setText(keyCodeToString(hotkeys[0].getCode()));
		txtKey2.setText(keyCodeToString(hotkeys[1].getCode()));
		txtKey3.setText(keyCodeToString(hotkeys[2].getCode()));
		txtKey4.setText(keyCodeToString(hotkeys[3].getCode()));
		txtKey5.setText(keyCodeToString(hotkeys[4].getCode()));
		
		//0-4 ctrl
		//5-9 alt
		//10-15 shift
		JCheckBox[] comp = getCheckBoxComponents();

		for(int i = 0; i < 5; i++){
			int temp = hotkeys[i].getModifiers();
			switch(temp){
			case 1:
				comp[i + 5].setSelected(true);
				break;
			case 2:
				comp[i].setSelected(true);
				break;
			case 3:
				comp[i + 5].setSelected(true);
				comp[i].setSelected(true);
				break;
			case 4:
				comp[i + 10].setSelected(true);
				break;
			case 5:
				comp[i + 5].setSelected(true);
				comp[i + 10].setSelected(true);
				break;
			case 6:
				comp[i].setSelected(true);
				comp[i + 10].setSelected(true);
				break;
			case 7:
				comp[i].setSelected(true);
				comp[i + 5].setSelected(true);
				comp[i + 10].setSelected(true);
				break;
			}
		}
	}

	private JCheckBox[] getCheckBoxComponents() {
		List<JCheckBox> finalComp = new ArrayList<JCheckBox>();
		for(Component c : this.getContentPane().getComponents()){
			if(c instanceof JCheckBox){
				finalComp.add((JCheckBox)c);
			}
		}
		return finalComp.toArray(new JCheckBox[finalComp.size()]);
	}
	
	private JTextField[] getTextFieldComponents() {
		List<JTextField> finalComp = new ArrayList<JTextField>();
		for(Component c : this.getContentPane().getComponents()){
			if(c instanceof JTextField){
				finalComp.add((JTextField)c);
			}
		}
		return finalComp.toArray(new JTextField[finalComp.size()]);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		JTextField txt = (JTextField) e.getSource();
		String c = null;

		c = keyCodeToString(e.getKeyCode());
		txt.setText(c);

		if (txt.equals(txtKey1)) {
			hotkeys[0].setCode(e.getKeyCode());
		} else if (txt.equals(txtKey2)) {
			hotkeys[1].setCode(e.getKeyCode());
		} else if (txt.equals(txtKey3)) {
			hotkeys[2].setCode(e.getKeyCode());
		} else if (txt.equals(txtKey4)) {
			hotkeys[3].setCode(e.getKeyCode());
		} else if (txt.equals(txtKey5)) {
			hotkeys[4].setCode(e.getKeyCode());
		}
	}

	private String keyCodeToString(int code){
		String c = "";
		switch (code) {
		case 8:
			c = "Backspace";
			break;
		case 9:
			c = "Tab";
			break;
		case 10:
			c = "Enter";
			break;
		case 13:
			c = "Enter";
			break;
		case 16:
			c = "Shift";
			break;
		case 17:
			c = "Ctrl";
			break;
		case 18:
			c = "Alt";
			break;
		case 19:
			c = "Pause/Break";
			break;
		case 20:
			c = "Caps Lock";
			break;
		case 27:
			c = "Esc";
			break;
		case 33:
			c = "Page Up";
			break;
		case 34:
			c = "Page Down";
			break;
		case 35:
			c = "End";
			break;
		case 36:
			c = "Home";
			break;
		case 37:
			c = "Left";
			break;
		case 38:
			c = "Up";
			break;
		case 39:
			c = "Right";
			break;
		case 40:
			c = "Down";
			break;
		case 44:
			c = ",";
			break;
		case 45:
			c = "-";
			break;
		case 46:
			c = ".";
			break;
		case 48:
			c = "0";
			break;
		case 49:
			c = "1";
			break;
		case 50:
			c = "2";
			break;
		case 51:
			c = "3";
			break;
		case 52:
			c = "4";
			break;
		case 53:
			c = "5";
			break;
		case 54:
			c = "6";
			break;
		case 55:
			c = "7";
			break;
		case 56:
			c = "8";
			break;
		case 57:
			c = "9";
			break;
		case 59:
			c = ";";
			break;
		case 61:
			c = "=";
			break;
		case 65:
			c = "A";
			break;
		case 66:
			c = "B";
			break;
		case 67:
			c = "V";
			break;
		case 68:
			c = "D";
			break;
		case 69:
			c = "E";
			break;
		case 70:
			c = "F";
			break;
		case 71:
			c = "G";
			break;
		case 72:
			c = "H";
			break;
		case 73:
			c = "I";
			break;
		case 74:
			c = "J";
			break;
		case 75:
			c = "K";
			break;
		case 76:
			c = "L";
			break;
		case 77:
			c = "M";
			break;
		case 78:
			c = "N";
			break;
		case 79:
			c = "O";
			break;
		case 80:
			c = "P";
			break;
		case 81:
			c = "Q";
			break;
		case 82:
			c = "R";
			break;
		case 83:
			c = "S";
			break;
		case 84:
			c = "T";
			break;
		case 85:
			c = "U";
			break;
		case 86:
			c = "V";
			break;
		case 87:
			c = "W";
			break;
		case 88:
			c = "X";
			break;
		case 89:
			c = "Y";
			break;
		case 90:
			c = "Z";
			break;
		case 91:
			c = "[";
			break;
		case 92:
			c = "Right Windows Key";
			break;
		case 93:
			c = "]";
			break;
		case 96:
			c = "Numpad 0";
			break;
		case 97:
			c = "Numpad 1";
			break;
		case 98:
			c = "Numpad 2";
			break;
		case 99:
			c = "Numpad 3";
			break;
		case 100:
			c = "Numpad 4";
			break;
		case 101:
			c = "Numpad 5";
			break;
		case 102:
			c = "Numpad 6";
			break;
		case 103:
			c = "Numpad 7";
			break;
		case 104:
			c = "Numpad 8";
			break;
		case 105:
			c = "Numpad 9";
			break;
		case 106:
			c = "*";
			break;
		case 107:
			c = "+";
			break;
		case 109:
			c = "-";
			break;
		case 110:
			c = ",";
			break;
		case 111:
			c = "/";
			break;
		case 112:
			c = "F1";
			break;
		case 113:
			c = "F2";
			break;
		case 114:
			c = "F3";
			break;
		case 115:
			c = "F4";
			break;
		case 116:
			c = "F5";
			break;
		case 117:
			c = "F6";
			break;
		case 118:
			c = "F7";
			break;
		case 119:
			c = "F8";
			break;
		case 120:
			c = "F9";
			break;
		case 121:
			c = "F10";
			break;
		case 122:
			c = "F11";
			break;
		case 123:
			c = "F12";
			break;
		case 129:
			c = "´";
			break;
		case 131:
			c = "~";
			break;
		case 144:
			c = "Num Lock";
			break;
		case 145:
			c = "Scroll Lock";
			break;
		case 186:
			c = ";";
			break;
		case 187:
			c = "=";
			break;
		case 188:
			c = ",";
			break;
		case 189:
			c = "-";
			break;
		case 190:
			c = ".";
			break;
		case 191:
			c = "/";
			break;
		case 192:
			c = "`";
			break;
		case 219:
			c = "[";
			break;
		case 220:
			c = "\\";
			break;
		case 221:
			c = "]";
			break;
		case 222:
			c = "'";
			break;
		case 32:
			c = "Space";
			break;
		default:
			c = "INVÁLIDO";
		}
		return c;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// JTextField txt = (JTextField) e.getSource();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void focusGained(FocusEvent e) {
		if(e.getSource() instanceof JTextField){
			JTextField txt = (JTextField)e.getSource();
			txt.setBackground(colorActive);
		}
		
	}

	@Override
	public void focusLost(FocusEvent e) {
		if(e.getSource() instanceof JTextField){
			JTextField txt = (JTextField)e.getSource();
			txt.setBackground(colorInactive);
		}
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		Configuration.getInstance().registerHotkeys();
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

}
