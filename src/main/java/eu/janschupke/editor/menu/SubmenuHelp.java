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

import eu.janschupke.editor.Localisation;
import eu.janschupke.editor.actions.AppActions;

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
