
package io.schupke.editor.dialog;

import java.awt.BorderLayout;
import java.awt.Frame;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import io.schupke.editor.Constants;
import io.schupke.editor.Localisation;
import io.schupke.editor.filehandling.ReadFile;

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
		
		textArea.setText(ReadFile.read("docs/index.html"));
		
	}
	
	void setWindow() {
		
		getContentPane().setLayout(new BorderLayout());
		this.setSize(800, 600);
		this.setLocation(250, 350);
		setResizable(false);

		add(scrollPane, BorderLayout.CENTER);
		
	}
	
}
