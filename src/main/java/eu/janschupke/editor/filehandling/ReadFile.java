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

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReadFile {
	
	// read a plain text file and return the text
	public static String read(String path) {

		String content = "";
		File f = new File(path);
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		BufferedReader dis = null;
		
    	String inputLine = "";

	    try {
	    	
	    	fis = new FileInputStream(f);
	    	bis = new BufferedInputStream(fis);
	    	dis = new BufferedReader(new InputStreamReader(bis));
	    	
	    	// read lines
	    	while ((inputLine = dis.readLine()) != null) {
	    		
	    		// this statement reads the line from the file and print it to
	    		// the console.
	    		content += inputLine + System.getProperty("line.separator");
	    		
	    	}

	    	// dispose all the resources after using them.
	    	fis.close();
	    	bis.close();
	    	dis.close();

	    } catch (FileNotFoundException e) {
	    	e.printStackTrace();
	    } catch (IOException e) {
	    	e.printStackTrace();
	    }
	    
		return content;
		
	}
	
}
