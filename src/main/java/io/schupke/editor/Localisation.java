
package io.schupke.editor;

import java.util.Locale;
import java.util.ResourceBundle;

// load property files with localized text strings
public class Localisation {
	
	private static ResourceBundle language;

	// load file
	public static void loadLang(String lang) {
		
		if("Czech".equals(lang)) {
			language = ResourceBundle.getBundle("Language", new Locale("cs", "CZ"));
		} else {
			language = ResourceBundle.getBundle("Language", new Locale("en", "GB"));
		}
		
	}
	
	// display required string
	public static String write(String key) {
		return language.getString(key);
	}

}
