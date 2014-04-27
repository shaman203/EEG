package Common;

import java.util.List;

public class SvmInput<T> {

	private boolean isTrainingSample;
	public T sampleClass;
	public List<T> input;
	
	
	public SvmInput(List<T> input){
		this.isTrainingSample = false;
		this.input = input;
	}
	
	public SvmInput(List<T> input, T sampleClass)
	{
		this.isTrainingSample = true;
		this.sampleClass = sampleClass;
		this.input = input;
	}
	
	
	public boolean isTrainingSample(){
		return this.isTrainingSample;
	}
	
}
