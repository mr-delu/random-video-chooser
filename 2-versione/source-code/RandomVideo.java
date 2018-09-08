import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class RandomVideo {

	public File[] video; // video che verranno mostrati
	public ArrayList<String> videoPlayed;
	private ArrayList<Integer> possibleRandomNumbers; // indici possibili per scegliere un video da mostrare da this.video
	private int videoToPlay; // video "caricato" pronto per essere mostrato
	public ArrayList<Long> timeToGuess;

	
	// costruttore: alloca spazio per num video (se num <= # file contenuti in rootVideo) o per # file in rootVideo (se num > # file rootVideo) 
	public RandomVideo(int num, String rootVideo) throws FileNotFoundException {

		int numberOfFileInDirectory = new File(rootVideo).list().length;
		num = (num > numberOfFileInDirectory ? numberOfFileInDirectory : num);

		this.video = new File[num];
		this.videoPlayed = new ArrayList<String>();
		this.timeToGuess = new ArrayList<Long>();
		this.possibleRandomNumbers = new ArrayList<Integer>(num);

		for (int i = 0; i < num; i++)
			possibleRandomNumbers.add(i);

		this.addVideoFromDirectory(rootVideo);
	}

	
	// salva internamente i riferimenti ai video presenti in directory, vengono caricati num video (se num <= #frd) o #frd (se num>#frd)
	public void addVideoFromDirectory(String directory) {

		File[] source = new File(directory).listFiles();
		ArrayList<Integer> indexForFileToChose= new ArrayList<Integer>(source.length);
		Random rn = new Random();
		
		for (int i=0;i<source.length;i++)
			indexForFileToChose.add(i);
			
		for (int i = 0; i < this.video.length; i++) {
			int randomNumber = rn.nextInt(indexForFileToChose.size()); 
			this.video[i] = source[indexForFileToChose.get(randomNumber)];
			indexForFileToChose.remove(randomNumber);
		}
	}
	
	
	// restituisce il numero di video che devono ancora essere indovinati
	public int toGuess() {
		return this.possibleRandomNumbers.size();
	}

	
	// restituisce il nome del video "caricato"
	public String lastPlayed() {
		return this.video[videoToPlay].getName();
	}

	
	// riproduce il video "caricato"
	public void openVideo() throws IOException {
		Desktop.getDesktop().open(this.video[videoToPlay]);
	}

	
	// "carica" un nuovo video da riprodurre in seguito
	@SuppressWarnings("deprecation")
	public void prepareNextVideo(boolean guess, long timeForGuess) {
		
		
		if (this.possibleRandomNumbers.isEmpty())
			return;
		
		if (!this.videoPlayed.isEmpty())
			this.timeToGuess.add( new Long(timeForGuess) );
		
		if (guess)
			possibleRandomNumbers.remove((Integer)this.videoToPlay);
		
		
		System.out.print("Video caricati: ");
		for(int i=0; i<this.video.length;i++)
			System.out.print(this.video[i].getName() + ", ");
		
		System.out.print("Possibili scelte di indici: ");
		for(int i=0; i<this.possibleRandomNumbers.size();i++)
			System.out.print(this.possibleRandomNumbers.get(i) + ", ");
		
		
		if (!this.possibleRandomNumbers.isEmpty()) { 
		
			Random rn = new Random();
			int randomNumber = rn.nextInt(possibleRandomNumbers.size());
			int randomIndex = possibleRandomNumbers.get(randomNumber);
			this.videoToPlay = randomIndex;
			
			this.videoPlayed.add(this.video[this.videoToPlay].getName());
			
			System.out.println("\n" + "scelto l'indice: " + randomIndex + "\n");
		}
	}

	
	// restituisce true se ci sono ancora video da indovinare, false altrimenti
	public boolean hasNextVideo() {
		return this.possibleRandomNumbers.size() > 0;
	}
	

} // class
