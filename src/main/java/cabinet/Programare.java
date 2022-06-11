package cabinet;

public class Programare {
	
	private String nume_pacient;
	private String data;
	private String ora;
	
	Programare(String nume_pacient, String data, String ora)
	{
		this.nume_pacient=nume_pacient;
		this.data=data;
		this.ora=ora;
	}
	
	public String getNumePacient()
	{
		return nume_pacient;
	}
	public String getData()
	{
		return data;
	}
	public String getOra()
	{
		return ora;
	}

}
