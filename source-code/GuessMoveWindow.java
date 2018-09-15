import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import javax.swing.JProgressBar;

public class GuessMoveWindow {

	private JFrame frame;
	private JTextField textField;
	private RandomVideo rv;
	private UserSession us;
	private int maxError;


	public GuessMoveWindow(RandomVideo rand, int erroriMassimiScelti, UserSession sessione) {
		this.rv = rand;
		this.us = sessione;
		this.maxError = erroriMassimiScelti;
		initialize();
		this.frame.setVisible(true);
	}


	
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 544, 435);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
				
		JLabel lblDaIndovinare = new JLabel("da indovinare:");
		lblDaIndovinare.setFont(new Font("Dialog", Font.BOLD, 16));
		lblDaIndovinare.setBounds(36, 104, 129, 15);
		frame.getContentPane().add(lblDaIndovinare);

		
		JLabel lblErroriTotali = new JLabel("errori totali:");
		lblErroriTotali.setFont(new Font("Dialog", Font.BOLD, 16));
		lblErroriTotali.setBounds(36, 145, 115, 15);
		frame.getContentPane().add(lblErroriTotali);
		
		JLabel lblMossa = new JLabel("mossa inserita:");
		lblMossa.setFont(new Font("Dialog", Font.BOLD, 16));
		lblMossa.setBounds(36, 248, 158, 15);
		frame.getContentPane().add(lblMossa);
		
		JLabel lblNewLabel = new JLabel("dettagli sessione in corso:");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		lblNewLabel.setBounds(119, 46, 286, 25);
		frame.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setFont(new Font("Dialog", Font.PLAIN, 16));
		textField.setBounds(235, 243, 164, 25);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblDaindovinare = new JLabel("" + rv.toGuess());
		lblDaindovinare.setFont(new Font("Dialog", Font.BOLD, 16));
		lblDaindovinare.setBounds(235, 104, 249, 15);
		frame.getContentPane().add(lblDaindovinare);

		
		JLabel lblErroritotali = new JLabel("" + rv.totalError());
		lblErroritotali.setFont(new Font("Dialog", Font.BOLD, 16));
		lblErroritotali.setBounds(235, 145, 249, 15);
		frame.getContentPane().add(lblErroritotali);
		
		
		JButton btnPlay = new JButton("play");
		btnPlay.setFont(new Font("Dialog", Font.BOLD, 16));
		btnPlay.setBounds(278, 365, 117, 25);
		frame.getContentPane().add(btnPlay);
		
		JButton btnAvati = new JButton("avanti");
		btnAvati.setFont(new Font("Dialog", Font.BOLD, 16));
		btnAvati.setBounds(407, 365, 117, 25);
		frame.getContentPane().add(btnAvati);
		
		
		// codice mio --------------------------------------------------------------------------------------------------------
		
		
		JButton btnSalvaEEsci = new JButton("termina");
		btnSalvaEEsci.setFont(new Font("Dialog", Font.BOLD, 16));
		btnSalvaEEsci.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if ( ExitWindow.run("Sei sicuro di voler terminare la sessione?") ) {
					frame.setVisible(false);
					new FinalWindow(rv,us);
				}
			}
		});
		btnSalvaEEsci.setBounds(12, 365, 117, 25);
		frame.getContentPane().add(btnSalvaEEsci);
		
		JProgressBar progressBar = new JProgressBar(0,rv.toGuess());
		progressBar.setFont(new Font("Dialog", Font.BOLD, 16));
		progressBar.setBounds(235, 285, 262, 25);
		frame.getContentPane().add(progressBar);
		progressBar.setValue(0);
		
		JLabel lblProgresso = new JLabel("progresso:");
		lblProgresso.setFont(new Font("Dialog", Font.BOLD, 16));
		lblProgresso.setBounds(36, 289, 129, 21);
		frame.getContentPane().add(lblProgresso);
		progressBar.setStringPainted(true);
		
		JLabel lblRispostaCorretta = new JLabel("mossa corretta:");
		lblRispostaCorretta.setFont(new Font("Dialog", Font.BOLD, 16));
		lblRispostaCorretta.setBounds(36, 208, 158, 15);
		frame.getContentPane().add(lblRispostaCorretta);
		
		JLabel lblRisposta = new JLabel(rv.getCurretMove());
		lblRisposta.setFont(new Font("Dialog", Font.BOLD, 16));
		lblRisposta.setBounds(235, 209, 164, 15);
		frame.getContentPane().add(lblRisposta);
		
		
		
		// quando clicchi play fa partire il video "caricato nel RandomVideo"
		btnPlay.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					rv.openVideo();
				} catch (IOException e1) {
					ErrorWindow.run("Errore durante l'apertura del video.");
					System.exit(2);}

			}
		});
		
		
		// quando clicchi avanti
		btnAvati.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				if ( !isCommandInteger(textField.getText()) ) {
					WarningWindow.run("Hai specificato un input non possibile per il programma.");
					return;
				}
				
				
				boolean repeat = false;
				do {

					if ( !repeat && rv.guess(textField.getText()) )
						progressBar.setValue(progressBar.getValue() + 1);

					if ( rv.hasNextVideo() ) {
						rv.prepareNextVideo();
						lblDaindovinare.setText("" + rv.toGuess());
						lblErroritotali.setText("" + rv.totalError());
						textField.setText("");
						lblRisposta.setText(rv.getCurretMove());

						if ( rv.errorOnCurrentVideo() >= maxError ) {
							if ( repeat = ExitWindow.run("Questa mossa ha associato un elevato numero di tentativi errati, vuoi che non venga piu' riproposta?") ) {
								rv.inhibitCurrentVideo();
							}
						
						} else
							repeat = false;

					} else {
						frame.setVisible(false);
						new FinalWindow(rv, us);
						repeat = false;
					}

				} while (repeat);
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
				
				
			frame.setTitle("RVC - Controllo Sessione"); // titolo della finestra
			frame.setLocationRelativeTo(null); //posiziona la finestra al centro
		
		
	}
	
	
	// restituisce true se la stringa input e' un numero, false altrimenti
	private boolean isCommandInteger(String input) {
		try {
			Integer.parseInt(input);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
