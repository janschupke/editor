
package io.schupke.editor.menu;

import java.awt.event.ActionEvent;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import io.schupke.editor.Localisation;
import io.schupke.editor.actions.AppActions;
import io.schupke.editor.gui.EditPane;

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
