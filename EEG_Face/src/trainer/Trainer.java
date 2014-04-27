package trainer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;

import libsvm.svm;
import libsvm.svm_model;
import libsvm.svm_node;
import libsvm.svm_parameter;
import libsvm.svm_print_interface;
import libsvm.svm_problem;
import Common.SvmInput;

public class Trainer {

	private svm_parameter param;		// set by parse_command_line
	private svm_problem prob;		// set by read_problem
	private svm_model model;
	private String model_file_name;		// set by parse_command_line
	private String error_msg;
	private int cross_validation;

	private TrainerParameters params;
	private List<SvmInput<Double>> trainingSamples;

	public Trainer(TrainerParameters params)
	{
		this.params = new TrainerParameters(params);
		init();
	}

	private void do_cross_validation()
	{
		/*int i;
		int total_correct = 0;
		double total_error = 0;
		double sumv = 0, sumy = 0, sumvv = 0, sumyy = 0, sumvy = 0;
		double[] target = new double[prob.l];

		svm.svm_cross_validation(prob,param,nr_fold,target);
		if(param.svm_type == svm_parameter.EPSILON_SVR ||
				param.svm_type == svm_parameter.NU_SVR)
		{
			for(i=0;i<prob.l;i++)
			{
				double y = prob.y[i];
				double v = target[i];
				total_error += (v-y)*(v-y);
				sumv += v;
				sumy += y;
				sumvv += v*v;
				sumyy += y*y;
				sumvy += v*y;
			}
			System.out.print("Cross Validation Mean squared error = "+total_error/prob.l+"\n");
			System.out.print("Cross Validation Squared correlation coefficient = "+
					((prob.l*sumvy-sumv*sumy)*(prob.l*sumvy-sumv*sumy))/
					((prob.l*sumvv-sumv*sumv)*(prob.l*sumyy-sumy*sumy))+"\n"
					);
		}
		else
		{
			for(i=0;i<prob.l;i++)
				if(target[i] == prob.y[i])
					++total_correct;
			System.out.print("Cross Validation Accuracy = "+100.0*total_correct/prob.l+"%\n");
		}*/
	}
	
	public void addTrainingSamples(List<SvmInput<Double>> samples)
	{
		this.trainingSamples.addAll(samples);
	}

	public void train() throws IOException
	{
		init();
		transfSamplesToSVMFormat();
		error_msg = svm.svm_check_parameter(prob,param);

		if(error_msg != null)
		{
			System.err.print("ERROR: "+error_msg+"\n");
			System.exit(1);
		}

		if(cross_validation != 0)
		{
			do_cross_validation();
		}
		else
		{
			model = svm.svm_train(prob,param);
			svm.svm_save_model(model_file_name,model);
		}
	}


	private void init()
	{
		svm_print_interface print_func = svm_print_null;	// default printing to stdout

		param = new svm_parameter();
		// default values
		param.svm_type = svm_parameter.NU_SVC;
		param.kernel_type = svm_parameter.RBF;
		param.degree = 3;
		param.gamma = 0.03;	// 1/num_features
		param.coef0 = 0;
		param.nu = 0.5;
		param.cache_size = 100;
		param.C = 1;
		param.eps = 1e-3;
		param.p = 0.1;
		param.shrinking = 1;
		param.probability = 0;
		param.nr_weight = 0;
		param.weight_label = new int[0];
		param.weight = new double[0];
		cross_validation = 0;

		this.model_file_name = this.params.SVMOutFile;
		svm.svm_set_print_string_function(print_func);

	}

	private void transfSamplesToSVMFormat() throws IOException
	{

		Vector<Double> vy = new Vector<Double>();
		Vector<svm_node[]> vx = new Vector<svm_node[]>();
		int max_index = 0;

		for(SvmInput<Double> sample : this.trainingSamples)
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

		if(param.gamma == 0 && max_index > 0)
			param.gamma = 1.0/max_index;

		if(param.kernel_type == svm_parameter.PRECOMPUTED)
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
