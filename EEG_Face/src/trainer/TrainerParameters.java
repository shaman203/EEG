package trainer;

public class TrainerParameters {


	public String SVMOutFile;
	public String SvmType;
	
	
	public Double NuStart;
	public Double NuStep;
	public Double NuEnd;
	public Double GammaStart;
	public Double GammaStep;
	public Double GammaEnd;
	public Double crossvalidationRatio;
	public Double bestNu;
	public Double bestGamma;
	
	public TrainerParameters(TrainerParameters params) {
		this.crossvalidationRatio = params.crossvalidationRatio;
		this.GammaEnd = params.GammaEnd;
		this.GammaStart = params.GammaStart;
		this.GammaStep = params.GammaStep;
		this.NuEnd = params.NuEnd;
		this.NuStart = params.NuStart;
		this.NuStep = params.NuStep;
		this.SVMOutFile = params.SVMOutFile;
		this.SvmType = params.SvmType;
	}

	public String printBestParams() {
		return "gamma= "+bestGamma+" nu= "+bestNu;
	}
}
