
package io.schupke.editor.dialog;

import java.awt.Frame;
import java.awt.event.ActionEvent;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import io.schupke.editor.Localisation;
import io.schupke.editor.MainWindow;
import io.schupke.editor.actions.AppActions;

public class AppSettings extends Settings {
	private static final long serialVersionUID = 1L;

	AppActions appActions;
	
	JLabel languageLabel;
	// panel to store the list
	String[] languageSelection = {
			Localisation.write("properties.app.languageSelection.czech"),
			Localisation.write("properties.app.languageSelection.english")};
	JComboBox<String> languages;

	JLabel fontSizeLabel;
	// panel to store the list
	Integer[] fontSizeSelection = {
			9,
			10,
			11,
			12,
			13,
			14,
			15
			};
	JComboBox<Integer> fontSize;
	
	JCheckBox lineWrapping;
	JCheckBox maximized;
	JCheckBox namingStyle; // either path or filename
	
	public AppSettings(Frame owner) {

		super(owner, Localisation.write("dialogue.menu.properties.app.title"));

		appActions = ((MainWindow)owner).appActions;
		
		// text label for the language selector
		languageLabel = new JLabel(Localisation.write("properties.app.languageSelection.label") + ":");
		languageLabel.setBounds(5, 5, 180, 30);
		
		// list of languages
		languages = new JComboBox<String>(languageSelection);
		languages.setSelectedItem(
				appActions.mainWindow.config.config.getProperty(appActions.mainWindow.config.LANGUAGE));
		// apply the scrollbar
		languages.setBounds(5, 40, 180, 40);

		// text label for the language selector
		fontSizeLabel = new JLabel(Localisation.write("properties.app.fontSize.label") + ":");
		fontSizeLabel.setBounds(5, 80, 180, 30);
		
		// list of fontSizes
		fontSize = new JComboBox<Integer>(fontSizeSelection);
		fontSize.setSelectedItem(
				appActions.mainWindow.config.config.getProperty(appActions.mainWindow.config.FONT_SIZE));
		// apply the scrollbar
		fontSize.setBounds(5, 115, 180, 40);
		
		// line wparring checkbox
		lineWrapping = new JCheckBox(
				Localisation.write("dialogue.menu.properties.app.lineWrapping"),
				Boolean.parseBoolean(appActions.mainWindow.config.config.getProperty(appActions.mainWindow.config.LINE_WRAPPING)));
		lineWrapping.setBounds(200, 5, 350, 30);

		// maximized checkbox
		maximized = new JCheckBox(
				Localisation.write("dialogue.menu.properties.app.maximized"),
				Boolean.parseBoolean(appActions.mainWindow.config.config.getProperty(appActions.mainWindow.config.MAXIMIZED)));
		maximized.setBounds(200, 40, 350, 30);
		
		// naming style checkbox
		namingStyle = new JCheckBox(
				Localisation.write("dialogue.menu.properties.app.namingStyle"),
				Boolean.parseBoolean(appActions.mainWindow.config.config.getProperty(appActions.mainWindow.config.NAMING_STYLE)));
		namingStyle.setBounds(200, 75, 350, 30);
		
		// try to render the window
		try {
			render();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// set the components to render
	void render() {

		mainPanel.add(languageLabel, null);
		mainPanel.add(languages, null);
		//mainPanel.add(fontSizeLabel, null);
		//mainPanel.add(fontSize, null);
		mainPanel.add(lineWrapping, null);
		mainPanel.add(maximized, null);
		mainPanel.add(namingStyle, null);
		
		// add all action listeners
		listeners();
		
	}

	void listeners() {
		
		buttonOK.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// preventing the null value
				if(languages.getSelectedItem() == null) {
					
					JOptionPane.showMessageDialog(null,
							Localisation.write("error.settings.noLanguageSelected.text"),
							Localisation.write("error.settings.noLanguageSelected.title"),
							JOptionPane.ERROR_MESSAGE);
				
				// save the selection
				} else {
					
					// 12 => (Integer)fontSize.getSelectedItem()
					appActions.mainWindow.config.save(
							(String)languages.getSelectedItem(),
							12,
							lineWrapping.isSelected(),
							namingStyle.isSelected(),
							maximized.isSelected());
					setVisible(false);
					
				}
				
			}
		});
		
	}

}
