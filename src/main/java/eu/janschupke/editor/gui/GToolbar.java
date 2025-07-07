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
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import eu.janschupke.editor.Localisation;
import eu.janschupke.editor.MainWindow;
import eu.janschupke.editor.actions.AppActions;

public class GToolbar extends JPanel {
	private static final long serialVersionUID = 1L;
	
	public AppActions appActions;
	
	JPanel buttons;
	
	JButton buttonNew;
	JButton buttonOpen;
	JButton buttonSaveAll;
	
	ClassLoader classLoader;
	
	public GToolbar(MainWindow mainWindow) {
		classLoader = this.getClass().getClassLoader();
		appActions = mainWindow.appActions;
		buttons = new JPanel();
		
		buttonNew = new JButton();
		buttonOpen = new JButton();
		buttonSaveAll = new JButton();
		
		assignButtonHotkeys();
		setButtonTooltips();
		
		buttons.setLayout(new FlowLayout());
		setLayout(new BorderLayout());
		
		addComponents();
	}
	
	private void addComponents() {
		buttons.add(buttonNew, null);
		buttons.add(buttonOpen, null);
		buttons.add(buttonSaveAll, null);
		
		add(buttons, BorderLayout.WEST);
	}
	
	private void assignButtonHotkeys() {
        int focused = JComponent.WHEN_IN_FOCUSED_WINDOW;
        
        assignNewHotkey(focused);
        assignOpenHotkey(focused);
        assignSaveAllHotkey(focused);
	}
	
	private void assignNewHotkey(int focused) {
        KeyStroke KeyNew = KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK);
        
        buttonNew.setAction(new AbstractAction("",
        		new ImageIcon(classLoader.getResource("resources/new_icon.gif"))) {
					private static final long serialVersionUID = 1L;
			{
                putValue(Action.ACTION_COMMAND_KEY, getValue(Action.NAME));
            }
			public void actionPerformed(ActionEvent e) {
				appActions.makeNewFile();
			}
        });

        getInputMap(focused).put(KeyNew, "NEW");
        getActionMap().put("NEW", buttonNew.getAction());
	}
	
	private void assignOpenHotkey(int focused) {
        KeyStroke KeyOpen = KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK);

		buttonOpen.setAction(new AbstractAction("",
				new ImageIcon(classLoader.getResource("resources/open_icon.gif"))) {
					private static final long serialVersionUID = 1L;
			{
                putValue(Action.ACTION_COMMAND_KEY, getValue(Action.NAME));
            }
			public void actionPerformed(ActionEvent e) {
				appActions.openFile();
			}
        });

        getInputMap(focused).put(KeyOpen, "OPEN");
        getActionMap().put("OPEN", buttonOpen.getAction());
	}
	
	private void assignSaveAllHotkey(int focused) {
        KeyStroke KeySaveAll = KeyStroke.getKeyStroke(KeyEvent.VK_S,
        		InputEvent.CTRL_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK | InputEvent.ALT_DOWN_MASK);

        buttonSaveAll.setAction(new AbstractAction("",
        		new ImageIcon(classLoader.getResource("resources/save_all_icon.gif"))) {
					private static final long serialVersionUID = 1L;
            {
                putValue(Action.ACTION_COMMAND_KEY, getValue(Action.NAME));
            }
			public void actionPerformed(ActionEvent e) {
				appActions.saveAll();
			}
        });

        getInputMap(focused).put(KeySaveAll, "SAVEALL");
        getActionMap().put("SAVEALL", buttonSaveAll.getAction());
	}
	
	private void setButtonTooltips() {
		buttonNew.setToolTipText(
				Localisation.write("tooltip.button.new"));
		buttonOpen.setToolTipText(
				Localisation.write("tooltip.button.open"));
		buttonSaveAll.setToolTipText(
				Localisation.write("tooltip.button.saveAll"));
	}
	
}
