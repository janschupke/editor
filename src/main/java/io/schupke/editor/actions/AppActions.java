
package io.schupke.editor.actions;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import io.schupke.editor.Constants;
import io.schupke.editor.Localisation;
import io.schupke.editor.MainWindow;
import io.schupke.editor.filehandling.ReadFile;
import io.schupke.editor.gui.EditPane;
import io.schupke.editor.gui.TEditor;

public class AppActions {

	public MainWindow mainWindow;

	EditPane editPane;

	JFileChooser fileChooserOpen;
	File defPath;

	public AppActions(MainWindow mainWindow) {

		fileChooserOpen = new JFileChooser();

		defPath = new File(".");
		
		// dialog for opening the files
		fileChooserOpen.setDialogType(JFileChooser.OPEN_DIALOG);
		fileChooserOpen.setDialogTitle(Constants.APP_TITLE + " - "
				+ Localisation.write("dialogue.filechooser.open.title"));
		fileChooserOpen.setCurrentDirectory(defPath);
		
		this.mainWindow = mainWindow;

	}
	
	// returns textarea
	public TEditor getTEditor() {
		return getSelectedEditPane().editor;
	}
	
	// returns the entire tab
	public EditPane getSelectedEditPane() {
		return ((EditPane) mainWindow.tabbedPane.getSelectedComponent());

	}
	
	public String getNamingStyle(File file) {
		
		if(Boolean.parseBoolean(mainWindow.config.config.getProperty(mainWindow.config.NAMING_STYLE))) {
			return file.getPath();
		} else {
			return file.getName();
		}
		
	}
	
	// -----------------------------------------------------------------
	// menu / toolbar actions
	// -----------------------------------------------------------------

	// creates a new tab
	public void makeNewFile() {
		
		// create a tab
		mainWindow.tabbedPane.addTab(
				Localisation.write("files.default.filename"),
				null,
				new EditPane(mainWindow),
				Localisation.write("files.default.filename"));
		// focus the tab
		mainWindow.tabbedPane.setSelectedIndex(
				mainWindow.tabbedPane.getTabCount() - 1);
		// print the default filename info the statusbar
		getSelectedEditPane().statusBar.fileName.setText(
				Localisation.write("files.default.filename"));
		// set character count
		getSelectedEditPane().tabActions.updateCharCount();

	}

	// Creates a tab and loads a text file into it
	public void openFile() {
		
		// if dialog is accepted
		if (fileChooserOpen.showOpenDialog(mainWindow) == JFileChooser.APPROVE_OPTION) {
			
			// create a new tab
			mainWindow.tabbedPane.addTab(
					getNamingStyle(fileChooserOpen.getSelectedFile()),
					null, new EditPane(mainWindow),
					getNamingStyle(fileChooserOpen.getSelectedFile()));
			// select / focus tab
			mainWindow.tabbedPane.setSelectedIndex(mainWindow.tabbedPane
					.getTabCount() - 1);
			// save file information into a variable (File data type)
			getSelectedEditPane().editor.setCurrentFile(fileChooserOpen
					.getSelectedFile());
			// read text into the textarea
			getSelectedEditPane().editor.textArea.setText(ReadFile
					.read(fileChooserOpen.getSelectedFile().getPath()));
			// set the text cursor to the beginning
			getSelectedEditPane().editor.textArea.setCaretPosition(0);
			getSelectedEditPane().editor.textArea.requestFocus();
			// print the path / name into the statusbar
			getSelectedEditPane().statusBar.fileName.setText(
					getNamingStyle(fileChooserOpen.getSelectedFile()));
			// set character count
			getSelectedEditPane().tabActions.updateCharCount();

		}

	}

	// iterates through all the opened tabs and saves them
	public void saveAll() {
		
		// remember current tab
		int selectedTab = mainWindow.tabbedPane.getSelectedIndex();

		// iterate
		for (int i = 0; i < mainWindow.tabbedPane.getTabCount(); i++) {
			// focus the first tab
			mainWindow.tabbedPane.setSelectedIndex(i);
			// save
			getSelectedEditPane().tabActions.saveFile();
		}

		// return to selected tab
		mainWindow.tabbedPane.setSelectedIndex(selectedTab);

	}

	// quit the application
	public void closeApp() {
		
		// confirmation
		int i = JOptionPane.showConfirmDialog(mainWindow, Localisation
				.write("confirm.close.app.text"), Constants.APP_TITLE,
				JOptionPane.YES_NO_OPTION);
		// close if confirmed
		if (i == JOptionPane.YES_OPTION) {
			System.exit(0);
		}

	}

	// -----------------------------------------------------------------
	// properties

	// open properties -> application settings dialog
	public void openAppSettings() {
		
		// show the dialog
		mainWindow.appSettings.setVisible(true);

	}
	
	// -----------------------------------------------------------------
	// help

	// show the "about" dialog
	public void openAbout() {
		
		// show the dialog
		mainWindow.aboutDialog.setVisible(true);

	}

	// opens the app documentation
	public void openDocs() {

		// show the dialog
		mainWindow.docsDialog.setVisible(true);

	}

}
