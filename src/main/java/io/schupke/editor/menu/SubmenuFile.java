
package io.schupke.editor.menu;

import java.awt.event.ActionEvent;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import io.schupke.editor.Localisation;
import io.schupke.editor.actions.AppActions;
import io.schupke.editor.gui.EditPane;

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
