import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class NoteWindow {

	private JFrame frame;

	
	
	public static void run(UserSession sessione, RandomVideo rv) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NoteWindow window = new NoteWindow(sessione,rv);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public NoteWindow(UserSession sessione, RandomVideo rv) {
		initialize(sessione,rv);
	}

	
	private void initialize(UserSession sessione, RandomVideo rv) {
		frame = new JFrame();
		frame.setBounds(100, 100, 514, 223);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblInserisciLeNote = new JLabel("inserisci le note");
		lblInserisciLeNote.setFont(new Font("Dialog", Font.BOLD, 18));
		lblInserisciLeNote.setBounds(168, 29, 206, 15);
		frame.getContentPane().add(lblInserisciLeNote);
		
		JTextPane textPane = new JTextPane();
		textPane.setFont(new Font("Dialog", Font.PLAIN, 16));
		textPane.setBounds(12, 58, 482, 82);
		frame.getContentPane().add(textPane);
		textPane.setText(sessione.note);
		
		JButton btnTermina = new JButton("inserisci e salva");
		btnTermina.setFont(new Font("Dialog", Font.BOLD, 14));
		btnTermina.setBounds(307, 152, 187, 25);
		frame.getContentPane().add(btnTermina);
	
	
		
		
		// cosa fare quando clicco su "aggiungi e salva"
		btnTermina.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				sessione.note = textPane.getText();
				sessione.complete(rv);
				
				try {
					sessione.save();
				}catch (FileNotFoundException | UnsupportedEncodingException exce ) {
					ErrorWindow.run("Errore nel salvataggio della sessione.");
					System.exit(1);}
				
				
				frame.setVisible(false);
				MessageWindow.run("Sessione salvata correttamente.");
				System.exit(0);
			}
		});
		
		
		// serve per far apparire il dialogo di conferma anche quando si clicca sulla x della finestra
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		WindowListener exitListener = new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				ExitWindow.run();
			}
		};
		frame.addWindowListener(exitListener);
							
							
		frame.setTitle("RVC - Inserimento note"); // titolo della finestra
		frame.setLocationRelativeTo(null); //posiziona la finestra al centro
	
	
	}
			
}
