package io.schupke.editor;

import java.awt.Font;
import java.util.Enumeration;

import javax.swing.UIManager;

import io.schupke.editor.MainWindow;
import io.schupke.editor.Visual;

public class Editor {

	public static void main(String[] args) {

		try {
			// TEditor, tab titles => monospace
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            
			// default font
			Enumeration<Object> keys = UIManager.getDefaults().keys();
			while (keys.hasMoreElements()) {
				Object key = keys.nextElement();
				Object value = UIManager.get(key);
				if (value instanceof Font) {
					UIManager.put(key, Visual.getNormalFont());
				}
			}
            
		} catch (Exception e) {
			e.printStackTrace();
		}

		MainWindow editor = new MainWindow();

		try {
			editor.render();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
