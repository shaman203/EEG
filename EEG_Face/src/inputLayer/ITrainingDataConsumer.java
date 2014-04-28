package inputLayer;

import common.SvmInput;

public interface ITrainingDataConsumer {

	void consume(SvmInput<Double> sample);
	
}
