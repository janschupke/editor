
package io.schupke.editor.gui;

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

import io.schupke.editor.Localisation;
import io.schupke.editor.MainWindow;
import io.schupke.editor.actions.AppActions;

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
        		new ImageIcon(classLoader.getResource("new_icon.gif"))) {
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
				new ImageIcon(classLoader.getResource("open_icon.gif"))) {
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
        		new ImageIcon(classLoader.getResource("save_all_icon.gif"))) {
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
