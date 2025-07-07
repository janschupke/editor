
package io.schupke.editor.gui;

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