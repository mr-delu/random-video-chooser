import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

public class FinalWindow {

	private JFrame frame;
	private RandomVideo rv;
	private UserSession us;
	private long timeSession;


	public FinalWindow(RandomVideo rand, UserSession sessione, long durataSessione) {
		this.rv = rand;
		this.us = sessione;
		this.timeSession = durataSessione;
		initialize();
	}


	
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 514, 223);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblSessioneConclusaCorrettamente = new JLabel("sessione conclusa correttamente");
		lblSessioneConclusaCorrettamente.setFont(new Font("Dialog", Font.BOLD, 18));
		lblSessioneConclusaCorrettamente.setBounds(79, 42, 363, 16);
		frame.getContentPane().add(lblSessioneConclusaCorrettamente);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("aggiungere note");
		chckbxNewCheckBox.setFont(new Font("Dialog", Font.BOLD, 16));
		chckbxNewCheckBox.setBounds(38, 112, 239, 23);
		frame.getContentPane().add(chckbxNewCheckBox);
		
		JButton btnSalvaEEsci = new JButton("termina");
		btnSalvaEEsci.setFont(new Font("Dialog", Font.BOLD, 16));
		btnSalvaEEsci.setBounds(377, 153, 117, 25);
		frame.getContentPane().add(btnSalvaEEsci);
		
		JCheckBox chckbxSalvareDatiSessione = new JCheckBox("salvare dati sessione");
		chckbxSalvareDatiSessione.setFont(new Font("Dialog", Font.BOLD, 16));
		chckbxSalvareDatiSessione.setSelected(true);
		chckbxSalvareDatiSessione.setBounds(38, 78, 244, 23);
		frame.getContentPane().add(chckbxSalvareDatiSessione);
		
		
		// codice mio -----------------------------------------------------------------------------------
		
		
		// serve per far apparire il dialogo di conferma anche quando si clicca sulla x della finestra
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		WindowListener exitListener = new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				ExitWindow.run();
			}
		};
		frame.addWindowListener(exitListener);
				
				
		frame.setTitle("RVC - Fine Sessione"); // titolo della finestra
		
		frame.setVisible(true);
		
		
		btnSalvaEEsci.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if ( !chckbxSalvareDatiSessione.isSelected() ) { // controllo opzione salva sessione
					ExitWindow.run();
					return;
				}
				
				if ( chckbxNewCheckBox.isSelected() ) { // controllo opzione aggiungi note
					frame.setVisible(false);
					NoteWindow.run(us,rv,timeSession);
					return;
				}
				
				us.complete(rv,timeSession);
				
				try {
					us.save();
				}catch (FileNotFoundException | UnsupportedEncodingException exce ) {
					ErrorWindow.run("Errore nel salvataggio della sessione.");
					System.exit(1);}
				
				frame.setVisible(false);
				MessageWindow.run("Sessione salvata correttamente.");
				System.exit(0);
			}
		});
		frame.setLocationRelativeTo(null); //posiziona la finestra al centro
	}
}
