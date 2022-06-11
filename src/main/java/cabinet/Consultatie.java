package cabinet;

public class Consultatie {

	private String medic;
	private String diagnostic;
	private String interventii;
	private String tratament;
	private int cost_consultatie;
	
	Consultatie(String medic, String diagnostic, String interventii, String tratament, int cost_consultatie)
	{
		this.medic=medic;
		this.diagnostic=diagnostic;
		this.interventii=interventii;
		this.tratament=tratament;
		this.cost_consultatie=cost_consultatie;
	}
	
	public String getMedic()
	{
		return medic;
	}
	public String getDiagnostic()
	{
		return diagnostic;
	}
	public String getInterventii()
	{
		return interventii;
	}
	public String getTratament()
	{
		return tratament;
	}
	public int getCostConsultatie()
	{
		return cost_consultatie;
	}
	
}
