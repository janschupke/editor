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
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import eu.janschupke.editor.MainWindow;
import eu.janschupke.editor.Visual;
import eu.janschupke.editor.actions.TabActions;

public class TEditor extends JPanel {
	private static final long serialVersionUID = 1L;
	
	TabActions tabActions;
	
	public UndoableTextArea textArea;
	JScrollPane textEditor;
	
	private File currentFile;
	private boolean unsaved;
	
	public TEditor(MainWindow mainWindow) {
		
		unsaved = false;
		
		textArea = new UndoableTextArea();
		textArea.setFont(Visual.getMonospacedFont());
		
		textArea.setLineWrap(
				Boolean.parseBoolean(mainWindow.config.config.getProperty(
						mainWindow.config.LINE_WRAPPING)));
	
		textEditor = new JScrollPane(textArea,
		JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
		JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		setLayout(new BorderLayout(0, 0));
		add(textEditor, BorderLayout.CENTER);
		
	}
	
	public File getCurrentFile() {
		return currentFile;
	}
	
	public void setCurrentFile(File file) {
		currentFile = file;
	}
	
	public boolean getUnsaved() {
		return unsaved;
	}
	
	public void setUnsaved(boolean status) {
		unsaved = status;
	}
	
	void listeners() {
		textArea.addKeyListener(new java.awt.event.KeyListener() {
			public void keyReleased(KeyEvent e) {
				tabActions.updateCharCount();
			}
			
			public void keyPressed(KeyEvent e) {
				//tabActions.redrawEditor();
			}
			
			public void keyTyped(KeyEvent e) {
				tabActions.markUnsaved();
			}
		});
	}
	
}
