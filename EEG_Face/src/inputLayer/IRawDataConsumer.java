package inputLayer;

import java.util.List;

public interface IRawDataConsumer {

	void consume(List<Measurement> measurements);
	
}
