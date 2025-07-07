
package io.schupke.editor.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import io.schupke.editor.Constants;
import io.schupke.editor.Localisation;

public class Config {
	
	public Properties config;
	public File f;
	public FileOutputStream out;

	public final String LANGUAGE = "language";
	public final String FONT_SIZE = "font.size";
	public final String LINE_WRAPPING = "line.wrapping";
	public final String NAMING_STYLE = "naming.style";
	public final String MAXIMIZED = "maximized";
	
	String configFolder, fileName;
	
	public Config() {
		
		// load the file
		try {
			config = new Properties();
			
			configFolder = System.getProperty("user.home") +
					System.getProperty("file.separator") +
					Constants.MAIN_FOLDER +
					System.getProperty("file.separator") +
					Constants.APP_FOLDER;
			
			fileName = configFolder +
					System.getProperty("file.separator") +
					"editor.conf";

			// prevent non-existing folder
			f = new File(configFolder);
			
			if(!f.exists()) {
				f.mkdirs();
			}
			
			f = new File(fileName);
			
			if(f.exists()) {
				config.load(new FileInputStream(f));
				
			} else {
				out = new FileOutputStream(f);
				config.store(out, "");
				out.close();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	

	public void save(
			String language,
			Integer fontSize,
			boolean lineWrapping,
			boolean namingStyle,
			boolean maximized) {
		
		// save values
		config.setProperty(
				LANGUAGE, language);
		config.setProperty(
				FONT_SIZE, new Integer(fontSize).toString());
		config.setProperty(
				LINE_WRAPPING, new Boolean(lineWrapping).toString());
		config.setProperty(
				NAMING_STYLE, new Boolean(namingStyle).toString());
		config.setProperty(
				MAXIMIZED, new Boolean(maximized).toString());
		
		try {
			
			// save the config file
			out = new FileOutputStream(f);
			config.store(out, "");
			out.close();
			
		} catch (IOException e) {
			
		    // Print out the exception that occurred
			System.out.println(Localisation.write("debug.app.settings.saveFailed"));
			
		}
		
	}


}
