
import java.time.LocalDate;
import java.util.ArrayList;

public class Condutor {
	final String cpf;
	String nome;
	String telefone;
	String endereco;
	String email;
	LocalDate dataNascimento;
	LocalDate dataLicenca;
	ArrayList<Sinistro> listaSinistros;
	
	public Condutor(String nome, String telefone, String endereco,  String email, String cpf, LocalDate dataNascimento, LocalDate dataLicenca) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.telefone = telefone;
		this.endereco = endereco;
		this.email = email;
		this.dataNascimento = dataNascimento;
		this.dataLicenca = dataLicenca;
		this.listaSinistros = new ArrayList<Sinistro>();
	}

	public boolean adicionarSinistro(Sinistro sinistro) {
		for(int i=0; i<listaSinistros.size(); i++) {
			// Caso o sinistro ja exista, retorna false
			if(listaSinistros.get(i).equals(sinistro)) {
				return false;
			}
		}
		
		listaSinistros.add(sinistro);
		return true;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public ArrayList<Sinistro> getListaSinistros() {
		return listaSinistros;
	}

	public String getCpf() {
		return cpf;
	}

	@Override
	public String toString() {
		return "Condutor [cpf=" + cpf + ", nome=" + nome + ", telefone=" + telefone + ", endereco=" + endereco
				+ ", email=" + email + ", dataNascimento=" + dataNascimento + ", listaSinistros=" + listaSinistros
				+ "]";
	}
}
