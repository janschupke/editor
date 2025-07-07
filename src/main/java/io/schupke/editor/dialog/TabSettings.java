
package io.schupke.editor.dialog;

import java.awt.Frame;

import javax.swing.JLabel;

import io.schupke.editor.Localisation;

public class TabSettings extends Settings {
	private static final long serialVersionUID = 1L;
	JLabel todo;
	
	public TabSettings(Frame owner) {
		
		super(owner, Localisation.write("dialogue.menu.properties.tab.title"));
		
		todo = new JLabel(
				"<html><b><font size=\"25\">" +
				Localisation.write("label.text.toBeDone") +
				"</font></b>");
		todo.setBounds(1, 1, 300, 150);
		
		try {
			render();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	void render() {
		
		// print the note
		mainPanel.add(todo, null);
		
	}
	
}
