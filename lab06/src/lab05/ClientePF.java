package lab05;
import java.time.LocalDate;
import java.util.ArrayList;

public class ClientePF extends Cliente {
	final String cpf;
	String genero;
	String educacao;
	LocalDate dataNascimento;
	LocalDate dataLicenca;
	String classeEconomica;
	ArrayList<Veiculo> listaVeiculos;
	
	public ClientePF(String nome, String telefone, String endereco, String email, String cpf, String genero,
			String educacao, LocalDate dataNascimento, LocalDate dataLicenca, String classeEconomica) {
		super(nome, telefone, endereco, email);
		this.cpf = cpf;
		this.genero = genero;
		this.educacao = educacao;
		this.dataNascimento = dataNascimento;
		this.dataLicenca = dataLicenca;
		this.classeEconomica = classeEconomica;
		this.listaVeiculos = new ArrayList<Veiculo>();
	}
	
	public boolean transferirVeiculo(ClientePF clienteDoador, ClientePF clienteRecebedor) {
		return true;
	}
	
	public boolean cadastrarVeiculo(Veiculo veiculo) {
		for(int i=0; i<listaVeiculos.size(); i++) {
			if(listaVeiculos.get(i).equals(veiculo)) {
				return false;
			}
		}
		
		listaVeiculos.add(veiculo);
		return true;
	}
	
	public boolean removerVeiculo(Veiculo veiculo) {
		for(int i=0; i<listaVeiculos.size(); i++) {
			if(listaVeiculos.get(i).equals(veiculo)) {
				listaVeiculos.remove(i);
				return true;
			}
		}
		
		return false;
	}

	public int getIdade() {
		return this.dataNascimento.getYear();
	}
	
	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
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

	public LocalDate getDataLicenca() {
		return dataLicenca;
	}

	public void setDataLicenca(LocalDate dataLicenca) {
		this.dataLicenca = dataLicenca;
	}

	public String getClasseEconomica() {
		return classeEconomica;
	}

	public void setClasseEconomica(String classeEconomica) {
		this.classeEconomica = classeEconomica;
	}

	public ArrayList<Veiculo> getListaVeiculos() {
		return listaVeiculos;
	}

	public String getCpf() {
		return cpf;
	}

	@Override
	public String toString() {
		return "ClientePF [nome=" + nome + ", cpf=" + cpf + ", genero=" + genero + ", educacao=" + educacao + ", dataNascimento="
				+ dataNascimento + ", dataLicenca=" + dataLicenca + ", classeEconomica=" + classeEconomica
				+ ", listaVeiculos=" + listaVeiculos + ", telefone=" + telefone + ", endereco="
				+ endereco + ", email=" + email + "]";
	}

}
