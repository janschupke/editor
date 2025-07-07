
package io.schupke.editor.dialog;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import io.schupke.editor.Constants;
import io.schupke.editor.Localisation;

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
