/*
 * This file is part of Editor, created by Jan Schupke in 2011.
 * 
 * Editor is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Editor is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with Editor. If not, see <http://www.gnu.org/licenses/>.
 */

package eu.janschupke.editor.filehandling;

import java.io.File;
import java.io.FileWriter;

import eu.janschupke.editor.actions.AppActions;

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
