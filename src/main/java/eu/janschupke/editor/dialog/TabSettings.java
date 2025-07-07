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

import java.awt.Frame;

import javax.swing.JLabel;

import eu.janschupke.editor.Localisation;

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
