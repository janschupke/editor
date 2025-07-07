
package io.schupke.editor.gui;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import io.schupke.editor.MainWindow;
import io.schupke.editor.Visual;
import io.schupke.editor.actions.TabActions;

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
