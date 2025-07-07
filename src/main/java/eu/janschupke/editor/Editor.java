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

import java.awt.Font;
import java.util.Enumeration;

import javax.swing.UIManager;

import eu.janschupke.editor.MainWindow;
import eu.janschupke.editor.Visual;

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
