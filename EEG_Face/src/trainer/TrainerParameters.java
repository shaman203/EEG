package trainer;

public class TrainerParameters {


	private String SVMOutFile;
	private String SvmKernelType;
	
	
	private Double NuStart;
	private Double NuStep;
	private Double NuEnd;
	private Double GammaStart;
	private Double GammaStep;
	private Double GammaEnd;
	private Double crossvalidationRatio;
	private Double bestNu;
	private Double bestGamma;
	
	public TrainerParameters(TrainerParameters params) {
		this.crossvalidationRatio = params.crossvalidationRatio;
		this.GammaEnd = params.GammaEnd;
		this.GammaStart = params.GammaStart;
		this.GammaStep = params.GammaStep;
		this.NuEnd = params.NuEnd;
		this.NuStart = params.NuStart;
		this.NuStep = params.NuStep;
		this.SVMOutFile = params.SVMOutFile;
		this.SvmKernelType = params.SvmKernelType;
	}

	

	public TrainerParameters() {
		this.crossvalidationRatio = 0.2;
		this.GammaEnd =  0.0083129;
		this.GammaStart = 0.0083129;
		this.GammaStep = 0.001;
		this.NuEnd = 0.01;
		this.NuStart = 0.01;
		this.NuStep = 0.01;
		this.SVMOutFile = "svm_model.out";
		this.SvmKernelType = "RBF";
	}

	public String printBestParams() {
		return "gamma= "+bestGamma+" nu= "+bestNu;
	}
	
	public String getSVMOutFile() {
		return SVMOutFile;
	}

	public void setSVMOutFile(String SVMOutFile) {
		this.SVMOutFile = SVMOutFile;
	}

	public String getSvmKernelType() {
		return SvmKernelType;
	}

	public void setSvmKernelType(String svmKernelType) {
		SvmKernelType = svmKernelType;
	}

	public Double getNuStart() {
		return NuStart;
	}

	public Double getNuStep() {
		return NuStep;
	}

	public Double getNuEnd() {
		return NuEnd;
	}

	public Double getGammaStart() {
		return GammaStart;
	}

	public Double getGammaStep() {
		return GammaStep;
	}

	public Double getGammaEnd() {
		return GammaEnd;
	}

	public Double getCrossvalidationRatio() {
		return crossvalidationRatio;
	}


	public Double getBestNu() {
		return bestNu;
	}

	public void setBestNu(Double bestNu) {
		this.bestNu = bestNu;
	}

	public Double getBestGamma() {
		return bestGamma;
	}

	public void setBestGamma(Double bestGamma) {
		this.bestGamma = bestGamma;
	}



	public void setCrossParams(Double nuStart, Double nuEnd, Double nuStep,
			Double gammaStart, Double gammaEnd, Double gammaStep,
			Double crossRatio) {
		
		this.NuStart = nuStart;
		this.NuEnd  = nuEnd;
		this.NuStep = nuStep;
		this.GammaStart = gammaStart;
		this.GammaEnd  = gammaEnd;
		this.GammaStep = gammaStep;
		this.crossvalidationRatio =crossRatio;
		
	}
}
