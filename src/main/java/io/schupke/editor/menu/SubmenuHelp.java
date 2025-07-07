
package io.schupke.editor.menu;

import java.awt.event.ActionEvent;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import io.schupke.editor.Localisation;
import io.schupke.editor.actions.AppActions;

public class SubmenuHelp extends JMenu {
	private static final long serialVersionUID = 1L;

	AppActions appActions;

	JMenuItem itemAbout;
	JMenuItem itemDocumentation;
	
	public SubmenuHelp(AppActions appActions, String title) {
		super(title);
		
		this.appActions = appActions;
		
		createMenuItems();
		addItems();
		assignListeners();
	}
	
	private void createMenuItems() {
		itemAbout = new JMenuItem(
				Localisation.write("menu.help.item.about"));
		itemDocumentation = new JMenuItem(
				Localisation.write("menu.help.item.docs"));
	}
	
	private void addItems() {
		add(itemAbout);
		add(itemDocumentation);
	}

	private void assignListeners() {

		itemAbout.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				appActions.openAbout();
			}
		});

		// item - documentation
		itemDocumentation.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				appActions.openDocs();
			}
		});

	}
	
}
