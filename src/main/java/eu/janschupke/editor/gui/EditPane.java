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

package eu.janschupke.editor.gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import eu.janschupke.editor.MainWindow;
import eu.janschupke.editor.actions.TabActions;
import eu.janschupke.editor.dialog.SearchTool;
import eu.janschupke.editor.dialog.TabSettings;

public class EditPane extends JPanel {
	private static final long serialVersionUID = 1L;
	SToolbar toolbar;
	public TEditor editor;

	HRuler hRuler;
	VRuler vRuler;
	
	public StatusBar statusBar;
	public TabActions tabActions;
	public TabSettings tabSettings;
	public SearchTool searchTool;

	JPanel workSpace;

	public EditPane(JFrame appWindow) {

		editor = new TEditor((MainWindow)appWindow);
		
		searchTool = new SearchTool(appWindow);
		tabSettings = new TabSettings(appWindow);
		tabActions = new TabActions(appWindow, this);

		editor.tabActions = this.tabActions;
		editor.listeners();

		toolbar = new SToolbar(appWindow, tabActions);

		hRuler = new HRuler(this);
		vRuler = new VRuler(this);
		statusBar = new StatusBar();

		workSpace = new JPanel();

		workSpace.setLayout(new BorderLayout(0, 0));
		setLayout(new BorderLayout(0, 0));
		
		addComponents();
	}
	
	private void addComponents() {
		workSpace.add(editor, BorderLayout.CENTER);
		workSpace.add(hRuler, BorderLayout.NORTH);
		workSpace.add(vRuler, BorderLayout.WEST);

		add(workSpace, BorderLayout.CENTER);
		add(toolbar, BorderLayout.WEST);
		add(statusBar, BorderLayout.SOUTH);
	}

}
