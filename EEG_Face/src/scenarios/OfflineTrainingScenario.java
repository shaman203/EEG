package scenarios;

import trainer.Trainer;
import trainer.TrainerParameters;
import common.Logger;
import common.SvmInput;
import inputLayer.ITrainingDataConsumer;
import inputLayer.InputLayerParameters;
import inputLayer.OfflineTrainingInputLayer;

public class OfflineTrainingScenario implements  Runnable{

	OfflineTrainingInputLayer inputLayer;
	Trainer trainer;
	Logger logger;
	
	public OfflineTrainingScenario(InputLayerParameters inputParams, TrainerParameters trainerParams, Logger logger)
	{
		inputLayer = new OfflineTrainingInputLayer(inputParams);
		trainer = new Trainer(trainerParams, logger);
		this.logger = logger;		
		
		inputLayer.setDataConsumer(new ITrainingDataConsumer() {
			@Override
			public void consume(SvmInput<Double> sample) {
				trainer.addTrainingSample(sample);
			}
		});
	}

	@Override
	public void run() {
		inputLayer.startAcquisition();
		trainer.runTraining();
	}	
}
