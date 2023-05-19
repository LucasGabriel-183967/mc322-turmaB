package lab04;
import java.time.LocalDate;

public class ClientePJ extends Cliente {
	String cnpj;
	LocalDate dataFundacao;
	int quantFuncionarios;
	
	
	public ClientePJ(String nome, String endereco, String cnpj, LocalDate dataFundacao, int quantFuncionarios) {
		super(nome, endereco); // Note a ausencia de listaVeiculos (a lista eh sempre inicializada como vazia por padrao)
		// Normaliza o cnpj.
		cnpj = cnpj.replaceAll("\\D", "");
		this.cnpj = cnpj;
		this.dataFundacao = dataFundacao;
		this.quantFuncionarios = quantFuncionarios;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public LocalDate getDataFundacao() {
		return dataFundacao;
	}

	public void setDataFundacao(LocalDate dataFundacao) {
		this.dataFundacao = dataFundacao;
	}
	
	public static String getClienteClass() {
		return "PJ";
	}
	
	public int getQuantFunctionarios() {
		return quantFuncionarios;
	}

	public void setQuantFunctionarios(int quantFunctionarios) {
		this.quantFuncionarios = quantFunctionarios;
	}
	
	@Override
	public double calculaScore() {
		double score = super.calculaScore();
		double funcionarios = this.quantFuncionarios;
		double fatorFuncionarios = funcionarios/100.0;
		score = score*(1+fatorFuncionarios)*this.listaVeiculos.size();
		return score;
	}

	@Override
	public String toString() {
		return "ClientePJ [cnpj=" + cnpj + ", dataFundacao=" + dataFundacao + ", quantFunctionarios="
				+ quantFuncionarios + ", nome=" + nome + ", endereco=" + endereco + ", listaVeiculos=" + listaVeiculos
				+ ", listaSinistros=" + listaSinistros + ", valorSeguro=" + valorSeguro + "]";
	}
}
