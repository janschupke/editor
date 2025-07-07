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

package eu.janschupke.editor.dialog;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.io.File;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import eu.janschupke.editor.Constants;
import eu.janschupke.editor.Localisation;
import eu.janschupke.editor.filehandling.ReadFile;

public class Docs extends JDialog {
	private static final long serialVersionUID = 1L;
	JLabel textArea;
	JScrollPane scrollPane;
	
	public Docs(Frame owner) {
		
		super(owner, Localisation.write("dialogue.menu.help.docs.title")
				+ " - " + Constants.APP_TITLE, false);
		setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		
		textArea = new JLabel();
		scrollPane = new JScrollPane(textArea,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		openDocs();
		setWindow();
		
	}

	void openDocs() {
		
		String jarDir = new File(Docs.class.getProtectionDomain().getCodeSource().
				getLocation().getPath()).getParent();
		
		textArea.setText(ReadFile.read(
				jarDir + Constants.DOCS_ROOT));
		
	}
	
	void setWindow() {
		
		getContentPane().setLayout(new BorderLayout());
		this.setSize(800, 600);
		this.setLocation(250, 350);
		setResizable(false);

		add(scrollPane, BorderLayout.CENTER);
		
	}
	
}
