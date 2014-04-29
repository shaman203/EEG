package inputLayer;

public class InputLayerParameters {

	
	public int SamplingRate;
	public int channelCount;
	public String inputFilename;
	public String inputDirectory;
	public boolean parseDir;
	
	
	public InputLayerParameters(InputLayerParameters params) {

		SamplingRate = params.SamplingRate;
		channelCount = params.channelCount;
		inputFilename = params.inputFilename;
		inputDirectory = params.inputDirectory;
		parseDir = params.parseDir;
	}


	public InputLayerParameters() {
		
		SamplingRate = 200;
		channelCount = 14;
		inputFilename = "data/train.in";
		inputDirectory = null;
		parseDir = false;
	}
	
	
	public int getSamplingRate() {
		return SamplingRate;
	}


	public void setSamplingRate(String samplingRate) {
		SamplingRate = Integer.parseInt(samplingRate);
	}


	public int getChannelCount() {
		return channelCount;
	}


	public void setChannelCount(String channelCount) {
		this.channelCount = Integer.parseInt(channelCount);
	}


	public String getInputFilename() {
		return inputFilename;
	}


	public void setInputFilename(String inputFilename) {
		this.inputFilename = inputFilename;
	}


	public String getInputDirectory() {
		return inputDirectory;
	}


	public void setInputDirectory(String inputDirectory) {
		this.inputDirectory = inputDirectory;
	}


	public boolean isParseDir() {
		return parseDir;
	}


	public void setParseDir(boolean parseDir) {
		this.parseDir = parseDir;
	}
}
