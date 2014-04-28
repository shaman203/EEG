package gui;

public class Controller implements ILogViewer{
	
	private MainGUI gui;

	public Controller(MainGUI gui)
	{
		this.gui = gui;
		init();
	}
	
	private void init() {
		// TODO Auto-generated method stub
		
	}

	public String getNuStart() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getNuEnd() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getNuStep() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getGammaStart() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getGammaEnd() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getGammaStep() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setNuStart(String text) {
		// TODO Auto-generated method stub
		
	}

	public void setNuEnd(String text) {
		// TODO Auto-generated method stub
		
	}

	public void setNuStep(String text) {
		// TODO Auto-generated method stub
		
	}

	public void setGammaStart(String text) {
		// TODO Auto-generated method stub
		
	}

	public void setGammaEnd(String text) {
		// TODO Auto-generated method stub
		
	}

	public void setGammaStep(String text) {
		// TODO Auto-generated method stub
		
	}

	public void setCrossRatio(String text) {
		// TODO Auto-generated method stub
		
	}

	public String getCrossRatio() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void viewMessage(String msg) {
		// TODO Auto-generated method stub
		gui.logTextArea.append(msg);
	}

}
