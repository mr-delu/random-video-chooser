import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ExitWindow {
	
	
	public static void run() {
		JFrame frame = new JFrame();
		int scelta = JOptionPane.showConfirmDialog(frame, "Sei sicuro di voler uscire?" + '\n' +
				"I dati NON verranno salvati.", "Attenzione", JOptionPane.YES_NO_OPTION);
		
		frame.setLocationRelativeTo(null); //posiziona la finestra al centro
		
		if ( scelta == JOptionPane.YES_OPTION)
			System.exit(0);
		
		else
			frame.setVisible(false);
	}
	
	
	public static boolean run(String msg) {
		JFrame frame = new JFrame();
		int scelta = JOptionPane.showConfirmDialog(frame, msg, "Attenzione", JOptionPane.YES_NO_OPTION);
		frame.setLocationRelativeTo(null); //posiziona la finestra al centro
		
		if ( scelta == JOptionPane.YES_OPTION)
			return true;
		
		else
			return false;
	}
	
	
} // class
