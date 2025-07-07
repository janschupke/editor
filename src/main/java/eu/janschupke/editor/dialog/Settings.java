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
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import eu.janschupke.editor.Constants;
import eu.janschupke.editor.Localisation;

public class Settings extends JDialog {
	private static final long serialVersionUID = 1L;
	
	JPanel mainPanel;
	JButton buttonOK;
	JButton buttonCancel;
	
	JLabel configPath;
	
	public Settings(Frame owner, String title) {
		
		super(owner, title, true);
		setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		
		mainPanel = new JPanel();
		
		configPath = new JLabel(
				"<html><b>" +
				Localisation.write("label.dialog.settings.homeFolder") +
				":</b> " +
				System.getProperty("user.home") +
				System.getProperty("file.separator") +
				Constants.MAIN_FOLDER +
				System.getProperty("file.separator") +
				Constants.APP_FOLDER);
		configPath.setBounds(5, 150, 480, 30);
		
		buttonCancel = new JButton(Localisation.write("label.dialog.button.cancel"));
		buttonCancel.setBounds(395, 180, 70, 30);

		buttonOK = new JButton(Localisation.write("label.dialog.button.ok"));
		buttonOK.setBounds(470, 180, 70, 30);

		setWindow();
	}
	
	void setWindow() {
		
		this.getContentPane().setLayout(new BorderLayout(0, 0));
		this.setSize(550, 250);
		this.setLocation(250, 350);
		this.setResizable(false);
		
		add(mainPanel, BorderLayout.CENTER);
		
		mainPanel.setLayout(null);

		mainPanel.add(configPath, null);
		mainPanel.add(buttonCancel, null);
		mainPanel.add(buttonOK, null);
		
		generalListeners();
	}

	void generalListeners() {
		
		// cancelation hides the dialog
		buttonCancel.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		
	}
	
}
