package GUI;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MainGUI {

	private JPanel predictPanel;
	private JPanel trainPanel;
	private JTextArea predictLogTxtArea;
	private JButton selectModelLocationBtn;
	private JTextField modelFilenameField;
	private JCheckBox saveLogToFileChckBox;
	private JButton selectLogLocationBtn;
	private JTextField logFilenameField;
	private JButton connectBtn;
	private JButton disconnectBtn;

	public  MainGUI() {
		JFrame guiFrame = new JFrame();

		//make sure the program exits when the frame closes
		guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		guiFrame.setTitle("Example GUI");
		guiFrame.setSize(300,250);

		//This will center the JFrame in the middle of the screen
		guiFrame.setLocationRelativeTo(null);

		JTabbedPane panes = new JTabbedPane();

		predictPanel = new JPanel();
		trainPanel = new JPanel();

		panes.addTab("Predict", predictPanel);
		panes.addTab("Train", trainPanel);


		GroupLayout predictLayout = new GroupLayout(predictPanel);
		predictPanel.setLayout(predictLayout);

		predictLayout.setAutoCreateGaps(true);
		predictLayout.setAutoCreateContainerGaps(true);

		predictLogTxtArea = new JTextArea("Here will the events appear");
		selectModelLocationBtn = new JButton("Load SVM...");
		modelFilenameField = new JTextField("SVM model");

		saveLogToFileChckBox = new JCheckBox("Save log");
		selectLogLocationBtn = new JButton("Log location...");
		logFilenameField =new JTextField("Log file"); 

		connectBtn = new JButton("Connect");
		disconnectBtn = new JButton("Disconnect");

		predictLayout.setHorizontalGroup(
				predictLayout.createParallelGroup()				      
				.addComponent(predictLogTxtArea)
				.addGroup(predictLayout.createSequentialGroup()
						.addComponent(modelFilenameField)
						.addComponent(selectModelLocationBtn))
				.addGroup(predictLayout.createSequentialGroup()
						.addComponent(saveLogToFileChckBox)
						.addComponent(logFilenameField)
						.addComponent(selectLogLocationBtn))
				.addGroup(predictLayout.createSequentialGroup()
						.addComponent(connectBtn)
						.addComponent(disconnectBtn))
				);
		predictLayout.setVerticalGroup(
				predictLayout.createSequentialGroup()
				.addComponent(predictLogTxtArea)
				.addGroup(predictLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(modelFilenameField)
						.addComponent(selectModelLocationBtn))
				.addGroup(predictLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(saveLogToFileChckBox)
						.addComponent(logFilenameField)
						.addComponent(selectLogLocationBtn))
				.addGroup(predictLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(connectBtn)
						.addComponent(disconnectBtn))
				);

		guiFrame.add(panes);
		//make sure the JFrame is visible
		guiFrame.setVisible(true);
	}

}
