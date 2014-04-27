package classifier;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import libsvm.svm;
import libsvm.svm_model;
import libsvm.svm_node;

public class Classifier {


	private ClassifierParameters params;
	private svm_model model;

	public Classifier(ClassifierParameters params) throws IOException
	{
		this.params = new ClassifierParameters(params);
		init();
	}

	public ClassifierParameters getClassifierParams() 
	{
		return new ClassifierParameters(params);
	}

	public boolean setClassifierParams(ClassifierParameters params) {
		this.params = new ClassifierParameters(params);
		try {
			init();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}

	private void init() throws IOException
	{
		model = svm.svm_load_model(params.modelFile);
	}

	public List<Double> predict(List<List<Double>> eventList)
	{
		List<Double> predictionList = new ArrayList<Double>();
		for(List<Double> event : eventList)
		{
			int m = event.size();
			svm_node[] x = new svm_node[m];
			for(int j=0;j<m;j++)
			{
				x[j] = new svm_node();
				x[j].index = j;
				x[j].value = event.get(j);
			}
			predictionList.add(svm.svm_predict(model,x));
		}
		return predictionList;
	}
}
