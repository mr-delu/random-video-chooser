import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class ImportFileWindow {

	public ImportFileWindow(String mode) {
		JFileChooser jFileChooser = new JFileChooser();
		
		if (mode == "d") {
			jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			jFileChooser.setAcceptAllFileFilterUsed(false);
		}

		int result = jFileChooser.showOpenDialog(new JFrame());

		if ( result == JFileChooser.APPROVE_OPTION ) {
			File selectedFile = jFileChooser.getSelectedFile();
			StartWindow.firstFile = selectedFile.getName();
			StartWindow.firstFilePath = selectedFile.getAbsolutePath();
		}
		
	}

	public static void main(String[] args) {
		new ImportFileWindow(args[0]);
	}

}
