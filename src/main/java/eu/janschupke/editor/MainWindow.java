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

package eu.janschupke.editor;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import eu.janschupke.editor.actions.AppActions;
import eu.janschupke.editor.config.Config;
import eu.janschupke.editor.dialog.About;
import eu.janschupke.editor.dialog.AppSettings;
import eu.janschupke.editor.dialog.Docs;
import eu.janschupke.editor.gui.GToolbar;
import eu.janschupke.editor.menu.MainMenu;

public class MainWindow extends JFrame {
	
	private static final long serialVersionUID = 1L;
	MainMenu mainMenu;
	GToolbar gToolbar;

	public JTabbedPane tabbedPane;
	public AppSettings appSettings;
	public AppActions appActions;
	public About aboutDialog;
	public Docs docsDialog;
	public Config config;
	
	public MainWindow() {
		
		config = new Config();
		
		Localisation.loadLang(config.config.getProperty(config.LANGUAGE));
		
		appActions = new AppActions(this);
		tabbedPane = new JTabbedPane();
		tabbedPane.setFont(Visual.getMonospacedFont());
		
		mainMenu = new MainMenu(this);
		gToolbar = new GToolbar(this);
		appSettings = new AppSettings(this);
		aboutDialog = new About(this);
		docsDialog = new Docs(this);
		
		appActions.makeNewFile();
		
	}
	
	void render() throws Exception {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSizeAndLocation();
		setTitle(Constants.APP_TITLE + " " + Constants.APP_VERSION);
		setResizable(true);

		getContentPane().setLayout(new BorderLayout(0, 0));
		getContentPane().add(gToolbar, BorderLayout.NORTH);
		getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		this.setVisible(true);
	}

	private void setSizeAndLocation() {
		setSize(800, 600);
		setLocation(150, 100);
		
		if(Boolean.parseBoolean(config.config.getProperty(config.MAXIMIZED))) {
			setExtendedState(JFrame.MAXIMIZED_BOTH);
		} 	
	}
	
}
