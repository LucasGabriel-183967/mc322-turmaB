package lab04;
import java.util.ArrayList;

public class Cliente {
	/*
	Essa classe compreende as informacoes do cliente segurado. Ela  ́e composta de cinco 
	atributos (nome, cpf, dataNascimento, idade e endereco). O atributo idade  ́e do tipo inteiro
	(int), ao passo que os demais sao todos do tipo String.	
	*/
	String nome;
	String endereco;
	ArrayList<Veiculo> listaVeiculos = new ArrayList<Veiculo>();
	ArrayList<Sinistro> listaSinistros = new ArrayList<Sinistro>();
	double valorSeguro;
	
	public Cliente(String nome, String endereco) {
		/* Note que listaVeiculos nao eh um atributo necessario no construtor; assim, cada objeto do tipo Cliente (PF ou PJ) inicializado
		 * tera por padrao uma lista de veiculos vazia, sendo possivel com o metodo adicionarVeiculo(Veiculo veiculo) adicionar um 
		 * veiculo a essa lista 
		*/
		this.nome = nome;
		this.endereco = endereco;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public ArrayList<Veiculo> getListaVeiculos() {
		return listaVeiculos;
	}
	
	public ArrayList<Sinistro> getListaSinsitro(){
		return listaSinistros;
	}
	
	public double getValorSeguro() {
		return valorSeguro;
	}

	public void setValorSeguro(double valorSeguro) {
		this.valorSeguro = valorSeguro;
	}
	
	// Funcao para adicionar um veiculo à listaVeiculos
	public void adicionarVeiculo(Veiculo veiculo) {
		listaVeiculos.add(veiculo);
	}
	
	public boolean removerVeiculo(Veiculo veiculo) {
		/*
		 * Retorna True se o elemento estava na lista; do contrario retorna False
		 */
		for(int i=0; i<listaVeiculos.size(); i++) {
			if(veiculo.equals(listaVeiculos.get(i))) {
				listaVeiculos.remove(i);
				return true;
			}
		}
		return false;
	}
	
	public void adicionarSinistro(Sinistro sinistro) {
		this.listaSinistros.add(sinistro);
	}
	
	public boolean removerSinistro(Sinistro sinistro) {
		for(int i=0; i<listaSinistros.size(); i++) {
			if(listaSinistros.get(i).equals(sinistro)) {
				listaSinistros.remove(i);
				return true;
			}
		}
		return false;
	}
	
	public double calculaScore() {
		return CalcSeguro.VALOR_BASE.getValor();
	}

	@Override
	public String toString() {
		return "Cliente [nome=" + nome + ", endereco=" + endereco + ", listaVeiculos=" + listaVeiculos
				+ ", valorSeguro=" + valorSeguro + "]";
	}
	
}
