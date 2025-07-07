package io.schupke.editor;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import io.schupke.editor.actions.AppActions;
import io.schupke.editor.config.Config;
import io.schupke.editor.dialog.About;
import io.schupke.editor.dialog.AppSettings;
import io.schupke.editor.dialog.Docs;
import io.schupke.editor.gui.GToolbar;
import io.schupke.editor.menu.MainMenu;

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
