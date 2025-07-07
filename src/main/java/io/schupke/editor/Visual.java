
package io.schupke.editor;

import java.awt.Color;
import java.awt.Font;

public class Visual {
	
	private static Font monospacedFont = new Font("Monospaced", Font.PLAIN, 12);
	private static Font normalFont = new Font("Serif", Font.PLAIN, 12);
	private static Color searchHighlightColor = new Color(0, 0, 0);
	
	public static Font getNormalFont() {
		return normalFont;
	}

	public static Font getMonospacedFont() {
		return monospacedFont;
	}
	
	public static Color getSearchHighlightColor() {
		return searchHighlightColor;
	}
	
}
