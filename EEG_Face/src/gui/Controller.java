package gui;

import scenarios.OfflineTrainingScenario;
import utils.Configuration;

import common.Logger;

public class Controller implements ILogViewer{

	private MainGUI gui;
	private Configuration config;

	public Controller()
	{
		this.config = new Configuration();
		init();
	}
	
	public void setGui(MainGUI gui)
	{
		this.gui = gui;
	}

	private void init() {
		// TODO Auto-generated method stub

	}

	public String getNuStart() {
		return config.getTrainerParameters().getNuStart().toString();
	}

	public String getNuEnd() {
		return config.getTrainerParameters().getNuEnd().toString();
	}

	public String getNuStep() {
		return config.getTrainerParameters().getNuStep().toString();
	}

	public String getGammaStart() {
		return config.getTrainerParameters().getGammaStart().toString();
	}

	public String getGammaEnd() {
		return config.getTrainerParameters().getGammaEnd().toString();
	}

	public String getGammaStep() {
		return config.getTrainerParameters().getGammaStep().toString();
	}

	public String getCrossRatio() {
		return config.getTrainerParameters().getCrossvalidationRatio().toString();
	}

	@Override
	public void viewMessage(String msg) {
		// TODO Auto-generated method stub
		gui.logTextArea.append(msg+"\n");
	}

	public void startOfflineTrain() {
		// TODO Auto-generated method stub
		Logger logger = new Logger(config.getLogFilename(), this);
		OfflineTrainingScenario scenario = new OfflineTrainingScenario(config.getInputLayerParameters(),config.getTrainerParameters(), logger);
		Thread t = new Thread(scenario);
		gui.offlineTrainStartBtn.setEnabled(false);
		t.start();
	}

	public boolean validateParameters() {
		if(config.isValid())
			return true;
		else
			viewMessage(config.getErrorMsg());
		return false;
	}

	public void setCrossvalidationParams(String nuStart, String nuEnd,
			String nuStep, String gammaStart, String gammaEnd, String gammaStep, String crossRatio) {
		
		try{
			Double NuStart = Double.parseDouble(nuStart);
			Double NuEnd = Double.parseDouble(nuEnd);
			Double NuStep = Double.parseDouble(nuStep);
			Double GammaStart = Double.parseDouble(gammaStart);
			Double GammaEnd = Double.parseDouble(gammaEnd);
			Double GammaStep = Double.parseDouble(gammaStep);
			Double CrossRatio = Double.parseDouble(crossRatio);
			config.getTrainerParameters().setCrossParams(NuStart,NuEnd,NuStep,GammaStart,GammaEnd,GammaStep,CrossRatio);
		}
		catch(NumberFormatException e)
		{
			viewMessage("Invalid number format");
		}
		
	}

}
