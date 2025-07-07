
package io.schupke.editor.gui;

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
