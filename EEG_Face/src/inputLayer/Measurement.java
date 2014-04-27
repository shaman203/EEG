package inputLayer;

import java.util.ArrayList;
import java.util.List;

public class Measurement {

	public Double measurementLabel;
	public long startTimeStamp;
	public List<List<Double>> channels;
	
	
	public Measurement(int channelCount)
	{
		channels = new ArrayList<List<Double>>();
		for(int i=0;i<channelCount; i++)
		{
			channels.add(new ArrayList<Double>());
		}
	}
}
