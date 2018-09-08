import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MessageWindow {
	
	public static void run(String msg) {
		JFrame frame = new JFrame();
		JOptionPane.showMessageDialog(frame, msg);
		frame.setLocationRelativeTo(null); //posiziona la finestra al centro
	}
}
