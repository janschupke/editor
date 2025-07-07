
package io.schupke.editor.dialog;

import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

import io.schupke.editor.Constants;
import io.schupke.editor.Localisation;

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
