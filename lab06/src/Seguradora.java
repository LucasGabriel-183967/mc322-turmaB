
import java.time.LocalDate;
import java.util.ArrayList;

public class Seguradora {
	String cnpj;
	String nome;
	String telefone;
	String endereco;
	String email;
	ArrayList<Cliente> listaClientes;
	ArrayList<Seguro> listaSeguros;
	
	public Seguradora(String cnpj, String nome, String telefone, String endereco, String email) {
		super();
		this.cnpj = cnpj;
		this.nome = nome;
		this.telefone = telefone;
		this.endereco = endereco;
		this.email = email;
		this.listaClientes = new ArrayList<Cliente>();
		this.listaSeguros = new ArrayList<Seguro>();
	}
	
	public void listarClientes() {
		for(int i=0; i<listaClientes.size(); i++) {
			System.out.println(listaClientes.get(i).toString());
		}
	}
	
	public boolean gerarSeguro(ClientePF cliente, LocalDate dataInicio, LocalDate dataFim, Seguradora seguradora, Veiculo veiculo) {
		// Cliente PF
		SeguroPF newSeguro = new SeguroPF(cliente, dataInicio, dataFim, seguradora, veiculo);
		for(int i=0; i<listaSeguros.size(); i++) {
			if(listaSeguros.get(i).equals(newSeguro)) {
				return false;
			}
		}
		
		listaSeguros.add(newSeguro);
		return true;
	}
	
	public boolean gerarSeguro(ClientePJ cliente, LocalDate dataInicio, LocalDate dataFim, Seguradora seguradora, Frota frota) {
		// Cliente PJ
		SeguroPJ newSeguro = new SeguroPJ(cliente, dataInicio, dataFim, seguradora, frota);
		for(int i=0; i<listaSeguros.size(); i++) {
			if(listaSeguros.get(i).equals(newSeguro)) {
				return false;
			}
		}
		
		listaSeguros.add(newSeguro);
		return true;
	}
	
	public boolean cancelarSeguro(Seguro seguro) {
		for(int i=0; i<listaSeguros.size(); i++) {
			if(listaSeguros.get(i).equals(seguro)) {
				listaSeguros.remove(i);
				return true;
			}
		}
		
		return false;
	}
	
	public boolean cadastrarCliente(Cliente cliente) {
		if(Utils.searchCliente(listaClientes, cliente.nome)==null) {
			listaClientes.add(cliente);
			return true;
		}
		
		return false;
	}
	
	public boolean removerCliente(Cliente cliente) {
		// Remove o cliente e seus seguros, bem como os sinistros gerados nestes seguros; após, remove tambem os mesmos sinistros
		// da lista de sinistros de cada condutor e atualiza o valor mensal de cada seguro
		for(int i=0; i<listaClientes.size(); i++) {
			if(listaClientes.get(i).equals(cliente)) {
				listaClientes.remove(i);
				return true;
			}
		}
		
		ArrayList<Seguro> newListaSeguros = new ArrayList<Seguro>();
		
		for(int i=0; i<listaSeguros.size(); i++) {
			if(listaSeguros.get(i).getCliente().equals(cliente)) {
				for(int j=0; j<listaSeguros.get(i).getListaSinistros().size(); j++) {
					Sinistro sinistro = listaSeguros.get(i).getListaSinistros().get(j);
					Condutor condutor = sinistro.condutor;
					for(int k=0; k<condutor.getListaSinistros().size(); k++) {
						if(condutor.getListaSinistros().get(k).equals(sinistro)) {
							condutor.getListaSinistros().remove(k);
							break;
						}
					}
				}
				listaSeguros.get(i).getListaSinistros().clear();
			}
			else {
				newListaSeguros.add(listaSeguros.get(i));
			}
		}

		for(int i=0; i<newListaSeguros.size(); i++) {
			double newValorMensal = newListaSeguros.get(i).calcularValor();
			newListaSeguros.get(i).setValorMensal(newValorMensal);
		}
		
		this.listaSeguros = newListaSeguros;
	
		return false;
	}
	
	public ArrayList<Seguro> getSegurosPorCliente(Cliente cliente) {
		ArrayList<Seguro> listaSegurosCliente = new ArrayList<Seguro>();
		for(int i=0; i<listaSeguros.size(); i++) {
			if(listaSeguros.get(i).getCliente().equals(cliente)) {
				listaSegurosCliente.add(listaSeguros.get(i));
			}
		}
		return listaSegurosCliente;
	}
	
	public ArrayList<Sinistro> getSinistrosPorCliente(Cliente cliente) {
		ArrayList<Seguro> listaSegurosCliente = new ArrayList<Seguro>();
		ArrayList<Sinistro> listaSinistrosCliente = new ArrayList<Sinistro>();
		
		listaSegurosCliente = getSegurosPorCliente(cliente);
		
		if(listaSegurosCliente.size()==0) {
			return null;
		}
		
		for(int i=0; i<listaSegurosCliente.size(); i++) {
			for(int j=0; j<listaSegurosCliente.get(i).getListaSinistros().size(); i++) {
				listaSinistrosCliente.add(listaSegurosCliente.get(i).getListaSinistros().get(j));
			}
		}
		
		return listaSinistrosCliente;
	}
	
	public double calcularReceita() {
		double valorReceita = 0;
		for(int i=0; i<this.listaSeguros.size(); i++) {
			valorReceita+=this.listaSeguros.get(i).getValorMensal();
		}
		
		return valorReceita;
	}
	
	public Cliente procurarCliente(String nome) {
		for(int i=0; i<this.listaClientes.size(); i++) {
			if(this.listaClientes.get(i).getNome().equals(nome)) {
				return this.listaClientes.get(i);
			}
		}
		
		return null;
	}
	
	public String getCnpj() {
		return cnpj;
	}
	
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
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
	
	public ArrayList<Cliente> getListaClientes() {
		return listaClientes;
	}
	
	
	public ArrayList<Seguro> getListaSeguros() {
		return listaSeguros;
	}

	@Override
	public String toString() {
		return "Seguradora [nome=" + nome + ", cnpj=" + cnpj + ", telefone=" + telefone + ", endereco=" + endereco
				+ ", email=" + email + ", listaClientes=" + listaClientes + ", listaSeguros=" + listaSeguros + "]";
	}
}
