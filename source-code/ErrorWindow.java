import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ErrorWindow {
	
	public static void run(String msg) {
		JFrame frame = new JFrame();
		JOptionPane.showMessageDialog(frame, msg, "Errore", JOptionPane.ERROR_MESSAGE);
		frame.setLocationRelativeTo(null); //posiziona la finestra al centro
	}

}
