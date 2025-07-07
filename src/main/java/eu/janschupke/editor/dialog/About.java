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

import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

import eu.janschupke.editor.Constants;
import eu.janschupke.editor.Localisation;

public class About extends JDialog {
	private static final long serialVersionUID = 1L;

	JLabel text;
	JButton buttonOK;
	
	public About(Frame owner) {
		
		super(owner, Localisation.write("dialogue.menu.help.about.title") + " " +
				Constants.APP_TITLE, true);
		setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		
		text = new JLabel(
				"<html><div style=\"width:270px;margin:10px;\">" +
				Localisation.write("dialogue.menu.help.about.text") +
				"</div><h2 style=\"margin-left:10px;\">" +
				Localisation.write("dialogue.menu.help.about.specifics.title") +
				"</h2>" +
				formatItem(
						Localisation.write("dialogue.menu.help.about.specifics.author"),
						Constants.APP_AUTHOR) +
				formatItem(
						Localisation.write("dialogue.menu.help.about.specifics.ver"),
						Constants.APP_VERSION) +
				formatItem(
						Localisation.write("dialogue.menu.help.about.specifics.licence"),
						Constants.APP_LICENCE) +
				formatItem(
						Localisation.write("dialogue.menu.help.about.specifics.email") +
						"</a>",
						Constants.APP_AUTHOR_CONTACT +
						"<br />&nbsp;")
				);
		
		buttonOK = new JButton(Localisation.write("label.dialog.button.ok"));
		
		setWindow();
	}
	
	private void setWindow() {
		
		this.getContentPane().setLayout(new FlowLayout());
		this.setSize(350, 350);
		this.setLocation(250, 350);
		this.setResizable(false);

		add(text, null);
		add(buttonOK, null);
		
		listeners();
		
	}
	
	private String formatItem(String label, String value) {
		String output =
			"<div style=\"width:270px;margin:0px 10px 2px 10px;\"><b>" +
			label + ":</b> " + value + "</div>";
		
		return output;
	}

	private void listeners() {
		buttonOK.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
	}
	
}
