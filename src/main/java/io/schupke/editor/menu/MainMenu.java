
package io.schupke.editor.menu;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

import io.schupke.editor.Localisation;
import io.schupke.editor.MainWindow;
import io.schupke.editor.actions.AppActions;

public class MainMenu extends JMenuBar {

	private static final long serialVersionUID = 1L;

	AppActions appActions;

	JMenu menuFile;
	JMenu menuEdit;
	JMenu menuProperties;
	JMenu menuHelp;

	public MainMenu(MainWindow mainWindow) {
		appActions = mainWindow.appActions;

		createMenus();
		addMenusToBar();
		
		mainWindow.setJMenuBar(this);
	}
	
	// appActions for assigning listener actions
	private void createMenus() {
		menuFile = new SubmenuFile(appActions,
				Localisation.write("menu.file.title"));
		menuEdit = new SubmenuEdit(appActions,
				Localisation.write("menu.edit.title"));
		menuProperties = new SubmenuProperties(appActions,
				Localisation.write("menu.properties.title"));
		menuHelp = new SubmenuHelp(appActions,
				Localisation.write("menu.help.title"));
	}
	
	private void addMenusToBar() {
		add(menuFile);
		add(menuEdit);
		add(menuProperties);
		add(menuHelp);
	}

}
