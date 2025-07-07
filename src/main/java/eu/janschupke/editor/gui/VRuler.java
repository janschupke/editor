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
		/*for(int i = 1; i < editPane.editor.textArea.getLineCount(); i++) {
			counter.setText(counter.getText() + "1" + System.getProperty("line.separator"));
		}*/
		/*for(int i = 1; i < 100; i++) {
			counter.setText(counter.getText() + "&nbsp;" + Integer.toString(i) + "&nbsp;<br />");
		}
		
		counter.setText(counter.getText() +
				Integer.toString(editPane.editor.textArea.getLineCount()));*/
		
		setLayout(new BorderLayout());
		setBackground(new Color(200, 200, 200));
		
		add(counter, BorderLayout.NORTH);
		
	}
	
}
