import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.melloware.jintellitype.JIntellitype;

import view.Tela;

public class Application extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {

		if (JIntellitype.checkInstanceAlreadyRunning("Timeline")) {
			int op = JOptionPane.showConfirmDialog(null,
					"Outra instância do aplicativo já está em execução\nDeseja terminá-la?", "Duplicação de instância",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (op == JOptionPane.YES_OPTION) {
				try {
					Runtime.getRuntime().exec("Taskkill /F /FI \"WINDOWTITLE eq Timeline*\" /T");
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "Erro ao executar comando",
							JOptionPane.ERROR_MESSAGE);
				}
			} else {
				System.exit(1);
			}
		}

		// System.out.println(JIntellitype.MOD_ALT + " - " +
		// JIntellitype.MOD_CONTROL + " - " + JIntellitype.MOD_SHIFT);

		new Tela();
	}

}
