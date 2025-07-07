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

public class SubmenuProperties extends JMenu {
	private static final long serialVersionUID = 1L;

	AppActions appActions;

	JMenuItem itemApp;
	JMenuItem itemTab;

	public SubmenuProperties(AppActions appActions, String title) {
		super(title);

		this.appActions = appActions;

		createMenuItems();
		addItems();
		assignListeners();
	}

	private void createMenuItems() {
		itemApp = new JMenuItem(Localisation
				.write("menu.properties.item.app"));
		itemTab = new JMenuItem(Localisation
				.write("menu.properties.item.tab"));
	}

	private void addItems() {
		add(itemApp);
		add(itemTab);
	}

	private void assignListeners() {

		itemApp.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				appActions.openAppSettings();
			}
		});

		// item - file settings
		itemTab.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// check whether there are any tabs available
				if (((EditPane) appActions.mainWindow.tabbedPane
						.getSelectedComponent()) == null) {

					// throw an warning if there aren't any tabs
					JOptionPane
							.showMessageDialog(
									appActions.mainWindow,
									Localisation
											.write("error.tab.not.available.text"),
									Localisation
											.write("error.tab.not.available.title"),
									JOptionPane.PLAIN_MESSAGE);

				} else {

					// proceed with opening the dialog if there is a tab
					// available
					((EditPane) appActions.mainWindow.tabbedPane
							.getSelectedComponent()).tabActions
							.openTabSettings();
				}

			}
		});

	}

}
