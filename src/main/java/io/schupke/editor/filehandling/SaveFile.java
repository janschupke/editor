
package io.schupke.editor.filehandling;

import java.io.File;
import java.io.FileWriter;

import io.schupke.editor.actions.AppActions;

public class SaveFile {
	
	AppActions appActions;
	
	// save a value from the textarea into a plain text file 
	public static void write(String text, File file) {
		
		try {
			
			String s = text;
			File f = new File(file.getPath());
			if(!f.exists()) f.createNewFile();
			FileWriter fw = new FileWriter(f);
			fw.write(s);
			fw.close();
			
		} catch(Exception fe) {
			fe.printStackTrace();
		}
		
	}
	
}
