import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.JSpinner;

public class StartWindow {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	public static String firstFile = "no file selected";
	public static String firstFilePath = "";
	JSpinner spinner = new JSpinner();
	

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartWindow window = new StartWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});	
	}

	
	public StartWindow() {
		initialize();
	}

	
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 544, 495);
		// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblIdSessione = new JLabel("ID sessione:");
		lblIdSessione.setFont(new Font("Dialog", Font.BOLD, 16));
		lblIdSessione.setBounds(72, 91, 120, 29);
		frame.getContentPane().add(lblIdSessione);

		JLabel lblIdUtente = new JLabel("ID utente:");
		lblIdUtente.setFont(new Font("Dialog", Font.BOLD, 16));
		lblIdUtente.setBounds(72, 142, 135, 15);
		frame.getContentPane().add(lblIdUtente);

		JLabel lblSesso = new JLabel("sesso:");
		lblSesso.setFont(new Font("Dialog", Font.BOLD, 16));
		lblSesso.setBounds(72, 186, 70, 15);
		frame.getContentPane().add(lblSesso);

		JLabel lblMano = new JLabel("mano:");
		lblMano.setFont(new Font("Dialog", Font.BOLD, 16));
		lblMano.setBounds(72, 227, 70, 15);
		frame.getContentPane().add(lblMano);

		JLabel lblEt = new JLabel("età:");
		lblEt.setFont(new Font("Dialog", Font.BOLD, 16));
		lblEt.setBounds(72, 272, 70, 15);
		frame.getContentPane().add(lblEt);

		textField = new JTextField();
		textField.setFont(new Font("Dialog", Font.PLAIN, 16));
		textField.setBounds(224, 96, 220, 19);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setFont(new Font("Dialog", Font.PLAIN, 16));
		textField_1.setBounds(225, 140, 219, 19);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);

		JRadioButton rdbtnMaschio = new JRadioButton("maschio",true);
		buttonGroup.add(rdbtnMaschio);
		rdbtnMaschio.setFont(new Font("Dialog", Font.BOLD, 16));
		rdbtnMaschio.setBounds(224, 182, 114, 23);
		frame.getContentPane().add(rdbtnMaschio);

		JRadioButton rdbtnFemmina = new JRadioButton("femmina");
		buttonGroup.add(rdbtnFemmina);
		rdbtnFemmina.setFont(new Font("Dialog", Font.BOLD, 16));
		rdbtnFemmina.setBounds(342, 182, 102, 23);
		frame.getContentPane().add(rdbtnFemmina);

		JRadioButton rdbtnDestra = new JRadioButton("destra",true);
		buttonGroup_1.add(rdbtnDestra);
		rdbtnDestra.setFont(new Font("Dialog", Font.BOLD, 16));
		rdbtnDestra.setBounds(224, 223, 114, 23);
		frame.getContentPane().add(rdbtnDestra);

		JRadioButton rdbtnSinistra = new JRadioButton("sinistra");
		buttonGroup_1.add(rdbtnSinistra);
		rdbtnSinistra.setFont(new Font("Dialog", Font.BOLD, 16));
		rdbtnSinistra.setBounds(342, 223, 102, 23);
		frame.getContentPane().add(rdbtnSinistra);

		textField_2 = new JTextField();
		textField_2.setFont(new Font("Dialog", Font.PLAIN, 16));
		textField_2.setBounds(224, 270, 89, 19);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);

		JLabel lblInserisciIDati = new JLabel("inserisci i dati preliminari per la sessione");
		lblInserisciIDati.setFont(new Font("Dialog", Font.BOLD, 18));
		lblInserisciIDati.setBounds(58, 43, 434, 29);
		frame.getContentPane().add(lblInserisciIDati);

		JButton btnTermina = new JButton("esci");
		btnTermina.setFont(new Font("Dialog", Font.BOLD, 16));
		btnTermina.setBounds(277, 433, 117, 25);
		frame.getContentPane().add(btnTermina);

		JButton btnAvanti = new JButton("avanti");
		btnAvanti.setFont(new Font("Dialog", Font.BOLD, 16));
		btnAvanti.setBounds(406, 433, 117, 25);
		frame.getContentPane().add(btnAvanti);
		
		JLabel label = new JLabel("numero video sessione:");
		label.setFont(new Font("Dialog", Font.BOLD, 16));
		label.setBounds(72, 340, 228, 15);
		frame.getContentPane().add(label);
		
		spinner.setFont(new Font("Dialog", Font.BOLD, 16));
		spinner.setBounds(403, 337, 41, 20);
		frame.getContentPane().add(spinner);
		
		JLabel lblCartellaVideo = new JLabel("cartella video:");
		lblCartellaVideo.setFont(new Font("Dialog", Font.BOLD, 16));
		lblCartellaVideo.setBounds(72, 381, 186, 15);
		frame.getContentPane().add(lblCartellaVideo);
		
		JLabel label_2 = new JLabel("no file selected");
		label_2.setFont(new Font("Dialog", Font.ITALIC, 12));
		label_2.setBounds(224, 382, 170, 15);
		frame.getContentPane().add(label_2);
		
		JButton button = new JButton("seleziona");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String[] mode = new String[1];
				mode[0] = "d";
				ImportFileWindow.main(mode);
				label_2.setText(firstFile);
			}
		});
		button.setFont(new Font("Dialog", Font.BOLD, 16));
		button.setBounds(401, 376, 117, 25);
		frame.getContentPane().add(button);

		// codice mio
		// --------------------------------------------------------------------------------------------------------

		
		// serve per far apparire il dialogo di conferma anche quando si clicca sulla x della finestra
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		WindowListener exitListener = new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				ExitWindow.run();
			}
		};
		frame.addWindowListener(exitListener);

		
		// apre finestra di conferma uscite se si clicca "esci"
		btnTermina.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ExitWindow.run();
			}
		});

		
		// se si clicca "avanti" controlla che i parametri siano inseriti e chiama la
		// prossima finestra
		btnAvanti.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				String idSession = textField.getText();
				String idUser = textField_1.getText();
				
				if ( !areFormsComplete() ) {
					WarningWindow.run();
					return;
				}

				
				String futureFileName = idUser + "-" + idSession + ".txt";
				if ( new File(futureFileName).exists() ) {
					WarningWindow.run("il file " + futureFileName + " esiste gia', modifica i parametri prima di proseguire.");
					return;
				}
				
				
				UserSession sessioneUtente = new UserSession(idSession, idUser, rdbtnMaschio.isSelected(), rdbtnDestra.isSelected(),
				Integer.parseInt(textField_2.getText()));
				frame.setVisible(false);
				
				
				// controllo numero video inseriti
				boolean next = true;
				int numeroScelto = (Integer) spinner.getValue();
				int numberOfFileInDirectory = new File(firstFilePath).list().length;
				
				if ( numeroScelto > numberOfFileInDirectory )
					next = ExitWindow.run("Il numero di video inserito per la sessione e' maggiore del numero di video " + '\n' +
							"presenti nella cartella " + firstFile + ". Verranno caricati solamente " + numberOfFileInDirectory +
									  " video." + '\n' + "Vuoi proseguire?");
				
				else if ( numeroScelto < numberOfFileInDirectory )
					next = ExitWindow.run("Il numero di video inserito per la sessione e' minore del numero di video " + '\n' +
							  "presenti nella cartella " + firstFile + ". Potrebbero essere caricati " + numberOfFileInDirectory +
							  " video." + '\n' + "Vuoi proseguire caricardo soltanto " + numeroScelto + " video?");
	
				
				if (!next) return;
				
				
				try {
					RandomVideo rv = new RandomVideo(numeroScelto,firstFilePath);
					rv.prepareNextVideo(false,0);
					new SessionWindow(rv, sessioneUtente);
					frame.setVisible(false);
					
				} catch (FileNotFoundException e) {}
				

			}
		});

		frame.setTitle("RVC - Configurazione Iniziale"); // titolo della finestra
		frame.setLocationRelativeTo(null); //posiziona la finestra al centro

	} // class

	
	// controlla che tutti i parametri siano inseriti
	private boolean areFormsComplete() {

		return !textField.getText().isEmpty() && !textField_1.getText().isEmpty() && !textField_2.getText().isEmpty()
				&& areTheStringOneInteger(textField_2.getText()) && (((Integer) spinner.getValue())>0 && !firstFilePath.equals("")) ;
	}

	
	// controlla che la data inserita sia un numero
	private boolean areTheStringOneInteger(String input) {

		try {
			Integer.parseInt(input);
		} catch (java.lang.NumberFormatException notInt) {return false;}

		return true;
	}
} // class
