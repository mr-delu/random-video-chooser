import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class WarningWindow {
	

	public static void run() {
		JFrame frame = new JFrame();
		JOptionPane.showMessageDialog(frame,
			"I parametri immessi non sono corretti o sono incompleti." + '\n' + "Controlla l'inserimento prima di proseguire.",
			"Attenzione",
			JOptionPane.WARNING_MESSAGE);
		frame.setLocationRelativeTo(null); //posiziona la finestra al centro
	}
	
	
	public static void run(String msg) {
		JFrame frame = new JFrame();
		JOptionPane.showMessageDialog(frame,msg,"Attenzione",JOptionPane.WARNING_MESSAGE);
		frame.setLocationRelativeTo(null); //posiziona la finestra al centro
	} 

}
