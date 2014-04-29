package trainer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import libsvm.svm;
import libsvm.svm_model;
import libsvm.svm_node;
import libsvm.svm_parameter;
import libsvm.svm_print_interface;
import libsvm.svm_problem;

import common.Logger;
import common.SvmInput;

public class Trainer {

	private svm_parameter libSVMParams;		// set by parse_command_line
	private svm_problem prob;		// set by read_problem
	private svm_model model;
	private String error_msg;

	private TrainerParameters params;
	private TrainerParameters bestParams;
	private Double crossRatio;

	private Map<Double,List<SvmInput<Double>>> allSamples;

	private Logger logger;

	public Trainer(TrainerParameters params, Logger logger)
	{
		this.params = new TrainerParameters(params);
		this.logger = logger;
		this.allSamples = new HashMap<Double,List<SvmInput<Double>>>();
		init();
	}

	public void runTraining()
	{
		List<SvmInput<Double>> trainingSamples = new ArrayList<SvmInput<Double>>();
		List<SvmInput<Double>> crossSamples =  new ArrayList<SvmInput<Double>>(); 

		this.crossRatio = 0.0;

		selectTrainingCrossSamples(trainingSamples,crossSamples);
		logger.log("Starting cross-validation....");
		for(double currentGamma = this.params.GammaStart;currentGamma<=this.params.GammaEnd;currentGamma += this.params.GammaStep)
		{
			for(double currentNu = this.params.NuStart;currentNu<=this.params.NuEnd;currentNu += this.params.NuStep)
			{
				svm_model m = train(trainingSamples,currentGamma,currentNu);
				Double ratio = evaluate(m,crossSamples);
				if(ratio > this.crossRatio)
				{
					this.crossRatio = ratio;
					this.params.bestGamma = currentGamma;
					this.params.bestNu = currentNu;
					try {
						svm.svm_save_model(this.params.SVMOutFile,m);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

		}
		logger.log("Crossvalidation ended: "+this.crossRatio+" accuracy");
		logger.log(this.params.printBestParams());
		logger.log("SVM saved to "+this.params.SVMOutFile);
	}

	private Double evaluate(svm_model model, List<SvmInput<Double>> crossSamples) {

		Double total = 0.0;
		Double correct = 0.0;

		for(SvmInput<Double> sample : crossSamples)
		{
			int m = sample.input.size();
			svm_node[] x = new svm_node[m];
			for(int j=0;j<m;j++)
			{
				x[j] = new svm_node();
				x[j].index = j;
				x[j].value = sample.input.get(j);
			}
			Double prediction = svm.svm_predict(model,x);
			if(prediction.equals(sample.sampleClass))
			{
				correct++;
			}
			total++;
		}

		return correct/total;
	}

	private void selectTrainingCrossSamples(
			List<SvmInput<Double>> trainingSamples,
			List<SvmInput<Double>> crossSamples) {

		for(Map.Entry<Double,List<SvmInput<Double>>> c : allSamples.entrySet())
		{
			List<SvmInput<Double>> l = c.getValue();
			int endIndex = (int)Math.round(l.size()*(1-params.crossvalidationRatio));
			trainingSamples.addAll(l.subList(0, endIndex));
			crossSamples.addAll(l.subList(endIndex,l.size()));
		}


	}

	public void addTrainingSamples(List<SvmInput<Double>> samples)
	{	
		for(SvmInput<Double> sample : samples)
		{
			this.addTrainingSample(sample);
		}
	}

	public void addTrainingSample(SvmInput<Double> sample)
	{	
		if(!allSamples.containsKey(sample.sampleClass))
		{
			List<SvmInput<Double>> l = new ArrayList<SvmInput<Double>>();
			l.add(sample);
			allSamples.put(sample.sampleClass, l);
		}
		else
		{
			allSamples.get(sample.sampleClass).add(sample);
		}

	}

	private svm_model  train(List<SvmInput<Double>> samples, double currentGamma, double currentNu)
	{
		init(currentGamma,currentNu);
		transfSamplesToSVMFormat(samples);
		error_msg = svm.svm_check_parameter(prob,libSVMParams);

		if(error_msg != null)
		{
			System.err.print("ERROR: "+error_msg+"\n");
			System.exit(1);
		}

		model = svm.svm_train(prob,libSVMParams);
		return model;
	}


	private void init(double currentGamma, double currentNu) {
		init();
		libSVMParams.gamma = currentGamma;
		libSVMParams.nu = currentNu;
	}

	private void init()
	{
		svm_print_interface print_func = svm_print_null;	// default printing to stdout

		libSVMParams = new svm_parameter();
		// default values
		libSVMParams.svm_type = svm_parameter.NU_SVC;
		libSVMParams.kernel_type = svm_parameter.RBF;
		libSVMParams.degree = 3;
		libSVMParams.gamma = 0.03;	// 1/num_features
		libSVMParams.coef0 = 0;
		libSVMParams.nu = 0.5;
		libSVMParams.cache_size = 100;
		libSVMParams.C = 1;
		libSVMParams.eps = 1e-3;
		libSVMParams.p = 0.1;
		libSVMParams.shrinking = 1;
		libSVMParams.probability = 0;
		libSVMParams.nr_weight = 0;
		libSVMParams.weight_label = new int[0];
		libSVMParams.weight = new double[0];

		svm.svm_set_print_string_function(print_func);

	}

	private void transfSamplesToSVMFormat(List<SvmInput<Double>> samples)
	{

		Vector<Double> vy = new Vector<Double>();
		Vector<svm_node[]> vx = new Vector<svm_node[]>();
		int max_index = 0;

		for(SvmInput<Double> sample : samples)
		{
			if(sample.isTrainingSample())
			{
				vy.addElement(sample.sampleClass);
				int m = sample.input.size();
				svm_node[] x = new svm_node[m];
				for(int j=0;j<m;j++)
				{
					x[j] = new svm_node();
					x[j].index = j;
					x[j].value = sample.input.get(j);
				}
				if(m>0) max_index = Math.max(max_index, x[m-1].index);
				vx.addElement(x);
			}
		}

		prob = new svm_problem();
		prob.l = vy.size();
		prob.x = new svm_node[prob.l][];
		for(int i=0;i<prob.l;i++)
			prob.x[i] = vx.elementAt(i);
		prob.y = new double[prob.l];
		for(int i=0;i<prob.l;i++)
			prob.y[i] = vy.elementAt(i);

		if(libSVMParams.gamma == 0 && max_index > 0)
			libSVMParams.gamma = 1.0/max_index;

		if(libSVMParams.kernel_type == svm_parameter.PRECOMPUTED)
			for(int i=0;i<prob.l;i++)
			{
				if (prob.x[i][0].index != 0)
				{
					System.err.print("Wrong kernel matrix: first column must be 0:sample_serial_number\n");
					System.exit(1);
				}
				if ((int)prob.x[i][0].value <= 0 || (int)prob.x[i][0].value > max_index)
				{
					System.err.print("Wrong input format: sample_serial_number out of range\n");
					System.exit(1);
				}
			}
	}

	private static svm_print_interface svm_print_null = new svm_print_interface()
	{
		public void print(String s) {}
	};

}
