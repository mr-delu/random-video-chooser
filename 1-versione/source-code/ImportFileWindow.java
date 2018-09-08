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

		if (result == JFileChooser.APPROVE_OPTION && mode == "d" ) {
			File selectedFile = jFileChooser.getSelectedFile();
			ConfigFileWindow.firstFile = selectedFile.getName();
			ConfigFileWindow.firstFilePath = selectedFile.getAbsolutePath();
		
		} else if (result == JFileChooser.APPROVE_OPTION && mode == "f" ) {
			File selectedFile = jFileChooser.getSelectedFile();
			ConfigFileWindow.secondFile = selectedFile.getName();
			ConfigFileWindow.secondFilePath = selectedFile.getAbsolutePath();
		}
	}

	public static void main(String[] args) {
		new ImportFileWindow(args[0]);
	}

}
