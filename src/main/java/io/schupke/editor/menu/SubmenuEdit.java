
package io.schupke.editor.menu;

import java.awt.event.ActionEvent;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import io.schupke.editor.Localisation;
import io.schupke.editor.actions.AppActions;
import io.schupke.editor.gui.EditPane;

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
