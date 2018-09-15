public class PairVideoAndMove {
	
	
	private String videoName;
	private String moveInsert;
	private boolean correct;

	public PairVideoAndMove(String arg1, String arg2, boolean arg3) {
		this.videoName = arg1;
		this.moveInsert = arg2;
		this.correct = arg3;
	}
	
	public String toString() {
		return "" + videoName + '\t' + moveInsert + '\t' + ( correct ? "CORRETTO" : "SBAGLIATO" );
	}
}