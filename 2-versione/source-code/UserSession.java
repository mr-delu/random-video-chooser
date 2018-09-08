import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;

public class UserSession {
	
	
	private final String idS; // identificativo univoco della sessione
	private final String idU; // identificativo univoco dell'utente
	private final boolean sessoMaschio; // sesso dell'utente
	private final boolean manoDestra; // mano predominante dell'utente
	private final int eta; // eta' dell'utente
	public String note; // note per la sessione
	private ArrayList<String> video; // nome dei video riprodotti durante la sessione
	private ArrayList<Long> videoTime; // nome dei video riprodotti durante la sessione
	private long timeSession;
	
	
	// costruttore
	public UserSession(String identS, String identU, boolean sex, boolean man, int age) {
		idS = identS;
		idU = identU;
		sessoMaschio = sex;
		manoDestra = man;
		eta = age;
		note = "";
	}
	
	
	// completa i campi di this acquisendo i dati dal RandomVideo in input
	public void complete(RandomVideo rv, long timeSession) {
		this.timeSession = timeSession;
		this.video = rv.videoPlayed;
		this.videoTime = rv.timeToGuess;

	}
	
	
	// rappresentazione in formato stringa della sessione, questa verra' salvata nel file di testo finale
	public String toString() {
		
		String uno = "*************************  ID  *************************" + System.lineSeparator() + header(2,"CAMPO","VALORE","")
						+ "ID Sessione:" + '\t' + this.idS + System.lineSeparator() + "ID utente:" + '\t' + this.idU + System.lineSeparator();
		
		String due = "*************************  DATI UTENTE  *************************" + System.lineSeparator() + header(2,"CAMPO","VALORE","")
						+ "Sesso:" + '\t' + getSesso() + System.lineSeparator() + "Mano:" + '\t' + getMano() + System.lineSeparator() + "Eta':"
						+ '\t' + this.eta + System.lineSeparator();
		
		String tre = "*************************  SEQUENZA DI VISUALIZZAZIONE  *************************" + System.lineSeparator()
						+ "NOME" + "\t" + "TEMPO IMPIEGATO [secondi]" + System.lineSeparator();
		
		for (int i=0; i<this.video.size(); i++)
			tre += this.video.get(i) + '\t' + (this.videoTime.get(i) / 1000) + System.lineSeparator();
		
		tre += "\n" + "TOTALE" + "\t" + (this.timeSession / 1000);
		
		String quattro = "*************************  DATA E NOTE  *************************" + System.lineSeparator() + header(2,"CAMPO","VALORE","")
		+ "DATA: " + dateAndTime() + System.lineSeparator() + "NOTE: " + this.note;
		
		return uno + System.lineSeparator() + due + System.lineSeparator() + tre + System.lineSeparator() + quattro + System.lineSeparator();
	}
	
	
	// restituisce la stringa corrispondente al sesso dell'utente
	private String getSesso() {
		return this.sessoMaschio ? "Maschio" : "Femmina";
	}
	
	
	// restituisce la stringa corrispondente alla mano predominante dell'utente
	private String getMano() {
		return this.manoDestra ? "Destra" : "Sinistra";
	}
	
	
	// salva la sessione in un file di testo
	public void save() throws FileNotFoundException, UnsupportedEncodingException {
		PrintWriter writer = new PrintWriter(this.idU + "-" + this.idS + ".txt");
		writer.println(this);
		writer.close();
	}
	
	
	// crea l'intestazione della tabella, ovvero la descrizione dei campi che seguiranno
	private String header(int num, String arg1, String arg2, String arg3) {
		String result = "----------------------------------------------------" + System.lineSeparator();
		result += arg1 + '\t' + "|" + '\t' + arg2;
		result += (num == 3) ? ('\t' + "|" + '\t' + arg3 + System.lineSeparator()) : (System.lineSeparator());
		result += "----------------------------------------------------";
		
		return result + System.lineSeparator();
	}
	
	
	private String dateAndTime() {
		String result = Calendar.getInstance().get(Calendar.DAY_OF_MONTH) + "/" + Calendar.getInstance().get(Calendar.MONTH) +
						"/" + Calendar.getInstance().get(Calendar.YEAR) + " alle ";
		result += Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + ":" + getMinute(Calendar.getInstance().get(Calendar.MINUTE));
		
		return result;
	}
	
	
	private String getMinute(int minuteInIntFormat) {
		return (minuteInIntFormat<10) ? "0" + minuteInIntFormat : "" + minuteInIntFormat;
	}
	
} // class
