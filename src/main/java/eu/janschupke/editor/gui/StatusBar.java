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

package eu.janschupke.editor.gui;

import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class StatusBar extends JPanel {
	private static final long serialVersionUID = 1L;
	
	public JLabel fileName;
	public JLabel fileChars;
	public JLabel fileWords;
	public JLabel fileLines;
	
	public StatusBar() {
		
		fileName = new JLabel("");
		fileChars = new JLabel("");
		fileWords = new JLabel("");
		fileLines = new JLabel("");
		
		setLayout(new FlowLayout());
		add(fileName, null);
		add(fileChars, null);
		//add(fileWords, null);
		//add(fileLines, null);
		
	}
	
}
