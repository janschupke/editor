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

package eu.janschupke.editor.actions;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;

import eu.janschupke.editor.Constants;
import eu.janschupke.editor.Localisation;
import eu.janschupke.editor.MainWindow;
import eu.janschupke.editor.Visual;
import eu.janschupke.editor.filehandling.SaveFile;
import eu.janschupke.editor.gui.EditPane;

public class TabActions {

	JFrame appWindow;
	EditPane editPane;

	JFileChooser fileChooserSave;
	File defPath;

	String fileName;

	public TabActions(JFrame appWindow, EditPane editPane) {

		fileChooserSave = new JFileChooser();

		defPath = new File(".");
		
		// dialog for saving
		fileChooserSave.setDialogType(JFileChooser.SAVE_DIALOG);
		fileChooserSave.setDialogTitle(Constants.APP_TITLE + " - "
				+ Localisation.write("dialogue.filechooser.save.title"));
		fileChooserSave.setCurrentDirectory(defPath);
		
		// the entire window
		this.appWindow = appWindow;
		
		// tab
		this.editPane = editPane;

	}
	
	// print the filename / path into the tab title (without unsaved (*) sign)
	public void updateTabTitle(String path) {

		if (appWindow instanceof MainWindow) {
			((MainWindow) appWindow).tabbedPane.setTitleAt(
					((MainWindow) appWindow).tabbedPane.getSelectedIndex(),
					path);
		}

	}
	
	// print the filename / path into the tab title (with unsaved (*) sign)
	public void updateTabTitle(String path, String sign) {

		if (appWindow instanceof MainWindow) {
			((MainWindow) appWindow).tabbedPane.setTitleAt(
					((MainWindow) appWindow).tabbedPane.getSelectedIndex(),
					path + sign);
		}

	}
	
	public void moveBack() {
		
		try {
			editPane.editor.textArea.m_undoManager.undo();
			updateCharCount();
		}
		catch(CannotUndoException e) {}
		catch(NullPointerException e) {}
		
	}

	public void moveForward() {
		
		try {
			editPane.editor.textArea.m_undoManager.redo();
			updateCharCount();
		}
		catch(CannotRedoException e) {}
		catch(NullPointerException e) {}
		
	}
	
	// execute the file saving
	private boolean savingProcedure(String text, File file) {

		// warning about the textarea being empty
		if (text.length() == 0) {
			
			// popup
			JOptionPane.showMessageDialog(appWindow,
					Localisation.write("error.textarea.empty.text"),
					Localisation.write("error.textarea.empty.title"),
					JOptionPane.PLAIN_MESSAGE);
			
			// not saved
			return false;

		// has text - proceed
		} else {
			
			// call the filehandling method
			SaveFile.write(text, file);
			
			// update title and remove the unsaved sign
			updateTabTitle(((MainWindow)appWindow).appActions.getNamingStyle(file));
			
			// update the filename in the statusbar
			editPane.statusBar.fileName.setText(
					((MainWindow)appWindow).appActions.getNamingStyle(file));
			
			// set the file status as saved
			editPane.editor.setUnsaved(false);
			
			// saved
			return true;

		}

	}
	
	// remove this tab from the tabbedPane
	void removeTab() {
		
		((MainWindow) appWindow).tabbedPane
		.remove(((MainWindow) appWindow).tabbedPane
				.getSelectedComponent());
		
	}
	
	// set the file status as unsaved whenever the textarea has been modified
	public void markUnsaved() {
		
		// add an sign after the filename in the tab title
		if (editPane.editor.getCurrentFile() != null) {
			// if file exists - opened or already saved before
			updateTabTitle(
					((MainWindow)appWindow).appActions.getNamingStyle(
							editPane.editor.getCurrentFile()),
					"*");
		} else {
			// if file does not exist yet - new file
			updateTabTitle(Localisation.write("files.default.filename"), "*");
		}
		
		// set the file status as unsaved
		editPane.editor.setUnsaved(true);

	}
	
	// updates statusbar with current file length
	public void updateCharCount() {
		
		// character count
		editPane.statusBar.fileChars.setText(" | " +
				Localisation.write("status.label.chars") + " " +
				Integer.toString(editPane.editor.textArea.getDocument().getLength()));

		// word count
		editPane.statusBar.fileWords.setText(" | " +
				Localisation.write("status.label.words") + " " +
				Integer.toString(editPane.editor.textArea.getDocument().getLength()));
		
		// line count
		editPane.statusBar.fileLines.setText(" | " +
				Localisation.write("status.label.lines") + " " +
				Integer.toString(editPane.editor.textArea.getRows()));
		
	}

	// -----------------------------------------------------------------
	// menu / toolbar actions
	// -----------------------------------------------------------------

	// -----------------------------------------------------------------
	// file

	// try to quicksave the file and call other required methods if needed
	public boolean saveFile() {
		
		// file exists - save
		if (editPane.editor.getCurrentFile() != null) {
			
			// returns 1 if saved, 0 if not
			return savingProcedure(editPane.editor.textArea.getText(), editPane.editor
					.getCurrentFile());

		// new file - call saveAs method
		} else {
			
			// returns 1 if saved, 0 if not
			return saveAsFile();

		}

	}

	// always open the saving dialog
	public boolean saveAsFile() {
		
		// dialog accepted
		if (fileChooserSave.showSaveDialog(appWindow) == JFileChooser.APPROVE_OPTION) {

			// overwriting
			if (fileChooserSave.getSelectedFile().exists()) {
				
				// overwrite confirmation
				int i = JOptionPane.showConfirmDialog(appWindow, Localisation
						.write("confirm.file.overwrite.text"), Localisation
						.write("confirm.file.overwrite.title") + " (" +
						fileChooserSave.getSelectedFile().getName() + ")",
						JOptionPane.YES_NO_OPTION);
				
				// confirmed
				if (i == JOptionPane.YES_OPTION) {
					
					// returns 1 if saved, 0 if not
					return savingProcedure(editPane.editor.textArea.getText(),
							fileChooserSave.getSelectedFile());
					
				// confirmation did not pass
				} else {
					// not saved
					return false;
				}

			// saving into a new file
			} else {
				
				// returns 1 if saved, 0 if not
				return savingProcedure(editPane.editor.textArea.getText(),
						fileChooserSave.getSelectedFile());

			}
		
		// dialog canceled
		} else {
			
			// not saved
			return false;
		}

	}

	// close the current tab
	public void closeTab() {
		
		// set the filemane, based on whether is has ever been saved before
		if (editPane.editor.getCurrentFile() == null) {
			// file does not exist
			fileName = Localisation.write("files.default.filename");
		} else {
			// file already exists in the filesystem
			fileName = editPane.editor.getCurrentFile().getPath();
		}

		// if the file is unsaved
		if (editPane.editor.getUnsaved() == true) {
			
			// ask for a confirmation
			int i = JOptionPane.showConfirmDialog(
					appWindow,
					Localisation.write("confirm.close.tab.text"),
					fileName,
					JOptionPane.YES_NO_CANCEL_OPTION);
			
			// decide
			switch(i) {

				// save and exit
				case JOptionPane.YES_OPTION:
					
					// do not close tab without saving
					if(saveFile()) {
						removeTab();
					}
					
					break;
			
				// remove without saving
				case JOptionPane.NO_OPTION:
					removeTab();
					break;

				// no action
				default:
				case JOptionPane.CANCEL_OPTION:
					break;
			
			}

		// is saved
		} else {
			removeTab();
		}

	}

	// -----------------------------------------------------------------
	// edit

	// open a dialog with the search feature
	public void searchFile() {
		
		// show the dialog
		editPane.searchTool.setVisible(true);

	}
	
	// jump to the convenient string
	public void findNext(String searchedText, Boolean found, boolean repeat) {

		String currentText = editPane.editor.textArea.getText();
		int docLength = editPane.editor.textArea.getDocument().getLength();
		int caretPos = editPane.editor.textArea.getCaretPosition();
		int startingIndex = currentText.indexOf(searchedText, caretPos+1);
		
		if(found.equals(null)) {
			found = false;
		}
		
		// if the search input is empty
		if (searchedText.equals("")) {
			// warning
			JOptionPane.showMessageDialog(null,
					Localisation.write("error.search.emptyInput.text"),
					Localisation.write("error.search.emptyInput.title"),
					JOptionPane.WARNING_MESSAGE);
		} else {
			
			// end of the document
			if (startingIndex == -1) {

				// return back after reaching the end of the document
				if (docLength > 0) {
					
					// carriage return
					editPane.editor.textArea.setCaretPosition(0);
					
					// cycle through once again or alert
					if(!repeat) {
						JOptionPane.showMessageDialog(null,
								Localisation.write("error.search.notFound.text"),
								Localisation.write("error.search.notFound.title"),
								JOptionPane.WARNING_MESSAGE);
					} else {
						((MainWindow)appWindow).appActions
						.getSelectedEditPane().tabActions.findNext(searchedText, found, false);
					}
					
				// throw a warning if empty
				} else {
					// warning
					JOptionPane.showMessageDialog(null,
							Localisation.write("error.search.emptyDoc.text"),
							Localisation.write("error.search.emptyDoc.title"),
							JOptionPane.WARNING_MESSAGE);
				}

			// highlight the string
			} else {
				
				// focus the textarea
				editPane.editor.textArea.requestFocus();
				// move the cursor to the beginning of the string
				editPane.editor.textArea.setCaretPosition(startingIndex);
				// highlight
				editPane.editor.textArea.moveCaretPosition(startingIndex
						+ searchedText.length());
				editPane.editor.textArea.setSelectionColor(
						Visual.getSearchHighlightColor());
				
				found = true;
				
			} // end of the doc

		} // empty input

	}

	// -----------------------------------------------------------------
	// properties

	// open the properties -> tab settins dialog
	public void openTabSettings() {
		
		// show the dialog
		editPane.tabSettings.setVisible(true);

	}

}
