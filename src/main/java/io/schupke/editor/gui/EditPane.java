
package io.schupke.editor.gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import io.schupke.editor.MainWindow;
import io.schupke.editor.actions.TabActions;
import io.schupke.editor.dialog.SearchTool;
import io.schupke.editor.dialog.TabSettings;

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
