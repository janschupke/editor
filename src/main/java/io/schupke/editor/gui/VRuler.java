
package io.schupke.editor.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class VRuler extends JPanel {
	private static final long serialVersionUID = 1L;
	JLabel counter;
	
	public VRuler(EditPane editPane) {
		
		counter = new JLabel("<html>");
		counter.setFont(new Font("Monospaced", Font.PLAIN, 12));

		// print line counter
		
		setLayout(new BorderLayout());
		setBackground(new Color(200, 200, 200));
		
		add(counter, BorderLayout.NORTH);
		
	}
	
}
