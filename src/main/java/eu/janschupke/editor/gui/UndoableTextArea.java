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

import javax.swing.JTextArea;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.UndoManager;

public class UndoableTextArea extends JTextArea implements UndoableEditListener {
	private static final long serialVersionUID = 1L;

	// Setting Undo Limit to 1500 edits
	public static final int UNDO_LIMIT = 1500;

	// UndoManager
	public UndoManager m_undoManager;

	// constructor
	public UndoableTextArea() {
		this(new String());
	}

	// constructor
	public UndoableTextArea(String text) {
		super(text);

		// add the UndoableEditListener to the TextArea
		getDocument().addUndoableEditListener(this);

		m_undoManager = new UndoManager();
		m_undoManager.setLimit(UNDO_LIMIT);
	}

	// undoableEditHappened called when edit happened
	public void undoableEditHappened(UndoableEditEvent e) {
		// add the edits to the undo manager
		m_undoManager.addEdit(e.getEdit());
	}

}