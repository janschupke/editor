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

public class SubmenuFile extends JMenu {
	private static final long serialVersionUID = 1L;

	AppActions appActions;
	
	JMenuItem itemNew;
	JMenuItem itemOpen;
	JMenuItem itemSave;
	JMenuItem itemSaveAs;
	JMenuItem itemSaveAll;
	JMenuItem itemCloseApp;
	
	public SubmenuFile(AppActions appActions, String title) {
		super(title);
		
		this.appActions = appActions;
		
		createMenuItems();
		addItems();
		assignListeners();
	}
	
	private void createMenuItems() {
		itemNew = new JMenuItem(
				Localisation.write("menu.file.item.new"));
		itemOpen = new JMenuItem(
				Localisation.write("menu.file.item.open"));
		itemSave = new JMenuItem(
				Localisation.write("menu.file.item.save"));
		itemSaveAs = new JMenuItem(
				Localisation.write("menu.file.item.saveas"));
		itemSaveAll = new JMenuItem(
				Localisation.write("menu.file.item.saveall"));
		itemCloseApp = new JMenuItem(
				Localisation.write("menu.file.item.close.app"));
	}
	
	private void addItems() {
		add(itemNew);
		add(itemOpen);
		add(itemSave);
		add(itemSaveAs);
		add(itemCloseApp);
		add(itemCloseApp);
	}

	private void assignListeners() {

		itemNew.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				appActions.makeNewFile();
			}
		});

		itemOpen.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				appActions.openFile();
			}
		});

		itemSave.addActionListener(new java.awt.event.ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				// check whether the is any tab opened
				if (((EditPane) appActions.mainWindow.tabbedPane
						.getSelectedComponent()) == null) {
					
					// throw a warning if there are not any tabs
					JOptionPane
							.showMessageDialog(
									appActions.mainWindow,
									Localisation.write("error.tab.not.available.text"),
									Localisation.write("error.tab.not.available.title"),
									JOptionPane.PLAIN_MESSAGE);

				} else {
					
					// proceed with saving, if a tab is available
					((EditPane) appActions.mainWindow.tabbedPane
							.getSelectedComponent()).tabActions.saveFile();
				}

			}

		});

		itemSaveAs.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// check whether the is any tab opened
				if (((EditPane) appActions.mainWindow.tabbedPane
						.getSelectedComponent()) == null) {
					
					// throw warning, if there are not any tabs
					JOptionPane
							.showMessageDialog(
									appActions.mainWindow,
									Localisation.write("error.tab.not.available.text"),
									Localisation.write("error.tab.not.available.title"),
									JOptionPane.PLAIN_MESSAGE);

				} else {
					
					// proceed with saving, if a tab is available
					((EditPane) appActions.mainWindow.tabbedPane
							.getSelectedComponent()).tabActions.saveAsFile();
				}
				
			}
		});

		itemSaveAll.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// check whether the is any tab opened
				if (((EditPane) appActions.mainWindow.tabbedPane
						.getSelectedComponent()) == null) {
					
					// throw warning, if there are not any tabs
					JOptionPane
							.showMessageDialog(
									appActions.mainWindow,
									Localisation.write("error.tab.not.available.text"),
									Localisation.write("error.tab.not.available.title"),
									JOptionPane.PLAIN_MESSAGE);

				} else {
					
					// proceed with saving, if a tab is available
					appActions.saveAll();
				}
				
			}
		});

		itemCloseApp.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				appActions.closeApp();
			}
		});

	}

	
}
