package view;

import java.awt.Font;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.melloware.jintellitype.HotkeyListener;
import com.melloware.jintellitype.JIntellitype;

import model.Annotation;
import model.Configuration;
import model.Utilities;

public class Tela extends JFrame implements ActionListener, HotkeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel lblTimer;
	JButton btnConfigs, btnPlay, btnStop, btnPause, btnSave, btnFileChooser, btnConfig, btnAdd;
	ImageIcon imgSave, imgConfig, imgAdd, imgAlert, imgIcon;

	TimerThread timerThread;
	Timer timer;
	// int time = 0;
	boolean isSaved = true;

	ArrayList<Annotation> annotations;
	SystemTray tray;
	TrayIcon trayIcon = null;
	MenuItem menuOpen, menuExit;
	PopupMenu popup;

	public Tela() {
		setSize(305, 170); // -5X -37Y --> 495 x 163
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLayout(null);
		setTitle("Timeline");
		setLocationRelativeTo(null);

		timer = new Timer();
		annotations = new ArrayList<>();
		imgSave = new ImageIcon(Class.class.getResource("/view/save.png"));
		imgAdd = new ImageIcon(Class.class.getResource("/view/add.png"));
		imgConfig = new ImageIcon(Class.class.getResource("/view/config.png"));
		imgAlert = new ImageIcon(Class.class.getResource("/view/alert.png"));
		imgIcon = new ImageIcon(Class.class.getResource("/view/icon.png"));

		setIconImage(imgIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));

		lblTimer = new JLabel();
		btnPlay = new JButton();
		btnPause = new JButton();
		btnStop = new JButton();
		btnSave = new JButton();
		btnConfig = new JButton();
		btnAdd = new JButton();

		btnPlay.setLocation(10, 15);
		btnPlay.setSize(100, 30);
		btnPlay.setText("Start");
		btnPlay.addActionListener(this);

		btnPause.setLocation(btnPlay.getLocation().x, btnPlay.getLocation().y + btnPlay.getSize().height + 10);
		btnPause.setSize(btnPlay.getSize());
		btnPause.setText("Pause");
		btnPause.setEnabled(false);
		btnPause.addActionListener(this);

		btnStop.setLocation(btnPause.getLocation().x, btnPause.getLocation().y + btnPause.getSize().height + 10);
		btnStop.setSize(btnPlay.getSize());
		btnStop.setText("Stop");
		btnStop.setEnabled(false);
		btnStop.addActionListener(this);

		lblTimer.setLocation(btnPlay.getLocation().x + btnPlay.getSize().width + 20, btnPlay.getLocation().y);
		lblTimer.setSize(160, btnPlay.getSize().height * 2 + 10);
		lblTimer.setText("00:00:00");
		lblTimer.setFont(new Font("Courier New", Font.BOLD, 30));
		lblTimer.setOpaque(true);

		btnSave.setSize(btnPlay.getSize().height - 5, btnPlay.getSize().height - 5);
		btnSave.setLocation(btnStop.getLocation().x + btnStop.getSize().width + 130, btnStop.getLocation().y);
		btnSave.setIcon(new ImageIcon(imgSave.getImage().getScaledInstance(btnSave.getSize().width,
				btnSave.getSize().height, java.awt.Image.SCALE_SMOOTH)));
		btnSave.setBorderPainted(false);
		btnSave.setContentAreaFilled(false);
		btnSave.setFocusPainted(false);
		btnSave.addActionListener(this);

		btnConfig.setSize(btnSave.getSize().width, btnSave.getSize().height);
		btnConfig.setLocation(btnSave.getLocation().x - btnConfig.getSize().width - 25, btnSave.getLocation().y);
		btnConfig.setIcon(new ImageIcon(imgConfig.getImage().getScaledInstance(btnConfig.getSize().width,
				btnConfig.getSize().height, java.awt.Image.SCALE_SMOOTH)));
		btnConfig.setBorderPainted(false);
		btnConfig.setContentAreaFilled(false);
		btnConfig.setFocusPainted(false);
		btnConfig.addActionListener(this);

		btnAdd.setSize(btnSave.getSize().width, btnSave.getSize().height);
		btnAdd.setLocation(btnConfig.getLocation().x - btnAdd.getSize().width - 25, btnConfig.getLocation().y);
		btnAdd.setIcon(new ImageIcon(imgAdd.getImage().getScaledInstance(btnAdd.getSize().width,
				btnAdd.getSize().height, java.awt.Image.SCALE_SMOOTH)));
		btnAdd.setBorderPainted(false);
		btnAdd.setContentAreaFilled(false);
		btnAdd.setFocusPainted(false);
		btnAdd.addActionListener(this);

		add(btnPlay);
		add(btnStop);
		add(btnPause);
		add(lblTimer);
		add(btnSave);
		add(btnConfig);
		add(btnAdd);
		setVisible(true);

		// registerHotkeys();
		tray = SystemTray.getSystemTray();
		setupSystemTrayBar();
		JIntellitype.getInstance().addHotKeyListener(this);
		Configuration.getInstance().registerHotkeys();

		this.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent evt) {
				JIntellitype.getInstance().cleanUp();
				System.exit(0);
			}
		});

		this.addWindowStateListener(new WindowStateListener() {
			@Override
			public void windowStateChanged(WindowEvent e) {
				if (e.getNewState() == ICONIFIED) {
					try {
						tray.add(trayIcon);
						setVisible(false);
					} catch (Exception ex) {
					}
				}
				if (e.getNewState() == NORMAL) {
					tray.remove(trayIcon);
					setVisible(true);
				}

			}
		});

	}

	@Override
	public void onHotKey(int key) {
		switch (key) {
		case 1:
			buttonStart();
			break;
		case 2:
			buttonPause();
			break;
		case 3:
			buttonStop();
			break;
		case 4:
			addAnnotation();
			break;
		case 5:
			saveArchive();
			break;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnPlay)) {
			buttonStart();
		} else if (e.getSource().equals(btnPause)) {
			buttonPause();
		} else if (e.getSource().equals(btnStop)) {
			buttonStop();
		} else if (e.getSource().equals(btnSave)) {
			saveArchive();
		} else if (e.getSource().equals(btnConfig)) {
			Configuration.getInstance().unregisterHotkeys();
			new TelaConfig();
		} else if (e.getSource().equals(btnAdd)) {
			addAnnotation();
		} else if (e.getSource().equals(menuOpen)) {
			this.setVisible(true);
			this.setExtendedState(JFrame.NORMAL);
		} else if (e.getSource().equals(menuExit)) {
			buttonStop();
			int temp = JOptionPane.showConfirmDialog(this, "Deseja salvar as anotações?", "Salvar",
					JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
					new ImageIcon(imgSave.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
			if (!isSaved && temp == JOptionPane.YES_OPTION) {
				saveArchive();
			} else if (temp != JOptionPane.CANCEL_OPTION) {
				this.dispose();
				System.exit(0);
			}
		}
	}

	public void buttonStart() {
		if (!btnPause.isEnabled() && !btnStop.isEnabled()) {
			if (!isSaved) {
				int temp = JOptionPane.showConfirmDialog(this, "Deseja salvar as anotações?", "Salvar",
						JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
						new ImageIcon(imgSave.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
				if (temp == JOptionPane.YES_OPTION) {
					saveArchive();
				} else if (temp == JOptionPane.CANCEL_OPTION) {
					return;
				}
			}
			lblTimer.setText("00:00:00");
			timerThread = new TimerThread();
			timerThread.start();
			btnPlay.setEnabled(false);
			btnStop.setEnabled(true);
			btnPause.setEnabled(true);
			// this.setState(Frame.ICONIFIED);

		}
	}

	public void buttonPause() {
		if (btnStop.isEnabled() && !btnPlay.isEnabled()) {
			timerThread.pause();
			btnPause.setText((timerThread.isPaused) ? "Play" : "Pause");
		}
	}

	public void buttonStop() {
		if (!btnPlay.isEnabled() && btnPause.isEnabled()) {
			timerThread.stop();
			btnPlay.setEnabled(true);
			btnStop.setEnabled(false);
			btnPause.setEnabled(false);
			btnPause.setText("Pause");
			this.requestFocus();
			this.setVisible(true);
			this.setExtendedState(JFrame.NORMAL);
		}
	}

	public void addAnnotation() {
		if (timerThread != null && timerThread.isRunning()) {
		} else {
			return;
		}
		int commentTime = timerThread.getTime();
		String comment = (String) JOptionPane.showInputDialog(this, "Escreva o comentário no campo à baixo",
				"Adicionar Anotação", 0, new ImageIcon(imgAdd.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH)),
				null, null);
		if (comment != null) {
			Annotation a = new Annotation(commentTime, comment);
			annotations.add(a);
		}
	}

	public void saveArchive() {
		if (timerThread == null) {
			return;
		}

		FileWriter writer = null;
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Escolha aonde deseja salvar o arquivo");

		int userSelection = fileChooser.showSaveDialog(this);

		try {
			if (userSelection == JFileChooser.APPROVE_OPTION) {
				writer = new FileWriter(fileChooser.getSelectedFile().getAbsolutePath() + ".txt", true);
				for (Annotation a : annotations) {
					writer.append(a.toString() + "\r\n");
				}
				writer.flush();
				writer.close();
			} else {
				return;
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(this, "Erro ao salvar o arquivo!", "Erro!", JOptionPane.ERROR_MESSAGE,
					new ImageIcon(imgAlert.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
			return;
		}
		isSaved = true;
		JOptionPane.showMessageDialog(this, "Arquivo salvo com sucesso!");
	}

	public boolean setupSystemTrayBar() {

		if (SystemTray.isSupported()) {
			popup = new PopupMenu();
			menuOpen = new MenuItem("Abrir");
			menuOpen.addActionListener(this);
			menuExit = new MenuItem("Sair");
			menuExit.addActionListener(this);

			popup.add(menuOpen);
			popup.add(menuExit);
			trayIcon = new TrayIcon(imgIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH), "Timeline",
					popup);
			trayIcon.addActionListener(this);
		} else {
			JOptionPane.showMessageDialog(this, "Sistema não tem suporte para ", "Erro!", JOptionPane.ERROR_MESSAGE,
					imgAlert);
			return false;
		}
		return true;
	}

	class TimerThread extends TimerTask {
		private boolean isRunning = false;
		private boolean isPaused = false;
		private int time = 0;

		@Override
		public void run() {
			if (!isPaused) {
				this.isRunning = true;
				time++;
				String formattedTime = Utilities.formatTimeValue(time);
				lblTimer.setText(formattedTime);
			}
		}

		public boolean isRunning() {
			return isRunning;
		}

		public void pause() {
			isPaused = !isPaused;
		}

		public void start() {
			isSaved = false;
			timer.scheduleAtFixedRate(this, 1000, 1000);
		}

		public void stop() {
			this.cancel();
			this.isRunning = false;
		}

		public int getTime() {
			return time;
		}
	}
}
