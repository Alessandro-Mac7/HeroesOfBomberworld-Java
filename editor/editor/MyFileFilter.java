package editor;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class MyFileFilter extends FileFilter {

	@Override
	public boolean accept(File file) {
		 if (file.isDirectory()) return true;
		    String fname = file.getName().toLowerCase();
		    return fname.endsWith("txt");

	}

	@Override
	public String getDescription() {
		return "File di testo";
	}

}
