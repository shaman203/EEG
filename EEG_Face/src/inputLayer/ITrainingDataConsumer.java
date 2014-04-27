package inputLayer;

import Common.SvmInput;

public interface ITrainingDataConsumer {

	void consume(SvmInput<Double> sample);
	
}
