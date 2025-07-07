
package io.schupke.editor.dialog;

import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextField;

import io.schupke.editor.Localisation;
import io.schupke.editor.MainWindow;
import io.schupke.editor.actions.AppActions;

public class SearchTool extends JDialog {
	private static final long serialVersionUID = 1L;

	AppActions appActions;
	JTextField searchInput;
	JButton buttonSearch;
	JButton buttonClose;
	
	public SearchTool(Frame owner) {
		
		super(owner, Localisation.write("dialogue.searchTool.title"), true);
		setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);

		appActions = ((MainWindow)owner).appActions;
		
		searchInput = new JTextField(20);
		
		buttonSearch = new JButton(Localisation.write("label.dialog.button.search"));
		buttonClose = new JButton(Localisation.write("label.dialog.button.cancel"));
		
		setWindow();
	}
	
	void setWindow() {
		
		this.getContentPane().setLayout(new FlowLayout());
		this.setSize(460, 70);
		this.setLocation(250, 350);
		this.setResizable(false);

		add(searchInput, null);
		
		add(buttonSearch, null);
		add(buttonClose, null);
		
		listeners();
	}

	void listeners() {

		// jump to a next result
		buttonSearch.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				appActions.getSelectedEditPane().tabActions.findNext(searchInput.getText(), false, true);
			}
		});
		
		// hide the dialog
		buttonClose.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		
	}

}
