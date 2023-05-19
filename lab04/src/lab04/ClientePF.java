package lab04;
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
	
	@Override
	public double calculaScore() {
		double score = super.calculaScore();
		score = score*this.listaVeiculos.size();
		int anoNascimento = this.dataNascimento.getYear();
		if(anoNascimento>=18 && anoNascimento<30) {
			score = score*CalcSeguro.FATOR_18_30.getValor();
		}
		else if(anoNascimento>=30 && anoNascimento<60) {
			score = score*CalcSeguro.FATOR_30_60.getValor();
		}
		else{
			score = score*CalcSeguro.FATOR_60_90.getValor();
		}
		return score;
	}

	@Override
	public String toString() {
		return "ClientePF [cpf=" + cpf + ", genero=" + genero + ", dataLicenca=" + dataLicenca + ", educacao="
				+ educacao + ", dataNascimento=" + dataNascimento + ", classeEconomica=" + classeEconomica + ", nome="
				+ nome + ", endereco=" + endereco + ", listaVeiculos=" + listaVeiculos + "]";
	}
	
}
