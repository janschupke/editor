
package io.schupke.editor.filehandling;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReadFile {
	
	// read a plain text file from the classpath (JAR) and return the text
	public static String readFromClasspath(String resourcePath) {
		StringBuilder content = new StringBuilder();
		try (BufferedReader dis = new BufferedReader(new InputStreamReader(
				ReadFile.class.getResourceAsStream(resourcePath)))) {
			String inputLine;
			while ((inputLine = dis.readLine()) != null) {
				content.append(inputLine).append(System.getProperty("line.separator"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return content.toString();
	}

	// read a plain text file and return the text
	public static String read(String path) {

		String content = "";
		File f = new File(path);
		if (f.exists()) {
			FileInputStream fis = null;
			BufferedInputStream bis = null;
			BufferedReader dis = null;
			
	    	String inputLine = "";

		    try {
		    	;
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
		} else {
			// Try to read from classpath (JAR)
			content = readFromClasspath(path.startsWith("/") ? path : "/" + path);
		}
		return content;
		
	}
	
}
