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

package eu.janschupke.editor.menu;

import java.awt.event.ActionEvent;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import eu.janschupke.editor.Localisation;
import eu.janschupke.editor.actions.AppActions;
import eu.janschupke.editor.gui.EditPane;

public class SubmenuEdit extends JMenu {
	private static final long serialVersionUID = 1L;

	AppActions appActions;

	JMenuItem itemBack;
	JMenuItem itemForward;
	JMenuItem itemSearch;
	
	public SubmenuEdit(AppActions appActions, String title) {
		super(title);
		
		this.appActions = appActions;
		
		createMenuItems();
		addItems();
		assignListeners();
	}
	
	private void createMenuItems() {
		itemBack = new JMenuItem(
				Localisation.write("menu.edit.item.back"));
		itemForward = new JMenuItem(
				Localisation.write("menu.edit.item.forward"));
		itemSearch = new JMenuItem(
				Localisation.write("menu.edit.item.search"));
	}
	
	private void addItems() {
		add(itemBack);
		add(itemForward);
		add(itemSearch);
	}

	private void assignListeners() {

		itemBack.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// check whether there are any tabs available
				if (((EditPane) appActions.mainWindow.tabbedPane
						.getSelectedComponent()) == null) {

					// throw a warning if there aren't any tabs
					JOptionPane
							.showMessageDialog(
									appActions.mainWindow,
									Localisation.write("error.tab.not.available.text"),
									Localisation.write("error.tab.not.available.title"),
									JOptionPane.PLAIN_MESSAGE);

				} else {

					((EditPane) appActions.mainWindow.tabbedPane
							.getSelectedComponent()).tabActions.moveBack();
					
				}
				
			}
		});

		// item - forward
		itemForward.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// check whether there are any tabs available
				if (((EditPane) appActions.mainWindow.tabbedPane
						.getSelectedComponent()) == null) {

					// throw an warning if there aren't any tabs
					JOptionPane
							.showMessageDialog(
									appActions.mainWindow,
									Localisation.write("error.tab.not.available.text"),
									Localisation.write("error.tab.not.available.title"),
									JOptionPane.PLAIN_MESSAGE);

				} else {

					((EditPane) appActions.mainWindow.tabbedPane
							.getSelectedComponent()).tabActions.moveForward();
					
				}
				
			}
		});

		// item - search
		itemSearch.addActionListener(new java.awt.event.ActionListener() {

			public void actionPerformed(ActionEvent e) {

				// check whether there are any tabs available
				if (((EditPane) appActions.mainWindow.tabbedPane
						.getSelectedComponent()) == null) {

					// throw an warning if there aren't any tabs
					JOptionPane
							.showMessageDialog(
									appActions.mainWindow,
									Localisation.write("error.tab.not.available.text"),
									Localisation.write("error.tab.not.available.title"),
									JOptionPane.PLAIN_MESSAGE);

				} else {

					// proceed with opening the dialog if there is a tab
					// available
					((EditPane) appActions.mainWindow.tabbedPane
							.getSelectedComponent()).tabActions.searchFile();
				}

			}

		});

	}

	
}
