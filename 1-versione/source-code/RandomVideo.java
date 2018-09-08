import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class RandomVideo {

	public File[] video; // video che verranno mostrati
	public int[] errors; // errori relativi ad ogni video mostrato
	private ArrayList<Integer> possibleRandomNumbers; // indici possibili per scegliere un video da mostrare da this.video
	private int videoToPlay; // video "caricato" pronto per essere mostrato
	private String[] correctMove; // mosse corrette per ogni video
	public ArrayList<PairVideoAndMove> log; // insieme di triple (video mostrato, mossa scelta, correttezza mossa)
	public ArrayList<Integer> inhibitedVideos; // lista dei video segnati come "non mostrare piu'"

	
	// costruttore: alloca spazio per num video (se num <= # file contenuti in rootVideo) o per # file in rootVideo (se num > # file rootVideo) 
	public RandomVideo(int num, String rootVideo, String answers) throws FileNotFoundException {

		int numberOfFileInDirectory = new File(rootVideo).list().length;
		num = (num > numberOfFileInDirectory ? numberOfFileInDirectory : num);

		this.video = new File[num];
		this.errors = new int[num];
		this.correctMove = new String[num];
		this.possibleRandomNumbers = new ArrayList<Integer>(num);

		for (int i = 0; i < num; i++) {
			errors[i] = 0;
			possibleRandomNumbers.add(i);
		}

		this.addVideoFromDirectory(rootVideo);
		this.loadCorrectMove(answers);
		this.log = new ArrayList<PairVideoAndMove>(num);
		this.inhibitedVideos = new ArrayList<Integer>();
	}

	
	// salva internamente i riferimenti ai video presenti in directory, vengono caricati num video (se num <= #frd) o #frd (se num>#frd)
	public void addVideoFromDirectory(String directory) {

		File[] source = new File(directory).listFiles();

		if (source.length == this.possibleRandomNumbers.size())
			this.video = source;

		else {
			Random rn = new Random();
			ArrayList<Integer> indexForFileToChose= new ArrayList<Integer>(source.length);
			for (int i=0;i<source.length;i++)
				indexForFileToChose.add(i);
			
			for (int i = 0; i < this.video.length; i++) {
				int randomNumber = rn.nextInt(indexForFileToChose.size()); 
				this.video[i] = source[indexForFileToChose.get(randomNumber)];
				indexForFileToChose.remove(randomNumber);
			}
		}
	}
	
	
	// salva internamente i riferimenti alle sequenze di mosse corrette per i singoli video precedentemente salvati in this
	public void loadCorrectMove(String answers) throws FileNotFoundException {

		File moves = new File(answers);
		Scanner sc = new Scanner(moves);

		while (sc.hasNextLine()) {
			String row = sc.nextLine();
			String[] fields = row.split("\\s+");

			for (int i = 0; i < video.length; i++) {
				if (video[i].getName().equals(fields[0]))
					correctMove[i] = fields[1];
			}
		}
		sc.close();
	}

	
	// controlla se la mossa "move" inserita e' corretta per il video "caricato"
	public boolean isTheMoveCorrect(String move) {
		return move.equals(correctMove[this.videoToPlay]);
	}
	
	
	// restituisce il numero di video che devono ancora essere indovinati
	public int toGuess() {
		return this.possibleRandomNumbers.size();
	}
	
	
	// controlla se la mossa "move" inserita e' corretta per il video "caricato" AND modifica adeguatamente le strutture dati di this 
	public boolean guess(String move) {

		boolean correct;

		if (isTheMoveCorrect(move)) {
			this.possibleRandomNumbers.remove((Integer) videoToPlay);
			correct = true;
		
		} else {
			errors[videoToPlay] += 1;
			correct = false;
		}

		this.log.add( new PairVideoAndMove(video[videoToPlay].getName(), move, correct) );
		return correct;
	}

	
	// restituisce il nome del video "caricato"
	public String lastPlayed() {
		return this.video[videoToPlay].getName();
	}

	
	// restituisce il numero di errori totali effettuati
	public int totalError() {
		int result = 0;
		for (int i = 0; i < this.errors.length; i++)
			result += this.errors[i];

		return result;
	}

	
	// riproduce il video "caricato"
	public void openVideo() throws IOException {
		Desktop.getDesktop().open(this.video[videoToPlay]);
	}

	
	// "carica" un nuovo video da riprodurre in seguito
	public void prepareNextVideo() {
		Random rn = new Random();
		int randomNumber = rn.nextInt(possibleRandomNumbers.size());
		int randomIndex = possibleRandomNumbers.get(randomNumber);
		this.videoToPlay = randomIndex;
	}

	
	// restituisce true se ci sono ancora video da indovinare, false altrimenti
	public boolean hasNextVideo() {
		return this.possibleRandomNumbers.size() > 0;
	}
	
	
	// restituisce null se tutti i video presenti hanno una mossa corretta associata, altrimenti restituisce il video senza di essa
	public File isMoveFileok() {
		for (int i = 0; i < this.correctMove.length; i++) {
			if (correctMove[i] == null)
				return this.video[i];
		}
		return null;
	}
	
	
	// restituisce il numero di errori associati al video "caricato"
	public int errorOnCurrentVideo() {
		return this.errors[this.videoToPlay];
	}
	
	
	// elimina dalla lista "video da mostrare" il video "caricato"
	public void inhibitCurrentVideo() {
		this.possibleRandomNumbers.remove((Integer) videoToPlay);
		this.inhibitedVideos.add((Integer) videoToPlay);
	}
	
	
	// restituisce la mossa corretta associata al video "caricato"
	public String getCurretMove() {
		return this.correctMove[this.videoToPlay];
	}

} // class
