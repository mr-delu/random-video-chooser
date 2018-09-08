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

public class StartWindow {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	private final ButtonGroup buttonGroup_2 = new ButtonGroup();

	
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
		frame.setBounds(100, 100, 544, 435);
		// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblIdSessione = new JLabel("ID sessione:");
		lblIdSessione.setFont(new Font("Dialog", Font.BOLD, 16));
		lblIdSessione.setBounds(72, 110, 120, 29);
		frame.getContentPane().add(lblIdSessione);

		JLabel lblIdUtente = new JLabel("ID utente:");
		lblIdUtente.setFont(new Font("Dialog", Font.BOLD, 16));
		lblIdUtente.setBounds(72, 161, 135, 15);
		frame.getContentPane().add(lblIdUtente);

		JLabel lblSesso = new JLabel("sesso:");
		lblSesso.setFont(new Font("Dialog", Font.BOLD, 16));
		lblSesso.setBounds(72, 242, 70, 15);
		frame.getContentPane().add(lblSesso);

		JLabel lblMano = new JLabel("mano:");
		lblMano.setFont(new Font("Dialog", Font.BOLD, 16));
		lblMano.setBounds(72, 283, 70, 15);
		frame.getContentPane().add(lblMano);

		JLabel lblEt = new JLabel("età:");
		lblEt.setFont(new Font("Dialog", Font.BOLD, 16));
		lblEt.setBounds(72, 324, 70, 15);
		frame.getContentPane().add(lblEt);

		textField = new JTextField();
		textField.setFont(new Font("Dialog", Font.PLAIN, 16));
		textField.setBounds(224, 116, 220, 19);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setFont(new Font("Dialog", Font.PLAIN, 16));
		textField_1.setBounds(225, 159, 219, 19);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);

		JRadioButton rdbtnMaschio = new JRadioButton("maschio",true);
		buttonGroup.add(rdbtnMaschio);
		rdbtnMaschio.setFont(new Font("Dialog", Font.BOLD, 16));
		rdbtnMaschio.setBounds(224, 238, 114, 23);
		frame.getContentPane().add(rdbtnMaschio);

		JRadioButton rdbtnFemmina = new JRadioButton("femmina");
		buttonGroup.add(rdbtnFemmina);
		rdbtnFemmina.setFont(new Font("Dialog", Font.BOLD, 16));
		rdbtnFemmina.setBounds(342, 238, 102, 23);
		frame.getContentPane().add(rdbtnFemmina);

		JRadioButton rdbtnDestra = new JRadioButton("destra",true);
		buttonGroup_1.add(rdbtnDestra);
		rdbtnDestra.setFont(new Font("Dialog", Font.BOLD, 16));
		rdbtnDestra.setBounds(224, 279, 114, 23);
		frame.getContentPane().add(rdbtnDestra);

		JRadioButton rdbtnSinistra = new JRadioButton("sinistra");
		buttonGroup_1.add(rdbtnSinistra);
		rdbtnSinistra.setFont(new Font("Dialog", Font.BOLD, 16));
		rdbtnSinistra.setBounds(342, 279, 102, 23);
		frame.getContentPane().add(rdbtnSinistra);

		textField_2 = new JTextField();
		textField_2.setFont(new Font("Dialog", Font.PLAIN, 16));
		textField_2.setBounds(224, 322, 89, 19);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);

		JLabel lblInserisciIDati = new JLabel("inserisci i dati preliminari per la sessione");
		lblInserisciIDati.setFont(new Font("Dialog", Font.BOLD, 18));
		lblInserisciIDati.setBounds(58, 43, 434, 29);
		frame.getContentPane().add(lblInserisciIDati);

		JButton btnTermina = new JButton("esci");
		btnTermina.setFont(new Font("Dialog", Font.BOLD, 16));
		btnTermina.setBounds(276, 368, 117, 25);
		frame.getContentPane().add(btnTermina);

		JButton btnAvanti = new JButton("avanti");
		btnAvanti.setFont(new Font("Dialog", Font.BOLD, 16));
		btnAvanti.setBounds(407, 368, 117, 25);
		frame.getContentPane().add(btnAvanti);
		
		JLabel lblTipoSessione = new JLabel("tipo sessione:");
		lblTipoSessione.setFont(new Font("Dialog", Font.BOLD, 16));
		lblTipoSessione.setBounds(72, 200, 135, 25);
		frame.getContentPane().add(lblTipoSessione);
		
		JRadioButton rdbtnTraining = new JRadioButton("training",true);
		buttonGroup_2.add(rdbtnTraining);
		rdbtnTraining.setFont(new Font("Dialog", Font.BOLD, 16));
		rdbtnTraining.setBounds(224, 201, 114, 23);
		frame.getContentPane().add(rdbtnTraining);
		
		JRadioButton rdbtnTest = new JRadioButton("test");
		buttonGroup_2.add(rdbtnTest);
		rdbtnTest.setFont(new Font("Dialog", Font.BOLD, 16));
		rdbtnTest.setBounds(342, 201, 102, 23);
		frame.getContentPane().add(rdbtnTest);

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
				ConfigFileWindow.run(sessioneUtente,rdbtnTraining.isSelected());

			}
		});

		frame.setTitle("RVC - Configurazione Sessione"); // titolo della finestra
		frame.setLocationRelativeTo(null); //posiziona la finestra al centro

	} // class

	
	// controlla che tutti i parametri siano inseriti
	private boolean areFormsComplete() {

		return !textField.getText().isEmpty() && !textField_1.getText().isEmpty() && !textField_2.getText().isEmpty()
				&& areTheStringOneInteger(textField_2.getText());
	}

	
	// controlla che la data inserita sia un numero
	private boolean areTheStringOneInteger(String input) {

		try {
			Integer.parseInt(input);
		} catch (java.lang.NumberFormatException notInt) {return false;}

		return true;
	}
} // class
