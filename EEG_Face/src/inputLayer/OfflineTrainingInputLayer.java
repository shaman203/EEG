package inputLayer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Common.SvmInput;

public class OfflineTrainingInputLayer {


	private InputLayerParameters params;
	private ITrainingDataConsumer consumer;

	public OfflineTrainingInputLayer(InputLayerParameters params)
	{
		this.params = new InputLayerParameters(params);
	}


	public void setDataConsumer(ITrainingDataConsumer consumer)
	{
		this.consumer = consumer;
	}

	public void startAcquisition()
	{
		if(!params.parseDir)
			readFile(params.inputFilename);
		else
			System.out.println("Directory parsing not yet implemented!");
	}


	private void readFile(String filename)
	{
		try(BufferedReader br = new BufferedReader(new FileReader(filename))) {

			String line = br.readLine();

			Measurement m=null;
			while (line != null) {

				String[] elements = line.split(",");
				
				List<Double> input = parseElements(elements,elements.length-1);
				Double c = new Double(Integer.parseInt(elements[elements.length-1]));
				
				SvmInput<Double> sample = new SvmInput<Double>(input,c);
				consumer.consume(sample);

				line = br.readLine();	            
			}
			//String everything = sb.toString();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	private List<Double> parseElements(String[] elements, int size) {

		List<Double> list = new ArrayList<Double>();
		
		for(int i =0; i<size;i++)
		{
			Double d = Double.parseDouble(elements[i]);
			if(d != null)
			{
				list.add(d);
			}
		}
		return list;
	}



}
