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

package eu.janschupke.editor;

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
