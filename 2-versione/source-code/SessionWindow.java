import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

import javax.swing.JProgressBar;

public class SessionWindow {

	private JFrame frame;
	private RandomVideo rv;
	private UserSession us;
	private long startTimeSession;
	private long startTimeVideo;


	public SessionWindow(RandomVideo rand, UserSession sessione) {
		this.rv = rand;
		this.us = sessione;
		this.startTimeSession = 0;
		initialize();
		this.frame.setVisible(true);
	}


	
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 544, 190);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
				
		JLabel lblDaIndovinare = new JLabel("da indovinare:");
		lblDaIndovinare.setFont(new Font("Dialog", Font.BOLD, 16));
		lblDaIndovinare.setBounds(36, 25, 129, 15);
		frame.getContentPane().add(lblDaIndovinare);
		
		JLabel lblDaindovinare = new JLabel("" + rv.toGuess());
		lblDaindovinare.setFont(new Font("Dialog", Font.BOLD, 16));
		lblDaindovinare.setBounds(235, 25, 249, 15);
		frame.getContentPane().add(lblDaindovinare);
		
		JButton btnPlay = new JButton("play");
		btnPlay.setFont(new Font("Dialog", Font.BOLD, 16));
		btnPlay.setBounds(134, 128, 117, 25);
		frame.getContentPane().add(btnPlay);
		
		JButton btnAvati = new JButton("no");
		btnAvati.setFont(new Font("Dialog", Font.BOLD, 16));
		btnAvati.setBounds(413, 128, 117, 25);
		frame.getContentPane().add(btnAvati);
		
		
		// codice mio --------------------------------------------------------------------------------------------------------
		
		
		JButton btnSalvaEEsci = new JButton("termina");
		btnSalvaEEsci.setFont(new Font("Dialog", Font.BOLD, 16));
		btnSalvaEEsci.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if ( ExitWindow.run("Sei sicuro di voler terminare la sessione?") ) {
					frame.setVisible(false);
					new FinalWindow(rv,us,0);
				}
			}
		});
		btnSalvaEEsci.setBounds(12, 128, 117, 25);
		frame.getContentPane().add(btnSalvaEEsci);
		
		JProgressBar progressBar = new JProgressBar(0,rv.toGuess());
		progressBar.setFont(new Font("Dialog", Font.BOLD, 16));
		progressBar.setBounds(235, 64, 268, 25);
		frame.getContentPane().add(progressBar);
		progressBar.setValue(0);
		
		JLabel lblProgresso = new JLabel("progresso:");
		lblProgresso.setFont(new Font("Dialog", Font.BOLD, 16));
		lblProgresso.setBounds(36, 66, 129, 21);
		frame.getContentPane().add(lblProgresso);
		progressBar.setStringPainted(true);
		
		JButton btnS = new JButton("sì");
		btnS.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				rv.prepareNextVideo(true, (System.currentTimeMillis() - startTimeVideo) );
				startTimeVideo = System.currentTimeMillis();;

				if ( rv.hasNextVideo() ) {
					lblDaindovinare.setText("" + rv.toGuess());
					progressBar.setValue(progressBar.getValue()+1);
					
					try {
						rv.openVideo();
					} catch (IOException ew) {}
					

				} else {
					frame.setVisible(false);
					new FinalWindow(rv, us, System.currentTimeMillis() - startTimeSession );
				}
				
				
			}
		});
		btnS.setFont(new Font("Dialog", Font.BOLD, 16));
		btnS.setBounds(292, 128, 117, 25);
		frame.getContentPane().add(btnS);
		
				
		// quando clicchi play fa partire il video "caricato nel RandomVideo"
		btnPlay.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if ( startTimeSession==0 ) {
					startTimeSession = System.currentTimeMillis();
					startTimeVideo = startTimeSession;
				}
				
				try {
					rv.openVideo();
				} catch (IOException e1) {
					ErrorWindow.run("Errore durante l'apertura del video.");
					System.exit(2);}

			}
		});
		
		
		// quando clicchi no
		btnAvati.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				rv.prepareNextVideo(false, System.currentTimeMillis() - startTimeVideo);
				startTimeVideo = System.currentTimeMillis();
				
				try {
					rv.openVideo();
				} catch (IOException e) {}
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
	
	
}
