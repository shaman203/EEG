package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MainGUI {

	JFrame guiFrame;

	private JPanel offlinePanel;
	private JPanel onlinePanel;
	public JTextArea logTextArea;
	private JButton selectModelLocationBtn;
	private JTextField modelFilenameField;
	private JCheckBox saveLogToFileChckBox;
	private JButton selectLogLocationBtn;
	private JTextField logFilenameField;


	private JButton offlineTrainSetupBtn;
	private JDialog offlineTrainSetupDialog;
	private JButton offlineTrainStartBtn;

	private JTextField NuStartField;
	private JTextField NuEndField;
	private JTextField NuStepField;
	private JTextField GammaStartField;
	private JTextField GammaEndField;
	private JTextField GammaStepField;
	private JTextField CrossvalidationRatio;
	private JButton offlineTrainSetupOkBtn;


	private JButton offlineClassifySetupBtn;
	private JDialog offlineClassifySetupDialog;
	private JButton offlineClassifyStartBtn;

	private Controller controller;

	public  MainGUI(Controller controller) {

		this.controller = controller;

		guiFrame = new JFrame();

		//make sure the program exits when the frame closes
		guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		guiFrame.setTitle("Example GUI");
		guiFrame.setSize(500,600);

		//This will center the JFrame in the middle of the screen
		guiFrame.setLocationRelativeTo(null);
		JPanel mainPanel = new JPanel();
		JTabbedPane panes = new JTabbedPane();

		offlinePanel = new JPanel();
		onlinePanel = new JPanel();

		panes.addTab("Offline", offlinePanel);
		panes.addTab("Online", onlinePanel);




		logTextArea = new JTextArea("Here will the events appear");
		selectModelLocationBtn = new JButton("Load SVM...");
		modelFilenameField = new JTextField("SVM model");

		saveLogToFileChckBox = new JCheckBox("Save log");
		selectLogLocationBtn = new JButton("Log location...");
		logFilenameField =new JTextField("Log file"); 

		offlineTrainSetupBtn = new JButton("Training setup...");
		offlineTrainSetupBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				offlineTrainSetupDialog.setLocationRelativeTo(null);
				offlineTrainSetupDialog.setVisible(true);

			}
		});
		offlineTrainStartBtn = new JButton("Train");

		offlineClassifySetupBtn = new JButton("Classifier setup...");
		offlineClassifyStartBtn = new JButton("Classify");

		GroupLayout offlineLayout = new GroupLayout(offlinePanel);
		offlinePanel.setLayout(offlineLayout);

		offlineLayout.setAutoCreateGaps(true);
		offlineLayout.setAutoCreateContainerGaps(true);

		offlineLayout.setHorizontalGroup(
				offlineLayout.createParallelGroup()				      
				.addGroup(offlineLayout.createSequentialGroup()
						.addComponent(modelFilenameField)
						.addComponent(selectModelLocationBtn))
						.addGroup(offlineLayout.createSequentialGroup()
								.addComponent(saveLogToFileChckBox)
								.addComponent(logFilenameField)
								.addComponent(selectLogLocationBtn))
								.addGroup(offlineLayout.createSequentialGroup()
										.addComponent(offlineTrainSetupBtn)
										.addComponent(offlineTrainStartBtn))
										.addGroup(offlineLayout.createSequentialGroup()
												.addComponent(offlineClassifySetupBtn)
												.addComponent(offlineClassifyStartBtn))
				);
		offlineLayout.setVerticalGroup(
				offlineLayout.createSequentialGroup()
				.addGroup(offlineLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(modelFilenameField)
						.addComponent(selectModelLocationBtn))
						.addGroup(offlineLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(saveLogToFileChckBox)
								.addComponent(logFilenameField)
								.addComponent(selectLogLocationBtn))
								.addGroup(offlineLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(offlineTrainSetupBtn)
										.addComponent(offlineTrainStartBtn))
										.addGroup(offlineLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
												.addComponent(offlineClassifySetupBtn)
												.addComponent(offlineClassifyStartBtn))
				);

		GroupLayout mainLayout = new GroupLayout(mainPanel);
		mainPanel.setLayout(mainLayout);

		mainLayout.setAutoCreateGaps(true);
		mainLayout.setAutoCreateContainerGaps(true);

		mainLayout.setHorizontalGroup(
				mainLayout.createParallelGroup()				      
				.addComponent(panes)
				.addComponent(logTextArea)
				);
		mainLayout.setVerticalGroup(
				mainLayout.createSequentialGroup()
				.addComponent(panes)
				.addComponent(logTextArea)
				);


		guiFrame.add(mainPanel);
		//make sure the JFrame is visible
		guiFrame.setVisible(true);


		this.offlineTrainSetupDialog = buildOfflineTrainSetupDialog();
		this.offlineClassifySetupDialog = buildOfflineClassifySetupDialog();
	}


	private JDialog buildOfflineTrainSetupDialog()
	{
		JDialog dialog = new JDialog();
		dialog.setTitle("Offline training setup");
		dialog.setSize(200, 270);

		JPanel dialogPanel = new JPanel();

		JLabel nuStartLabel = new JLabel("NuStart");
		NuStartField  = new JTextField(controller.getNuStart());
		JLabel nuEndLabel = new JLabel("NuEnd");
		NuEndField = new JTextField(controller.getNuEnd());
		JLabel nuStepLabel = new JLabel("NuStep");
		NuStepField = new JTextField(controller.getNuStep());

		JLabel gammaStartLabel = new JLabel("GammaStart");
		GammaStartField = new JTextField(controller.getGammaStart());
		JLabel gammaEndLabel = new JLabel("GammaEnd");
		GammaEndField = new JTextField(controller.getGammaEnd());
		JLabel gammaStepLabel = new JLabel("GammaStep");
		GammaStepField = new JTextField(controller.getGammaStep());
		JLabel crossvalidationRatioLabel = new JLabel("Cross-ratio");
		CrossvalidationRatio = new JTextField(controller.getCrossRatio());

		offlineTrainSetupOkBtn = new JButton("Save");
		offlineTrainSetupOkBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				controller.setNuStart(NuStartField.getText());
				controller.setNuEnd(NuEndField.getText());
				controller.setNuStep(NuStepField.getText());
				controller.setGammaStart(GammaStartField.getText());
				controller.setGammaEnd(GammaEndField.getText());
				controller.setGammaStep(GammaStepField.getText());
				controller.setCrossRatio(CrossvalidationRatio.getText());
			}
		});
		
		GroupLayout layout = new GroupLayout(dialogPanel);
		dialogPanel.setLayout(layout);

		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

		layout.setHorizontalGroup(
				layout.createParallelGroup()				      
				.addGroup(layout.createSequentialGroup()
						.addComponent(nuStartLabel)
						.addComponent(NuStartField))
						.addGroup(layout.createSequentialGroup()
								.addComponent(nuEndLabel)
								.addComponent(NuEndField))
								.addGroup(layout.createSequentialGroup()
										.addComponent(nuStepLabel)
										.addComponent(NuStepField))
										.addGroup(layout.createSequentialGroup()
												.addComponent(gammaStartLabel)
												.addComponent(GammaStartField))
												.addGroup(layout.createSequentialGroup()
														.addComponent(gammaEndLabel)
														.addComponent(GammaEndField))
														.addGroup(layout.createSequentialGroup()
																.addComponent(gammaStepLabel)
																.addComponent(GammaStepField))
																.addGroup(layout.createSequentialGroup()
																		.addComponent(crossvalidationRatioLabel)
																		.addComponent(CrossvalidationRatio))
																		.addComponent(offlineTrainSetupOkBtn)
				);
		layout.setVerticalGroup(
				layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(nuStartLabel)
						.addComponent(NuStartField))
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(nuEndLabel)
								.addComponent(NuEndField))
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(nuStepLabel)
										.addComponent(NuStepField))
										.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
												.addComponent(gammaStartLabel)
												.addComponent(GammaStartField))
												.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
														.addComponent(gammaEndLabel)
														.addComponent(GammaEndField))
														.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
																.addComponent(gammaStepLabel)
																.addComponent(GammaStepField))
																.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
																		.addComponent(crossvalidationRatioLabel)
																		.addComponent(CrossvalidationRatio))
																		.addComponent(offlineTrainSetupOkBtn)
				);


		dialog.setContentPane(dialogPanel);
		return dialog;
	}

	private JDialog buildOfflineClassifySetupDialog()
	{
		JDialog dialog = new JDialog();

		return dialog;
	}

}
