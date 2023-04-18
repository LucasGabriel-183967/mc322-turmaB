package lab03;
import java.time.LocalDate;

public class ClientePF extends Cliente {
	String cpf;
	String genero;
	LocalDate dataLicenca;
	String educacao;
	LocalDate dataNascimento;
	String classeEconomica;

	public ClientePF(String nome, String endereco, String cpf, String genero, LocalDate dataLicenca, String educacao,
			LocalDate dataNascimento, String classeEconomica) {
		super(nome, endereco); // Note a ausencia de listaVeiculos (a lista eh sempre inicializada como vazia por padrao)
		// Normaliza o cpf.
		cpf = cpf.replaceAll("\\D", "");
		this.cpf = cpf;
		this.genero = genero;
		this.dataLicenca = dataLicenca;
		this.educacao = educacao;
		this.dataNascimento = dataNascimento;
		this.classeEconomica = classeEconomica;
	}
	
	public String getCpf() {
		return cpf;
	}



	public void setCpf(String cpf) {
		this.cpf = cpf;
	}



	public String getGenero() {
		return genero;
	}



	public void setGenero(String genero) {
		this.genero = genero;
	}



	public LocalDate getDataLicenca() {
		return dataLicenca;
	}



	public void setDataLicenca(LocalDate dataLicenca) {
		this.dataLicenca = dataLicenca;
	}



	public String getEducacao() {
		return educacao;
	}



	public void setEducacao(String educacao) {
		this.educacao = educacao;
	}



	public LocalDate getDataNascimento() {
		return dataNascimento;
	}



	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}



	public String getClasseEconomica() {
		return classeEconomica;
	}



	public void setClasseEconomica(String classeEconomica) {
		this.classeEconomica = classeEconomica;
	}



	private boolean verifyingDigitsCPF(String cpf) {
		/*
		Source: https://www.macoratti.net/alg_cpf.htm 
		*/
		int d1=0, d2=0;
		String digits;
		for(int i=0; i<9; i++) {
			d1+=Character.getNumericValue(cpf.charAt(i))*(10-i);
		}
		for(int i=0; i<10; i++) {
			d2+=Character.getNumericValue(cpf.charAt(i))*(11-i);
		}
		d1 = d1%11; d2 = d2%11;
		if(d1<2) {
			d1 = 0;
		}
		else {
			d1 = 11-d1;
		}
		
		if(d2<2) {
			d2 = 0;
		}
		else {
			d2 = 11-d2;
		}
		
		digits = Integer.toString(d1)+Integer.toString(d2);
		return digits.equals(this.cpf.substring(9)); 
	}
	
	public boolean validarCPF(String cpf) {
		/*
		This method does the following:
		-> Removes all non-digit characters from the cpf string (the result is 
		refered here as "normalized cpf")
		-> Verify is the normalized cpf has 11 digits
		-> Verify if the cpf is all made by equal digits
		*/
		String normalizedCpf = cpf.replaceAll("\\D", "");
		if(normalizedCpf.length()!=11 | !normalizedCpf.equals(this.cpf) | equalDigits(normalizedCpf) | !verifyingDigitsCPF(normalizedCpf)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "ClientePF [cpf=" + cpf + ", genero=" + genero + ", dataLicenca=" + dataLicenca + ", educacao="
				+ educacao + ", dataNascimento=" + dataNascimento + ", classeEconomica=" + classeEconomica + ", nome="
				+ nome + ", endereco=" + endereco + ", listaVeiculos=" + listaVeiculos + "]";
	}
	
}
