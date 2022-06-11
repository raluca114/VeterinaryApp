package cabinet;

public class Inregistrare {
	
	private String nume_proprietar;
	private String adresa;
	private String telefon;
	private String nume_pacient;
	private int varsta;
	private String data_deparazitare;
	private String data_vaccinare;
	private String specie;
	private String rasa;
	
	Inregistrare(String nume_proprietar, String adresa, String telefon, String nume_pacient, int varsta, String data_deparazitare, String data_vaccinare, String specie,String rasa)
	{
		this.nume_proprietar=nume_proprietar;
		this.adresa=adresa;
		this.telefon=telefon;
		this.nume_pacient=nume_pacient;
		this.varsta=varsta;
		this.data_deparazitare=data_deparazitare;
		this.data_vaccinare=data_vaccinare;
		this.specie=specie;
		this.rasa=rasa;			
	}
	
	public String getNumeProprietar()
	{
		return nume_proprietar;
	}
	public String getAdresa()
	{
		return adresa;
	}
	public String getTelefon()
	{
		return telefon;
	}
	public String getNumePacient()
	{
		return nume_pacient;
	}
	public int getVarsta()
	{
		return varsta;
	}
	public String getDataDeparazitare()
	{
		return data_deparazitare;
	}
	public String getDataVaccinare()
	{
		return data_vaccinare;
	}
	public String getSpecie()
	{
		return specie;
	}
	public String getRasa()
	{
		return rasa;
	}
	

}
