
package io.schupke.editor.gui;

import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import io.schupke.editor.Localisation;
import io.schupke.editor.actions.TabActions;
import io.schupke.editor.layout.VerticalLayout;

public class SToolbar extends JPanel {
	private static final long serialVersionUID = 1L;
	
	TabActions tabActions;
	
	JButton buttonSave;
	JButton buttonSaveAs;
	JButton buttonSearch;
	JButton buttonBack;
	JButton buttonForward;
	JButton buttonClose;
	
	ClassLoader classLoader;
	
	public SToolbar(JFrame appWindow, TabActions tabActions) {
		
		this.tabActions = tabActions;
		classLoader = this.getClass().getClassLoader();

		setLayout(new VerticalLayout());
		
		createButtons();
		assignHotkeys();
		setButtonTooltips();
		addComponents();
	}
	
	private void createButtons() {
		buttonSave = new JButton();
		buttonSaveAs = new JButton();
		buttonSearch = new JButton();
		buttonBack = new JButton();
		buttonForward = new JButton();
		buttonClose = new JButton();
	}
	
	private void setButtonTooltips() {
		buttonSave.setToolTipText(
				Localisation.write("tooltip.button.save"));
		buttonSaveAs.setToolTipText(
				Localisation.write("tooltip.button.saveAs"));
		buttonSearch.setToolTipText(
				Localisation.write("tooltip.button.search"));
		buttonBack.setToolTipText(
				Localisation.write("tooltip.button.back"));
		buttonForward.setToolTipText(
				Localisation.write("tooltip.button.forward"));
		buttonClose.setToolTipText(
				Localisation.write("tooltip.button.closeTab"));
	}
	
	private void addComponents() {
		add(buttonSave, null);
		add(buttonSaveAs, null);
		add(buttonSearch, null);
		add(buttonBack, null);
		add(buttonForward, null);
		add(buttonClose, null);
	}
	
	private void assignHotkeys() {
        int focused = JComponent.WHEN_IN_FOCUSED_WINDOW;
        
        assignSaveHotkey(focused);
        assignSaveAsHotkey(focused);
        assignSearchHotkey(focused);
        assignBackHotkey(focused);
        assignForwardHotkey(focused);
        assignCloseHotkey(focused);
	}
	
	private void assignSaveHotkey(int focused) {
        KeyStroke KeySave = KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK);
        
        buttonSave.setAction(new AbstractAction("",
        		new ImageIcon(classLoader.getResource("save_icon.gif"))) {
					private static final long serialVersionUID = 1L;
            {
                putValue(Action.ACTION_COMMAND_KEY, getValue(Action.NAME));
            }
			public void actionPerformed(ActionEvent e) {
				tabActions.saveFile();
			}
        });

        getInputMap(focused).put(KeySave, "SAVE");
        getActionMap().put("SAVE", buttonSave.getAction());
	}
	
	private void assignSaveAsHotkey(int focused) {
        KeyStroke KeySaveAs = KeyStroke.getKeyStroke(KeyEvent.VK_S,
        		InputEvent.CTRL_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK);

        buttonSaveAs.setAction(new AbstractAction("",
        		new ImageIcon(classLoader.getResource("save_as_icon.gif"))) {
					private static final long serialVersionUID = 1L;
            {
                putValue(Action.ACTION_COMMAND_KEY, getValue(Action.NAME));
            }
			public void actionPerformed(ActionEvent e) {
				tabActions.saveAsFile();
			}
        });

        getInputMap(focused).put(KeySaveAs, "SAVEAS");
        getActionMap().put("SAVEAS", buttonSaveAs.getAction());
	}
	
	private void assignSearchHotkey(int focused) {
        KeyStroke KeySearch = KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_DOWN_MASK);

        buttonSearch.setAction(new AbstractAction("",
        		new ImageIcon(classLoader.getResource("search_icon.gif"))) {
					private static final long serialVersionUID = 1L;
            {
                putValue(Action.ACTION_COMMAND_KEY, getValue(Action.NAME));
            }
			public void actionPerformed(ActionEvent e) {
				tabActions.searchFile();
			}
        });

        getInputMap(focused).put(KeySearch, "SEARCH");
        getActionMap().put("SEARCH", buttonSearch.getAction());
	}
	
	private void assignBackHotkey(int focused) {
        KeyStroke KeyBack = KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_DOWN_MASK);

        buttonBack.setAction(new AbstractAction("",
        		new ImageIcon(classLoader.getResource("back_icon.gif"))) {
					private static final long serialVersionUID = 1L;
            {
                putValue(Action.ACTION_COMMAND_KEY, getValue(Action.NAME));
            }
			public void actionPerformed(ActionEvent e) {
				tabActions.moveBack();
			}
        });

        getInputMap(focused).put(KeyBack, "BACK");
        getActionMap().put("BACK", buttonBack.getAction());
	}
	
	private void assignForwardHotkey(int focused) {
        KeyStroke KeyForward = KeyStroke.getKeyStroke(KeyEvent.VK_Z,
        		InputEvent.CTRL_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK);

        buttonForward.setAction(new AbstractAction("",
        		new ImageIcon(classLoader.getResource("forward_icon.gif"))) {
					private static final long serialVersionUID = 1L;
            {
                putValue(Action.ACTION_COMMAND_KEY, getValue(Action.NAME));
            }
			public void actionPerformed(ActionEvent e) {
				tabActions.moveForward();
			}
        });

        getInputMap(focused).put(KeyForward, "FORWARD");
        getActionMap().put("FORWARD", buttonForward.getAction());
	}
	
	private void assignCloseHotkey(int focused) {
        KeyStroke KeyClose = KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_DOWN_MASK);

        buttonClose.setAction(new AbstractAction("",
        		new ImageIcon(classLoader.getResource("close_icon.gif"))) {
					private static final long serialVersionUID = 1L;
            {
                putValue(Action.ACTION_COMMAND_KEY, getValue(Action.NAME));
            }
			public void actionPerformed(ActionEvent e) {
				tabActions.closeTab();
			}
        });

        getInputMap(focused).put(KeyClose, "CLOSE");
        getActionMap().put("CLOSE", buttonClose.getAction());
	}
}
