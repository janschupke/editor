
package io.schupke.editor.gui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class HRuler extends JPanel {
	private static final long serialVersionUID = 1L;
	JLabel counter;
	
	public HRuler(EditPane editPane) {
		counter = new JLabel("");

		setLayout(new BorderLayout());
		setBackground(new Color(200, 200, 200));
		
		add(counter, BorderLayout.CENTER);
	}
}
