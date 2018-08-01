import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileNotFoundException;
import javax.swing.JButton;
import javax.swing.JSpinner;

public class ConfigFileWindow {

	public static String firstFile = "no file selected";
	public static String secondFile = "no file selected";
	public static String firstFilePath = "";
	public static String secondFilePath = "";	
	private JFrame frame;
	
	
	public static void run(UserSession sessione, boolean training) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConfigFileWindow window = new ConfigFileWindow(sessione,training);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public ConfigFileWindow(UserSession sessione,boolean training) {
		initialize(sessione,training);
	}

	
	private void initialize(UserSession sessione, boolean training) {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 544, 435);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblInserisciIDati = new JLabel("inserisci i dati di configurazione per la sessione");
		lblInserisciIDati.setFont(new Font("Dialog", Font.BOLD, 18));
		lblInserisciIDati.setBounds(23, 44, 489, 15);
		frame.getContentPane().add(lblInserisciIDati);
		
		JLabel lblNumeroVideoSessione = new JLabel("numero video sessione:");
		lblNumeroVideoSessione.setFont(new Font("Dialog", Font.BOLD, 16));
		lblNumeroVideoSessione.setBounds(33, 108, 228, 15);
		frame.getContentPane().add(lblNumeroVideoSessione);
		
		JLabel lblCartellaSorgenteVideo = new JLabel("cartella sorgente video:");
		lblCartellaSorgenteVideo.setFont(new Font("Dialog", Font.BOLD, 16));
		lblCartellaSorgenteVideo.setBounds(35, 230, 216, 15);
		frame.getContentPane().add(lblCartellaSorgenteVideo);
		
		JLabel lblFileSorgenteMosse = new JLabel("file sorgente mosse:");
		lblFileSorgenteMosse.setFont(new Font("Dialog", Font.BOLD, 16));
		lblFileSorgenteMosse.setBounds(35, 275, 210, 21);
		frame.getContentPane().add(lblFileSorgenteMosse);
		
		JButton btnInizia = new JButton("inizia");
		btnInizia.setFont(new Font("Dialog", Font.BOLD, 16));
		btnInizia.setBounds(407, 365, 117, 25);
		frame.getContentPane().add(btnInizia);
		
		JButton btnEsci = new JButton("esci");
		btnEsci.setFont(new Font("Dialog", Font.BOLD, 16));
		btnEsci.setBounds(278, 365, 117, 25);
		frame.getContentPane().add(btnEsci);
		
		JSpinner spinner = new JSpinner();
		spinner.setFont(new Font("Dialog", Font.BOLD, 16));
		spinner.setBounds(300, 107, 41, 20);
		frame.getContentPane().add(spinner);
		
		JLabel lblVideopath = new JLabel(firstFile);
		lblVideopath.setFont(new Font("Dialog", Font.ITALIC, 12));
		lblVideopath.setBounds(278, 231, 117, 15);
		frame.getContentPane().add(lblVideopath);
		
		JLabel lblSolutionpath = new JLabel(secondFile);
		lblSolutionpath.setFont(new Font("Dialog", Font.ITALIC, 12));
		lblSolutionpath.setBounds(278, 279, 117, 15);
		frame.getContentPane().add(lblSolutionpath);
		
		JButton btnCarica = new JButton("seleziona");
		btnCarica.setFont(new Font("Dialog", Font.BOLD, 16));
		btnCarica.setBounds(407, 225, 117, 25);
		frame.getContentPane().add(btnCarica);
		
		JButton btnCarica_1 = new JButton("seleziona");
		btnCarica_1.setFont(new Font("Dialog", Font.BOLD, 16));
		btnCarica_1.setBounds(407, 273, 117, 25);
		frame.getContentPane().add(btnCarica_1);
		
		JLabel lblNumeroErroriMassimo = new JLabel("numero errori massimo:");
		lblNumeroErroriMassimo.setFont(new Font("Dialog", Font.BOLD, 16));
		lblNumeroErroriMassimo.setBounds(35, 157, 216, 15);
		frame.getContentPane().add(lblNumeroErroriMassimo);
		
		JSpinner spinner_1 = new JSpinner();
		spinner_1.setFont(new Font("Dialog", Font.BOLD, 16));
		spinner_1.setBounds(300, 156, 41, 20);
		frame.getContentPane().add(spinner_1);
		
		
		
		// codice mio --------------------------------------------------------------------------------------------------------
		
		
		
		if (training) {
			spinner.setValue(new Integer(24)); // setta il valore predefinito dei video per sessione
			spinner_1.setValue(new Integer(5)); // setta il valore predefinito degli errori massimi
		} else {
			spinner.setValue(new Integer(6)); // setta il valore predefinito dei video per sessione
			spinner_1.setValue(new Integer(1)); // setta il valore predefinito degli errori massimi
		}
		
		
		// chiama il caricatore di file in modalità "directory"
		btnCarica.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String[] mode = new String[1];
				mode[0] = "d";
				ImportFileWindow.main(mode);
				lblVideopath.setText(firstFile);
			}
		});
		
		
		// chiama il caricatore di file in modalità "file"
		btnCarica_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String[] mode = new String[1];
				mode[0] = "f";
				ImportFileWindow.main(mode);
				lblSolutionpath.setText(secondFile);
			}
		});
		
		
		// chiede conferma dell'uscita se si clicca "esci"
		btnEsci.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ExitWindow.run();
			}
		});
		
		
		// cosa succede se si clicca su "avanti"
		btnInizia.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if ( (Integer) spinner.getValue() <= 0 || firstFilePath.equals("") || secondFilePath.equals("") ) { // controllo correttezza parametri
					WarningWindow.run();
					return;
				}
				
				
				// controllo numero video inseriti
				boolean next = true;
				int numeroScelto = (Integer) spinner.getValue();
				int erroriScelti = (Integer) spinner_1.getValue();
				int numberOfFileInDirectory = new File(firstFilePath).list().length;
				
				if ( numeroScelto > numberOfFileInDirectory )
					next = ExitWindow.run("Il numero di video inserito per la sessione e' maggiore del numero di video " + '\n' +
							"presenti nella cartella " + firstFile + ". Verranno caricati solamente " + numberOfFileInDirectory +
									  " video." + '\n' + "Vuoi proseguire?");
				
				else if ( numeroScelto < numberOfFileInDirectory )
					next = ExitWindow.run("Il numero di video inserito per la sessione e' minore del numero di video " + '\n' +
							  "presenti nella cartella " + firstFile + ". Potrebbero essere caricati " + numberOfFileInDirectory +
							  " video." + '\n' + "Vuoi proseguire caricardo soltanto " + numeroScelto + " video?");
	
				
				if ( erroriScelti < 1) {
					WarningWindow.run("Il numero di errori massimo scelto per i video e' troppo basso");
					next = false;
				}
				
				if (!next) return;
				// fine controllo parametri inseriti
				
				
				
				
				try {
					RandomVideo rv = new RandomVideo(numeroScelto,firstFilePath,secondFilePath);
					
					File isOk = rv.isMoveFileok();
					if (isOk != null) {
						WarningWindow.run("I parametri immessi non sono corretti." + '\n' + "Non riesco a trovare la mossa per "
								+ "il video: " + isOk.getName());
						return;
					}
					
					rv.prepareNextVideo();
					new GuessMoveWindow(rv, erroriScelti,sessione);
					frame.setVisible(false);
					
				} catch (FileNotFoundException e1) {}
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
		
		
		frame.setTitle("RVC - Configurazione File Sorgenti"); // titolo della finestra
		frame.setLocationRelativeTo(null); //posiziona la finestra al centro
		
	}
} // class
