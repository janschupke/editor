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
import javax.swing.JTextField;

import eu.janschupke.editor.Localisation;
import eu.janschupke.editor.MainWindow;
import eu.janschupke.editor.actions.AppActions;

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
