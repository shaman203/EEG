package utils;

import preprocess.PreprocessParameters;
import trainer.TrainerParameters;
import inputLayer.InputLayerParameters;
import classifier.ClassifierParameters;

public class Configuration {

	private String configFile;
	
	public Configuration(String configFile)
	{
		this.configFile = configFile;
	}
	
	
	public InputLayerParameters getInputLayerParameters()
	{
		return null;
	}
	
	public PreprocessParameters getPreprocessParameters()
	{
		return null;
	}
	
	public TrainerParameters getTrainerParameters()
	{
		return null;
	}
	
	public ClassifierParameters getClassifierParameters()
	{
		return null;
	}
	
	
	
	
}
